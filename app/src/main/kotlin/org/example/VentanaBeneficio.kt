package org.example

import javax.swing.*
import java.awt.*

//Ventana modal (bloquea la ventana principal hasta que se cierre). Uso JDialog para crear esta ventana, ya que se usa para ventanas secundarias (Ventana para acreditar el incentivo)
class VentanaBeneficio(
    private val beneficioTotal: Double,
    private val onFinalizar: () -> Unit //Función que se ejecuta al terminar
) : JDialog() {
    
//Campos de texto para los datos del usuario
    private val txtNombre = JTextField(20)
    private val txtDNI = JTextField(20)
    private val txtCBU = JTextField(20)
    private val btnAcreditar = JButton("Acreditar Beneficio")
    private val lblMensaje = JLabel(" ") //Label para mostrar errores
    
//Bloque init que se ejecuta al crear la ventana
    init {
        configurarVentana()
        construirUI()
        configurarEventos()
    }
    
//Configuración inicial de la ventana
    private fun configurarVentana() {
        title = "Obtener Beneficio"
        setSize(500, 400)
        setLocationRelativeTo(null)  
        isModal = true //Bloquea la ventana principal hasta que esta se cierre
        layout = BorderLayout(10, 10)  
    }
    
//Método con la interfaz de la ventana
    private fun construirUI() {
        add(crearPanelTitulo(), BorderLayout.NORTH)     
        add(crearPanelFormulario(), BorderLayout.CENTER) 
        add(lblMensaje, BorderLayout.SOUTH)              
        
        lblMensaje.border = BorderFactory.createEmptyBorder(5, 10, 5, 10)
        lblMensaje.horizontalAlignment = SwingConstants.CENTER
    }
    
//Método que crea el panel del título con fondo verde y el beneficio a acreditar
    private fun crearPanelTitulo(): JPanel {
        return JPanel().apply {
            background = Color(46, 125, 50) 
            border = BorderFactory.createEmptyBorder(20, 10, 20, 10)
            layout = BoxLayout(this, BoxLayout.Y_AXIS)  
            
//Título
            add(JLabel("Obtener Beneficio").apply {
                foreground = Color.WHITE
                font = Font("Arial", Font.BOLD, 24)
                alignmentX = Component.CENTER_ALIGNMENT 
            })
            
          
            add(Box.createVerticalStrut(10))
            
//Muestra el beneficio total
            add(JLabel("Beneficio Total: $${String.format("%.2f", beneficioTotal)}").apply {
                foreground = Color.WHITE
                font = Font("Arial", Font.BOLD, 20)
                alignmentX = Component.CENTER_ALIGNMENT
            })
        }
    }
    
//Formulario con tres campos: Nombre, DNI y CBU
    private fun crearPanelFormulario(): JPanel {
        val panel = JPanel(GridBagLayout()) 
        panel.border = BorderFactory.createEmptyBorder(20, 30, 20, 30)
        
//GridBagConstraints controla posición y tamaño de cada componente
        val gbc = GridBagConstraints().apply {
            insets = Insets(10, 10, 10, 10)  
            fill = GridBagConstraints.HORIZONTAL 
        }
        
// ===== Campo Nombre Completo =====
        gbc.gridx = 0  
        gbc.gridy = 0  
        gbc.weightx = 0.0  
        panel.add(JLabel("Nombre Completo:").apply {
            font = Font("Arial", Font.BOLD, 14)
        }, gbc)
        
        gbc.gridx = 1  
        gbc.weightx = 1.0  
        panel.add(txtNombre, gbc)
        
// ===== Campo DNI =====
        gbc.gridx = 0
        gbc.gridy = 1 
        gbc.weightx = 0.0
        panel.add(JLabel("DNI:").apply {
            font = Font("Arial", Font.BOLD, 14)
        }, gbc)
        
        gbc.gridx = 1
        gbc.weightx = 1.0
        panel.add(txtDNI, gbc)
        
// ===== Campo CBU/CVU =====
        gbc.gridx = 0
        gbc.gridy = 2  
        gbc.weightx = 0.0
        panel.add(JLabel("CBU/CVU:").apply {
            font = Font("Arial", Font.BOLD, 14)
        }, gbc)
        
        gbc.gridx = 1
        gbc.weightx = 1.0
        panel.add(txtCBU, gbc)
        
// ===== Botón de Acreditar =====
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
    
//Configura el listener del botón
    private fun configurarEventos() {
        btnAcreditar.addActionListener {

//Obtiene los valores ingresados (trim quita los espacios en blanco)
            val nombre = txtNombre.text.trim()
            val dni = txtDNI.text.trim()
            val cbu = txtCBU.text.trim()
            
//Validaciones simples con un when
            when {
                nombre.isEmpty() -> mostrarError("Por favor ingrese su nombre completo")
                dni.isEmpty() -> mostrarError("Por favor ingrese su DNI")
                cbu.isEmpty() -> mostrarError("Por favor ingrese su CBU/CVU")
                dni.length < 7 -> mostrarError("DNI inválido")
                cbu.length < 22 -> mostrarError("CBU/CVU inválido (mínimo 22 dígitos)")
                else -> mostrarExito()  //Si todo está bien, acredita
            }
        }
    }
    
//Método que muestra un mensaje de error en rojo si hay algún error
    private fun mostrarError(mensaje: String) {
        lblMensaje.text = "❌ $mensaje"
        lblMensaje.foreground = Color.RED
    }
    
//Método que muestra un mensaje de éxito, cierra la ventana y reinicia el programa
    private fun mostrarExito() {
        val mensaje = """
            ✅ BENEFICIO ACREDITADO
            
            El beneficio de $${String.format("%.2f", beneficioTotal)}
            será acreditado en las próximas 48hs
            a la cuenta de ${txtNombre.text}
        """.trimIndent()
        
//Muestra un aviso de confirmación
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Beneficio Acreditado",
            JOptionPane.INFORMATION_MESSAGE
        )
        
        dispose()  //Cierra la ventana
        onFinalizar()  //Ejecuta la función callback (reinicia el programa)
    }
}
