package org.example

//Interfaz que define cómo acceder al catálogo de materiales (Contratos para lógica de negocio)
interface ICatalogoMateriales {
    fun listarMateriales(): List<MaterialReciclable>
    fun buscarPorNombre(nombre: String): MaterialReciclable?
}

//Interfaz para entrada/salida por consola
interface IEntradaSalida {
    fun mostrarMensaje(mensaje: String)
    fun mostrarError(mensaje: String)
    fun solicitarTexto(prompt: String): String?
    fun solicitarNumero(prompt: String): Double?
    fun mostrarCatalogo(materiales: List<MaterialReciclable>)
    fun mostrarResumen(items: List<ItemReciclado>, beneficioTotal: Double)
}

//Interfaz que define cómo calcular beneficios
interface ICalculadoraBeneficios {
    fun calcularBeneficioItem(item: ItemReciclado): Double
    fun calcularBeneficioTotal(items: List<ItemReciclado>): Double
}

//Interfaz que define cómo validar entradas del usuario
interface IValidadorEntrada {
    fun validarPeso(peso: Double): ResultadoValidacion
    fun validarNombreMaterial(nombre: String): ResultadoValidacion
}