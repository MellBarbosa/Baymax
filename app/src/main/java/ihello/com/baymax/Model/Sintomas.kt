package ihello.com.baymax.Model

class Sintomas() : ArrayList<Sintoma>() {
}

class Sintoma{

    var Selecionado : Boolean = false
    var DoencaId : Int = 0
    var Sintoma : String = ""
    var Regiao : String = ""
}
