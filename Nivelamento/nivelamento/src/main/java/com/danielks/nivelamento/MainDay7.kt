package com.danielks.nivelamento
/*
class People {
    var name : String = ""
    private set
    var age : Int = 0
    private set
    fun message() {
        println("Olá $name, você tem $age anos.")
    }
}
 */

// Construtor 1
/*
class People {
    var name : String = ""
    var age : Int = 0

    constructor(name : String, age : Int) {
        this.name = name
        this.age = age
    }
}
 */

// Com construtor 2
//class People(val name : String, val age : Int)

// HERANÇA - 1
//open class People() {
//    protected var name : String = ""
//    protected var age : Int = 0
//
//    protected fun peopleData() {
//        println("Nome -> $name")
//        println("Idade -> $age")
//    }
//}
//
//class Developer : People() {
//    private var level : Int = 0
//    private var mainLanguage : String = ""
//
//    private fun devData() {
//        println("Nível -> $level")
//        println("Linguagem principal -> $mainLanguage")
//    }
//
//    fun data(name : String, age : Int, level : Int, mainLanguage: String) {
//        this.name = name
//        this.age = age
//        this.level = level
//        this.mainLanguage = mainLanguage
//        peopleData()
//        devData()
//    }
// }

// HERANÇA - 2
//open class People() {
//    protected var name : String = ""
//    protected var age : Int = 0
//
//    protected fun peopleData() {
//        println("Nome -> $name")
//        println("Idade -> $age")
//    }
//}
//
//class Developer : People {
//    private var level : Int = 0
//    private var mainLanguage : String = ""
//
//    private fun devData() {
//        println("Nível -> $level")
//        println("Linguagem principal -> $mainLanguage")
//    }
//
//    constructor(name : String, age : Int, level : Int, mainLanguage: String) {
//        this.name = name
//        this.age = age
//        this.level = level
//        this.mainLanguage = mainLanguage
//        peopleData()
//        devData()
//    }
//}

// HERANÇA - 3
//open class People() {
//    private var name : String = ""
//    private var age : Int = 0
//
//    constructor(name : String, age : Int) : this() {
//        this.name = name
//        this.age = age
//    }
//
//    protected fun peopleData() {
//        println("Nome -> $name")
//        println("Idade -> $age")
//    }
//}
//
//class Developer : People {
//    private var level : Int = 0
//    private var mainLanguage : String = ""
//
//    constructor(name : String, age: Int, level : Int, mainLanguage: String) : super(name, age) {
//        this.level = level
//        this.mainLanguage = mainLanguage
//        peopleData()
//        devData()
//    }
//
//    private fun devData() {
//        println("Nível -> $level")
//        println("Linguagem principal -> $mainLanguage")
//    }
//}

// HERANÇA - 4
//open class People() {
//    private var name : String = ""
//    private var age : Int = 0
//
//    constructor(name : String, age : Int) : this() {
//        this.name = name
//        this.age = age
//    }
//
//    protected fun peopleData() {
//        println("Nome -> $name")
//        println("Idade -> $age")
//    }
//}
//
//class Developer(
//    name : String,
//    age : Int,
//    val level : Int,
//    val mainLanguage : String
//) : People (name, age) {
//
//    fun data() {
//        peopleData()
//        devData()
//    }
//
//    private fun devData() {
//        println("Nível -> $level")
//        println("Linguagem principal -> $mainLanguage")
//    }
//}

// ABSTRAÇÃO
//class People {
//    var name : String = ""
//    var age : Int = 0
//}
//
//abstract class Crud {
//    abstract fun register(obj : People) : Boolean
//    abstract fun list() : List<People>
//    abstract fun update(obj : People) : Boolean
//    abstract fun del(idx : Int) : Boolean
//
//    fun countPeople() : Int {
//        return 0
//    }
//}
//
//class PeopleActions : Crud() {
//    override fun register(obj: People): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun list(): List<People> {
//        TODO("Not yet implemented")
//    }
//
//    override fun update(obj: People): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun del(idx: Int): Boolean {
//        TODO("Not yet implemented")
//    }



fun main() {

    /* DIRETO SEM CONSTRUTOR
    var obj = People()
    obj.name = "Krieger"
    obj.age = 28
    obj.message()
     */

    // com construtor
    //var obj = People("Krieger", 28)

    // herança, sem construtor
    //var obj = Developer()
    //obj.data("Krieger", 28, 3, "EDGE")

    // herança, com construtor
    // var obj = Developer("Krieger", 28, 3, "EDGE")
    // obj.data()
}