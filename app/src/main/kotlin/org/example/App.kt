package org.example

fun main() {
    val servicio = ServicioCalculadora()
    val presentacion = PresentacionReciclaje(servicio)

    presentacion.mostrarMensajeBienvenida()
    presentacion.mostrarCatalogo()

    val tarea = servicio.iniciarNuevaTarea()
    var terminado = false

    // Bucle interactivo para solicitar items
    while (!terminado) {
        val nombreMaterialInput = presentacion.solicitarNombreMaterial()

        // --- INICIO DIAGNÓSTICO DE CONSOLA ---
        // Si el entorno no es interactivo, readLine() puede devolver null inmediatamente.
        // Si esto sucede, mostramos un error de diagnóstico para el usuario.
        if (nombreMaterialInput == null) {
            println("\n[*** DIAGNÓSTICO DE CONSOLA ***]")
            println("ERROR: La aplicación no pudo leer la entrada (readLine() devolvió null).")
            println("Asegúrese de que el entorno de ejecución (como 'gradle run') esté configurado")
            println("para correr en un terminal interactivo que soporte la entrada de usuario (stdin).")
            terminado = true
            break
        }
        // --- FIN DIAGNÓSTICO ---

        // Corrección del error: Usar 'uppercase()' en lugar de 'toUpperCase()'
        if (nombreMaterialInput.isBlank() || nombreMaterialInput.uppercase() == "FIN") {
            terminado = true
            break
        }

        val nombreMaterial = nombreMaterialInput

        val peso = presentacion.solicitarPeso(nombreMaterial)

        // Validación de entrada (chequeo de que el peso sea un número positivo)
        if (peso != null && peso > 0) {
            // Lógica de negocio: delegada al servicio. 
            // El servicio (ServicioCalculadora) se encarga de imprimir el resultado de la operación.
            servicio.agregarItemATarea(tarea, nombreMaterial, peso)
        } else {
            // Manejo de error de entrada inválida (peso nulo o <= 0).
            presentacion.mostrarErrorEntradaInvalida(nombreMaterial, peso ?: 0.0) 
        }
    }

    // Al salir del bucle, mostrar el resumen final
    presentacion.mostrarResumenTarea(tarea)
}
