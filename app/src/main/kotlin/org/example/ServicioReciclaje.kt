package org.example

//Solo maneja la lógica de negocio del reciclaje
//Depende de las interfaces, no de implementaciones concretas

class ServicioReciclaje(
    private val catalogo: ICatalogoMateriales,
    private val calculadora: ICalculadoraBeneficios,
    private val validador: IValidadorEntrada
) {
    
    fun iniciarNuevaTarea(): TareaDeReciclaje = TareaDeReciclaje()
    
    fun agregarItemATarea(
        tarea: TareaDeReciclaje,
        nombreMaterial: String,
        peso: Double
    ): ResultadoAgregarItem {
        
        // Validar nombre
        val validacionNombre = validador.validarNombreMaterial(nombreMaterial)
        if (validacionNombre is ResultadoValidacion.Invalido) {
            return ResultadoAgregarItem.Error(validacionNombre.mensaje)
        }
        
        // Validar peso
        val validacionPeso = validador.validarPeso(peso)
        if (validacionPeso is ResultadoValidacion.Invalido) {
            return ResultadoAgregarItem.Error(validacionPeso.mensaje)
        }
        
        // Buscar material
        val material = catalogo.buscarPorNombre(nombreMaterial)
            ?: return ResultadoAgregarItem.MaterialNoEncontrado(
                nombreMaterial,
                catalogo.listarMateriales()
            )
        
        // Agregar item
        val item = ItemReciclado(material, peso)
        tarea.agregarItem(item)
        
        val beneficio = calculadora.calcularBeneficioItem(item)
        return ResultadoAgregarItem.Exito(item, beneficio)
    }
    
    fun finalizarTarea(tarea: TareaDeReciclaje): Double {
        return calculadora.calcularBeneficioTotal(tarea.obtenerItems())
    }
}
