package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ItemRecicladoTest {

    @Test
    fun `calcularBeneficio debe retornar el valor correcto`() {
        val material = MaterialReciclable("Papel", CategoriaResiduos.PAPEL, 0.15)
        val item = ItemReciclado(material, 10.0)
        assertEquals(1.5, item.calcularBeneficio())
    }
}