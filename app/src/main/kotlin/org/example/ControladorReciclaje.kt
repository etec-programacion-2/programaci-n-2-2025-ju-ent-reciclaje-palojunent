package org.example

//Controlador por si el programa se ejecuta en consola (Controlador para versión consola)
class ControladorReciclaje(
    private val servicio: ServicioReciclaje,
    private val io: IEntradaSalida  // Interfaz para entrada/salida por consola
) {
    
//Ejecuta el flujo completo del programa por consola
    fun ejecutar() {

//Muestra el catálogo de materiales disponibles
        io.mostrarCatalogo(CatalogoMateriales.listarMateriales())
        
//Inicia una nueva tarea de reciclaje
        val tarea = servicio.iniciarNuevaTarea()
        
//Loop que pide items al usuario
        procesarItems(tarea)
        
//Calcula el beneficio total y muestra el resumen
        val beneficioTotal = servicio.finalizarTarea(tarea)
        io.mostrarResumen(tarea.obtenerItems(), beneficioTotal)
    }
    
    private fun procesarItems(tarea: TareaDeReciclaje) {
        var terminado = false

//Loop que pide items al usuario hasta que escriba "FIN"        
        while (!terminado) {

//Solicita el nombre del material al usuario
            val nombreMaterial = io.solicitarTexto(
                ">> Ingrese el nombre del material (o 'FIN' para terminar): "
            )
            
//Si escribe FIN, presiona Enter vacío, o es null, termina el loop
            if (nombreMaterial == null || nombreMaterial.isBlank() || 
                nombreMaterial.uppercase() == "FIN") {
                terminado = true
                break
            }
            
//Solicita el peso del material
            val peso = io.solicitarNumero(">> Ingrese el peso en Kg de '$nombreMaterial': ")
            
//Si el peso no es válido, muestra error y vuelve a pedir
            if (peso == null) {
                io.mostrarError("Peso inválido. Debe ingresar un número.")
                continue
            }
            
//Intenta procesar el item
            procesarItem(tarea, nombreMaterial, peso)
        }
    }
    
//Método que intenta agregar un item y muestra el resultado según el caso
    private fun procesarItem(tarea: TareaDeReciclaje, nombreMaterial: String, peso: Double) {
        val resultado = servicio.agregarItemATarea(tarea, nombreMaterial, peso)
        
// When que maneja cada posible resultado
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
