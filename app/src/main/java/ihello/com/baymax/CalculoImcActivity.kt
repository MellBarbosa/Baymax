package ihello.com.baymax

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calculo_imc.*

class CalculoImcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo_imc)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.title = "Calcular IMC"
        }

        btnCalcularImc.setOnClickListener {

            if (ValidaDados()){
                var altura = edtAltura.text.toString().toDouble()
                var peso = edtPeso.text.toString().toDouble()
                var res = (peso/((altura/100)*(altura/100)))

            var feedBack:String = "";

            if (res <= 16.9) {
                feedBack = "Seu IMC é " + "%.2f".format(res) + " kg/m². \n" +
                        "Classificação: Muito abaixo do peso \n"+
                        "O que pode acontecer: Queda de cabelo, infertilidade, ausência menstrual."
            }
            else if ((res <= 18.4)) {
                feedBack = "Seu IMC é " + "%.2f".format(res) + " kg/m². \n" +
                        "Classificação: Abaixo do peso \n"+
                        "O que pode acontecer: Fadiga, stress, ansiedade."
            }
            else if ((res <= 24.9)) {
                feedBack = "Seu IMC é " + "%.2f".format(res) + " kg/m². \n" +
                        "Classificação: Peso normal \n"+
                        "O que pode acontecer: Menor risco de doenças cardíacas e vasculares."
            }
            else if ((res <= 29.9)) {
                feedBack = "Seu IMC é " + "%.2f".format(res) + " kg/m². \n" +
                        "Classificação: Acima do peso \n"+
                        "O que pode acontecer: Fadiga, má circulação, varizes."
            }
            else if ((res <= 34.9)) {
                feedBack = "Seu IMC é " + "%.2f".format(res) + " kg/m². \n" +
                        "Classificação: Obesidade Grau I \n"+
                        "O que pode acontecer: Diabetes, angina, infarto, aterosclerose."
            }
            else if ((res <= 40)) {
                feedBack = "Seu IMC é " + "%.2f".format(res) + " kg/m². \n" +
                        "Classificação: Obesidade Grau II \n"+
                        "O que pode acontecer: Apneia do sono, falta de ar."
            }
            else if ((res > 40)) {
                feedBack = "Seu IMC é " + "%.2f".format(res) + " kg/m². \n" +
                        "Classificação: Obesidade Grau III \n"+
                        "O que pode acontecer: Refluxo, dificuldade para se mover, escaras, diabetes, infarto, AVC."
            }

            val builder = AlertDialog.Builder(this).setTitle("Resultado IMC").setMessage(feedBack)
            builder.create().show()

            edtPeso.setText("")
            edtAltura.setText("")
            edtPeso.requestFocus()
            }
        }
    }

    private fun ValidaDados(): Boolean {
        if (edtPeso.getText().toString().trim({ it <= ' ' }).isEmpty()) {
            showMessage("Peso sem valor")
            return false
        }
        if (edtAltura.getText().toString().trim({ it <= ' ' }).isEmpty()) {
            showMessage("Altura sem valor")
            return false
        }
        return true
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
