package org.example

import javax.swing.SwingUtilities

fun main() {
    //dependencias
    val catalogoMateriales = CatalogoMateriales
    val calculadoraBeneficios = CalculadoraBeneficios()
    val validador = ValidadorEntrada()
    val servicio = ServicioReciclaje(catalogoMateriales, calculadoraBeneficios, validador)
    
    // Abrir interfaz gráfica
    SwingUtilities.invokeLater {
        val tarea = TareaDeReciclaje()
        val formateador = FormateadorUI()
        val controlador = ControladorUI(servicio, tarea, formateador)
        val ventana = VentanaReciclaje(catalogoMateriales, formateador, controlador)
        controlador.conectarVista(ventana)
        ventana.isVisible = true
    }
}