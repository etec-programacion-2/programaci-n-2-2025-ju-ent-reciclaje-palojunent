package org.example 

//Data class que representa una entrega específica de un material (Representa un item agregado)
data class ItemReciclado(
    val material: MaterialReciclable,
    val pesoEnKg: Double
) {

//Un bloque init se ejecuta al crear la instancia, verificando que el peso no sea negativo
    init {
        require(pesoEnKg >= 0) { "El peso no puede ser negativo: peso $pesoEnKg kg." }
    }
}
