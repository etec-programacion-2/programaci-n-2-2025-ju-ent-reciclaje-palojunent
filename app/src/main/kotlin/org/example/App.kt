package org.example

import javax.swing.SwingUtilities

fun main() {
    val servicio = ServicioReciclaje(CatalogoMateriales, CalculadoraBeneficios(), ValidadorEntrada())
    SwingUtilities.invokeLater {
        val controlador = ControladorUI(servicio, TareaDeReciclaje(), FormateadorUI())
        val ventana = VentanaReciclaje(CatalogoMateriales, FormateadorUI(), controlador)
        controlador.conectarVista(ventana)
        ventana.isVisible = true 
    } 
}