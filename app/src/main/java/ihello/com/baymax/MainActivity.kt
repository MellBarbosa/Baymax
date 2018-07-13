package ihello.com.baymax

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ihello.com.baymax.Model.RespostasDepressao
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import org.json.JSONException
import org.json.JSONArray
import com.google.gson.Gson

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

        var respostas : RespostasDepressao
        val arquivo = "[\n" +
                "   {\n" +
                "     \"Numero\": 1,\n" +
                "     \"Resposta1\": \"Não me sinto triste.\",\n" +
                "     \"Resposta2\": \"Eu me sinto triste.\",\n" +
                "     \"Resposta3\": \"Estou sempre triste e não consigo sair disto.\",\n" +
                "     \"Resposta4\": \"Estou tão triste ou infeliz que não consigo suportar.\"\t \n" +
                "    },\n" +
                "   {\n" +
                "     \"Numero\": 2,\n" +
                "     \"Resposta1\": \"Não estou especialmente desanimado quanto ao futuro.\" ,\n" +
                "     \"Resposta2\": \"Eu me sinto desanimado quanto ao futuro.\",\n" +
                "     \"Resposta3\": \"Acho que nada tenho a esperar.\",\n" +
                "     \"Resposta4\": \"Acho o futuro sem esperança e tenho a impressão de que as coisas não podem melhorar.\"\t \n" +
                "    }]"

        val gson = Gson()
        respostas = gson.fromJson(arquivo, RespostasDepressao::class.java!!)

    }
}
