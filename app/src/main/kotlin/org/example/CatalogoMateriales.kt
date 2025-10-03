package org.example

object CatalogoMateriales {

    private val materialesReciclables = listOf(
        MaterialReciclable("Papel periódico", CategoriaResiduos.PAPEL, 0.15),
        MaterialReciclable("Cartón corrugado", CategoriaResiduos.CARTÓN, 0.12),
        MaterialReciclable("Botella PET", CategoriaResiduos.PLASTICO, 0.30),
        MaterialReciclable("Envase yogur", CategoriaResiduos.PLASTICO, 0.25),
        MaterialReciclable("Botella vidrio", CategoriaResiduos.VIDRIO, 0.08),
        MaterialReciclable("Lata refresco", CategoriaResiduos.ALUMINIO, 1.20),
        MaterialReciclable("Cable cobre", CategoriaResiduos.METAL, 6.50)
    )

   fun listarMateriales(): List<MaterialReciclable> = materialesReciclables

   fun buscarPorNombre(nombre: String): MaterialReciclable? = 
        materialesReciclables.find { it.nombre == nombre }

    fun materialesPorCategoria(categoria: CategoriaResiduos): List<MaterialReciclable> = 
    materialesReciclables.filter { it.categoria == categoria }
}