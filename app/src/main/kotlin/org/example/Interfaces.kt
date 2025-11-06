package org.example

interface ICatalogoMateriales {
    fun listarMateriales(): List<MaterialReciclable>
    fun buscarPorNombre(nombre: String): MaterialReciclable?
}

interface IEntradaSalida {
    fun mostrarMensaje(mensaje: String)
    fun mostrarError(mensaje: String)
    fun solicitarTexto(prompt: String): String?
    fun solicitarNumero(prompt: String): Double?
    fun mostrarCatalogo(materiales: List<MaterialReciclable>)
    fun mostrarResumen(items: List<ItemReciclado>, beneficioTotal: Double)
}

interface ICalculadoraBeneficios {
    fun calcularBeneficioItem(item: ItemReciclado): Double
    fun calcularBeneficioTotal(items: List<ItemReciclado>): Double
}

interface IValidadorEntrada {
    fun validarPeso(peso: Double): ResultadoValidacion
    fun validarNombreMaterial(nombre: String): ResultadoValidacion
}
