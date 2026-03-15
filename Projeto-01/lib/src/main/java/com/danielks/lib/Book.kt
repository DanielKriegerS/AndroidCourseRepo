package com.danielks.lib

class Book(id: Int, name: String, author: String, genre: String, numPags: Int, coverType: String) {
    var id : Int? = id // poderia ser um UUID ou o ISBN (embora preferência UUID)
    var name : String? = null
    protected var author : String? = null
    protected var genre : String? = null // String para simplificar, poderia ser uma classe ou lista predefinida
    protected var numPags : Int? = null
    protected var coverType : String? = null // usando string para simplificar, poderia ser uma classe

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

    fun validate(name : String, author : String, genre: String, numPags : Int, coverType : String) : Boolean {
        return  validateSimpleTextAttr(name) &&
                validateSimpleTextAttr(author) &&
                validateSimpleTextAttr(genre) &&
                validateNumOfPags(numPags) &&
                validateCoverType(coverType)
    }

    private fun validateSimpleTextAttr(attr : String?) : Boolean {
        if (attr != null) {
            return attr.length >= 3
        }
        return false
    }

    private fun validateNumOfPags(numPags: Int?) : Boolean {
        if (numPags != null) {
            return numPags >= 1
        }
        return false
    }

    private fun validateCoverType(coverType: String?) : Boolean {
        if (coverType?.uppercase() != "HARDBACK" && coverType?.uppercase() != "PAPERBACK") {
            println("Tipo da capa $coverType é inválido.")
            return false
        }
        return true
    }

    private fun create(id : Int, name : String, author : String, genre: String, numPags : Int, coverType : String) {
        this.id = id
        this.name = name
        this.author = author
        this.genre = genre
        this.numPags = numPags
        this.coverType = coverType.uppercase()
    }

    fun update(newBookData: BookUpdateData) {
        if (this.name != newBookData.name && validateSimpleTextAttr(newBookData.name))
            this.name = newBookData.name

        if (this.author != newBookData.author && validateSimpleTextAttr(newBookData.author))
            this.author = newBookData.author


        if (this.genre != newBookData.genre && validateSimpleTextAttr(newBookData.genre))
            this.genre = newBookData.genre

        if (this.numPags != newBookData.numPags && validateNumOfPags(newBookData.numPags))
            this.numPags = newBookData.numPags

        if (coverType != null) return

        if (this.coverType != newBookData.coverType && validateCoverType(newBookData.coverType))
            this.coverType = newBookData.coverType
    }

    // to string basico, poderia ser melhor formatado
    override fun toString(): String {
        return "Book(id=$id, name=$name, author=$author, genre=$genre, numPags=$numPags, coverType=$coverType)"
    }


}