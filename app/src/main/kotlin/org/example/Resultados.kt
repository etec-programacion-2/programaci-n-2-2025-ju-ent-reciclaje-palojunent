package org.example

//Sealed classes permiten extensiones sin modificaciones

sealed class ResultadoValidacion {
    object Valido : ResultadoValidacion()
    data class Invalido(val mensaje: String) : ResultadoValidacion()
}

sealed class ResultadoAgregarItem {
    data class Exito(val item: ItemReciclado, val beneficio: Double) : ResultadoAgregarItem()
    data class Error(val mensaje: String) : ResultadoAgregarItem()
    data class MaterialNoEncontrado(
        val nombreBuscado: String,
        val materialesDisponibles: List<MaterialReciclable>
    ) : ResultadoAgregarItem()
}
