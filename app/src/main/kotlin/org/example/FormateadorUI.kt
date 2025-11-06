package org.example

interface IFormateadorUI {
    fun formatearListaItems(items: List<ItemReciclado>): List<String>
    fun formatearMensajeExito(item: ItemReciclado, beneficio: Double): String
    fun formatearCatalogo(materiales: List<MaterialReciclable>): String
}

class FormateadorUI : IFormateadorUI {
    
    override fun formatearListaItems(items: List<ItemReciclado>): List<String> {
        return items.mapIndexed { index, item ->
            val beneficio = item.pesoEnKg * item.material.precioPorUnidad
            "${index + 1}. ${item.material.nombre} - ${item.pesoEnKg}kg - $${String.format("%.2f", beneficio)}"
        }
    }
    
    override fun formatearMensajeExito(item: ItemReciclado, beneficio: Double): String {
        return "Material agregado: ${item.material.nombre} - " +
               "${item.pesoEnKg}kg - Beneficio: $${String.format("%.2f", beneficio)}"
    }
    
    override fun formatearCatalogo(materiales: List<MaterialReciclable>): String {
        return buildString {
            materiales.forEach { material ->
                appendLine("• ${material.nombre}")
                appendLine("  Categoría: ${material.categoria}")
                appendLine("  Precio: $${String.format("%.2f", material.precioPorUnidad)}/kg")
                appendLine()
            }
        }
    }
}