package org.example

//Controlador que hace de intermediario entre la vista y el servicio, usando el patrón MVC (Model-View-Controller) (Conecta vista con lógica)
class ControladorUI(
    private val servicio: ServicioReciclaje,
    private var tarea: TareaDeReciclaje,
    private val formateador: IFormateadorUI
) : IControladorUI {
    
    private var vista: IVistaReciclaje? = null
    
//Método que conecta la ventana con el controlador
    fun conectarVista(vista: IVistaReciclaje) {
        this.vista = vista
    }

//Método para inicialización futura si hace falta   
    override fun iniciar() {
        
    }
    
//Método que maneja el evento de agregar un item
    override fun agregarItem() {
        val vistaActual = vista ?: return
        
//Obtiene los datos de la vista
        val nombreMaterial = vistaActual.obtenerMaterialSeleccionado()
        val peso = vistaActual.obtenerPeso()
        
//Ifs de validaciones básicas
        if (nombreMaterial == null || nombreMaterial == "-- Seleccione un material --") {
            vistaActual.mostrarMensajeError("Debe seleccionar un material")
            return
        }
        
        if (peso == null) {
            vistaActual.mostrarMensajeError("Peso inválido. Debe ingresar un número.")
            return
        }
        
//Intenta agregar el item usando el servicio
        val resultado = servicio.agregarItemATarea(tarea, nombreMaterial, peso)
        
//When que maneja el resultado con pattern matching
        when (resultado) {
            is ResultadoAgregarItem.Exito -> {
                // Actualiza la vista con los nuevos datos
                vistaActual.actualizarListaItems(formateador.formatearListaItems(tarea.obtenerItems()))
                vistaActual.actualizarBeneficioTotal(servicio.finalizarTarea(tarea))
                vistaActual.limpiarFormulario()
                vistaActual.mostrarMensajeExito(
                    formateador.formatearMensajeExito(resultado.item, resultado.beneficio)
                )
            }
            is ResultadoAgregarItem.Error -> {
                vistaActual.mostrarMensajeError(resultado.mensaje)
            }
            is ResultadoAgregarItem.MaterialNoEncontrado -> {
                vistaActual.mostrarMensajeError("El material '${resultado.nombreBuscado}' no existe en el catálogo")
            }
        }
    }
    
//Método que reinicia la tarea para una nueva sesión
    fun reiniciarTarea() {
        tarea = TareaDeReciclaje()
    }
}