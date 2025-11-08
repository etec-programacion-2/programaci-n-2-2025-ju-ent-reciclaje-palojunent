package org.example

import javax.swing.SwingUtilities

//Object (Singleton) encargado de correr toda la interfaz gráfica
object IniciadorGUI {
    
//Método que recibe el servicio y crea toda la interfaz gráfica
    fun iniciar(servicio: ServicioReciclaje) {

//SwingUtilities.invokeLater asegura que la GUI se cree en el hilo correcto de Swing
        SwingUtilities.invokeLater {

//Crear el controlador de la interfaz con una nueva tarea y el formateador
            val controlador = ControladorUI(servicio, TareaDeReciclaje(), FormateadorUI())
            
//Crear la ventana principal pasándole el catálogo, formateador y controlador
            val ventana = VentanaReciclaje(CatalogoMateriales, FormateadorUI(), controlador)
            
//Conectar la ventana con el controlador (patrón MVC)
            controlador.conectarVista(ventana)
            
//Hacer visible la ventana para que el usuario la vea
            ventana.isVisible = true
        }
    }
}