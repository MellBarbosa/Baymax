package ihello.com.baymax

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class ResultadoIdentificacaoDoencaActivity : AppCompatActivity() {

    companion object {
        var nome = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_identificacao_doenca)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Relatório Médico"

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
