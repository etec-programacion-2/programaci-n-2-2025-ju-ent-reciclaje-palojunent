package org.example

//Sealed class que representa el resultado de la validación. Solo puede ser Valido o Invalido (con mensaje de error) (Resultado de las validaciones)
sealed class ResultadoValidacion {
    object Valido : ResultadoValidacion()
    data class Invalido(val mensaje: String) : ResultadoValidacion()
}

