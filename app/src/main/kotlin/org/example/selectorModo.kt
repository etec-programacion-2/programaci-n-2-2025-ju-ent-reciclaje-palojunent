package org.example

//Object (Singleton) que se encarga de mostrar el menú y averiguar como quiere correr el programa el usuario 
object SelectorModo {
    fun solicitar(): String {
        println("---------------------------------\n       ReSimple - RECICLAJE\n---------------------------------\n")
        println("Seleccione el modo:\n  1. Consola/Terminal\n  2. Interfaz Grafica\n") //Opciones disponibles
        print("Opcion (1 o 2): ")

//Leer la respuesta del usuario y retornarla (si no ingresa nada, devuelve "2" por defecto)
        return readLine()?.trim() ?: "2"
    }
}