package com.danielks.nivelamento

fun main() {
    // Streams (Sequence)
    /*
    val numbers = arrayOf(1, 2, 3, 4, 5)
    val result = numbers
                    .asSequence() // organiza a estrutura (não é obrigatório, mas recomendado)
                    .filter { it % 2 == 0 } // retorna apenas pares
                    .map { it * it } // multiplica o valor por ele mesmo APÓS filtro
    result.forEach { println(it) }
     */

/*
    val numbers = 1 .. 20 // gera 20 numeros
    val numbers : IntRange = (1..20) // tambem gera, porém melhor pratica (tipagem)
    val firstFive = numbers.take(5) // pega apenas (n) primeiros valores
    println(numbers)
    println(firstFive)
 */

/*
    val numbers : IntRange = (1..20)
    val lastFive = numbers.asSequence().drop(15) // remove (n) primeiros valores
    val lastFive = numbers.asSequence().drop(numbers.count() - 5) // remove (todos - n) primeiros valores
    lastFive.forEach { println(it) }
 */

 /*
    val number : Int = 5
    val verifyPair = number.takeIf { it % 2 == 0 }
    println(verifyPair)

    val numbers : IntRange = (1..10)
    val verifyPairs = numbers
                        .asSequence()
                        .mapNotNull { it.takeIf { it % 2 == 0 }}
                        // O take If retorna nulo quando não cumpre, o mapNotNull remove os nulos
    verifyPairs.forEach {
        println(it)
    }
  */

/*
    val numbers : IntRange = (-20..20)
    val numbersUnderZero = numbers
                            .asSequence()
                            .takeWhile { it < 0 }
    numbersUnderZero.forEach { println(it) }
 */

/*
    val names1 = arrayOf("Daniel", "Krieger")
    val names2 = arrayOf("Deia", "Nascimento")

    // val names3 = names1 + names2 // apenas concatena
    val names3 = sequenceOf(names1, names2) // ele aceita arrays ou sequences e outros
                                            // mas não maps e alguns outros (pois tem outra estrutura)
                    .flatMap { it.asSequence() } // gera uma sequencia unificando

    names3.forEach { println(it) }
 */

    /*
    val names = arrayOf("Dani", "Krieger", "Dani", "Deia", "Dani")
    val notRepeatNames = names
                            .asSequence()
                            .distinct()
    notRepeatNames.forEach { println(it) }
     */

/*
    val numbers = arrayOf(3, 2, 9, 5, 4)
    val sortedNumbers = numbers
                            .asSequence() // põe em sequencia
                            .sorted()
                            .sortedDescending() // inverte a sequencia
    sortedNumbers.forEach { println(it) }
 */

/*
    val names = arrayOf("Dani", "Krieger", "Andreia", "Nascimento", "Nome")
    val name = names
                .asSequence()
                .any{it.contains("Krieger", ignoreCase = true)} // true = desativou o case sensitive
    println(name) // retorna true or false
 */

    val names = arrayOf("Dani", "Krieger", "Andreia", "Nascimento", "Nome")
    val quantityOfNamesWithI = names
                                .asSequence()
                                .filter { it.contains("i", ignoreCase = true) }
                                .count()

    println("Quantidade de nomes com a letra I = $quantityOfNamesWithI")

}