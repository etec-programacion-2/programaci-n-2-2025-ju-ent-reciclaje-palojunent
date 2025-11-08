package org.example


//Clase que valida y verifica las entradas antes de procesarlas (Validación datos del usuario)
class ValidadorEntrada : IValidadorEntrada {
    
//Método que valida que el peso sea lógico y dentro de límites
    override fun validarPeso(peso: Double): ResultadoValidacion {
        return when {
            peso <= 0 -> ResultadoValidacion.Invalido(
                "El peso debe ser un número positivo mayor a 0"
            )
            peso > 10000 -> ResultadoValidacion.Invalido(
                "El peso excede el límite máximo permitido (10000 kg)"
            )
            else -> ResultadoValidacion.Valido
        }
    }
    
//Método que verifica que el nombre del material sea válido
    override fun validarNombreMaterial(nombre: String): ResultadoValidacion {
        return when {
            nombre.isBlank() -> ResultadoValidacion.Invalido(
                "El nombre del material no puede estar vacío"
            )
            nombre.length < 3 -> ResultadoValidacion.Invalido(
                "El nombre debe tener al menos 3 caracteres"
            )
            else -> ResultadoValidacion.Valido
        }
    }
}