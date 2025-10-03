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
========================================================

ISSUE 4 
codigo generado x ia para comprobar el funcionamiento de las modificaciones del archivo de ItemReciclable (sin los testeos unitarios)

fun main (){
    println("=== SISTEMA DE RECICLAJE ===\n")
    
    // 1. Crear algunos items
    val cobre = CatalogoDeMateriales.buscarPorNombre("Cable cobre")!!
    val item1 = ItemReciclado(cobre, 2.0)
    
    val lata = CatalogoDeMateriales.buscarPorNombre("Lata refresco")!!
    val item2 = ItemReciclado(lata, 3.0)
    
    // 2. Mostrar beneficios
    println("Item 1: ${item1.material.nombre}")
    println("Peso: ${item1.pesoEnKg} kg")
    println("Beneficio: $${item1.calcularBeneficio()}")
    
    println()
    
    println("Item 2: ${item2.material.nombre}")
    println("Peso: ${item2.pesoEnKg} kg") 
    println("Beneficio: $${item2.calcularBeneficio()}")
    
    println()
    
    // 3. Total
    val total = item1.calcularBeneficio() + item2.calcularBeneficio()
    println("Total: $$total")
    
    println("\n=== PRUEBA DE VALIDACIÓN ===")
    
    // 4. Probar peso negativo
    try {
        ItemReciclado(cobre, -1.0)
    } catch (e: IllegalArgumentException) {
        println("✓ Error capturado : ${e.message}")
    }
}

(Utilicé las librerías JUnit 5 en el archivo de ItemReciclableTest.Kt para poder crear los tests unitarios, pero en un archivo separado para más organización y para mejor funcionamiento ) 
12-09-25
(saqué el archivo ItemReciclableTest.Kt, no hace falta hacerlo.)

Es bueno que la clase ItemReciclado se encargue de este cálculo ya que la clase tiene la información y valores del peso y del material para poder realizar el cálculo 

(CODIGO FINAL PARA PROBAR EL FUNCIONAMIENTO DEL MÉTODO EN LA CLASE ITEMRECICLADO)

fun main (){
    println("=== SISTEMA DE RECICLAJE ===\n")
    
    // 1. Crear algunos items
    val cobre = CatalogoDeMateriales.buscarPorNombre("Cable cobre")!!
    val item1 = ItemReciclado(cobre, -1.0)
    
    val lata = CatalogoDeMateriales.buscarPorNombre("Lata refresco")!!
    val item2 = ItemReciclado(lata, 3.0)
    
    // 2. Mostrar beneficios
    println("Item 1: ${item1.material.nombre}")
    println("Peso: ${item1.pesoEnKg} kg")
    println("Beneficio: $${item1.calcularBeneficio()}")
    
    println()
    
    println("Item 2: ${item2.material.nombre}")
    println("Peso: ${item2.pesoEnKg} kg") 
    println("Beneficio: $${item2.calcularBeneficio()}")
    
    println()
    
    // 3. Total
    val total = item1.calcularBeneficio() + item2.calcularBeneficio()
    println("Total: $$total")
    
}

==========================================

