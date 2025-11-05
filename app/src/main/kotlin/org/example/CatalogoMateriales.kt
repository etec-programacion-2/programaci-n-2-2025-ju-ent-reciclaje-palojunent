package org.example

// SRP: Solo gestiona el catálogo de materiales

object CatalogoMateriales : ICatalogoMateriales {
    private val materialesReciclables = listOf(
        MaterialReciclable("Papel periódico", CategoriaResiduos.PAPEL, 0.15),
        MaterialReciclable("Cartón corrugado", CategoriaResiduos.CARTÓN, 0.12),
        MaterialReciclable("Botella PET", CategoriaResiduos.PLASTICO, 0.30),
        MaterialReciclable("Envase yogur", CategoriaResiduos.PLASTICO, 0.25),
        MaterialReciclable("Botella vidrio", CategoriaResiduos.VIDRIO, 0.08),
        MaterialReciclable("Lata refresco", CategoriaResiduos.ALUMINIO, 1.20),
        MaterialReciclable("Cable cobre", CategoriaResiduos.METAL, 6.50)
    )

    override fun listarMateriales(): List<MaterialReciclable> = materialesReciclables
    
    override fun buscarPorNombre(nombre: String): MaterialReciclable? =
        materialesReciclables.find { it.nombre == nombre }
}
