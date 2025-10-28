package org.example

fun main() {
    //dependencias
    val catalogoMateriales = CatalogoMateriales
    val entradaSalida = EntradaSalidaConsola()
    val calculadoraBeneficios = CalculadoraBeneficios()
    val validador = ValidadorEntrada()
    
    val servicio = ServicioReciclaje(catalogoMateriales,calculadoraBeneficios,validador)
    
    val controlador = ControladorReciclaje(servicio, entradaSalida)
    controlador.ejecutar()
}
