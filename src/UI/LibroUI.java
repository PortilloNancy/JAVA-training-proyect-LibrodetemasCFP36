package UI;

import Dao.CaracterDAO;
import Dao.SedeDAO;
import Dao.ComisionDAO;
import Dao.LibroDAO;
import Entidades.Caracter;
import Entidades.Sede;
import Entidades.Comision;
import Entidades.Libro;
import Entidades.Usuario;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class LibroUI extends javax.swing.JDialog {
    
    private int metodo;
    private final int INSERT = 1;
    private final int UPDATE = 2;
    private int idComis;
    private int idLibroSel;
    private final Usuario user;
    
    public LibroUI(java.awt.Frame parent, boolean modal, Usuario user) {//
        super(parent, modal);
        this.user = user;
        initComponents();
        setTitle("Libro");
        AutoCompleteDecorator.decorate(cmbSede);
        AutoCompleteDecorator.decorate(cmbComision);
        AutoCompleteDecorator.decorate(cmbCaracter);
        ADcomponentes(false);
        cargarSedes();
        cargarComisiones(0);
        cargarCaracters();
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(false);
    }
    
    private void cargarSedes() {
        SedeDAO sd = new SedeDAO();
        DefaultComboBoxModel sed = new DefaultComboBoxModel();
        LinkedList<Sede> lista = sd.getAllSedeByProfe(user.getIdUsuario());
        sed.addElement("seleccione sede");
        lista.forEach((sede) -> {
            sed.addElement(sede);
        });
        cmbSede.setModel(sed);
    }
    
    private void cargarCaracters() {
        CaracterDAO cd = new CaracterDAO();
        DefaultComboBoxModel car = new DefaultComboBoxModel();
        LinkedList<Caracter> lista = cd.getAllCaracters();
        car.addElement("seleccione carácter");
        lista.forEach((caracter) -> {
            car.addElement(caracter);
        });
        cmbCaracter.setModel(car);
    }
    
    private void cargarComisiones(int idSede) {
        ComisionDAO cd = new ComisionDAO();
        DefaultComboBoxModel local = new DefaultComboBoxModel();
        local.addElement("seleccione comisión");
        if (idSede != 0) {
            LinkedList<Comision> lista = cd.getComisioneBySedeByProfe(idSede, user.getIdUsuario());
            for (Comision comision : lista) {
                local.addElement(comision);
            }
        }
        cmbComision.setModel(local);
    }
    
    private void mostrarLibros(int idComi) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("id");
        modelo.addColumn("Comisión");
        modelo.addColumn("Contenido");
        modelo.addColumn("Actividad");
        modelo.addColumn("Observación");
        modelo.addColumn("Fecha");
        modelo.addColumn("Carácter");
        modelo.addColumn("Feriado");
        LibroDAO ldao = new LibroDAO();
        LinkedList<Libro> lista;
        lista = ldao.getAllLibrosByComisionId(idComi);
        Object[] data = new Object[8];
        for (Libro libro : lista) {
            data[0] = libro.getIdLibro();
            data[1] = libro.getComision();
            data[2] = libro.getContenido();
            data[3] = libro.getActividad();
            data[4] = libro.getObservacion();
            data[5] = libro.getFecha();
            data[6] = libro.getCaracter();
            data[7] = libro.isFeriado() ? "si" : "no";
            modelo.addRow(data);
        }
        tblLibroDiario.setModel(modelo);
        TableColumn col0 = tblLibroDiario.getColumnModel().getColumn(0);
        TableColumn col1 = tblLibroDiario.getColumnModel().getColumn(1);
        TableColumn col2 = tblLibroDiario.getColumnModel().getColumn(2);
        TableColumn col3 = tblLibroDiario.getColumnModel().getColumn(3);
        TableColumn col4 = tblLibroDiario.getColumnModel().getColumn(4);
        TableColumn col5 = tblLibroDiario.getColumnModel().getColumn(5);
        TableColumn col6 = tblLibroDiario.getColumnModel().getColumn(6);
        TableColumn col7 = tblLibroDiario.getColumnModel().getColumn(7);
        col0.setResizable(false);
        col0.setMinWidth(0);
        col0.setMaxWidth(0);
    }
    
    private void ADcomponentes(boolean b) {
        txtContenido.setEditable(b);
        txtActividad.setEditable(b);
        txtObservacion.setEditable(b);
        txtContenido.setEnabled(b);
        txtActividad.setEnabled(b);
        txtObservacion.setEnabled(b);
        txtFecha.setEnabled(b);
        cmbCaracter.setEnabled(b);
        chkEsFeriado.setEnabled(b);
    }
    
    private Libro cargarLibro() {
        Libro libro = new Libro();
        libro.setComision((Comision) cmbComision.getSelectedItem());
        libro.setContenido(txtContenido.getText());
        libro.setActividad(txtActividad.getText());
        libro.setObservacion(txtObservacion.getText());
        libro.setFecha(txtFecha.getDate());
        libro.setCaracter((Caracter) cmbCaracter.getSelectedItem());
        libro.setFeriado(chkEsFeriado.isSelected());
        return libro;
    }
    
    private void mostrarLibroSelectcionado() {
        LibroDAO librodao = new LibroDAO();
        Libro libro = librodao.getLibroxID(idLibroSel);
        ADcomponentes(true);
        txtContenido.setText(libro.getContenido());
        txtActividad.setText(libro.getActividad());
        txtObservacion.setText(libro.getObservacion());
        txtFecha.setDate(libro.getFecha());
        cmbCaracter.setSelectedItem(libro.getCaracter());
        chkEsFeriado.setSelected(libro.isFeriado());
        ADcomponentes(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLibroDiario = new javax.swing.JTable();
        chkEsFeriado = new javax.swing.JCheckBox();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        cmbCaracter = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        cmbSede = new javax.swing.JComboBox<>();
        cmbComision = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtContenido = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtActividad = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        tblLibroDiario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Comisión", "Contenido", "Actividad", "Observación", "Fecha", "Carácter", "Feriado"
            }
        ));
        tblLibroDiario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLibroDiarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLibroDiario);

        chkEsFeriado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkEsFeriado.setText("Feriado ?");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Fecha: ");

        cmbCaracter.setToolTipText("");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        cmbSede.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSedeItemStateChanged(evt);
            }
        });

        cmbComision.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbComisionItemStateChanged(evt);
            }
        });

        txtContenido.setColumns(20);
        txtContenido.setRows(5);
        jScrollPane3.setViewportView(txtContenido);

        txtActividad.setColumns(20);
        txtActividad.setRows(5);
        jScrollPane4.setViewportView(txtActividad);

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane5.setViewportView(txtObservacion);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("CONTENIDO");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("ACTIVIDAD");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("OBSERVACIONES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(cmbSede, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbComision, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnModificar)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(chkEsFeriado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCaracter, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkEsFeriado)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbCaracter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cmbSedeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSedeItemStateChanged
        if (cmbSede.getSelectedIndex() > 0) {
            cargarComisiones(((Sede) cmbSede.getSelectedItem()).getIdSede());
        }
    }//GEN-LAST:event_cmbSedeItemStateChanged

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        ADcomponentes(true);
        clean();
        metodo = INSERT;
        txtFecha.requestFocus();
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        LibroDAO ldao = new LibroDAO();
        Libro libro = cargarLibro();
        if (validar()) {
            if (metodo == INSERT) {
                if (!ldao.insert(libro)) {
                    JOptionPane.showMessageDialog(null, "Fallo al agregar libro");
                }
            }
            if (metodo == UPDATE) {
                if (!ldao.update(libro)) {
                    JOptionPane.showMessageDialog(null, "Fallo al actualizar libro");
                }
            }
            ADcomponentes(false);
            mostrarLibros(idComis);
            btnAgregar.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(false);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbComisionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbComisionItemStateChanged
        if (cmbComision.getSelectedIndex() > 0) {
            idComis = ((Comision) cmbComision.getSelectedItem()).getIdComision();
            mostrarLibros(idComis);
        }
    }//GEN-LAST:event_cmbComisionItemStateChanged

    private void tblLibroDiarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLibroDiarioMouseClicked
        idLibroSel = (int) tblLibroDiario.getValueAt(tblLibroDiario.getSelectedRow(), 0);
        clean();
        mostrarLibroSelectcionado();
        btnGuardar.setEnabled(false);
        btnAgregar.setEnabled(true);
        btnModificar.setEnabled(true);
    }//GEN-LAST:event_tblLibroDiarioMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ADcomponentes(true);
        metodo = UPDATE;
        btnModificar.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkEsFeriado;
    private javax.swing.JComboBox<Object> cmbCaracter;
    private javax.swing.JComboBox<Comision> cmbComision;
    private javax.swing.JComboBox<Sede> cmbSede;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblLibroDiario;
    private javax.swing.JTextArea txtActividad;
    private javax.swing.JTextArea txtContenido;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextArea txtObservacion;
    // End of variables declaration//GEN-END:variables

    private void clean() {
        txtContenido.setText("");
        txtActividad.setText("");
        txtObservacion.setText("");
        txtFecha.setDate(null);
        cmbCaracter.setSelectedIndex(0);
        chkEsFeriado.setSelected(false);
    }
    
    private boolean validar() {
        return true;
    }
}
