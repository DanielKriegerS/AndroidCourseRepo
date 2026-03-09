package com.danielks.nivelamento

fun main() {
    // FUNÇÕES

    // fun soma1(number1 : Int, number2 : Int) {} // Função normal

    // val soma2 = fun(number1 : Int, number2 : Int) = number1 + number2 // função anonima, sem chaves o retorno é inferido

    /*
    val soma3 = fun(number1 : Int, number2 : Int) : Int {
        return number1 + number2
    }
     */

    // LAMBDAS
    // val soma4 = {number1 : Int, number2 : Int -> number1 + number2}

    // High-Order Functions
    // A forma abaixo é a menos recomendada, mas muito utilizada
        val numbers = arrayOf<Int>(1,2,3,4,5,6,7,8,9,10)

        val pairs = fun (list : Array<Int>) : Sequence<Int> =
                list.asSequence()
                    .filter { it % 2 == 0 }

        val odds = fun (list : Array<Int>) : Sequence<Int> =
            list.asSequence()
                .filter { it % 2 != 0 }

        val multipleOfThree = fun (list : Array<Int>) : Sequence<Int> =
            list.asSequence()
                .filter { it % 3 == 0 }
//      FORMA ERRADA
//        val mainFun = fun(list : Array<Int>, type : String) {
//            when(type) {
//                "par" -> pairs(list).forEach { println(it)}
//                "impar" -> odds(list).forEach { println(it)}
//                "mult3" -> multipleOfThree(list).forEach { println(it)}
//                else -> println("Tipo invalido")
//            }
//        }

    // FORMA CORRETA
//    val mainFun = fun(list : Array<Int>, type : (Array<Int>) -> Sequence<Int>) : Sequence<Int> {
//        return type(list).asSequence()
//    }
// testando as funções encadeadas
//        println("*** PARES ***")
////        mainFun(numbers, "par") // jeito errado
//        mainFun(numbers, pairs).forEach { println(it) }
//        println("")
//        println("*** IMPARES ***")
//        mainFun(numbers, odds).forEach { println(it) }
////      mainFun(numbers, "impar") // jeito errado
//        println("")
//        println("*** MULTIPLOS 3 ***")
//        mainFun(numbers, multipleOfThree).forEach { println(it) }
////        mainFun(numbers, "mult3") // jeito errado
////        println("")
////        println("*** Outro ***")
////        mainFun(numbers, "Krieger")

    // TRAILING LAMBDA
    val names = arrayOf<String>("Dani", "Krieger", "Andreia", "Nascimento", "Silva", "Santos")
    val func = {
        list : Array<String>,
        filterType : (Array<String>)
            -> Sequence<String> // define que retorna uma sequence de string
            -> filterType(list) // define o corpo da lambda (fazer filtro da lista de acordo com tipo)
    }

    // maneira incorreta
    println("Maneira incorreta:")
    func(names,
        {names.asSequence()
            .filter { it.contains("k", ignoreCase = true) }} // regra de filtragem
    ).forEach{ println(it) }

    // maneira correta
    println("Maneira correta:")
    func(names) {
        names.asSequence()
            .filter { it.contains("k", ignoreCase = true) } // regra de filtragem
    }.forEach{ println(it) }
}