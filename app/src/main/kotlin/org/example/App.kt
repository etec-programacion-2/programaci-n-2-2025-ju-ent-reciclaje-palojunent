package org.example


fun main (){
    val servicio = ServicioCalculadora()

        //catalogo de materiales(materiales disponibles)
        println("══════════════════════════════════════════")
        println("=== CATÁLOGO DE MATERIALES RECICLABLES ===\n")
        println(" - Materiales disponibles para la venta -\n")

        CatalogoMateriales.listarMateriales().forEach { material ->
            println("   • ${material.nombre} (${material.categoria}) - $${material.precioPorUnidad}/kg")
    }

    println("\nTotal de Materiales en Catálogo: ${CatalogoMateriales.listarMateriales().size}\n")
    
     println(" - Materiales agregados -\n")

   // Iniciar nueva tarea
   val tarea = servicio.iniciarNuevaTarea()
  
   // Agregar items válidos
   servicio.agregarItemATarea(tarea, "Papel periódico", 10.5)
   servicio.agregarItemATarea(tarea, "Botella PET", 2.3)
   servicio.agregarItemATarea(tarea, "Lata refresco", 1.5)
   servicio.agregarItemATarea(tarea, "Cable cobre", 0.8)
  
   println()
   
   // Mostrar detalle de items
   println("\n - Materiales procesados - \n")
   tarea.items.forEachIndexed { index, item ->
       println("  ${index + 1}. ${item.material.nombre}")
       println("     Categoría: ${item.material.categoria}")
       println("     Peso: ${item.pesoEnKg}kg")
       println("     Precio/kg: $${item.material.precioPorUnidad}")
       println("     Beneficio: $${String.format("%.2f", item.calcularBeneficio())}")
       println()
   }
   // Finalizar tarea y mostrar beneficio total
   val beneficioTotal = servicio.finalizarTarea(tarea)
   println("───────────────────────────────────────")
   println("BENEFICIO TOTAL: $${String.format("%.2f", beneficioTotal)}")
   println("TOTAL DE ITEMS: ${tarea.items.size}")
   println("═══════════════════════════════════════")
    }