ISSUE 5 
codigo para probar el funcionamiento de tarea de reciclaje generado x IA
fun main (){
   println("=== SISTEMA DE RECICLAJE - PRUEBA DE FUNCIONAMIENTO ===\n")
    
    // Crear una nueva tarea de reciclaje
    val tareaReciclaje = TareaDeReciclaje()
    
    // Obtener algunos materiales del catálogo
    val papelPeriodico = CatalogoDeMateriales.buscarPorNombre("Papel periódico")
    val botellaPlastico = CatalogoDeMateriales.buscarPorNombre("Botella PET")
    val lataAluminio = CatalogoDeMateriales.buscarPorNombre("Lata refresco")
    val cableCobre = CatalogoDeMateriales.buscarPorNombre("Cable cobre")
    val carton = CatalogoDeMateriales.buscarPorNombre("Cartón corrugado")
    
    println("1. MATERIALES DISPONIBLES EN EL CATÁLOGO:")
    CatalogoDeMateriales.materialesReciclables.forEach { material ->
        println("   • ${material.nombre} (${material.categoria}) - $${material.precioporunidad}/kg")
    }
    
    println("\n2. AGREGANDO ITEMS A LA TAREA DE RECICLAJE:")
    
    // Crear y agregar items reciclados
    try {
        papelPeriodico?.let { material ->
            val item1 = ItemReciclado(material, 5.0)
            tareaReciclaje.agregarItem(item1)
            println("   ✓ Agregado: ${material.nombre} - ${item1.pesoEnKg}kg - Beneficio: $${item1.calcularBeneficio()}")
        }
        
        botellaPlastico?.let { material ->
            val item2 = ItemReciclado(material, 2.5)
            tareaReciclaje.agregarItem(item2)
            println("   ✓ Agregado: ${material.nombre} - ${item2.pesoEnKg}kg - Beneficio: $${item2.calcularBeneficio()}")
        }
        
        lataAluminio?.let { material ->
            val item3 = ItemReciclado(material, 1.2)
            tareaReciclaje.agregarItem(item3)
            println("   ✓ Agregado: ${material.nombre} - ${item3.pesoEnKg}kg - Beneficio: $${item3.calcularBeneficio()}")
        }
        
        cableCobre?.let { material ->
            val item4 = ItemReciclado(material, 0.8)
            tareaReciclaje.agregarItem(item4)
            println("   ✓ Agregado: ${material.nombre} - ${item4.pesoEnKg}kg - Beneficio: $${item4.calcularBeneficio()}")
        }
        
        carton?.let { material ->
            val item5 = ItemReciclado(material, 3.2)
            tareaReciclaje.agregarItem(item5)
            println("   ✓ Agregado: ${material.nombre} - ${item5.pesoEnKg}kg - Beneficio: $${item5.calcularBeneficio()}")
        }
        
    } catch (e: IllegalArgumentException) {
        println("   ❌ Error: ${e.message}")
    }
    
    println("\n3. RESUMEN DE LA TAREA DE RECICLAJE:")
    println("   Total de items: ${tareaReciclaje.items.size}")
    println("   Beneficio total: $${String.format("%.2f", tareaReciclaje.calcularBeneficioTotal())}")
    
    println("\n4. DETALLE POR ITEM:")
    tareaReciclaje.items.forEachIndexed { index, item ->
        println("   ${index + 1}. ${item.material.nombre}")
        println("      Categoría: ${item.material.categoria}")
        println("      Peso: ${item.pesoEnKg}kg")
        println("      Precio por kg: $${item.material.precioporunidad}")
        println("      Beneficio: $${String.format("%.2f", item.calcularBeneficio())}")
        println()
    }
    
    // Prueba de búsqueda por categoría
    println("5. MATERIALES POR CATEGORÍA:")
    CategoriaResiduos.values().forEach { categoria ->
        val materiales = CatalogoDeMateriales.materialesPorCategoria(categoria)
        if (materiales.isNotEmpty()) {
            println("   ${categoria}:")
            materiales.forEach { material ->
                println("     • ${material.nombre} - $${material.precioporunidad}/kg")
            }
        }
    }
    
    // Prueba de manejo de errores
    println("\n6. PRUEBA DE VALIDACIÓN (peso negativo):")
    try {
        papelPeriodico?.let { material ->
            val itemInvalido = ItemReciclado(material, -2.0)
            tareaReciclaje.agregarItem(itemInvalido)
        }
    } catch (e: IllegalArgumentException) {
        println("   ❌ Error capturado correctamente: ${e.message}")
    }
    
    println("\n=== FIN DE LAS PRUEBAS ===")
     }

================================
ISSUE 6 

SINGLETON: 
Patrón de diseño que se utiliza para garantizar que una clase tenga una única instancia y brindar un punto de acceso global para esta. Se utiliza la palabra object porque además de ser la manera más fácil para crear singletons, declara un objecto único, haciendo que ese object ya esté instanciado, y el código tenga menos errores. 

(MODIFICACIONES)

