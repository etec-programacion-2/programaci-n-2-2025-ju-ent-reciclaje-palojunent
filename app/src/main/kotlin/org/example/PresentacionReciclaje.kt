package org.example

class PresentacionReciclaje {
    
    fun mostrarCatalogo() {
        println("══════════════════════════════════════════")
        println("=== CATÁLOGO DE MATERIALES RECICLABLES ===\n")
        println(" - Materiales disponibles para la venta -\n")
        CatalogoMateriales.listarMateriales().forEach { material ->
            println("   • ${material.nombre} (${material.categoria}) - $${material.precioPorUnidad}/kg")
        }
        println("\nTotal de Materiales en Catálogo: ${CatalogoMateriales.listarMateriales().size}\n")
    }
    
    fun mostrarResumenTarea(tarea: TareaDeReciclaje, beneficioTotal: Double) {
        println("\n - Materiales procesados - \n")
        tarea.items.forEachIndexed { index, item ->
            println("  ${index + 1}. ${item.material.nombre}")
            println("     Categoría: ${item.material.categoria}")
            println("     Peso: ${item.pesoEnKg}kg")
            println("     Precio/kg: $${item.material.precioPorUnidad}")
            println("     Beneficio: $${String.format("%.2f", item.calcularBeneficio())}")
            println()
        }
        
        println("───────────────────────────────────────")
        println("BENEFICIO TOTAL: $${String.format("%.2f", beneficioTotal)}")
        println("TOTAL DE ITEMS: ${tarea.items.size}")
        println("═══════════════════════════════════════")
    }
}