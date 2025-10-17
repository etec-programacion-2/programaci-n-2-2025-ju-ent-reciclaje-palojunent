package org.example

fun main() {
    val servicio = ServicioCalculadora()
    val presentacion = PresentacionReciclaje()
    
    // Mostrar catálogo
    presentacion.mostrarCatalogo()
    
    println(" - Materiales agregados -\n")
    
    // Iniciar tarea y agregar items
    val tarea = servicio.iniciarNuevaTarea()
    servicio.agregarItemATarea(tarea, "Papel periódico", 10.5)
    servicio.agregarItemATarea(tarea, "Botella PET", 2.3)
    servicio.agregarItemATarea(tarea, "Lata refresco", 1.5)
    servicio.agregarItemATarea(tarea, "Cable cobre", 0.8)
    
    // Finalizar y mostrar resumen
    val beneficioTotal = servicio.finalizarTarea(tarea)
    presentacion.mostrarResumenTarea(tarea, beneficioTotal)
}