package org.example

import javax.swing.SwingUtilities

fun main(args: Array<String>) {

//Servicio principal con todas sus dependencias (catálogo, calculadora, validador)
    val servicio = ServicioReciclaje(CatalogoMateriales, CalculadoraBeneficios(), ValidadorEntrada())
    
//Determina qué modo usar: si hay argumentos usa el primero, sino pregunta al usuario
    val modo = if (args.isNotEmpty()) args[0] else SelectorModo.solicitar()
    
//Si el modo es consola (puede ser "consola", "terminal", "cli" o "1"), con un if inicia el controlador de consola y lo corre, sino corre la interfaz gráfica
    if (modo in listOf("consola", "terminal", "cli", "1")) {
        ControladorReciclaje(servicio, EntradaSalidaConsola()).ejecutar()
    } else {
        IniciadorGUI.iniciar(servicio)
    }
}