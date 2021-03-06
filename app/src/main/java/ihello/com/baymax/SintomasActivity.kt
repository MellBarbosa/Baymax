package ihello.com.baymax

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ihello.com.baymax.Model.Doenca
import ihello.com.baymax.Model.Sintomas
import kotlinx.android.synthetic.main.activity_sintomas.*
import java.io.InputStream


class SintomasActivity : AppCompatActivity() {

    lateinit var sintomas : Sintomas
    lateinit var adapter: SintomasAdapter
    var doencas : List<Doenca> = emptyList()
    val doencaListType = object : TypeToken<List<Doenca>>() {}.type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sintomas)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Identificação de Doenças"

        try {
            val inputStreamS: InputStream = assets.open("sintomas.json")
            val jsonStringS = inputStreamS.bufferedReader().use { it.readText() }

            val gsonS = Gson()
            sintomas = gsonS.fromJson(jsonStringS, Sintomas::class.java)

        } catch (e: Exception) {
            // erro
        }

        adapter = SintomasAdapter(sintomas)
        rv_sintomas.adapter = adapter
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rv_sintomas.layoutManager = layoutManager

        edtPesquisa.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
            }


            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

        })

        btFinalizar.setOnClickListener{

            if (adapter.items.filter { it.Selecionado }.size == 0)
                Toast.makeText(this, "Favor Selecionar ao menos um sintoma.", Toast.LENGTH_SHORT).show()
            else
            {
                try {

                    val itensSelecionados = adapter.items.filter { it.Selecionado }
                            .map { it.Doencas  }
                            .reduce { a, b -> a + b  }
                            .distinct()

                    val inputStreamD: InputStream = assets.open("doenças.json")
                    val jsonStringD = inputStreamD.bufferedReader().use { it.readText() }

                    val gsonS = Gson()
                    doencas = gsonS.fromJson(jsonStringD, doencaListType)
                    ResultadoIdentificacaoDoencaActivity.possiveisDoencas =  doencas.filter { itensSelecionados.contains(it.Id) }.take(2)

                } catch (e: Exception) {
                    // erro
                }

                val intent = Intent(this, ResultadoIdentificacaoDoencaActivity::class.java)
                startActivity(intent)

            }

            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
