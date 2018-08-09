package ihello.com.baymax.Model

class Sintomas() : ArrayList<Sintoma>() {
}

class Sintoma{

    var Selecionado : Boolean = false
    lateinit var Doencas : List<Int>
    var Sintoma : String = ""
    var Regiao : String = ""
}
