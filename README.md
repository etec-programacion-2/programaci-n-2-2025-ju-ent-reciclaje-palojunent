# Paloma Juñent - Integrador Programación II

## ReSimple - Sistema de Reciclaje Inteligente

ReSimple es una aplicación desarrollada en Kotlin que calcula automáticamente cuánto dinero ganarás al reciclar diferentes materiales. Simplemente ingresá qué materiales tenés y su peso, y el sistema convierte tus residuos reciclables en beneficios económicos de forma simple y rápida.

------------------------------------------------------------------------------------------------------------------------------------

# Información Técnica

- **Lenguaje:** Kotlin 2.2.0
- **Build System:** Gradle 9.0.0
- **JDK Target:** 21
- **GUI Framework:** Java Swing
- **Testing:** JUnit 5
  
------------------------------------------------------------------------------------------------------------------------------------

# Requisitos Previos

- Java Development Kit (JDK) 21+

ReSimple requiere **JDK 21 o superior**. Verificá si ya lo tenés instalado:

    java -version

¿No tenés Java? Descárgalo aquí:
- Windows/macOS/Linux: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) o [Adoptium](https://adoptium.net/)

  
## Instrucciones de instalación según tu sistema operativo

**Windows (utilizando alguno de los links dados anteriormente):**

1. Descarga el instalador JDK 21 en Oracle JDK Downloads
2. Ejecuta el instalador y sigue a el asistente
3. Verifica con `java -version` en la terminal

**Linux (Ubuntu/Debian):**

1. En el terminal (win + T), actualiza repositorios:

        sudo apt update
    
2. Instala OpenJDK 21:

        sudo apt install openjdk-21-jdk

3. Verifica la instalación:

       java -version

**macOS (con Homebrew):**

1. Instala Homebrew (si no lo tienes):
2. Abre Terminal (Cmd + Espacio → escribe "Terminal")
3. Pega este comando:

        /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

4. Presiona Enter y sigue las instrucciones
5. Instala OpenJDK 21:

           brew install openjdk@21

6. Configurar el PATH:

    - **Para macOS con chip Intel**:

            bash   echo 'export PATH="/usr/local/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
            source ~/.zshrc

    - **Para macOS con chip Apple Silicon (M1/M2/M3)**:

            bash   echo 'export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
            source ~/.zshrc

7. Crea un enlace simbólico:

        sudo ln -sfn /opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-21.jdk

8. Verifica la instalación:

        java -version

## ¿Y GRADLE?

El proyecto incluye Gradle Wrapper (9.0.0) que lo maneja automáticamente.

------------------------------------------------------------------------------------------------------------------------------------

# Instalación

- **Opción 1: Clonar con Git (recomendado)**:
  
    -Abre una terminal o línea de comandos en la ubicación donde querés guardar el proyecto y copiá estos comandos: 

        git clone https://github.com/etec-programacion-2/programaci-n-2-2025-ju-ent-reciclaje-palojunent.git
        cd programaci-n-2-2025-ju-ent-reciclaje-palojunent

- **Opción 2: Descargar ZIP**

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


> **Primera ejecución:** Gradle va a descargar dependencias (1-2 minutos). Las ejecuciones que le siguen serán instantáneas.

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

## Funcionamiento:

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
   - Confirma y listo 

3. **Nueva operación:**
   - El programa se reinicia automáticamente
   - Puedes comenzar una nueva sesión inmediatamente

------------------------------------------------------------------------------------------------------------------------------------

# Materiales Aceptados Dentro del Catálogo

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

**Copia estos comandos en la terminal:**

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

**Copia este comando en el terminal:**

    ./gradlew clean build

**Si persiste, eliminá el caché con el siguiente comando, copiandolo en el terminal:**

    rm -rf ~/.gradle/caches
    ./gradlew clean build

------------------------------------------------------------------------------------------------------------------------------------

# Arquitectura

El proyecto sigue principios SOLID y POO, además está organizado en capas:

    org.example/
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
    
---

## Comandos Útiles

**Compilar sin ejecutar:**

    ./gradlew build

**Ejecutar tests:**

    ./gradlew test

**Limpiar proyecto:**

    ./gradlew clean

**Ver dependencias:**

    ./gradlew dependencies

------------------------------------------------------------------------------------------------------------------------------------
