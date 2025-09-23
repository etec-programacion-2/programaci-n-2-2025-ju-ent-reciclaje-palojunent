package org.example 

class TareaDeReciclaje(
    val items: Mutablelist <ItemReciclado> = mutableListOf()
){
    fun agregarItem(item: ItemReciclado){
            items.add(item)
    }
    fun calcularBeneficioTotal(): Double {
        var BeneficioTotal= 0.0 
        for (item in items) {
            BeneficioTotal += item.calcularBeneficio()

    }
            return BeneficioTotal


    }
}