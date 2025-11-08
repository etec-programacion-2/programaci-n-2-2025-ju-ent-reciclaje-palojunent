package org.example

//Implementación de IEntradaSalida para usar el programa por consola con una clase (Implementación para consola)
class EntradaSalidaConsola : IEntradaSalida {
    
//Método que printea un mensaje normal en la consola
    override fun mostrarMensaje(mensaje: String) {
        println(mensaje)
    }
    
//Método que imprime un mensaje de error
    override fun mostrarError(mensaje: String) {
        println("  [✗] Error: $mensaje")
    }
    
//Método que solicita texto al usuario y lo devuelve. Devuelve null si no se puede leer la entrada
    override fun solicitarTexto(prompt: String): String? {
        print(prompt)
        val input = readLine()?.trim()
        
//Si readLine() devuelve null, muestra un mensaje de ayuda con un if
        if (input == null) {
            mostrarError("No se pudo leer la entrada (readLine() devolvió null).")
            mostrarMensaje("Asegurese de ejecutar en un terminal interactivo.")
        }
        
        return input
    }
    
//Método que solicita un número al usuario. Devuelve null si la entrada no es un número válido
    override fun solicitarNumero(prompt: String): Double? {
        print(prompt)
        return readLine()?.trim()?.toDoubleOrNull()
    }
    
//Método que muestra el catálogo completo de materiales con formato
    override fun mostrarCatalogo(materiales: List<MaterialReciclable>) {
        mostrarMensaje("\n------------------------------------------")
        mostrarMensaje("=== ASISTENTE DE REGISTRO DE RECICLAJE ===")
        mostrarMensaje("  Calcularemos el beneficio de su tarea.")
        mostrarMensaje("------------------------------------------\n")
        mostrarMensaje("=== CATALOGO DE MATERIALES VALIDOS ===\n")
        
//Método que muestra cada material con su categoría y precio
        materiales.forEach { material ->
            mostrarMensaje("   - ${material.nombre} (${material.categoria}) - $${material.precioPorUnidad}/kg")
        }
        
        mostrarMensaje("\nTotal de Materiales: ${materiales.size}\n")
    }
    
//Método que muestra el resumen final de la tarea con todos los items y el total
    override fun mostrarResumen(items: List<ItemReciclado>, beneficioTotal: Double) {
        mostrarMensaje("\n------------------------------------------")
        mostrarMensaje("          RESUMEN DE LA TAREA")
        mostrarMensaje("------------------------------------------")
        
        if (items.isEmpty()) {
            mostrarMensaje("No se procesaron items válidos.")
        } else {
            mostrarMensaje("\n - Items Procesados (${items.size}) - \n")
            items.forEachIndexed { index, item ->
                val beneficio = item.pesoEnKg * item.material.precioPorUnidad
                mostrarMensaje("  ${index + 1}. ${item.material.nombre}")
                mostrarMensaje("     Peso: ${item.pesoEnKg}kg")
                mostrarMensaje("     Beneficio: $${String.format("%.2f", beneficio)}")
            }
        }
        
        mostrarMensaje("\n------------------------------------------")
        mostrarMensaje("BENEFICIO TOTAL: $${String.format("%.2f", beneficioTotal)}")
        mostrarMensaje("------------------------------------------\n")
    }
}