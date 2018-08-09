package ihello.com.baymax.Model

class Sintomas() : ArrayList<Sintoma>() {
}

class Sintoma{

    var Selecionado : Boolean = false
    var Doencas : List<Int> = emptyList()
    var Sintoma : String = ""
    var Regiao : String = ""
}
