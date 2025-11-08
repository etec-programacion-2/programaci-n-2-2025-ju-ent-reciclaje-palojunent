package org.example 

//Data class que representa un tipo de material reciclable. El "data" genera automáticamente métodos útiles como toString(), equals(), hashCode(), etc. (Modelo de datos para los materiales)  
data class MaterialReciclable(
    val nombre: String,
    val categoria: CategoriaResiduos,
    val precioPorUnidad: Double
)
