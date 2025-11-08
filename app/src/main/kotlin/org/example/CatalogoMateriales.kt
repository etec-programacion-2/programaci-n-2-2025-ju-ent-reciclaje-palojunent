package org.example

//Object (singleton), mantiene la lista de materiales disponibles. Solo existe una instancia en toda la aplicación ("Base de datos" de los materiales)
object CatalogoMateriales : ICatalogoMateriales {

//Lista inmutable de todos los materiales que acepta el sistema
    private val materialesReciclables = listOf(
        MaterialReciclable("Papel periodico", CategoriaResiduos.PAPEL, 0.15),
        MaterialReciclable("Carton corrugado", CategoriaResiduos.CARTON, 0.12),
        MaterialReciclable("Botella PET", CategoriaResiduos.PLASTICO, 0.30),
        MaterialReciclable("Envase yogur", CategoriaResiduos.PLASTICO, 0.25),
        MaterialReciclable("Botella vidrio", CategoriaResiduos.VIDRIO, 0.08),
        MaterialReciclable("Lata refresco", CategoriaResiduos.ALUMINIO, 1.20),
        MaterialReciclable("Cable cobre", CategoriaResiduos.METAL, 6.50)
    )

//Devuelve todos los materiales disponibles
    override fun listarMateriales(): List<MaterialReciclable> = materialesReciclables
    
//Busca un material por su nombre exacto, retorna null si no existe
    override fun buscarPorNombre(nombre: String): MaterialReciclable? =
        materialesReciclables.find { it.nombre == nombre }
}