package org.example

class CalculadoraBeneficios : ICalculadoraBeneficios {
    
    override fun calcularBeneficioItem(item: ItemReciclado): Double {
        return item.pesoEnKg * item.material.precioPorUnidad
    }
    
    override fun calcularBeneficioTotal(items: List<ItemReciclado>): Double {
        return items.sumOf { calcularBeneficioItem(it) }
    }
}
