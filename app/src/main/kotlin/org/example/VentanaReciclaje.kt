package org.example

import javax.swing.*
import java.awt.*

//Ventana principal donde el usuario agrega materiales reciclados hacha con JFrame, que es la clase base de Swing para ventanas principales(Ventana principal del programa)
class VentanaReciclaje(
    private val catalogo: ICatalogoMateriales,
    private val formateador: IFormateadorUI,
    private val controlador: ControladorUI
) : JFrame("ReSimple"), IVistaReciclaje {
    
//===== Componentes de la interfaz gráfica =====
    private val listModel = DefaultListModel<String>() //Modelo para la lista de items
    private val comboMateriales = JComboBox<String>() //Dropdown de materiales
    private val txtPeso = JTextField(10) //Campo de texto para el peso
    private val btnAgregar = JButton("Agregar Material") //Botón para agregar un material
    private val btnObtenerBeneficio = JButton("Obtener Beneficio") //Botón para acreditar
    private val listItems = JList(listModel) //Lista visual de items
    private val lblBeneficioTotal = JLabel("Beneficio Total: $0.00")
    private val lblMensaje = JLabel(" ") //Label para mensajes temporales
    private var beneficioActual = 0.0 //Guarda el beneficio acumulado
    
//Bloque init que se ejecuta al crear la ventana
    init {
        configurarVentana()
        construirUI()
        cargarMateriales()
        configurarEventos()
    }
    
//===== Implementación de IVistaReciclaje =====
    
//Método que muestra un mensaje de éxito por 3 segundos
    override fun mostrarMensajeExito(mensaje: String) {
        lblMensaje.text = "✅ $mensaje"
        lblMensaje.foreground = Color(46, 125, 50)
//Timer que borra el mensaje después de 3 segundos
        Timer(3000) { lblMensaje.text = " " }.apply {
            isRepeats = false //Solo se ejecuta una vez
            start()
        }
    }
    
//Método que muestra un mensaje de error por 3 segundos
    override fun mostrarMensajeError(mensaje: String) {
        lblMensaje.text = "❌ Error: $mensaje"
        lblMensaje.foreground = Color.RED
        Timer(3000) { lblMensaje.text = " " }.apply { 
            isRepeats = false
            start()
        }
    }
    
//Actualiza la lista visual de items procesados
    override fun actualizarListaItems(items: List<String>) {
        listModel.clear() //Borra todo
        items.forEach { listModel.addElement(it) } //Agrega cada item
    }
    
//Método que actualiza el label del beneficio total
    override fun actualizarBeneficioTotal(beneficio: Double) {
        beneficioActual = beneficio
        lblBeneficioTotal.text = "Beneficio Total: $${String.format("%.2f", beneficio)}"
        btnObtenerBeneficio.isEnabled = beneficio > 0 //Habilita botón solo si hay beneficio
    }
    
//Limpia los campos del formulario
    override fun limpiarFormulario() {
        txtPeso.text = ""
        comboMateriales.selectedIndex = 0 //Vuelve a "Seleccione un material"
    }
    
//Obtiene el material seleccionado del dropdown
    override fun obtenerMaterialSeleccionado(): String? {
        return comboMateriales.selectedItem as? String
    }
    
//Obtiene el peso ingresado y lo convierte a Double (o null si no es válido)
    override fun obtenerPeso(): Double? {
        return txtPeso.text.toDoubleOrNull()
    }
    
// ===== Métodos privados de construcción de UI =====
    
//Método que hace la configuración inicial de la ventana
    private fun configurarVentana() {
        defaultCloseOperation = EXIT_ON_CLOSE //Cierra el programa al cerrar ventana
        setSize(900, 600) 
        setLocationRelativeTo(null)  
        layout = BorderLayout(10, 10)
    }
    
//Método que construye toda la interfaz llamando a métodos auxiliares
    private fun construirUI() {
        add(crearPanelTitulo(), BorderLayout.NORTH)      
        add(crearPanelIzquierdo(), BorderLayout.WEST)    
        add(crearPanelDerecho(), BorderLayout.CENTER)   
        add(lblMensaje, BorderLayout.SOUTH)              
        
        lblMensaje.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
    }
    
//Método que crea el panel del título con fondo verde
    private fun crearPanelTitulo(): JPanel {
        return JPanel().apply {
            background = Color(46, 125, 50)
            add(JLabel("ReSimple - Asistente de Registro de Reciclaje").apply {
                foreground = Color.WHITE
                font = Font("Arial", Font.BOLD, 24)
            })
        }
    }
    
//Mtodo que crea el panel izquierdo con catálogo y formulario apilados verticalmente
    private fun crearPanelIzquierdo(): JPanel {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS) 
        panel.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        panel.preferredSize = Dimension(400, 0) 
        
        panel.add(crearPanelCatalogo())
        panel.add(Box.createVerticalStrut(20))
        panel.add(crearPanelFormulario())
        
        return panel
    }
    
//Método que cre un panel que muestra el catálogo de materiales en un área de texto con scroll
    private fun crearPanelCatalogo(): JPanel {
        val panel = JPanel(BorderLayout())
        panel.border = BorderFactory.createTitledBorder("📦 Catálogo de Materiales")
        
        val textArea = JTextArea(15, 30).apply {
            isEditable = false 
            font = Font("Monospaced", Font.PLAIN, 12)
            text = formateador.formatearCatalogo(catalogo.listarMateriales())
        }
        
        panel.add(JScrollPane(textArea), BorderLayout.CENTER) 
        return panel
    }
    
