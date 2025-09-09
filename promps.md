(PROMPS Y DOCUMENTACION DEL TRABAJO)

 - ISSUE 1: -

    Enum Class: 

    clase especial que representa un conjunto fijo de constantes relacionadas/categorías.
    Puede representar estados, opciones de configuración, tipos de datos categóricos, o cualquier conjunto fijo de valores relacionados de manera type-safe.

    Explicaciones de las categorias con enum:

    Con enum, el compilador garantiza que solo puedes usar valores válidos.
    Autocompletado y refactoring
    El IDE puede sugerir automáticamente los valores válidos del enum y hacer refactoring seguro cuando cambies nombres.
    Se comparan por referencia (más rápido)
    Ocupan menos memoria
    No requieren validación en tiempo de ejecución
    
============================================================================================
 - ISSUE 2: -

    “MaterialReciclable” actúa como una plantilla o modelo de datos que define la estructura común para todos los tipos de materiales reciclables en el codigo.

    Al usar val en el constructor primario de una data class, todas las propiedades son inmutables, y sirve para representar datos que no tienen que cambiar una vez creados.

    Diferencias entre una class y una data class: 

    La data class genera automáticamente, usando una class normal, hay que escribir todo manualmente. 
    Es un contenedor de datos inmutables, en este caso actua como una plantilla o un modelo de datos que define la estructura común para todos los tipos de materiales reciclables en el sistema.
    Todos los materiales tienen exactamente la misma estructura
    Facilita operaciones del sistema
    Proporciona toda la funcionalidad necesaria (comparación, copia, representación) sin código adicional.
    Solo pueden heredar de interfaces (no de otras clases)

    Una class es la forma estándar de crear objetos. Hay que implementar manualmente todos los metodos necesarios  
    Tiene principalmente comportamiento, no datos y permite controlar ese comportamiento 
    Se usa cuando se necesita logica completa en una clase 

============================================================================================

- (CODIGO PARA COMPROBAR EL FUNCIONAMIENTO DE LA ISSUE 1 Y LA ISSUE 2) - 

            //catalogo de materiales(materiales disponibles)
        println("=== CATÁLOGO DE MATERIALES RECICLABLES ===\n")
        println(" - Materiales disponibles para la venta -\n")
        CatalogoDeMateriales.materialesReciclables.forEach { material ->
                println("   ${material.nombre}: ${material.categoria} - $${material.precioporunidad}")
        }

        println("\n" + "=".repeat(43) + "\n")
            //busca los materiales por nombre 
            println(" - Buscar material por nombre -\n")
            val materialBuscado = CatalogoDeMateriales.buscarPorNombre("Botella PET")
            if (materialBuscado != null) {
                println("   Material: ${materialBuscado.nombre}")
                println("   Categoría: ${materialBuscado.categoria}")
                println("   Precio por kg: $${materialBuscado.precioporunidad}")
            } else {
                println("   Material no encontrado")
            }
            //divide los materiales por categoria
            println("\n" + "=".repeat(43) + "\n")
            println (" - Materiales por categoría -\n")
            

            println("  PLÁSTICOS: ")
            val plasticos = CatalogoDeMateriales.materialesPorCategoria(CategoriaResiduos.PLASTICO)
            plasticos.forEach { 
                println("     - ${it.nombre}: $${it.precioporunidad}")
            }
            
            println(" \n  METALES:")
            val metales = CatalogoDeMateriales.materialesPorCategoria(CategoriaResiduos.METAL)
            metales.forEach { 
                println("     - ${it.nombre}: $${it.precioporunidad}")
            }

            println("   \n  ALUMINIO:")
            val aluminio = CatalogoDeMateriales.materialesPorCategoria(CategoriaResiduos.ALUMINIO)
            aluminio.forEach { 
                println("     - ${it.nombre}: $${it.precioporunidad}")
            }
            println("\n" + "=".repeat(62) + "\n")

            //Cálculo de valor de materiales según la cantidad ofrecida
            println(" - Valor de materiales según la cantidad ofrecida - \n")   

            val cantidades = mapOf(
                "Botella PET" to 10,
                "Lata refresco" to 5,
                "Papel periódico" to 20
            )
            
            var totalValor = 0.0
            cantidades.forEach { (nombre, cantidad) ->
                val material = CatalogoDeMateriales.buscarPorNombre(nombre)
                if (material != null) {
                    val valor = material.precioporunidad * cantidad
                    totalValor += valor
                    println("   $cantidad x ${material.nombre} = $${String.format("%.2f", valor)}")
                }
            }
            println("\n   TOTAL: $${String.format("%.2f", totalValor)}")
            
            println("\n" + "=".repeat(62) + "\n")
            println("- Total de plata obtenida por reciclar:  $${String.format("%.2f", totalValor)} - ")

===========================================================

ISSUE 3

la clase ItemReciclable representa un item reciclado entregado por un usuario y modela una entrega específica de material reciclable, combinando el tipo de material con la cantidad entregada. Devuelve IllegalArgumentException si el peso es negativo.
 

codigo para probar el funcionamiento de la issue 3 generado con ia : 

