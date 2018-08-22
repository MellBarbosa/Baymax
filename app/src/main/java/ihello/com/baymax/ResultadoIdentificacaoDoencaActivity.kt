package ihello.com.baymax

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.MenuItem
import com.google.gson.reflect.TypeToken
import ihello.com.baymax.Model.Doenca
import kotlinx.android.synthetic.main.activity_resultado_identificacao_doenca.*

class ResultadoIdentificacaoDoencaActivity : AppCompatActivity() {

    companion object {
        var nome = ""
        var possiveisDoencas : List<Doenca> = emptyList()
    }

    lateinit var adapter: ResultadoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_identificacao_doenca)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Relatório Médico"

        tvPaciente.text = nome

        adapter = ResultadoAdapter(possiveisDoencas)
        rv_resultado.adapter = adapter
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rv_resultado.layoutManager = layoutManager

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
