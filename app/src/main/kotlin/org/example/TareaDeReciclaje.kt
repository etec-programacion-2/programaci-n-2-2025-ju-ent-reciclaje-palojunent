package org.example 

class TareaDeReciclaje(
    private val items: MutableList<ItemReciclado> = mutableListOf()
) {
    fun agregarItem(item: ItemReciclado) {
        items.add(item)
    }
    
    fun obtenerItems(): List<ItemReciclado> = items.toList()
}