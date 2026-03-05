package com.danielks.nivelamento

// Inicialização
fun main() {
//    Null Safety
//    var text : String = null << isso não funciona em Kotlin
//    var text : String? = null << assim é null safety
//    Elvis Operator
//    print(text.length) << assim retorna erro
//    print(text?.length) << assim retorna nulo
//    print(text?.length ?: "O texto não pode ficar sem valor" << assim quando é nulo, retorna personalizado

//    print("hello world!")
//    var sName = "Krieger"
//    var sName : String = "Krieger"
//    var sName : String
//    var idade : int = 1 << não funciona, Kotlin não possui tipos primitivos
//    var idade : Int = 28
//    print("Olá " + sName + " você tem " + idade + " anos.") << não é recomendado
//    print("Olá $sName você tem $idade anos")
// var = variavel; val = constante
//    val vDolar = 5.20
//    val pi : Double = 3.14
/*
    if (idade >= 18) {
        print("maior de idade")
    } else {
        print("menor de idade")
    }
 */
//    idade >= 18 ? "maior de idade" :  "menor de idade" << não existe no Kotlin
//    print(if (idade >= 18)  "maior de idade" else "menor de idade") << equivalente do ternário
/*
    val day : Int = 4
    val dayOfWeek = when (day) {
        1 -> "Domingo"
        2 -> "Segunda"
        3 -> "Terça"
        4 -> "Quarta"
        5 -> "Quinta"
        6 -> "Sexta"
        7 -> "Sábado"
        else -> "Dia inexistente!"
    }

    print(dayOfWeek)
 */

}