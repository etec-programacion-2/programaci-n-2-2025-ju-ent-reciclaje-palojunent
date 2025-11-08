package org.example

//Interfaz que define qué puede hacer la ventana (Contratos para la interfaz)
interface IVistaReciclaje {
    fun mostrarMensajeExito(mensaje: String)
    fun mostrarMensajeError(mensaje: String)
    fun actualizarListaItems(items: List<String>)
    fun actualizarBeneficioTotal(beneficio: Double)
    fun limpiarFormulario()
    fun obtenerMaterialSeleccionado(): String?
    fun obtenerPeso(): Double?
}

//Interfaz que define las acciones del controlador
interface IControladorUI {
    fun iniciar()
    fun agregarItem()
}