en el archivo de MaterialReciclable, quité el object que había creado con la lista y lo puse en el archivo nuevo de CatalogoMateriales, así el código está más organizado, dejando solamente en MaterialReciclable la data class definida. 


CODIGO GENERADOR POR IA PARA PROBAR EL FUNCIONAMIENTO DEL CODIGO 

fun main {
    println("--- Iniciando Pruebas de Funcionamiento del Sistema de Reciclaje ---")
    println(" ")

    // 1. Prueba del Singleton CatalogoMateriales
    println("### 1. Pruebas de Catálogo de Materiales (Singleton) ###")
    
    // Listar todos los materiales
    val listaCompleta = CatalogoMateriales.listarMateriales()
    println("Total de Materiales en Catálogo: ${listaCompleta.size}")

    // Prueba explícita del Singleton: Ambas referencias deben ser la misma instancia
    val managerA = CatalogoMateriales
    val managerB = CatalogoMateriales
    println("Comprobando Singleton: ¿managerA es el mismo objeto que managerB? ${managerA === managerB}") // Debería ser 'true'

    // Buscar por nombre
    val materialCobre = CatalogoMateriales.buscarPorNombre("Cable cobre")
    val materialNoExiste = CatalogoMateriales.buscarPorNombre("Plástico burbuja")
    println("Búsqueda 'Cable cobre': ${materialCobre?.nombre} (Precio: $${materialCobre?.precioPorUnidad}/kg)")
    println("Búsqueda 'Plástico burbuja': ${materialNoExiste?.nombre ?: "No encontrado"}")

    // Filtrar por categoría
    val plasticos = CatalogoMateriales.materialesPorCategoria(CategoriaResiduos.PLASTICO)
    println("Materiales en categoría PLASTICO (${plasticos.size}):")
    plasticos.forEach {
        println("  -> ${it.nombre} ($${it.precioPorUnidad}/kg)")
    }

    println("\n" + "-".repeat(50) + "\n")


    // 2. Creación de Tarea de Reciclaje y Cálculo de Beneficio
    println("### 2. Pruebas de TareaDeReciclaje y Beneficio ###")

    // Obtener materiales para la tarea
    val papel = CatalogoMateriales.buscarPorNombre("Papel periódico")!! // Asumimos que existe
    val lata = CatalogoMateriales.buscarPorNombre("Lata refresco")!!   // Asumimos que existe

    // Crear la tarea
    val miTarea = TareaDeReciclaje()
    
    // Agregar items
    val itemPapel = ItemReciclado(papel, 5.5) // 5.5 kg de papel
    val itemLata = ItemReciclado(lata, 2.0)   // 2.0 kg de latas
    val itemPapelExtra = ItemReciclado(papel, 10.0) // 10.0 kg más de papel

    miTarea.agregarItem(itemPapel)
    miTarea.agregarItem(itemLata)
    miTarea.agregarItem(itemPapelExtra)

    println("Items agregados a la tarea:")
    miTarea.items.forEach { 
        println("- ${it.material.nombre} | Peso: ${it.pesoEnKg} kg | Beneficio: $${String.format("%.2f", it.calcularBeneficio())}")
    }
    
    // Calcular beneficio total
    val beneficioFinal = miTarea.calcularBeneficioTotal()
    println("\nBENEFICIO TOTAL DE LA TAREA: $${String.format("%.2f", beneficioFinal)}")
    
    // Cálculo manual para verificar:
    // (5.5 * 0.15) + (2.0 * 1.20) + (10.0 * 0.15) = 0.825 + 2.40 + 1.50 = 4.725
    println("Verificación de cálculo: $4.725")

    println("\n" + "-".repeat(50) + "\n")
    
    // 3. Prueba de Requisito de ItemReciclado (Peso Negativo)
    println("### 3. Prueba de Excepción de Peso Negativo ###")
    try {
        ItemReciclado(papel, -1.0)
    } catch (e: IllegalArgumentException) {
        println("Excepción capturada con éxito: ${e.message}")
    }

    println("\n--- Pruebas Finalizadas ---")
}
