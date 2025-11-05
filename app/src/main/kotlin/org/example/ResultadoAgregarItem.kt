package org.example

sealed class ResultadoAgregarItem {
    data class Exito(val item: ItemReciclado, val beneficio: Double) : ResultadoAgregarItem()
    data class Error(val mensaje: String) : ResultadoAgregarItem()
    data class MaterialNoEncontrado(
        val nombreBuscado: String,
        val materialesDisponibles: List<MaterialReciclable>
    ) : ResultadoAgregarItem()
}
