package UI;

import Dao.LocalidadDAO;
import Dao.ProvinciaDAO;
import Dao.UsuarioDAO;
import Entidades.Localidad;
import Entidades.Provincia;
import Entidades.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class UsuarioUI extends javax.swing.JDialog {

    private String busqueda = "";
    private int metodo;
    private int idUsuarioSelect;
    private final int INSERT = 1;
    private final int UPDATE = 2;
    Usuario user;

    public UsuarioUI(java.awt.Frame parent, boolean modal, Usuario user) {
        super(parent, modal);
        this.user = user;
        initComponents();
        administrarRoles();
        inicializarCampos();
        llenarComboProvincia();
        llenarComboLocalidades(0);
        mostrarUsuarios(busqueda);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        setTitle("Usuarios");
        allText(false);
    }
    private void inicializarCampos() {
        TextPrompt txtDni = new TextPrompt("Ingrese DNI sin puntos", this.txtDni);
        txtDni.setForeground(Color.GRAY);
        txtDni.changeAlpha(0.5f);
        txtDni.changeStyle(Font.ROMAN_BASELINE + Font.ITALIC);

        TextPrompt txtMail = new TextPrompt("xxxxxx@xxxxxx.com", this.txtMail);
        txtMail.setForeground(Color.GRAY);
        txtMail.changeAlpha(0.5f);
        txtMail.changeStyle(Font.ROMAN_BASELINE + Font.ITALIC);
    }

    private void administrarRoles() {
        switch (user.getTipo()) {
            case "Auditor":
                btnAgregar.setEnabled(false);
                break;
            case "Director":
                AutoCompleteDecorator.decorate(cmbProvincia);
                AutoCompleteDecorator.decorate(cmbLocalidad);
                btnAgregar.setEnabled(true);
                break;
            default:
                JOptionPane.showMessageDialog(null, "No se encontro el perfil del usuario");
                dispose();
                break;
        }
    }

    private void llenarComboProvincia() {
        ProvinciaDAO provDAO = new ProvinciaDAO();
        DefaultComboBoxModel prov = new DefaultComboBoxModel();
        LinkedList<Provincia> lista = provDAO.getProvincias();
        prov.addElement("seleccione provincia");
        lista.forEach((provincia) -> {
            prov.addElement(provincia);
        });
        cmbProvincia.setModel(prov);
    }

    private void llenarComboLocalidades(int id_provincia) {
        LocalidadDAO locDAO = new LocalidadDAO();
        DefaultComboBoxModel local = new DefaultComboBoxModel();
        local.addElement("seleccione localidad");
        if (id_provincia != 0) {
            LinkedList<Localidad> lista = locDAO.getLocalidadesByProvinciaId(id_provincia);
            for (Localidad localidad : lista) {
                local.addElement(localidad);
            }
        }
        cmbLocalidad.setModel(local);
    }

    private void mostrarUsuarios(String apellido) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("id");
        modelo.addColumn("DNI");
        modelo.addColumn("Apellido");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cargo");
        modelo.addColumn("Estado");
        UsuarioDAO udao = new UsuarioDAO();
        LinkedList<Usuario> lista;
        lista = udao.getAllUsuarios(apellido);
        Object[] data = new Object[6];
        for (Usuario usuario : lista) {
            data[0] = usuario.getIdUsuario();
            data[1] = usuario.getDni();
            data[2] = usuario.getApellido();
            data[3] = usuario.getNombre();
            data[4] = usuario.getTipo();
            data[5] = usuario.isDeshabilitado() ? "Deshabilitado" : "Habilitado";
            modelo.addRow(data);
        }
        tablaUsr.setModel(modelo);
        TableColumn col0 = tablaUsr.getColumnModel().getColumn(0);
        TableColumn col1 = tablaUsr.getColumnModel().getColumn(1);
        TableColumn col2 = tablaUsr.getColumnModel().getColumn(2);
        TableColumn col3 = tablaUsr.getColumnModel().getColumn(3);
        TableColumn col4 = tablaUsr.getColumnModel().getColumn(4);
        col0.setResizable(false);
        col0.setMinWidth(0);
        col0.setMaxWidth(0);
    }

    public void MostrarUsuarioSel() {
        allText(true);
        UsuarioDAO usr = new UsuarioDAO();
        Usuario usuario = usr.getUsuarioById(idUsuarioSelect);
        txtDni.setText(String.valueOf(usuario.getDni()));
        txtNombre.setText(String.valueOf(usuario.getNombre()));
        txtApellido.setText(usuario.getApellido());
        txtDomicilio.setText(usuario.getDomicilio());
        txtFechaNac.setDate(usuario.getFecha_nac());
        txtMail.setText(usuario.getMail());
        txtPassword.setText(usuario.getPassword());
        txtTelefono.setText(usuario.getTelefono());
        cmbTipo.setSelectedItem(usuario.getTipo());
        cmbProvincia.setSelectedItem(usuario.getLocalidad().getProvincia());
        cmbLocalidad.setSelectedItem(usuario.getLocalidad());
        jCheckDasabilitar.setSelected(usuario.isDeshabilitado());
        allText(false);
    }

    public boolean AgregarUsuario(Usuario usuario) {
        UsuarioDAO usuariodao = new UsuarioDAO();
        boolean insert = usuariodao.newUsuario(usuario);
        if (!insert) {
            JOptionPane.showMessageDialog(this, "Falló el alta de Usuario");
        }
        return insert;
    }

    private boolean actualizarUsuario(Usuario usuario) {
        UsuarioDAO usuariodao = new UsuarioDAO();
        usuario.setIdUsuario(idUsuarioSelect);
        boolean update = usuariodao.updateUsuario(usuario);
        if (!update) {
            JOptionPane.showMessageDialog(this, "Falló la modificacion Usuario");
        }
        return update;
    }

    private Usuario cargarDatosUsuario() {
        Usuario usuario = new Usuario();
        int dni = Integer.parseInt(txtDni.getText());
        usuario.setDni(dni);
        usuario.setNombre(txtNombre.getText());
        usuario.setApellido(txtApellido.getText());
        usuario.setFecha_nac(txtFechaNac.getDate());
        usuario.setTelefono(txtTelefono.getText());
        usuario.setMail(txtMail.getText());
        usuario.setDomicilio(txtDomicilio.getText());
        usuario.setLocalidad((Localidad) cmbLocalidad.getSelectedItem());
        usuario.setPassword(txtPassword.getText());
        usuario.setTipo((String) cmbTipo.getSelectedItem());
        usuario.setDeshabilitado(jCheckDasabilitar.isSelected());
        return usuario;
    }

    private void limpiarTextos() {
        txtApellido.setText("");
        txtDni.setText("");
        txtDomicilio.setText("");
        txtFechaNac.setDate(null);
        txtMail.setText("");
        txtNombre.setText("");
        txtPassword.setText("");
        txtTelefono.setText("");
        cmbProvincia.setSelectedIndex(0);
        cmbLocalidad.setSelectedIndex(0);
        cmbTipo.setSelectedIndex(0);
        jCheckDasabilitar.setSelected(false);
    }

    private void allText(boolean habilitado) {
        txtApellido.setEditable(habilitado);
        txtDni.setEditable(habilitado);
        txtDomicilio.setEditable(habilitado);
        txtFechaNac.setEnabled(habilitado);
        txtMail.setEditable(habilitado);
        txtNombre.setEditable(habilitado);
        txtPassword.setEditable(habilitado);
        txtTelefono.setEditable(habilitado);
        cmbProvincia.setEnabled(habilitado);
        cmbLocalidad.setEnabled(habilitado);
        cmbTipo.setEnabled(habilitado);
        jCheckDasabilitar.setEnabled(habilitado);
    }

      private boolean validar() {
        if (!txtDni.getText().isEmpty()) {
             try {
                int a = Integer.parseInt(txtDni.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese solo numeros el en campo DNI", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
                txtDni.requestFocus();
                return false;
            }
        } else {
           JOptionPane.showMessageDialog(this, "Ingrese DNI", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
            txtDni.requestFocus();
            return false;
        }
        if (txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese Apellido", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese Nombre", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese numero telefónico", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarMail()) {
            JOptionPane.showMessageDialog(this, "Ingrese Email Valido", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtDomicilio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese Domicilio", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (cmbProvincia.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione Provincia", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (cmbLocalidad.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione Localidad", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una Contaseña", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (cmbTipo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione Tipo", "Mensaje Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean validarMail() {
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(txtMail.getText());

        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsr = new javax.swing.JTable();
        panelUsr = new javax.swing.JPanel();
        lblIDusr = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        lblApellido_busqueda = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblFechanacim = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblMail = new javax.swing.JLabel();
        lblDomicilio = new javax.swing.JLabel();
        lblProvincia = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        txtDomicilio = new javax.swing.JTextField();
        lblDepartamento = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtFechaNac = new com.toedter.calendar.JDateChooser();
        cmbProvincia = new javax.swing.JComboBox<>();
        cmbLocalidad = new javax.swing.JComboBox<>();
        cmbTipo = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jCheckDasabilitar = new javax.swing.JCheckBox();
        btnExportar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tablaUsr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaUsr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsrMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaUsr);

        panelUsr.setBackground(new java.awt.Color(51, 255, 51));
        panelUsr.setPreferredSize(new java.awt.Dimension(861, 689));

        lblIDusr.setText("DNI");

        lblApellido_busqueda.setText("Apellido:");

        lblNombre.setText("Nombre:");

        lblFechanacim.setText("Fecha de Nacimiento:");

        lblTelefono.setText("Telefono:");

        lblMail.setText("Mail:");

        lblDomicilio.setText("Domicilio:");

        lblProvincia.setText("Provincia:");

        lblDepartamento.setText("Localidad");

        lblPassword.setText("Password:");

        lblTipo.setText("Tipo:");

        txtFechaNac.setDateFormatString("d- M-yy");

        cmbProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProvinciaItemStateChanged(evt);
            }
        });

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Auditor", "Director", "Profesor" }));

        btnAgregar.setMnemonic('n');
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setMnemonic('m');
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSalir.setMnemonic('s');
        btnSalir.setText("Salir");
        btnSalir.setToolTipText("");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jCheckDasabilitar.setText("Deshabilitar usuario");

        btnExportar.setMnemonic('s');
        btnExportar.setText("Exportar");
        btnExportar.setToolTipText("");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUsrLayout = new javax.swing.GroupLayout(panelUsr);
        panelUsr.setLayout(panelUsrLayout);
        panelUsrLayout.setHorizontalGroup(
            panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsrLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblIDusr, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblFechanacim, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblTelefono, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblMail, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblDomicilio, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblProvincia, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblDepartamento, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblTipo, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(panelUsrLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(lblApellido_busqueda)))
                .addGap(18, 18, 18)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellido)
                    .addComponent(txtNombre)
                    .addGroup(panelUsrLayout.createSequentialGroup()
                        .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(307, 307, 307))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUsrLayout.createSequentialGroup()
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelUsrLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckDasabilitar))
                    .addGroup(panelUsrLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(295, 295, 295))
        );
        panelUsrLayout.setVerticalGroup(
            panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsrLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIDusr))
                .addGap(28, 28, 28)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido_busqueda)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechanacim))
                .addGap(18, 18, 18)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMail)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDomicilio))
                .addGap(16, 16, 16)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProvincia))
                .addGap(17, 17, 17)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDepartamento))
                .addGap(18, 18, 18)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckDasabilitar))
                .addGap(16, 16, 16)
                .addGroup(panelUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(135, 135, 135))
        );

        btnBuscar.setText("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(panelUsr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUsr, javax.swing.GroupLayout.PREFERRED_SIZE, 647, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaUsrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsrMouseClicked
        idUsuarioSelect = (int) tablaUsr.getValueAt(tablaUsr.getSelectedRow(), 0);
        limpiarTextos();
        MostrarUsuarioSel();
        if (user.getTipo().equals("Director")) {
            btnGuardar.setEnabled(false);
            btnAgregar.setEnabled(true);
            btnModificar.setEnabled(true);
        }
    }//GEN-LAST:event_tablaUsrMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        metodo = INSERT;
        limpiarTextos();
        btnGuardar.setEnabled(true);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        allText(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cmbProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProvinciaItemStateChanged
        if (cmbProvincia.getSelectedIndex() == 0) {
            llenarComboLocalidades(0);
        } else {
            llenarComboLocalidades(((Provincia) cmbProvincia.getSelectedItem()).getIdProvincia());
        }
    }//GEN-LAST:event_cmbProvinciaItemStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar()) {
            if (metodo == UPDATE) {
                if (!actualizarUsuario(cargarDatosUsuario())) {
                    return;
                }
            }
            if (metodo == INSERT) {
                if (!AgregarUsuario(cargarDatosUsuario())) {
                    return;
                }
            }
            btnAgregar.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(false);
            allText(false);
            limpiarTextos();
            busqueda = "";
            mostrarUsuarios(busqueda);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        metodo = UPDATE;
        allText(true);
        btnGuardar.setEnabled(true);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        busqueda = txtBusqueda.getText().trim();
        mostrarUsuarios(busqueda);
        if (user.getTipo().equals("Director")) {
            btnGuardar.setEnabled(false);
            btnAgregar.setEnabled(true);
            btnModificar.setEnabled(false);
        }
        limpiarTextos();
        allText(false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        busqueda = "";
        UsuarioDAO usuarios = new UsuarioDAO();
        LinkedList<Usuario> listau = usuarios.getAllUsuarios(busqueda);

        FileWriter fw = null;

        JFileChooser fc = new JFileChooser();
        int seleccion = fc.showSaveDialog(contentPane);

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            File archivoex = (fc.getSelectedFile());
            System.out.println(archivoex);
            try {
                fw = new FileWriter(archivoex);
                BufferedWriter bw = new BufferedWriter(fw);

                for (Usuario usuario : listau) {
                    bw.write(usuario.getDni() + ";");
                    bw.write(usuario.getApellido() + ";");
                    bw.write(usuario.getNombre() + ";");
                    bw.write(usuario.getMail() + ";");
                    bw.newLine();
                }
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(UsuarioUI.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(UsuarioUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (!btnGuardar.isEnabled()) {
            busqueda = "";
            mostrarUsuarios(busqueda);
        }
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    public javax.swing.JComboBox<Object> cmbLocalidad;
    public javax.swing.JComboBox<Object> cmbProvincia;
    public javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JCheckBox jCheckDasabilitar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblApellido_busqueda;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDomicilio;
    private javax.swing.JLabel lblFechanacim;
    private javax.swing.JLabel lblIDusr;
    private javax.swing.JLabel lblMail;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel panelUsr;
    private javax.swing.JTable tablaUsr;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtDomicilio;
    private com.toedter.calendar.JDateChooser txtFechaNac;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
