package org.example

//Controlador para la interfaz gráfica (Swing)
class ControladorUI(
    private val servicio: ServicioReciclaje,
    private var tarea: TareaDeReciclaje,
    private val formateador: IFormateadorUI
) : IControladorUI {
    
    private var vista: IVistaReciclaje? = null
    
    fun conectarVista(vista: IVistaReciclaje) {
        this.vista = vista
    }
    
    override fun iniciar() {

        // Controlador listo
    }
    
    override fun agregarItem() {
        val vistaActual = vista ?: return
        
        val nombreMaterial = vistaActual.obtenerMaterialSeleccionado()
        val peso = vistaActual.obtenerPeso()
        
        if (nombreMaterial == null || nombreMaterial == "-- Seleccione un material --") {
            vistaActual.mostrarMensajeError("Debe seleccionar un material")
            return
        }
        
        if (peso == null) {
            vistaActual.mostrarMensajeError("Peso inválido. Debe ingresar un número.")
            return
        }
        
        val resultado = servicio.agregarItemATarea(tarea, nombreMaterial, peso)
        
//Maneja los diferentes resultados posibles
        when (resultado) {
            is ResultadoAgregarItem.Exito -> {
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

// Reinicia la tarea (cuando se acredita el beneficio)
    fun reiniciarTarea() {
        tarea = TareaDeReciclaje()
    }
}