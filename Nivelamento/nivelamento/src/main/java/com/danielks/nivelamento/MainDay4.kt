package com.danielks.nivelamento

// Functions
fun message() {
    println("Hello world")
}

fun message2(name : String) { // para um parâmetro mais flexível, utilizar : Any
    println("Hello $name")
}

fun sum(number1 : Int, number2 : Int) : Int {
    return number1 + number2
}

fun noReturn() : Unit { // Unit equivale ao void do JAVA
    print("Sem retorno...")
}

// Smart Cast (is)
fun sumValue(number : Any) {
    if (number is Int) {
        println(number + 10)
    } else if (number is Double){
        println(number + 15)
    } else {
        println("Tipo inválido.")
    }
}

fun main() {
//    try - catch - finally
/*
    try {
        val value = 10 / 0
        println(value)
    } catch (fail : Exception) {
        println(fail.message)
    } finally {
        println("Finalização do try - catch - finally")
    }
 */

//    chamadas de funções
/*
    var number : Int = 2
    var number : Double = 3.5
    var notNumber : String = "não é numero"
    sumValue(notNumber)
 */

//    message()
//    message2("Krieger")
//    print(sum(2,4))
//    noReturn()

    // Alterar tipagem de dados
    /*
    var number : Int = 5
    var realNumber : Double = number // falha na tipagem
    var realNumber : Double = number.toDouble() // assim converte (uppercast)
    print(number::class.simpleName) // demonstra o tipo
    print(realNumber::class.simpleName) // demonstra o tipo
     */


    // while é igual ao JAVA
    /*
    WHILE
    var idx : Int = 1
    while (idx < 11) {
        println(idx)
        idx++ // pode ser += 1,2,... ou *= 1,2,...
    }
     */

    /*
    DO - WHILE
    var idx : Int = 10
    do {
        print(idx)
        idx--
    } while (idx > 0)
     */

    /*
    FOR
    for (idx in 1..10) {
        println(idx)
    }

    for (idx in 'a'..'z') {
        println(idx)
    }
     */

    // Array Mutable - mutável
    /*
    var names = mutableListOf<String>() // onde está string pode referenciar classes em geral
    names.add("Krieger")
    names.add("Dani")
    names.add("Daniel")
    names[1] = "Daniii"
    names.set(0, "Kriegerrr")
    names.remove("Daniii") // remove a primeira ocorrência apenas
    names.removeAt(0)
    names.forEach{ println(it) }
    println("Existem ${names.size} elementos no array.")
     */

    // ArrayOf - imutável
    /* por ser imutável, para adicionar/remover itens, tem que criar outro array para receber o resultado
    val colors = arrayOf("Blue", "Pink", "Red","Green" )
    println(colors[0])
    val colors2 = colors + "yellow"
    colors2.forEach { println(it) } // esse "it" equivale ao valor da posição atual no loop
    val colors3 = colors2.filter { it != "Green" } // filtra valores de colors2 e adiciona no colors3
    println(colors3)
     */
}