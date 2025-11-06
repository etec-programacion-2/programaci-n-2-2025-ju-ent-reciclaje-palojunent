package org.example

//interfaz consola enrada y salida
class EntradaSalidaConsola : IEntradaSalida {
    
    override fun mostrarMensaje(mensaje: String) {
        println(mensaje)
    }
    
    override fun mostrarError(mensaje: String) {
        println("  [✗] Error: $mensaje")
    }

//Solicita texto al usuario y retorna lo ingresado (o null si falla)
    override fun solicitarTexto(prompt: String): String? {
        print(prompt)
        val input = readLine()?.trim()

//Si readLine() retorna null, muestra un mensaje de error
        if (input == null) {
            mostrarError("No se pudo leer la entrada (readLine() devolvió null).")
            mostrarMensaje("Asegúrese de ejecutar en un terminal interactivo.")
        }
        
        return input
    }

//Solicita un número y lo convierte a Double (o null si es inválido)
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

//forEach itera sobre cada material
        materiales.forEach { material ->
            mostrarMensaje("   • ${material.nombre} (${material.categoria}) - $${material.precioPorUnidad}/kg")
        }
        
        mostrarMensaje("\nTotal de Materiales: ${materiales.size}\n")
    }

//Muestra el resumen final de la tarea con todos los items y el beneficio
    override fun mostrarResumen(items: List<ItemReciclado>, beneficioTotal: Double) {
        mostrarMensaje("\n══════════════════════════════════════════")
        mostrarMensaje("          RESUMEN DE LA TAREA")
        mostrarMensaje("══════════════════════════════════════════")
        
        if (items.isEmpty()) {
            mostrarMensaje("No se procesaron items válidos.")
        } else {
            mostrarMensaje("\n - Items Procesados (${items.size}) - \n")

//forEachIndexed permite el acceso al índice y al elemento
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
