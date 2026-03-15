package com.danielks.lib

import java.util.Scanner

class Menu(processor: Processor) {

    private var processor : Processor? = null

    init {
        this.processor = processor
        run()
    }

    private fun run() {
        do {
            displayMenu()
            val option = receiveInput()
            val isValidInput = validateInput(option)
            if (!isValidInput) break
            process(option)
        } while (option != 0)
    }

    fun displayMenu() {
        println("********************** BEM-VINDO **********************")
        println("Aplicação para CRUD de objeto: Livro (Book)")
        println("Selecione uma opção abaixo:")
        println("1 - Cadastrar.")
        println("2 - Listar cadastrados.")
        println("3 - Buscar por Id ou Nome.")
        println("4 - Alterar.")
        println("5 - Excluir.")
        println("0 - Finalizar.")
        println("*******************************************************")
    }

    private fun receiveInput() : Int{
        val input = Scanner(System.`in`)
        return input.nextInt()
    }

    private fun validateInput(option : Int) : Boolean {
        if (option < 0) {
            println("Opção inválida!")
            return false
        }
        return true
    }


    private fun process(option : Int) {
        processor?.process(option)
    }

}