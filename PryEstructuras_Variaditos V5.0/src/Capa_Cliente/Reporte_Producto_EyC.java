/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Cliente;

import Capa_Logica.Producto;
import ListasAux.ListaEnlazada;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Simplemente
 */
public class Reporte_Producto_EyC extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmBuscarProveedoresPorMesyAnio
     */
    public Reporte_Producto_EyC() {
        initComponents();
        listarProducto();
    }

    public void listarProducto() {
        DefaultTableModel modelo = new DefaultTableModel();
        ListaEnlazada LP;

        modelo.addColumn("ID Prod.");
        modelo.addColumn("Nombre");
        modelo.addColumn("Estado");
        modelo.addColumn("Categoria");
        modelo.addColumn("T. Unidad");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");

        Object datos[][] = new Object[1][7];

        LP = Producto.consultarTodo();

        for (int i = 0; i < LP.tamaño(); i++) {
            Producto objCliente = (Producto) LP.Buscar(i);
            datos[0][0] = objCliente.getCodProducto();
            datos[0][1] = objCliente.getNombre();
            datos[0][2] = objCliente.getEstado();
            datos[0][3] = objCliente.getCategoria();
            datos[0][4] = objCliente.getUnidad();
            datos[0][5] = objCliente.getCantidad();
            datos[0][6] = objCliente.getPrecio();

            modelo.addRow(datos[0]);
        }

        tblListado.setModel(modelo);
        tblListado.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        double porcentaje[] = Producto.porcentajexestado();
        P1.setText(String.valueOf(porcentaje[0]));
        P2.setText(String.valueOf(porcentaje[1]));
        P3.setVisible(false);
        P4.setVisible(false);
        P5.setVisible(false);
        lbl3.setVisible(false);
        lbl4.setVisible(false);
        lbl5.setVisible(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblListado = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cboestado = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbocate = new javax.swing.JComboBox<>();
        lbltt = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        P1 = new javax.swing.JLabel();
        P2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        P3 = new javax.swing.JLabel();
        P4 = new javax.swing.JLabel();
        P5 = new javax.swing.JLabel();
        btngrafica = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Mostrar productos por Estado y/o Categoria");

        tblListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblListado);

        jLabel1.setText("Estado :");

        cboestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cboestado.setSelectedIndex(-1);
        cboestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboestadoActionPerformed(evt);
            }
        });

        jLabel2.setText("Categoria :");

        cbocate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Abarrotes", "Bebidas", "Condimentos y Sales", "Golosinas y Postres", "Lacteos" }));
        cbocate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbocateActionPerformed(evt);
            }
        });

        lbltt.setFont(new java.awt.Font("Wide Latin", 1, 14)); // NOI18N
        lbltt.setText("Porcentajes");

        lbl1.setText("Productos Activos:");

        lbl2.setText("Productos de Baja:");

        lbl3.setText("Condimentos y Sales:");

        lbl4.setText(" Golosinas y Postres:");

        lbl5.setText("Lacteos:");

        btngrafica.setText("Graficar Estado");
        btngrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngraficaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(cboestado, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbocate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(lbltt))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl3)
                            .addComponent(lbl1)
                            .addComponent(lbl5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(P1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(128, 128, 128)
                                        .addComponent(lbl2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(P3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(P2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(P4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(P5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 15, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(btngrafica)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbocate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltt, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(P2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(P1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl4)
                                .addComponent(lbl3))
                            .addComponent(P3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl5)
                            .addComponent(P5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(btngrafica)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void cboestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboestadoActionPerformed
        // TODO add your handling code here:
        String estado = cboestado.getSelectedItem().toString();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Prod.");
        modelo.addColumn("Nombre");
        modelo.addColumn("Estado");
        modelo.addColumn("Categoria");
        modelo.addColumn("T. Unidad");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");

        Object datos[][] = new Object[1][7];

        ListaEnlazada LP = Producto.Productosxestado(estado);

        for (int i = 0; i < LP.tamaño(); i++) {
            Producto objCliente = (Producto) LP.Buscar(i);
            datos[0][0] = objCliente.getCodProducto();
            datos[0][1] = objCliente.getNombre();
            datos[0][2] = objCliente.getEstado();
            datos[0][3] = objCliente.getCategoria();
            datos[0][4] = objCliente.getUnidad();
            datos[0][5] = objCliente.getCantidad();
            datos[0][6] = objCliente.getPrecio();

            modelo.addRow(datos[0]);
        }
        cbocate.setSelectedIndex(0);
        tblListado.setModel(modelo);

        double porcentaje[] = Producto.porcentaxcat(estado);
        btngrafica.setVisible(true);
        lbltt.setVisible(true);
        P3.setVisible(true);
        P4.setVisible(true);
        P5.setVisible(true);
        P2.setVisible(true);
        P1.setVisible(true);
        lbl3.setVisible(true);
        lbl4.setVisible(true);
        lbl5.setVisible(true);
        lbl2.setVisible(true);
        lbl1.setVisible(true);
        lbl1.setText("Abarrotes");
        lbl2.setText("Bebidas");
        P1.setText(String.valueOf(porcentaje[0]));
        P2.setText(String.valueOf(porcentaje[1]));
        P3.setText(String.valueOf(porcentaje[2]));
        P4.setText(String.valueOf(porcentaje[3]));
        P5.setText(String.valueOf(porcentaje[4]));

       
            btngrafica.setText("Grafica Categorias");
        
    }//GEN-LAST:event_cboestadoActionPerformed

    private void cbocateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbocateActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Prod.");
        modelo.addColumn("Nombre");
        modelo.addColumn("Estado");
        modelo.addColumn("Categoria");
        modelo.addColumn("T. Unidad");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");

        Object datos[][] = new Object[1][7];
        String estado = cboestado.getSelectedItem().toString();
        String cate = cbocate.getSelectedItem().toString();
        ListaEnlazada LP = null;
        try {
            LP = Producto.Productosxestadoycategoria(estado, cate);
        } catch (Exception ex) {
            Logger.getLogger(Reporte_Producto_EyC.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < LP.tamaño(); i++) {
            Producto objCliente = (Producto) LP.Buscar(i);
            datos[0][0] = objCliente.getCodProducto();
            datos[0][1] = objCliente.getNombre();
            datos[0][2] = objCliente.getEstado();
            datos[0][3] = objCliente.getCategoria();
            datos[0][4] = objCliente.getUnidad();
            datos[0][5] = objCliente.getCantidad();
            datos[0][6] = objCliente.getPrecio();

            modelo.addRow(datos[0]);;
        }
        tblListado.setModel(modelo);
        P3.setVisible(false);
        P4.setVisible(false);
        P5.setVisible(false);
        P2.setVisible(false);
        P1.setVisible(false);
        lbl3.setVisible(false);
        lbl4.setVisible(false);
        lbl5.setVisible(false);
        lbl2.setVisible(false);
        lbl1.setVisible(false);
        btngrafica.setVisible(false);
        lbltt.setVisible(false);
    }//GEN-LAST:event_cbocateActionPerformed


    private void btngraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngraficaActionPerformed

        String btn = btngrafica.getText();
        if (btn.equalsIgnoreCase("Graficar Estado")) {
            DefaultPieDataset data = new DefaultPieDataset();
            data.setValue("Activo", new Double(P1.getText()));
            data.setValue("Inactivo", new Double(P2.getText()));

            // Creando el Grafico
            JFreeChart chart = ChartFactory.createPieChart(
                    "GRAFICO DE PORCENTAJE POR ESTADOS",
                    data,
                    true,
                    true,
                    false);

            // Mostrar Grafico
            ChartFrame frame = new ChartFrame("GRAFICO ESTADOS", chart);
            frame.pack();
            frame.setVisible(true);
        }else{
            DefaultPieDataset data = new DefaultPieDataset();
            data.setValue("Abarrotes", new Double(P1.getText()));
            data.setValue("Bebidas", new Double(P2.getText()));
            data.setValue("Condimentos y Sales", new Double(P3.getText()));
            data.setValue("Golosinas y Postres", new Double(P4.getText()));
            data.setValue("Lacteos", new Double(P5.getText()));

            // Creando el Grafico
            JFreeChart chart = ChartFactory.createPieChart(
                    "GRAFICO DE PORCENTAJE POR CATEGORIA",
                    data,
                    true,
                    true,
                    false);

            // Mostrar Grafico
            ChartFrame frame = new ChartFrame("GRAFICO CATEGORIA", chart);
            frame.pack();
            frame.setVisible(true);
        }
        
    }//GEN-LAST:event_btngraficaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel P1;
    private javax.swing.JLabel P2;
    private javax.swing.JLabel P3;
    private javax.swing.JLabel P4;
    private javax.swing.JLabel P5;
    private javax.swing.JButton btngrafica;
    private javax.swing.JComboBox<String> cbocate;
    private javax.swing.JComboBox<String> cboestado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbltt;
    private javax.swing.JTable tblListado;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