fun main() {
    println("=== PRUEBA DEL SISTEMA DE RECICLAJE ===\n")
    
    // 1. Probar el catálogo de materiales
    println("1. Catálogo de materiales disponibles:")
    CatalogoDeMateriales.materialesReciclables.forEach { material ->
        println("   ${material.nombre} (${material.categoria}) - $${material.precioporunidad}/kg")
    }
    
    println("\n" + "=".repeat(60) + "\n")
    
    // 2. Probar búsqueda de materiales
    println("2. Búsqueda de materiales:")
    val botellaPet = CatalogoDeMateriales.buscarPorNombre("Botella PET")
    if (botellaPet != null) {
        println("   ✓ Encontrado: ${botellaPet.nombre} - $${botellaPet.precioporunidad}/kg")
    }
    
    val noExiste = CatalogoDeMateriales.buscarPorNombre("Papel higiénico")
    println("   ${if (noExiste != null) "✓" else "✗"} Papel higiénico: ${if (noExiste != null) "Encontrado" else "No encontrado"}")
    
    println("\n" + "=".repeat(60) + "\n")
    
    // 3. Probar ItemReciclado - Casos válidos
    println("3. Creando items reciclados (casos válidos):")
    
    try {
        val cableCobre = CatalogoDeMateriales.buscarPorNombre("Cable cobre")!!
        val item1 = ItemReciclado(cableCobre, 2.5)
        println("   ✓ Item creado: ${item1.material.nombre} - ${item1.pesoEnKg}kg")
        println("     Valor total: $${item1.material.precioporunidad * item1.pesoEnKg}")
        
        val lataRefresco = CatalogoDeMateriales.buscarPorNombre("Lata refresco")!!
        val item2 = ItemReciclado(lataRefresco, 0.8)
        println("   ✓ Item creado: ${item2.material.nombre} - ${item2.pesoEnKg}kg")
        println("     Valor total: $${item2.material.precioporunidad * item2.pesoEnKg}")
        
        // Caso límite: peso 0
        val item3 = ItemReciclado(botellaPet!!, 0.0)
        println("   ✓ Item con peso 0: ${item3.material.nombre} - ${item3.pesoEnKg}kg (válido)")
        
    } catch (e: IllegalArgumentException) {
        println("   ✗ Error inesperado: ${e.message}")
    }
    
    println("\n" + "=".repeat(60) + "\n")
    
    // 4. Probar ItemReciclado - Casos inválidos
    println("4. Probando validación de peso negativo:")
    
    try {
        val papel = CatalogoDeMateriales.buscarPorNombre("Papel periódico")!!
        val itemInvalido = ItemReciclado(papel, -1.5)
        println("   ✗ ERROR: Se creó un item con peso negativo (no debería pasar)")
    } catch (e: IllegalArgumentException) {
        println("   ✓ Validación funcionando: ${e.message}")
    }
    
    try {
        val carton = CatalogoDeMateriales.buscarPorNombre("Cartón corrugado")!!
        val itemInvalido2 = ItemReciclado(carton, -0.1)
        println("   ✗ ERROR: Se creó un item con peso negativo (no debería pasar)")
    } catch (e: IllegalArgumentException) {
        println("   ✓ Validación funcionando: ${e.message}")
    }
    
    println("\n" + "=".repeat(60) + "\n")
    
    // 5. Ejemplo práctico: Simulación de entrega de reciclaje
    println("5. Simulación de entrega de reciclaje:")
    
    val entregaReciclaje = listOf(
        ItemReciclado(CatalogoDeMateriales.buscarPorNombre("Botella PET")!!, 3.2),
        ItemReciclado(CatalogoDeMateriales.buscarPorNombre("Cable cobre")!!, 1.8),
        ItemReciclado(CatalogoDeMateriales.buscarPorNombre("Lata refresco")!!, 2.1),
        ItemReciclado(CatalogoDeMateriales.buscarPorNombre("Botella vidrio")!!, 5.5)
    )
    
    var valorTotal = 0.0
    println("   Detalle de la entrega:")
    entregaReciclaje.forEach { item ->
        val valorItem = item.material.precioporunidad * item.pesoEnKg
        valorTotal += valorItem
        println("   - ${item.material.nombre}: ${item.pesoEnKg}kg × $${item.material.precioporunidad} = $${String.format("%.2f", valorItem)}")
    }
    
    println("\n   💰 TOTAL A PAGAR: $${String.format("%.2f", valorTotal)}")
    
    println("\n" + "=".repeat(60) + "\n")
    
    // 6. Resumen de materiales por categoría
    println("6. Resumen por categorías:")
    CategoriaResiduos.values().forEach { categoria ->
        val materiales = CatalogoDeMateriales.materialesPorCategoria(categoria)
        if (materiales.isNotEmpty()) {
            println("   ${categoria}: ${materiales.size} material(es)")
            materiales.forEach { material ->
                println("     • ${material.nombre} - $${material.precioporunidad}/kg")
            }
        }
    }
    
    println("\n=== PRUEBAS COMPLETADAS ===")
}

(MODIFICACIONES 09-09-25:
Agregué un package org.example en cada archivo .kt para que la estructura de carpetas coincida con la del paquete y así poder verificar el funcionamiento del código)
