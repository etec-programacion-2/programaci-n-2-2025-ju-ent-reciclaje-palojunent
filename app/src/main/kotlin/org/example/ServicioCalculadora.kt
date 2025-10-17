package org.example

class ServicioCalculadora(){

    fun iniciarNuevaTarea(): TareaDeReciclaje{
    return TareaDeReciclaje()
    }
    
    fun agregarItemATarea(tarea: TareaDeReciclaje, nombreMaterial: String, peso: Double) {
        val material = CatalogoMateriales.buscarPorNombre(nombreMaterial)

        if (material != null) {
        val item = ItemReciclado(material, peso)
        tarea.agregarItem(item)
        println("Material agregado: ${material.nombre} - ${peso}kg - Beneficio: $${String.format("%.2f", item.calcularBeneficio())}")
    
    } else {
        println("Error: El material '$nombreMaterial' no existe en el catálogo")
        println("Materiales disponibles:")
        CatalogoMateriales.listarMateriales().forEach {
        println("    - ${it.nombre} (${it.categoria})")
        }
     }
  }
    fun finalizarTarea(tarea: TareaDeReciclaje): Double{
    return tarea.calcularBeneficioTotal()
    }
}