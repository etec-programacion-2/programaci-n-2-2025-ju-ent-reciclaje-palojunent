package org.example

//object = Singleton (una única instancia en toda la aplicación)
object CatalogoMateriales : ICatalogoMateriales {

//Lista privada e inmutable de materiales disponibles
    private val materialesReciclables = listOf(
        MaterialReciclable("Papel periódico", CategoriaResiduos.PAPEL, 0.15),
        MaterialReciclable("Cartón corrugado", CategoriaResiduos.CARTÓN, 0.12),
        MaterialReciclable("Botella PET", CategoriaResiduos.PLASTICO, 0.30),
        MaterialReciclable("Envase yogur", CategoriaResiduos.PLASTICO, 0.25),
        MaterialReciclable("Botella vidrio", CategoriaResiduos.VIDRIO, 0.08),
        MaterialReciclable("Lata refresco", CategoriaResiduos.ALUMINIO, 1.20),
        MaterialReciclable("Cable cobre", CategoriaResiduos.METAL, 6.50)
    )

//Retorna la lista completa de materiales
    override fun listarMateriales(): List<MaterialReciclable> = materialesReciclables
    
//Busca un material por nombre exacto, si no lo encuentra, null 
    override fun buscarPorNombre(nombre: String): MaterialReciclable? =
        materialesReciclables.find { it.nombre == nombre }
}
