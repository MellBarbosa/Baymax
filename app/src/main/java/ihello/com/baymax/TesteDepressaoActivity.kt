package ihello.com.baymax

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.gson.Gson
import ihello.com.baymax.Model.Respostas
import ihello.com.baymax.Model.RespostasDepressao
import kotlinx.android.synthetic.main.activity_teste_depressao.*
import java.io.InputStream

class TesteDepressaoActivity : AppCompatActivity() {

    var posicaoAtual = 0
    lateinit var respostas : RespostasDepressao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste_depressao)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Teste de Depressão"

        try {
            val inputStream: InputStream = assets.open("respostas.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            val gson = Gson()
            respostas = gson.fromJson(jsonString, RespostasDepressao::class.java!!)

        } catch (e: Exception) {
            // erro
        }

        showNextQuestion()
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

    fun showNextQuestion(){
        val resposta = respostas.getOrNull(posicaoAtual)
        if (resposta != null) {
            mostrarProximaRespota(resposta)
        } else {
            finalizarQuiz()
        }
        posicaoAtual++
    }

    fun mostrarProximaRespota(resposta: Respostas){
        rb1.text = resposta.Resposta1
        rb2.text = resposta.Resposta2
        rb3.text = resposta.Resposta3
        rb4.text = resposta.Resposta4
    }

    fun finalizarQuiz(){

    }
}

