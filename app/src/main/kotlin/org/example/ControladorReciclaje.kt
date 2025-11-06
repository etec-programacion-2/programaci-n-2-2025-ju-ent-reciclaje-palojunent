package org.example

class ControladorReciclaje(
    private val servicio: ServicioReciclaje,
    private val io: IEntradaSalida
) {
    
    fun ejecutar() {
        io.mostrarCatalogo(CatalogoMateriales.listarMateriales())
        
        val tarea = servicio.iniciarNuevaTarea()
        procesarItems(tarea)
        
        val beneficioTotal = servicio.finalizarTarea(tarea)
        io.mostrarResumen(tarea.obtenerItems(), beneficioTotal)
    }
    
    private fun procesarItems(tarea: TareaDeReciclaje) {
        var terminado = false
        
        while (!terminado) {
            val nombreMaterial = io.solicitarTexto(
                ">> Ingrese el nombre del material (o 'FIN' para terminar): "
            )
            
            if (nombreMaterial == null || nombreMaterial.isBlank() || 
                nombreMaterial.uppercase() == "FIN") {
                terminado = true
                break
            }
            
            val peso = io.solicitarNumero(">> Ingrese el peso en Kg de '$nombreMaterial': ")
            
            if (peso == null) {
                io.mostrarError("Peso inválido. Debe ingresar un número.")
                continue
            }
            
            procesarItem(tarea, nombreMaterial, peso)
        }
    }
    
    private fun procesarItem(tarea: TareaDeReciclaje, nombreMaterial: String, peso: Double) {
        val resultado = servicio.agregarItemATarea(tarea, nombreMaterial, peso)
        
        when (resultado) {
            is ResultadoAgregarItem.Exito -> {
                io.mostrarMensaje(
                    "Material agregado: ${resultado.item.material.nombre} - " +
                    "${resultado.item.pesoEnKg}kg - " +
                    "Beneficio: $${String.format("%.2f", resultado.beneficio)}"
                )
            }
            is ResultadoAgregarItem.Error -> {
                io.mostrarError(resultado.mensaje)
            }
            is ResultadoAgregarItem.MaterialNoEncontrado -> {
                io.mostrarError("El material '${resultado.nombreBuscado}' no existe en el catálogo")
                io.mostrarMensaje("Materiales disponibles:")
                resultado.materialesDisponibles.forEach {
                    io.mostrarMensaje("    - ${it.nombre} (${it.categoria})")
                }
            }
        }
        
        io.mostrarMensaje("----------------------------------------------------------")
    }
}
