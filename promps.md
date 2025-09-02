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
 

codigo para probar el funcionamiento de la issue 3 generado con ia

fun main() {
    // Código para verificar el funcionamiento de las clases en sus archivos separados.

    // 1. Mostrar el catálogo completo de materiales disponibles
    println("=== CATÁLOGO DE MATERIALES RECICLABLES ===\n")
    println(" - Materiales disponibles para la venta -\n")
    CatalogoDeMateriales.materialesReciclables.forEach { material ->
        println("   ${material.nombre}: ${material.categoria} - $${material.precioporunidad}")
    }

    // 2. Probar la función de búsqueda por nombre
    println("\n" + "=".repeat(43) + "\n")
    println(" - Buscar material por nombre -\n")
    val materialBuscado = CatalogoDeMateriales.buscarPorNombre("Botella PET")
    if (materialBuscado != null) {
        println("   Material encontrado: ${materialBuscado.nombre}")
        println("   Categoría: ${materialBuscado.categoria}")
        println("   Precio por kg: $${materialBuscado.precioporunidad}")
    } else {
        println("   Material no encontrado")
    }

    // 3. Probar la función de filtrado por categoría
    println("\n" + "=".repeat(43) + "\n")
    println(" - Materiales por categoría -\n")
    
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

    // 4. Probar la clase ItemReciclado con un peso válido
    println("\n" + "=".repeat(62) + "\n")
    println(" - PRUEBAS DE LA CLASE ITEMRECICLADO - \n")
    
    val materialParaItem = CatalogoDeMateriales.buscarPorNombre("Botella PET")
    if (materialParaItem != null) {
        val itemValido = ItemReciclado(materialParaItem, 2.5)
        println("   ✅ Item creado correctamente: ${itemValido.material.nombre} con ${itemValido.pesoEnKg} kg")
    }

    // 5. Probar la validación de peso negativo en ItemReciclado
    try {
        val materialInvalido = CatalogoDeMateriales.buscarPorNombre("Lata refresco")
        if (materialInvalido != null) {
            val itemInvalido = ItemReciclado(materialInvalido, -1.5)
            // Esta línea no debería ejecutarse
        }
    } catch (e: IllegalArgumentException) {
        println("   ❌ Error capturado. La restricción de peso negativo funciona:")
        println("   Mensaje de error: ${e.message}")
    }

    // 6. Calcular el valor total de una colección de items reciclados
    println("\n" + "=".repeat(62) + "\n")
    println(" - Cálculo del valor total de ítems - \n")

    val itemsDePrueba = listOf(
        ItemReciclado(CatalogoDeMateriales.buscarPorNombre("Botella PET")!!, 10.0),
        ItemReciclado(CatalogoDeMateriales.buscarPorNombre("Lata refresco")!!, 5.0),
        ItemReciclado(CatalogoDeMateriales.buscarPorNombre("Papel periódico")!!, 20.0)
    )

    var totalValor = 0.0
    itemsDePrueba.forEach { item ->
        val valor = item.material.precioporunidad * item.pesoEnKg
        totalValor += valor
        println("   ${item.pesoEnKg} kg de ${item.material.nombre} = $${String.format("%.2f", valor)}")
    }
    
    println("\n   TOTAL: $${String.format("%.2f", totalValor)}")
    println("\n" + "=".repeat(62) + "\n")
}