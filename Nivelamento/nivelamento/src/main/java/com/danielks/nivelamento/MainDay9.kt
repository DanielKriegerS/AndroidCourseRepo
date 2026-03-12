package com.danielks.nivelamento

import java.util.Scanner

//enum class OrderStatus{
//    PENDING,
//    PROCESSING,
//    SEND,
//    RECEIVED,
//    CANCELED
//}

// EXTENSION FUNCTIONS
class People(
    val name : String,
    val age : Int
) {}

fun People.concat() {
    println("Boa tarde ${this.name}, você tem ${this.age} anos.")
}

fun String.count() {
    println("$this possui ${this.length} caracteres.")
}

fun main() {
 //   println(OrderStatus.PROCESSING)

//    val obj = People("Krieger", 28)
//    obj.concat()
//
//    val text : String = "Kotlin"
//    text.count()

    // utilizando  Scanner
    val obj = Scanner(System.`in`)
    val name : String = obj.nextLine()
}