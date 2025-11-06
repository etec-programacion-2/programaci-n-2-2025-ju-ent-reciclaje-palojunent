package org.example

import javax.swing.*
import java.awt.*

class VentanaBeneficio(
    private val beneficioTotal: Double,
    private val onFinalizar: () -> Unit
) : JDialog() {
    
    private val txtNombre = JTextField(20)
    private val txtDNI = JTextField(20)
    private val txtCBU = JTextField(20)
    private val btnAcreditar = JButton("Acreditar Beneficio")
    private val lblMensaje = JLabel(" ")
    
    init {
        configurarVentana()
        construirUI()
        configurarEventos()
    }
    
    private fun configurarVentana() {
        title = "Obtener Beneficio"
        setSize(500, 400)
        setLocationRelativeTo(null)
        isModal = true
        layout = BorderLayout(10, 10)
    }
    
    private fun construirUI() {
        add(crearPanelTitulo(), BorderLayout.NORTH)
        add(crearPanelFormulario(), BorderLayout.CENTER)
        add(lblMensaje, BorderLayout.SOUTH)
        
        lblMensaje.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
        lblMensaje.horizontalAlignment = SwingConstants.CENTER
    }
    
    private fun crearPanelTitulo(): JPanel {
        return JPanel().apply {
            background = Color(46, 125, 50)
            border = BorderFactory.createEmptyBorder(20, 10, 20, 10)
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            
            add(JLabel("💰 Obtener Beneficio").apply {
                foreground = Color.WHITE
                font = Font("Arial", Font.BOLD, 24)
                alignmentX = Component.CENTER_ALIGNMENT
            })
            
            add(Box.createVerticalStrut(10))
            
            add(JLabel("Beneficio Total: $${String.format("%.2f", beneficioTotal)}").apply {
                foreground = Color.WHITE
                font = Font("Arial", Font.BOLD, 20)
                alignmentX = Component.CENTER_ALIGNMENT
            })
        }
    }
    
    private fun crearPanelFormulario(): JPanel {
        val panel = JPanel(GridBagLayout())
        panel.border = BorderFactory.createEmptyBorder(20, 30, 20, 30)
        
        val gbc = GridBagConstraints().apply {
            insets = Insets(10, 10, 10, 10)
            fill = GridBagConstraints.HORIZONTAL
        }
        
        // Nombre
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.weightx = 0.0
        panel.add(JLabel("Nombre Completo:").apply {
            font = Font("Arial", Font.BOLD, 14)
        }, gbc)
        
        gbc.gridx = 1
        gbc.weightx = 1.0
        panel.add(txtNombre, gbc)
        
        // DNI
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.weightx = 0.0
        panel.add(JLabel("DNI:").apply {
            font = Font("Arial", Font.BOLD, 14)
        }, gbc)
        
        gbc.gridx = 1
        gbc.weightx = 1.0
        panel.add(txtDNI, gbc)
        
        // CBU
        gbc.gridx = 0
        gbc.gridy = 2
        gbc.weightx = 0.0
        panel.add(JLabel("CBU/CVU:").apply {
            font = Font("Arial", Font.BOLD, 14)
        }, gbc)
        
        gbc.gridx = 1
        gbc.weightx = 1.0
        panel.add(txtCBU, gbc)
        
        // Botón
        gbc.gridx = 0
        gbc.gridy = 3
        gbc.gridwidth = 2
        gbc.insets = Insets(30, 10, 10, 10)
        btnAcreditar.apply {
            background = Color(46, 125, 50)
            foreground = Color.WHITE
            font = Font("Arial", Font.BOLD, 16)
            preferredSize = Dimension(200, 50)
        }
        panel.add(btnAcreditar, gbc)
        
        return panel
    }
    
    private fun configurarEventos() {
        btnAcreditar.addActionListener {
            val nombre = txtNombre.text.trim()
            val dni = txtDNI.text.trim()
            val cbu = txtCBU.text.trim()
            
            when {
                nombre.isEmpty() -> mostrarError("Por favor ingrese su nombre completo")
                dni.isEmpty() -> mostrarError("Por favor ingrese su DNI")
                cbu.isEmpty() -> mostrarError("Por favor ingrese su CBU/CVU")
                dni.length < 7 -> mostrarError("DNI inválido")
                cbu.length < 22 -> mostrarError("CBU/CVU inválido (mínimo 22 dígitos)")
                else -> mostrarExito()
            }
        }
    }
    
    private fun mostrarError(mensaje: String) {
        lblMensaje.text = "❌ $mensaje"
        lblMensaje.foreground = Color.RED
    }
    
    private fun mostrarExito() {
        val mensaje = """
            ✅ BENEFICIO ACREDITADO
            
            El beneficio de $${String.format("%.2f", beneficioTotal)}
            será acreditado en las próximas 48hs
            a la cuenta de ${txtNombre.text}
        """.trimIndent()
        
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Beneficio Acreditado",
            JOptionPane.INFORMATION_MESSAGE
        )
        
        dispose()
        onFinalizar()
    }
}