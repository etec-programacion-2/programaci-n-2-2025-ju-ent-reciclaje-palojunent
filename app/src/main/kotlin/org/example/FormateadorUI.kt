package org.example

//Interfaz que define cómo formatear datos para la interfaz de usuario (UI)(Formatea datos para poder mostrar los resultados de los items)
interface IFormateadorUI {
    fun formatearListaItems(items: List<ItemReciclado>): List<String>
    fun formatearMensajeExito(item: ItemReciclado, beneficio: Double): String
    fun formatearCatalogo(materiales: List<MaterialReciclable>): String
}

//Clase implementación que convierte datos en strings legibles
class FormateadorUI : IFormateadorUI {
    
//Método que convierte cada item en un string con un formato " Material - Peso - Beneficio"
    override fun formatearListaItems(items: List<ItemReciclado>): List<String> {
        return items.mapIndexed { index, item ->
            val beneficio = item.pesoEnKg * item.material.precioPorUnidad
            "${index + 1}. ${item.material.nombre} - ${item.pesoEnKg}kg - $${String.format("%.2f", beneficio)}"
        }
    }
    
//Método que formatea el mensaje cuando se agrega un item exitosamente
    override fun formatearMensajeExito(item: ItemReciclado, beneficio: Double): String {
        return "Material agregado: ${item.material.nombre} - " +
               "${item.pesoEnKg}kg - Beneficio: $${String.format("%.2f", beneficio)}"
    }
    
//Método que formatea el catálogo completo de materiales
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