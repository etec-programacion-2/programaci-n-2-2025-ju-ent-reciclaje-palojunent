package org.example

import javax.swing.SwingUtilities

fun main() {
    
//Inicializa el servicio principal con sus dependencias
    val servicio = ServicioReciclaje(CatalogoMateriales, CalculadoraBeneficios(), ValidadorEntrada())
    
//Esto segura que la interfaz gráfica se corra en el hilo correcto
    SwingUtilities.invokeLater {

//Controlador que maneja la lógica entre la vista y el servicio y ventana principal
        val controlador = ControladorUI(servicio, TareaDeReciclaje(), FormateadorUI())
        val ventana = VentanaReciclaje(CatalogoMateriales, FormateadorUI(), controlador)
        
//Conecta la ventana con el controlador
        controlador.conectarVista(ventana)
        
//Hace visible la ventana
        ventana.isVisible = true 
    } 
}
