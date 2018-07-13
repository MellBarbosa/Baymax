package ihello.com.baymax

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalculoImc.setOnClickListener{
            val intent = Intent(this, CalculoImcActivity::class.java)
            startActivity(intent)
        }

        btnDiagnosticarDoencas.setOnClickListener{
            val intent = Intent(this, NomeActivity::class.java)
            startActivity(intent)
        }

        btnTesteDepressao.setOnClickListener{
            val intent = Intent(this, TesteDepressaoActivity::class.java)
            startActivity(intent)
        }
    }
}
