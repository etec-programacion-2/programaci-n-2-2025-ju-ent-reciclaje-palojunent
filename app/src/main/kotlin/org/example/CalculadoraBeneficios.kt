package org.example

class CalculadoraBeneficios : ICalculadoraBeneficios {

//Calcula el beneficio de un solo item(multiplica el peso del item por el precio en kg del material)
    override fun calcularBeneficioItem(item: ItemReciclado): Double {
        return item.pesoEnKg * item.material.precioPorUnidad
    }
//Calcula el beneficio total de una lista de items con sumOf
    override fun calcularBeneficioTotal(items: List<ItemReciclado>): Double {
        return items.sumOf { calcularBeneficioItem(it) }
    }
}
