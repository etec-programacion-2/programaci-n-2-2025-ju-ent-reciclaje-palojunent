package org.example

//Sealed class que representa los posibles resultados/retornos al agregar un item. Permite manejar diferentes casos de forma segura y organizada (Resultado de agregar items)
sealed class ResultadoAgregarItem {

//Caso exitoso: devuelve el item y su beneficio
    data class Exito(val item: ItemReciclado, val beneficio: Double) : ResultadoAgregarItem()
    
//Error genérico de validación
    data class Error(val mensaje: String) : ResultadoAgregarItem()
    
//Error específico: el material no existe en el catálogo
    data class MaterialNoEncontrado(
        val nombreBuscado: String,
        val materialesDisponibles: List<MaterialReciclable>
    ) : ResultadoAgregarItem()
}
