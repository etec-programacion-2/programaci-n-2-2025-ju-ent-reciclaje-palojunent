package org.example

//Clase que encapsula toda la lógica de cálculo de los beneficios (Calculo de los incentivos)
class CalculadoraBeneficios : ICalculadoraBeneficios {
    
//Calcula el beneficio de un solo item (peso × precio)
    override fun calcularBeneficioItem(item: ItemReciclado): Double {
        return item.pesoEnKg * item.material.precioPorUnidad
    }
    
//Suma los beneficios obtenidos de todos los items con sumOf, simplificando la suma
    override fun calcularBeneficioTotal(items: List<ItemReciclado>): Double {
        return items.sumOf { calcularBeneficioItem(it) }
    }
}