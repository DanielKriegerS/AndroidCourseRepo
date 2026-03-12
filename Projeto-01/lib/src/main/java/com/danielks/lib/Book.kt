package com.danielks.lib


class Book(id: Int, name: String, author: String, genre: String, numPags: Int, coverType: String) {
    private var id : Int? = id // poderia ser um UUID ou o ISBN (embora preferência UUID)
    private var name : String? = null
    private var author : String? = null
    private var genre : String? = null // String para simplificar, poderia ser uma classe ou lista predefinida
    private var numPags : Int? = null
    private var coverType : String? = null // usando string para simplificar, poderia ser uma classe

    init {
        validateAndCreate(id, name, author, genre, numPags, coverType)
    }

    private fun validateAndCreate(id : Int, name : String, author : String, genre: String, numPags : Int, coverType : String) {
        val isValid = validate(name, author, genre, numPags, coverType)
        if (isValid) {
            create(id, name, author, genre, numPags, coverType)
            return
        }

        println("Dados do livro inválido!")
    }

    private fun validate(name : String, author : String, genre: String, numPags : Int, coverType : String) : Boolean {
        return  validateSimpleTextAttr(name) &&
                validateSimpleTextAttr(author) &&
                validateSimpleTextAttr(genre) &&
                validateNumOfPags(numPags) &&
                validateCoverType(coverType)
    }

    private fun validateSimpleTextAttr(attr : String) : Boolean {
        return attr.length >= 3
    }

    private fun validateNumOfPags(numPags: Int) : Boolean {
        return numPags >= 1
    }

    private fun validateCoverType(coverType: String) : Boolean {
        if (coverType.uppercase() != "HARDBACK" && coverType.uppercase() != "PAPERBACK") { return false }
        return true
    }

    private fun create(id : Int, name : String, author : String, genre: String, numPags : Int, coverType : String) {
        this.id = id
        this.name = name
        this.author = author
        this.genre = genre
        this.numPags = numPags
        this.coverType = coverType
    }
}