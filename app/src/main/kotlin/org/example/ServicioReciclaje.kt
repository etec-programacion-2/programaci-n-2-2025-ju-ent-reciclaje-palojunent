package org.example

//Clase que coordina todas las operaciones del sistema. Separa la lógica de negocio de la interfaz (Lógica principal)
class ServicioReciclaje(
    private val catalogo: ICatalogoMateriales,
    private val calculadora: ICalculadoraBeneficios,
    private val validador: IValidadorEntrada
) {
    
//Crea una nueva sesión de reciclaje
    fun iniciarNuevaTarea(): TareaDeReciclaje = TareaDeReciclaje()
    
//Intenta agregar un item a la tarea actual y hace todas las validaciones necesarias
    fun agregarItemATarea(
        tarea: TareaDeReciclaje,
        nombreMaterial: String,
        peso: Double
    ): ResultadoAgregarItem {
        
//Validación del nombre del material. Guarda el resultado de llamar al método validarNombreMaterial de ValidadorEntrada.kt
        val validacionNombre = validador.validarNombreMaterial(nombreMaterial)
        if (validacionNombre is ResultadoValidacion.Invalido) {
            return ResultadoAgregarItem.Error(validacionNombre.mensaje)
        }
        
//Validación  del peso. Guarda el resultado de llamar al método validarNombreMaterial de ValidadorEntrada.kt
        val validacionPeso = validador.validarPeso(peso)
        if (validacionPeso is ResultadoValidacion.Invalido) {
            return ResultadoAgregarItem.Error(validacionPeso.mensaje)
        }
        
//Busca el material en el catálogo
        val material = catalogo.buscarPorNombre(nombreMaterial)
            ?: return ResultadoAgregarItem.MaterialNoEncontrado(
                nombreMaterial,
                catalogo.listarMateriales()
            )
        
//Crea el item y lo agrega a la tarea
        val item = ItemReciclado(material, peso)
        tarea.agregarItem(item)
        
//Calcula y devuelve el beneficio
        val beneficio = calculadora.calcularBeneficioItem(item)
        return ResultadoAgregarItem.Exito(item, beneficio)
    }
    
//Calcula el beneficio total de una tarea
    fun finalizarTarea(tarea: TareaDeReciclaje): Double {
        return calculadora.calcularBeneficioTotal(tarea.obtenerItems())
    }
}

