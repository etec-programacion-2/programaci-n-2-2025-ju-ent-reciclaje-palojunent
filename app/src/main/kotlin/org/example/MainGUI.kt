package org.example

import javax.swing.SwingUtilities

// Main alternativo para la interfaz gráfica
// NO modifica el Main.kt original
fun main() {
    SwingUtilities.invokeLater {
        // Inyección de dependencias
        val catalogoMateriales = CatalogoMateriales
        val calculadora = CalculadoraBeneficios()
        val validador = ValidadorEntrada()
        val servicio = ServicioReciclaje(catalogoMateriales, calculadora, validador)
        val tarea = TareaDeReciclaje()
        val formateador = FormateadorUI()
        
        // Crear y conectar componentes UI
        val controlador = ControladorUI(servicio, tarea, formateador)
        val ventana = VentanaReciclaje(catalogoMateriales, formateador, controlador)
        controlador.conectarVista(ventana)
        
        // Mostrar ventana
        ventana.isVisible = true
    }
}