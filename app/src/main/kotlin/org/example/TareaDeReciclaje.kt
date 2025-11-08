package org.example 

//Clase que representa una sesión de reciclaje completa. Mantiene todos los items que el usuario va agregando (Agrupa items de una sesión)
class TareaDeReciclaje(
    private val items: MutableList<ItemReciclado> = mutableListOf()
) {

//Agrega un nuevo item a la lista
    fun agregarItem(item: ItemReciclado) {
        items.add(item)
    }
    
//Devuelve una copia inmutable de los items agregados con un .toList(), que evita que se modifique la lista original
    fun obtenerItems(): List<ItemReciclado> = items.toList()
}
