package org.example

//Sealed classes permiten extensiones sin modificaciones

sealed class ResultadoValidacion {
    object Valido : ResultadoValidacion()
    data class Invalido(val mensaje: String) : ResultadoValidacion()
}

