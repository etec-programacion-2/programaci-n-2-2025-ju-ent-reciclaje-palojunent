package org.example

sealed class ResultadoValidacion {
    object Valido : ResultadoValidacion()
    data class Invalido(val mensaje: String) : ResultadoValidacion()
}

