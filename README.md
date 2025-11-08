Paloma Juñent - Integrador Programación II

# ReSimple - Sistema de Reciclaje Inteligente

ReSimple es una aplicación desarrollada en Kotlin que calcula automáticamente cuánto dinero ganarás al reciclar diferentes materiales. Simplemente ingresá qué materiales tenés y su peso, y el sistema convierte tus residuos reciclables en beneficios económicos de forma simple y rápida.

------------------------------------------------------------------------------------------------------------------------------------

# Tabla de Contenidos

- [Requisitos Previos](#-requisitos-previos)
- [Instalación Rápida](#-instalación-rápida)
- [Ejecutar la Aplicación](#-ejecutar-la-aplicación)
- [Guía de Uso](#-guía-de-uso)
- [Materiales Aceptados](#-materiales-aceptados)
- [Solución de Problemas](#-solución-de-problemas)
- [Información Técnica](#-información-técnica)

------------------------------------------------------------------------------------------------------------------------------------

# Requisitos Previos

    - Java Development Kit (JDK) 21+

    ReSimple requiere **JDK 21 o superior**. Verificá si ya lo tenés instalado:

    java -version

    ¿No tenés Java? Descárgalo aquí:
    - Windows/macOS/Linux: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) o [Adoptium](https://adoptium.net/)


## Instrucciones de instalación según tu sistema operativo

**Windows:**

    1. Descarga el instalador JDK 21
    2. Ejecuta el instalador y sigue a el asistente
    3. Verifica con `java -version`

**macOS (con Homebrew):**

    brew install openjdk@21
    echo 'export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
    source ~/.zshrc

**Linux (Ubuntu/Debian):**

    sudo apt update
    sudo apt install openjdk-21-jdk


## ¿Y GRADLE?

    No necesitás instalar Gradle - el proyecto incluye Gradle Wrapper (9.0.0) que lo maneja automáticamente.

------------------------------------------------------------------------------------------------------------------------------------

# Instalación Rápida

- Opción 1: Clonar con Git (recomendado):
    -Abre una terminal o línea de comandos en la ubicación donde querés guardar el proyecto y copiá estos comandos: 

        git clone git clone https://github.com/etec-programacion-2/programaci-n-2-2025-ju-ent-reciclaje-palojunent.git
        cd programaci-n-2-2025-ju-ent-reciclaje-palojunent

- Opción 2: Descargar ZIP

    1. Descarga el proyecto como ZIP desde GitHub.com copiando este enlace: https://github.com/etec-programacion-2/programaci-n-2-2025-ju-ent-reciclaje-palojunent.git
    2. Ya en la página, deberías ver un botón verde donde dice "<>code"
    3. cliquea ahí, y toca en donde dice "download ZIP"
    4. Extrae el archivo en tu carpeta preferida, haciendo click derecho en el archivo ZIP, y click en "extraer todo aqui"
    5. Abre una terminal en la carpeta extraída

------------------------------------------------------------------------------------------------------------------------------------

# Ejecutar la Aplicación

**Windows**

    1. Abrí PowerShell o CMD en la carpeta del proyecto
    2. Copiá y ejecutá:

        gradlew.bat run

**macOS / Linux**

    1. Abre el terminal en la carpeta del proyecto
    2. Copiá y ejecutá:

        chmod +x gradlew
        ./gradlew run


> **Primera ejecución:** Gradle descargará dependencias (1-2 minutos). Las ejecuciones que le siguen serán instantáneas.

------------------------------------------------------------------------------------------------------------------------------------

# Guía de Uso

**Seleccionar Modo de Ejecución**

    Al iniciar verás:

    ---------------------------------
        ReSimple - RECICLAJE
    ---------------------------------

    Seleccione el modo:
    1. Consola/Terminal
    2. Interfaz Gráfica

    Opción (1 o 2):

---

# Modo Consola

**Ideal para usuarios avanzados y servidores sin GUI**

## Flujo de trabajo:

1. Visualiza el catálogo de materiales
2. Ingresa el **nombre exacto** del material
3. Especifica el **peso en kilogramos** (acepta decimales: 2.5)
4. Repite para más materiales o escribe **`FIN`** para terminar

## Ejemplo práctico:

>> Ingrese el nombre del material (o 'FIN' para terminar): Botella PET
>> Ingrese el peso en Kg de 'Botella PET': 2.5
✓ Material agregado: Botella PET - 2.5kg - Beneficio: $0.75

>> Ingrese el nombre del material (o 'FIN' para terminar): Cable cobre
>> Ingrese el peso en Kg de 'Cable cobre': 1.8
✓ Material agregado: Cable cobre - 1.8kg - Beneficio: $11.70

>> Ingrese el nombre del material (o 'FIN' para terminar): FIN

═══════════════════════════════════
RESUMEN DE LA TAREA
═══════════════════════════════════
Items procesados: 2
BENEFICIO TOTAL: $12.45

---

# Modo Interfaz Gráfica

**Ideal para usuarios que prefieren una experiencia visual**

## Características:

**Panel Izquierdo:**
- **Catálogo completo** con precios actualizados
- **Formulario simple** para agregar materiales

**Panel Derecho:**
- **Lista de items** agregados en tiempo real
- **Beneficio total** que se actualiza automáticamente

## Proceso:

1. **Agregar materiales:**
   - Selecciona del menú desplegable
   - Ingresa el peso (usa `.` para decimales: `2.5`)
   - Presiona `Agregar Material` o `Enter`

2. **Acreditar beneficio:**
   - Clic en el botón amarillo `Obtener Beneficio`
   - Completa el formulario:
     - **Nombre completo**
     - **DNI** (mínimo 7 dígitos)
     - **CBU/CVU** (22 dígitos)
   - Confirma y listo ✅

3. **Nueva operación:**
   - El programa se reinicia automáticamente
   - Puedes comenzar una nueva sesión inmediatamente

------------------------------------------------------------------------------------------------------------------------------------

# Materiales Aceptados

| Material          | Categoría | Precio/kg | Ejemplo con 10kg |
|-------------------|-----------|-----------|------------------|
| Papel periódico   | Papel     | $0.15     | $1.50            |
| Cartón corrugado  | Cartón    | $0.12     | $1.20            |
| Botella PET       | Plástico  | $0.30     | $3.00            |
| Envase yogur      | Plástico  | $0.25     | $2.50            |
| Botella vidrio    | Vidrio    | $0.08     | $0.80            |
| Lata refresco     | Aluminio  | $1.20     | $12.00           |
| Cable cobre       | Metal     | $6.50     | $65.00           |

>**Importante:** Los nombres deben ingresarse **exactamente** como aparecen en la tabla

# Validaciones:
- Peso entre `0.01` y `10,000` kg
- Decimales permitidos (usar punto: `2.5`)
- No se aceptan valores negativos o cero

------------------------------------------------------------------------------------------------------------------------------------

# Solución de Problemas

1. ## "java: command not found"

**Solución:** Java no está instalado o no está en el PATH, necesitás primero verificar la instalación copiando este comando en el terminal:

    java -version

- Luego, configurar JAVA_HOME si es necesario copia este/os comandos según tu sistema operativo:

**para Windows (Variables de entorno del sistema):**
 
    JAVA_HOME=C:\Program Files\Java\jdk-21

**para macOS/Linux (~/.bashrc o ~/.zshrc):**

    export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
    export PATH=$JAVA_HOME/bin:$PATH


2. ## "Permission denied" (Linux/Mac)

    chmod +x gradlew
    ./gradlew run


3. ## "Debe seleccionar un material"

    - Asegurate de seleccionar un material específico del catálogo de materiales dado
    - No dejes la opción por defecto "-- Seleccione un material --"


4. ## "Peso inválido"

    - Usa solo números: `5`, `2.5`, `0.8`
    - Usa **punto** (`.`) como decimal, no coma (`,`)
    - No uses letras ni caracteres especiales


5. ## "DNI inválido" / "CBU inválido"

    - **DNI:** mínimo 7 dígitos numéricos
    - **CBU/CVU:** exactamente 22 dígitos


6. ## La interfaz gráfica no aparece

    **Causa:** Sistema sin entorno gráfico (servidor, WSL, etc.)

    **Solución:** Usa el modo consola (opción 1)


7. ## Build failed

- Copia este comando en el terminal: 

    ./gradlew clean build

- Si persiste, eliminá el caché con el siguiente comando, copiandolo en el terminal:

    rm -rf ~/.gradle/caches
    ./gradlew clean build

------------------------------------------------------------------------------------------------------------------------------------

# Información Técnica

- **Lenguaje:** Kotlin 2.2.0
- **Build System:** Gradle 9.0.0
- **JDK Target:** 21
- **GUI Framework:** Java Swing
- **Testing:** JUnit 5

## Arquitectura

El proyecto sigue principios SOLID y POO, además está organizado en capas:

    📁 org.example/
    ├──  App.kt                    # Punto de entrada
    ├──  CatalogoMateriales.kt     # Singleton del catálogo
    ├──  CategoriaResiduos.kt      # Enum de categorías
    ├──  MaterialReciclable.kt     # Modelo de datos
    ├──  ItemReciclado.kt          # Item con cálculo
    ├──  TareaDeReciclaje.kt       # Sesión de reciclaje
    ├──  ServicioReciclaje.kt      # Lógica de negocio
    ├──  CalculadoraBeneficios.kt  # Motor de cálculo
    ├──  ValidadorEntrada.kt       # Validaciones
    ├──  ControladorReciclaje.kt   # Controlador consola
    ├──  ControladorUI.kt          # Controlador GUI
    ├──  VentanaReciclaje.kt       # Ventana principal
    ├──  VentanaBeneficio.kt       # Ventana acreditación
    └──  IniciadorGUI.kt           # Bootstrap GUI

## Comandos Útiles

- Compilar sin ejecutar:

    ./gradlew build

- Ejecutar tests:

    ./gradlew test

- Limpiar proyecto:

    ./gradlew clean

- Ver dependencias:

    ./gradlew dependencies

------------------------------------------------------------------------------------------------------------------------------------

[⬆ Volver arriba](#-resimple---sistema-de-reciclaje-inteligente)
