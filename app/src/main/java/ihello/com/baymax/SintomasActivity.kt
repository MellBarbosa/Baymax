package ihello.com.baymax

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.MenuItem
import com.google.gson.Gson
import ihello.com.baymax.Model.Sintomas
import kotlinx.android.synthetic.main.activity_sintomas.*
import java.io.InputStream


class SintomasActivity : AppCompatActivity() {

    lateinit var sintomas : Sintomas

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
            sintomas = gsonS.fromJson(jsonStringS, Sintomas::class.java!!)

        } catch (e: Exception) {
            // erro
        }

        rv_sintomas.adapter = SintomasAdapter(sintomas)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rv_sintomas.layoutManager = layoutManager

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
