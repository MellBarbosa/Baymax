package ihello.com.baymax

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nome.*

class NomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nome)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Identificação de Doenças"

        btnNext.setOnClickListener{
            if (ValidaDados()) {
                ResultadoIdentificacaoDoencaActivity.nome = edtNome.text.toString()
                val intent = Intent(this, SintomasActivity::class.java)
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

    private fun ValidaDados(): Boolean {
        if (edtNome.getText().toString().trim({ it <= ' ' }).isEmpty()) {
            Toast.makeText(this, "Favor informar um nome!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}
