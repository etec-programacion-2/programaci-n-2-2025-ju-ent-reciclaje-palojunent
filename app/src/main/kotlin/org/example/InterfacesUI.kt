package org.example

// DIP: Abstracciones para la capa de presentación
interface IVistaReciclaje {
    fun mostrarMensajeExito(mensaje: String)
    fun mostrarMensajeError(mensaje: String)
    fun actualizarListaItems(items: List<String>)
    fun actualizarBeneficioTotal(beneficio: Double)
    fun limpiarFormulario()
    fun obtenerMaterialSeleccionado(): String?
    fun obtenerPeso(): Double?
}

interface IControladorUI {
    fun iniciar()
    fun agregarItem()
}