//Panel del formulario para agregar materiales
    private fun crearPanelFormulario(): JPanel {
        val panel = JPanel(GridBagLayout())  
        panel.border = BorderFactory.createTitledBorder("➕ Agregar Material")
        
//GridBagConstraints controla la posición y tamaño de componentes
        val gbc = GridBagConstraints().apply {
            insets = Insets(5, 5, 5, 5) 
            fill = GridBagConstraints.HORIZONTAL  
        }
        
        gbc.gridx = 0
        gbc.gridy = 0
        panel.add(JLabel("Material:"), gbc)  
        
        gbc.gridx = 1
        gbc.weightx = 1.0 
        panel.add(comboMateriales, gbc)
        
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.weightx = 0.0
        panel.add(JLabel("Peso (kg):"), gbc)
        
        gbc.gridx = 1
        gbc.weightx = 1.0
        panel.add(txtPeso, gbc)
        
        gbc.gridx = 0
        gbc.gridy = 2
        gbc.gridwidth = 2  
        btnAgregar.background = Color(46, 125, 50)
        btnAgregar.foreground = Color.WHITE
        btnAgregar.font = Font("Arial", Font.BOLD, 14)
        panel.add(btnAgregar, gbc)
        
        return panel
    }
    
//Método que crea un panel derecho con lista de items y resumen del beneficio
    private fun crearPanelDerecho(): JPanel {
        val panel = JPanel(BorderLayout(10, 10))
        panel.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        
// ===== Panel de la lista de items =====
        val panelLista = JPanel(BorderLayout())
        panelLista.border = BorderFactory.createTitledBorder("📋 Items Procesados")
        
        listItems.font = Font("Monospaced", Font.PLAIN, 12)
        panelLista.add(JScrollPane(listItems), BorderLayout.CENTER)
        
// ===== Panel del resumen con fondo verde =====
        val panelResumen = JPanel()
        panelResumen.layout = BoxLayout(panelResumen, BoxLayout.Y_AXIS)
        panelResumen.border = BorderFactory.createTitledBorder("💰 Resumen")
        panelResumen.background = Color(46, 125, 50)
        
        lblBeneficioTotal.apply {
            font = Font("Arial", Font.BOLD, 20)
            foreground = Color.WHITE
            alignmentX = Component.CENTER_ALIGNMENT
        }
        
        btnObtenerBeneficio.apply {
            background = Color(255, 193, 7) 
            foreground = Color.BLACK
            font = Font("Arial", Font.BOLD, 16)
            alignmentX = Component.CENTER_ALIGNMENT
            isEnabled = false //Deshabilitado hasta que haya beneficio
        }
        
//Agrega componentes con espaciado
        panelResumen.add(Box.createVerticalStrut(10))
        panelResumen.add(lblBeneficioTotal)
        panelResumen.add(Box.createVerticalStrut(20))
        panelResumen.add(btnObtenerBeneficio)
        panelResumen.add(Box.createVerticalStrut(10))
        
        panel.add(panelLista, BorderLayout.CENTER)
        panel.add(panelResumen, BorderLayout.SOUTH)
        
        return panel
    }
    
//Método que llena el ComboBox con los materiales disponibles del catálogo
    private fun cargarMateriales() {
        comboMateriales.addItem("-- Seleccione un material --")
        catalogo.listarMateriales().forEach { material ->
            comboMateriales.addItem(material.nombre)
        }
    }
    
//Método que configura los listeners de eventos (clicks, Enter, etc)
    private fun configurarEventos() {

//Cuando se hace click en "Agregar Material"
        btnAgregar.addActionListener {
            controlador.agregarItem()
        }
        
//Cuando se presiona Enter en el campo de peso (atajo)
        txtPeso.addActionListener {
            controlador.agregarItem()
        }
        
//Cuando se hace click en "Obtener Beneficio"
        btnObtenerBeneficio.addActionListener {
            abrirVentanaBeneficio()
        }
    }
    
//Abre la ventana modal para acreditar el beneficio
    private fun abrirVentanaBeneficio() {
        val ventanaBeneficio = VentanaBeneficio(beneficioActual) {
            reiniciarPrograma() //Callback: se ejecuta al cerrar VentanaBeneficio
        }
        ventanaBeneficio.isVisible = true
    }
    
//Reinicia todo el programa para una nueva sesión
    private fun reiniciarPrograma() {

//Reinicia la tarea en el controlador
        controlador.reiniciarTarea()
        
//Limpia lista de items
        listModel.clear()
        
//Resetea beneficio
        beneficioActual = 0.0
        lblBeneficioTotal.text = "Beneficio Total: $0.00"
        btnObtenerBeneficio.isEnabled = false
        
//Limpia formulario
        limpiarFormulario()
        
//Mensaje de confirmación temporal
        lblMensaje.text = "✅ Programa reiniciado - Nueva tarea iniciada"
        lblMensaje.foreground = Color(46, 125, 50)
        Timer(3000) { lblMensaje.text = " " }.apply {
            isRepeats = false
            start()
        }
    }
}