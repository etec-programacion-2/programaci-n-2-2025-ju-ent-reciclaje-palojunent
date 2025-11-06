package org.example

import javax.swing.SwingUtilities

fun main() {
    
//Crea el servicio principal con todas sus dependencias
    val servicio = ServicioReciclaje(CatalogoMateriales, CalculadoraBeneficios(), ValidadorEntrada())

//SwingUtilities.invokeLater ejecuta el código en el hilo de eventos de Swing, para poder correr la interfaz
    SwingUtilities.invokeLater {

//Este controlador va a contectar la vista con el servicio 
        val controlador = ControladorUI(servicio, TareaDeReciclaje(), FormateadorUI())
        val ventana = VentanaReciclaje(CatalogoMateriales, FormateadorUI(), controlador)
        controlador.conectarVista(ventana)
        ventana.isVisible = true 
    } 
}