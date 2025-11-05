package org.example

import javax.swing.*
import java.awt.*

// SRP: Solo maneja la construcción y actualización de componentes visuales
// ISP: Implementa solo la interfaz IVistaReciclaje
// DIP: No depende de implementaciones concretas
class VentanaReciclaje(
    private val catalogo: ICatalogoMateriales,
    private val formateador: IFormateadorUI,
    private val controlador: IControladorUI
) : JFrame("Sistema de Reciclaje"), IVistaReciclaje {
    
    private val listModel = DefaultListModel<String>()
    private val comboMateriales = JComboBox<String>()
    private val txtPeso = JTextField(10)
    private val btnAgregar = JButton("Agregar Material")
    private val listItems = JList(listModel)
    private val lblBeneficioTotal = JLabel("Beneficio Total: $0.00")
    private val lblMensaje = JLabel(" ")
    
    init {
        configurarVentana()
        construirUI()
        cargarMateriales()
        configurarEventos()
    }
    
    // Implementación IVistaReciclaje
    override fun mostrarMensajeExito(mensaje: String) {
        lblMensaje.text = "✅ $mensaje"
        lblMensaje.foreground = Color(46, 125, 50)
        Timer(3000) { lblMensaje.text = " " }.apply {
            isRepeats = false
            start()
        }
    }
    
    override fun mostrarMensajeError(mensaje: String) {
        lblMensaje.text = "❌ Error: $mensaje"
        lblMensaje.foreground = Color.RED
        Timer(3000) { lblMensaje.text = " " }.apply { 
            isRepeats = false
            start()
        }
    }
    
    override fun actualizarListaItems(items: List<String>) {
        listModel.clear()
        items.forEach { listModel.addElement(it) }
    }
    
    override fun actualizarBeneficioTotal(beneficio: Double) {
        lblBeneficioTotal.text = "Beneficio Total: $${String.format("%.2f", beneficio)}"
    }
    
    override fun limpiarFormulario() {
        txtPeso.text = ""
        comboMateriales.selectedIndex = 0
    }
    
    override fun obtenerMaterialSeleccionado(): String? {
        return comboMateriales.selectedItem as? String
    }
    
    override fun obtenerPeso(): Double? {
        return txtPeso.text.toDoubleOrNull()
    }
    
    // Métodos privados de construcción UI
    private fun configurarVentana() {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(900, 600)
        setLocationRelativeTo(null)
        layout = BorderLayout(10, 10)
    }
    
    private fun construirUI() {
        add(crearPanelTitulo(), BorderLayout.NORTH)
        add(crearPanelIzquierdo(), BorderLayout.WEST)
        add(crearPanelDerecho(), BorderLayout.CENTER)
        add(lblMensaje, BorderLayout.SOUTH)
        
        lblMensaje.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
    }
    
    private fun crearPanelTitulo(): JPanel {
        return JPanel().apply {
            background = Color(46, 125, 50)
            add(JLabel("🌿 Asistente de Registro de Reciclaje").apply {
                foreground = Color.WHITE
                font = Font("Arial", Font.BOLD, 24)
            })
        }
    }
    
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
    
    private fun crearPanelFormulario(): JPanel {
        val panel = JPanel(GridBagLayout())
        panel.border = BorderFactory.createTitledBorder("➕ Agregar Material")
        
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
    
    private fun crearPanelDerecho(): JPanel {
        val panel = JPanel(BorderLayout(10, 10))
        panel.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        
        val panelLista = JPanel(BorderLayout())
        panelLista.border = BorderFactory.createTitledBorder("📋 Items Procesados")
        
        listItems.font = Font("Monospaced", Font.PLAIN, 12)
        panelLista.add(JScrollPane(listItems), BorderLayout.CENTER)
        
        val panelResumen = JPanel()
        panelResumen.layout = BoxLayout(panelResumen, BoxLayout.Y_AXIS)
        panelResumen.border = BorderFactory.createTitledBorder("💰 Resumen")
        panelResumen.background = Color(46, 125, 50)
        
        lblBeneficioTotal.apply {
            font = Font("Arial", Font.BOLD, 20)
            foreground = Color.WHITE
            alignmentX = Component.CENTER_ALIGNMENT
        }
        
        panelResumen.add(Box.createVerticalStrut(10))
        panelResumen.add(lblBeneficioTotal)
        panelResumen.add(Box.createVerticalStrut(10))
        
        panel.add(panelLista, BorderLayout.CENTER)
        panel.add(panelResumen, BorderLayout.SOUTH)
        
        return panel
    }
    
    private fun cargarMateriales() {
        comboMateriales.addItem("-- Seleccione un material --")
        catalogo.listarMateriales().forEach { material ->
            comboMateriales.addItem(material.nombre)
        }
    }
    
    private fun configurarEventos() {
        btnAgregar.addActionListener {
            controlador.agregarItem()
        }
        
        txtPeso.addActionListener {
            controlador.agregarItem()
        }
    }
}