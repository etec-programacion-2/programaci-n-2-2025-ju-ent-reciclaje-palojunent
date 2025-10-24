package org.example

// Nota: Esta clase contiene toda la lógica de entrada/salida (E/S) por consola.
class PresentacionReciclaje(private val servicio: ServicioCalculadora) {

    fun mostrarMensajeBienvenida() {
        println("\n══════════════════════════════════════════")
        println("=== ASISTENTE DE REGISTRO DE RECICLAJE ===")
        println("  Calcularemos el beneficio de su tarea.")
        println("══════════════════════════════════════════\n")
    }

    fun mostrarCatalogo() {
        println("=== CATÁLOGO DE MATERIALES VÁLIDOS ===\n")
        CatalogoMateriales.listarMateriales().forEach { material ->
            println("   • ${material.nombre} (${material.categoria}) - $${material.precioPorUnidad}/kg")
        }
        println("\nTotal de Materiales en Catálogo: ${CatalogoMateriales.listarMateriales().size}\n")
    }

    fun solicitarNombreMaterial(): String? {
        print(">> Ingrese el nombre del material a reciclar (o 'FIN' para terminar): ")
        return readLine()?.trim()
    }

    fun solicitarPeso(nombreMaterial: String): Double? {
        print(">> Ingrese el peso en Kg de '$nombreMaterial': ")
        return readLine()?.trim()?.toDoubleOrNull()
    }

    fun mostrarErrorEntradaInvalida(nombre: String, peso: Double) {
        println("  [✗] Error: El peso ingresado para '$nombre' es inválido (${String.format("%.2f", peso)}kg). Debe ser un número positivo.")
        println("----------------------------------------------------------")
    }

    fun mostrarResumenTarea(tarea: TareaDeReciclaje) {
        val beneficioTotal = servicio.finalizarTarea(tarea)

        println("\n══════════════════════════════════════════")
        println("          RESUMEN DE LA TAREA FINALIZADA")
        println("══════════════════════════════════════════")

        if (tarea.items.isEmpty()) {
            println("No se procesaron items válidos.")
        } else {
            println("\n - Items Procesados (${tarea.items.size} en total) - \n")
            tarea.items.forEachIndexed { index, item ->
                println("  ${index + 1}. ${item.material.nombre}")
                println("     Peso: ${item.pesoEnKg}kg")
                println("     Beneficio: $${String.format("%.2f", item.calcularBeneficio())}")
            }
        }

        println("\n──────────────────────────────────────────")
        println("BENEFICIO TOTAL OBTENIDO: $${String.format("%.2f", beneficioTotal)}")
        println("──────────────────────────────────────────\n")
    }
}