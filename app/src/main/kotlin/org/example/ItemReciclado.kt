package org.example 
data class ItemReciclado(
    val material: MaterialReciclable,
    val pesoEnKg: Double
){
    init {
        require(pesoEnKg >= 0) {
            "El peso no puede ser negativo : peso $pesoEnKg kg. "
        }
    }
    fun calcularBeneficio(): Double{
        return pesoEnKg * material.precioporunidad
    }
}