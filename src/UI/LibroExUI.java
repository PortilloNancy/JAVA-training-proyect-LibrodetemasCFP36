package UI;

import Dao.SedeDAO;
import Dao.ComisionDAO;
import Dao.Conexion;
import Dao.LibroDAO;
import Entidades.Sede;
import Entidades.Comision;
import Entidades.Libro;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class LibroExUI extends javax.swing.JDialog {

    private int idComis;
    private int idLibroSel;
    private Date desde;
    private Date hasta;

    public LibroExUI(java.awt.Frame parent, boolean modal) {//
        super(parent, modal);
        initComponents();
        setTitle("Exportar libro");
        AutoCompleteDecorator.decorate(cmbSede);
        AutoCompleteDecorator.decorate(cmbComision);
        cargarSedes();
        cargarComisiones(0);
    }

    private void cargarSedes() {
        SedeDAO sd = new SedeDAO();
        DefaultComboBoxModel sed = new DefaultComboBoxModel();
        LinkedList<Sede> lista = sd.getAllSedes();
        sed.addElement("seleccione sede");
        lista.forEach((sede) -> {
            sed.addElement(sede);
        });
        cmbSede.setModel(sed);
    }

    private void cargarComisiones(int idSede) {
        ComisionDAO cd = new ComisionDAO();
        DefaultComboBoxModel local = new DefaultComboBoxModel();
        local.addElement("seleccione comisión");
        if (idSede != 0) {
            LinkedList<Comision> lista = cd.getComisionesBySedeId(idSede);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLibroDiario = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        cmbSede = new javax.swing.JComboBox<>();
        cmbComision = new javax.swing.JComboBox<>();

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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Comisión", "Contenido", "Actividad", "Observación", "Fecha", "Carácter", "Feriado"
            }
        ));
        tblLibroDiario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tblLibroDiario);

        btnGuardar.setText("Exportar selección");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Exportar todo");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(cmbSede, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(cmbComision, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        try {
            Connection link = new Conexion().conectar();

            JasperReport Reportelibro = null;
            String path = "src\\ReportesLibro\\ReporteLibro.jasper";

            Reportelibro = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(Reportelibro, null, link);

            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(LibroExUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        cargarFechas();
        try {
            Connection link = new Conexion().conectar();

            JasperReport Reportelibro = null;
            String path = "src\\ReportesLibro\\ReporteLibro.jasper";

            Reportelibro = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(Reportelibro, null, link);

            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(LibroExUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbComisionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbComisionItemStateChanged
        if (cmbComision.getSelectedIndex() > 0) {
            idComis = ((Comision) cmbComision.getSelectedItem()).getIdComision();
            mostrarLibros(idComis);
        }
    }//GEN-LAST:event_cmbComisionItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<Comision> cmbComision;
    private javax.swing.JComboBox<Sede> cmbSede;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLibroDiario;
    // End of variables declaration//GEN-END:variables

    private void cargarFechas() {
        int[] seleccion = tblLibroDiario.getSelectedRows();
        String fechaDesde;
        String fechaHasta;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaDesde = formato.format((String) tblLibroDiario.getValueAt(seleccion[0], 5));
        } catch (Exception ex) {
            System.out.println("error de Fecha" + ex);
            fechaDesde = null;
        }
        desde = Date.valueOf(fechaDesde);
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaHasta = formato.format((String) tblLibroDiario.getValueAt(seleccion.length - 1, 5));
        } catch (Exception ex) {
            System.out.println("error de Fecha" + ex);
            fechaHasta = null;
        }
        hasta = Date.valueOf(fechaHasta);
    }
}
