package org.example

//interfaz consola

class EntradaSalidaConsola : IEntradaSalida {
    
    override fun mostrarMensaje(mensaje: String) {
        println(mensaje)
    }
    
    override fun mostrarError(mensaje: String) {
        println("  [✗] Error: $mensaje")
    }
    
    override fun solicitarTexto(prompt: String): String? {
        print(prompt)
        val input = readLine()?.trim()
        
        if (input == null) {
            mostrarError("No se pudo leer la entrada (readLine() devolvió null).")
            mostrarMensaje("Asegúrese de ejecutar en un terminal interactivo.")
        }
        
        return input
    }
    
    override fun solicitarNumero(prompt: String): Double? {
        print(prompt)
        return readLine()?.trim()?.toDoubleOrNull()
    }
    
    override fun mostrarCatalogo(materiales: List<MaterialReciclable>) {
        mostrarMensaje("\n══════════════════════════════════════════")
        mostrarMensaje("=== ASISTENTE DE REGISTRO DE RECICLAJE ===")
        mostrarMensaje("  Calcularemos el beneficio de su tarea.")
        mostrarMensaje("══════════════════════════════════════════\n")
        mostrarMensaje("=== CATÁLOGO DE MATERIALES VÁLIDOS ===\n")
        
        materiales.forEach { material ->
            mostrarMensaje("   • ${material.nombre} (${material.categoria}) - $${material.precioPorUnidad}/kg")
        }
        
        mostrarMensaje("\nTotal de Materiales: ${materiales.size}\n")
    }
    
    override fun mostrarResumen(items: List<ItemReciclado>, beneficioTotal: Double) {
        mostrarMensaje("\n══════════════════════════════════════════")
        mostrarMensaje("          RESUMEN DE LA TAREA")
        mostrarMensaje("══════════════════════════════════════════")
        
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
        
        mostrarMensaje("\n──────────────────────────────────────────")
        mostrarMensaje("BENEFICIO TOTAL: $${String.format("%.2f", beneficioTotal)}")
        mostrarMensaje("──────────────────────────────────────────\n")
    }
}
