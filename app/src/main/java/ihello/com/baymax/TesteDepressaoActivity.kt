package ihello.com.baymax

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Html
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import ihello.com.baymax.Model.Respostas
import ihello.com.baymax.Model.RespostasDepressao
import kotlinx.android.synthetic.main.activity_teste_depressao.*
import java.io.InputStream


class TesteDepressaoActivity : AppCompatActivity() {

    var posicaoAtual = 0
    var pontos = 0
    lateinit var respostas : RespostasDepressao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste_depressao)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Teste de Depressão"

        val progressBar = pbRespostas
        progressBar.progress = 0

        try {
            val inputStream: InputStream = assets.open("respostas.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            val gson = Gson()
            respostas = gson.fromJson(jsonString, RespostasDepressao::class.java)

        } catch (e: Exception) {
            // erro
        }

        progressBar.max = respostas.size -1
        showNextQuestion()

        btnNext.setOnClickListener {

            var id = rgRespostas.checkedRadioButtonId
            progressBar.progress = posicaoAtual
            if (id != -1) {
                if (rb2.isChecked)
                    pontos = pontos + 1
                else if (rb3.isChecked)
                    pontos = pontos + 2
                else if (rb4.isChecked)
                    pontos = pontos + 3

                if (respostas.size == 20)
                    finalizarQuiz()
                else
                    showNextQuestion()
            } else
                Toast.makeText(this, "Favor marcar uma opção", Toast.LENGTH_SHORT).show()
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
        rgRespostas.clearCheck()
        rb1.text = resposta.Resposta1
        rb2.text = resposta.Resposta2
        rb3.text = resposta.Resposta3
        rb4.text = resposta.Resposta4
    }

    fun finalizarQuiz(){

        val builder = AlertDialog.Builder(this).setTitle("Resultado Teste de Depressão")

        if (pontos > 0 && pontos <= 13)
            builder.setMessage("Nenhuma Depressão")
        else if (pontos >= 14 && pontos <=19)
            builder.setMessage("Depressão leve \n" +
                    " Favor procurar ajuda médica.")
        else if (pontos >= 20 && pontos <=28)
            builder.setMessage("Depressão moderada \n" +
                    " Favor procurar ajuda médica.")
        else if (pontos >= 29 && pontos <= 63)
            builder.setMessage(Html.fromHtml("<b>"+"DEPRESSÃO GRAVE " + "<br />" +
                    " Favor procurar ajuda médica." + "</b>"))

        builder.create()

        builder.setPositiveButton("ok"){dialog, which ->

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        builder.show()
    }
}

