package com.danielks.lib

import java.util.Scanner

class Processor {
    private var bookList = mutableListOf<Book>()

    fun process(option : Int) {
        when (option) {
            1 -> insert()
            2 -> listAll()
            3 -> listByIdOrName()
            4 -> update()
            5 -> delete()
        }
    }

    private fun insert() {
        println("Por favor, informe os dados do novo livro:")
        val input = getScanner()
        print("Nome: ")
        val name = input.nextLine()
        print("Autor: ")
        val author = input.nextLine()
        print("Gênero: ")
        val genre = input.nextLine()
        print("Tipo de capa (HARDBACK ou PAPERBACK): ")
        val coverType = input.nextLine()
        print("Número de páginas: ")
        val numPags = input.nextInt()
        val id = generateId()

        val newBook = Book(id, name, author, genre, numPags, coverType)
        val isValid = newBook.validate(name, author, genre, numPags, coverType) // não é a melhor forma, mas para simplificar repliquei a validação

        if (!isValid) return
        bookList.add(newBook)
    }

    private fun listAll() {
        println("Lista de livros cadastrados:")
        bookList.forEach{
            println(it)
        }
    }

    private fun listByIdOrName() : MutableList<Book>? {
        println("Informe o nome do(s) livro(s) ou ID do livro:")
        val input = getScanner()
        val idOrName = input.nextLine()

        when {
            idOrName.toIntOrNull() != null -> return listById(idOrName.toInt())
            idOrName.isNotBlank() -> return listByName(idOrName)
            else -> {
                println("Livro não encontrado.")
                return null
            }
        }
    }

    private fun listById(id : Int) : MutableList<Book> {
        val selectedBook = bookList
                            .filter { it.id == id }
                            .toMutableList()
        displayBooks(selectedBook)
        return selectedBook
    }

    private fun listByName(name : String) : MutableList<Book> {
        val selectedBooks = bookList
                                .filter { it.name == name }
                                .toMutableList()
        displayBooks(selectedBooks)
        return selectedBooks
    }

    private fun update() {
        println("OBS1.: A alteração de livros por nome será aplicada a todos os livros com mesmo nome.")
        println("OBS2.: Dados informados em branco não serão alterados.")
        val selectedBooks = listByIdOrName() ?: return

        println("Informe os novos dados do(s) livro(s):")
        val newBookData = receiveData()

        selectedBooks.forEach{
            it.update(newBookData)
        }
    }

    private fun delete() {
        println("OBS.: A deleção de livros por nome será aplicada a todos os livros com mesmo nome.")
        val selectedBooks = listByIdOrName() ?: return
        val idsRemoved : MutableList<Int>? = null
        selectedBooks.forEach {
            val idToDelete = it.id
            if (idToDelete != null) {
                idsRemoved?.add(idToDelete)
                removeFromList(idToDelete)
            }
        }

        if (idsRemoved == null) return

        print("Ids removidos: ")
        idsRemoved.forEach { print(it) }
    }

    private fun removeFromList(idToDelete : Int) {
        val iterator = bookList.iterator()

        while (iterator.hasNext()) {
            val book = iterator.next()
            if (book.id == idToDelete) {
                iterator.remove()
            }
        }
    }

    private fun receiveData() : BookUpdateData {
        val input = getScanner()
        print("Nome: ")
        val name = input.nextLine()
        print("Autor: ")
        val author = input.nextLine()
        print("Gênero: ")
        val genre = input.nextLine()
        print("Tipo de capa (HARDBACK ou PAPERBACK): ")
        val coverType = input.nextLine()
        print("Número de páginas: ")
        val numPags = input.nextInt()

        return BookUpdateData(name, author, genre, numPags, coverType) // DTO de dados para atualização
    }

    private fun displayBooks(books: MutableList<Book>) {
        println("Resultados da busca:")
        books.forEach{
            println(it)
        }
    }

    private fun generateId() : Int{
        return bookList.size + 1
    }

    private fun getScanner() : Scanner {
        return Scanner(System.`in`)
    }
}