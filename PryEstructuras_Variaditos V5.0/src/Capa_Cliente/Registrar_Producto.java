/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Cliente;


import Capa_Datos.Lista_Productos;
import Capa_Logica.Producto;
import Capa_Logica.Venta;
import ListasAux.ListaEnlazada;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utils.Alternos;

/**
 *
 * @author Gianx
 */
public class Registrar_Producto extends javax.swing.JInternalFrame {

    /**
     * Creates new form Frm_Producto
     */
    int filas;
    String estado;
    public Registrar_Producto() throws Exception {

        initComponents();
        listarProducto();
        Alternos.limitarSoloMontos(txtcantidad);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jSlider1 = new javax.swing.JSlider();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btndd = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        formProducto = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        cbouni = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbocat = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Mantenimiento - Producto");

        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel7.setText("Productos");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        btndd.setText("Dar de Baja");
        btndd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnddActionPerformed(evt);
            }
        });

        jButton6.setText("Actualizar Stock");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre :");

        cbouni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unidad", "kilo" }));
        cbouni.setSelectedIndex(-1);

        jLabel2.setText("Unidad :");

        jLabel3.setText("Cantidad :");

        jLabel4.setText("Precio :");

        jLabel8.setText("ID Prod. :");

        txtid.setEditable(false);

        jButton5.setText("Generar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setText("_______________________________________________________________________________________");

        jLabel9.setText("Categoria :");

        cbocat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Abarrotes", "Bebidas", "Condimentos y Sales", "Golosinas y Postres", "Lacteos" }));
        cbocat.setSelectedIndex(-1);

        javax.swing.GroupLayout formProductoLayout = new javax.swing.GroupLayout(formProducto);
        formProducto.setLayout(formProductoLayout);
        formProductoLayout.setHorizontalGroup(
            formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formProductoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formProductoLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbouni, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(formProductoLayout.createSequentialGroup()
                                .addGroup(formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(formProductoLayout.createSequentialGroup()
                                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton5))
                                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formProductoLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(formProductoLayout.createSequentialGroup()
                                .addComponent(cbocat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(formProductoLayout.createSequentialGroup()
                                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        formProductoLayout.setVerticalGroup(
            formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(14, 14, 14)
                .addGroup(formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbocat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbouni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel5)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono pdf.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(formProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(formProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(btndd)
                        .addGap(26, 26, 26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     private void listar() throws Exception {
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

        table.setModel(modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    public void listarProducto() throws Exception {
        DefaultTableModel modelo = new DefaultTableModel();
        ListaEnlazada LP;

        modelo.addColumn("ID Prod.");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoria");
        modelo.addColumn("T. Unidad");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Estado");
        Object datos[][] = new Object[1][7];

        LP = Producto.consultarTodo();

        for (int i = 0; i < LP.tamaño(); i++) {
            Producto objCliente = (Producto) LP.Buscar(i);
            datos[0][0] = objCliente.getCodProducto();
            datos[0][1] = objCliente.getNombre();
            datos[0][2] = objCliente.getCategoria();
            datos[0][3] = objCliente.getUnidad();
            datos[0][4] = objCliente.getCantidad();
            datos[0][5] = objCliente.getPrecio();
            datos[0][6] = objCliente.getEstado();
            modelo.addRow(datos[0]);
        }

        table.setModel(modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Colores color = new Colores(6);
        table.getColumnModel().getColumn(6).setCellRenderer(color);

    }

    public void registrar() throws Exception {
        Producto objPro;

         //validar campos
        if(!Alternos.Probar(formProducto))return;
        
        
        //ListaEn_Clientes LC = new ListaEn_Clientes();
        String codProducto = txtid.getText();
        String uni = cbouni.getSelectedItem().toString();
        String nombre = txtnombre.getText();
        int cantidad = Integer.parseInt(txtcantidad.getText());
        float precio = Float.parseFloat(txtprecio.getText());

       
        int pos = Lista_Productos.buscarCodCliente(codProducto);

        if (pos == -1) {
            objPro = new Producto();
            objPro.setCantidad(cantidad);
            objPro.setNombre(nombre);
            objPro.setPrecio(precio);
            objPro.setCodProducto(codProducto);
            objPro.setUnidad(uni);
            objPro.setEstado("Activo");
            objPro.setCategoria(cbocat.getSelectedItem().toString());

            Lista_Productos.adicionar(objPro);
        } else {
            JOptionPane.showMessageDialog(this, "PRODUCTO YA REGISTADO", "MENSAJE DE SISTEMA", JOptionPane.ERROR_MESSAGE);
        }
        //mucho texto
        cbouni.setSelectedIndex(-1);
        cbocat.setSelectedIndex(-1);
        txtcantidad.setText("");
        txtnombre.setText("");
        txtprecio.setText("");
        txtid.setText("");
        txtid.requestFocus();
         //one line
        Alternos.LimpiarCajas(formProducto);
        listarProducto();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            registrar();
        } catch (Exception ex) {
            Alternos.message("ha ocurrdo en error en registrar: "+ ex.getMessage());
        
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            generarM();
            txtid.setText(generarM());
        } catch (Exception ex) {
            Logger.getLogger(Registrar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            ListaEnlazada lista = Producto.consultarTodo();
            int posCta = table.getSelectedRow();
            if (posCta == -1) //Si no selecciono una cuenta
            {
                JOptionPane.showMessageDialog(this, "Seleccione un Producto", "MENSAJE DE SISTEMA", JOptionPane.ERROR_MESSAGE);
            } else {
                Producto objPro = (Producto) lista.Buscar(posCta);

                objPro.setCantidad(Integer.parseInt(txtcantidad.getText()));
                objPro.setNombre(txtnombre.getText());
                objPro.setPrecio(Float.parseFloat(txtprecio.getText()));
                objPro.setUnidad(cbouni.getSelectedItem().toString());
                objPro.setCategoria(cbocat.getSelectedItem().toString());
                
                cbouni.setSelectedIndex(-1);
                cbocat.setSelectedIndex(-1);
                txtcantidad.setText("");
                txtnombre.setText("");
                txtprecio.setText("");
                txtid.setText("");
                txtid.requestFocus();
                txtcantidad.setEditable(true);
                listar();

            }
        } catch (Exception ex) {
            Logger.getLogger(Registrar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int selection = table.getSelectedRow();
        txtcantidad.setEditable(false);
        String uni = table.getValueAt(selection, 4).toString();
        switch (uni) {
            case "Unidad":
                cbouni.setSelectedIndex(0);
                break;
            case "kilo":
                cbouni.setSelectedIndex(1);
                break;
        }
        String cat = table.getValueAt(selection, 3).toString();
        switch (cat) {
            case "Abarrotes":
                cbocat.setSelectedIndex(0);
                break;
            case "Bebidas":
                cbocat.setSelectedIndex(1);
                break;
            case "Condimentos y Sales":
                cbocat.setSelectedIndex(2);
                break;
            case "Golosinas y Postres":
                cbocat.setSelectedIndex(3);
                break;
            case "Lacteos":
                cbocat.setSelectedIndex(4);
                break;
        }
        txtcantidad.setText(table.getValueAt(selection, 5).toString());
        txtnombre.setText(table.getValueAt(selection, 1).toString());
        txtprecio.setText(table.getValueAt(selection, 6).toString());
        txtid.setText(table.getValueAt(selection, 0).toString());
        estado = table.getValueAt(selection, 2).toString();
        if(estado.equalsIgnoreCase("inactivo")){
            btndd.setText("Activar");
        }else{
            btndd.setText("Dar de Baja");
        }
        filas = selection;

    }//GEN-LAST:event_tableMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Actualizar_Stock stock;
        try {
            stock = new Actualizar_Stock();
            Frm_Principal.contenedor.add(stock);
            stock.setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Registrar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnddActionPerformed
         try {
            ListaEnlazada lista = Producto.consultarTodo();
            int posCta = table.getSelectedRow();
            if (posCta == -1) //Si no selecciono una cuenta
            {
                JOptionPane.showMessageDialog(this, "Seleccione un Producto", "MENSAJE DE SISTEMA", JOptionPane.ERROR_MESSAGE);
            } else {
                Producto objPro = (Producto) lista.Buscar(posCta);
               
                if(btndd.getText().equalsIgnoreCase("Dar de baja")){
                objPro.setEstado("Inactivo");
                }else{
                objPro.setEstado("Activo");
                }
                txtid.requestFocus();
                cbouni.setSelectedIndex(-1);
                cbocat.setSelectedIndex(-1);
                txtcantidad.setText("");
                txtnombre.setText("");
                txtprecio.setText("");
                txtid.setText("");
                txtcantidad.setEditable(true);
               
                listar();

            }
        } catch (Exception ex) {
            Logger.getLogger(Registrar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        try {
            int posCta = table.getSelectedRow();
            ListaDin_Producto.eliminar(posCta);
            listar();
            txtcantidad.setEditable(true);
        } catch (Exception ex) {
            Logger.getLogger(Frm_Producto.class.getName()).log(Level.SEVERE, null, ex);
        /*
        try {
            int posCta = table.getSelectedRow();
            ListaDin_Producto.eliminar(posCta);
            listar();
            txtcantidad.setEditable(true);
        } catch (Exception ex) {
            Logger.getLogger(Frm_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btnddActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{

            OutputStream file = new FileOutputStream(new File("C://impresiones//Lista de Productos.pdf"));
            Document document = new Document(PageSize.A4,2,2,2,2);

            PdfWriter.getInstance(document, file);
            document.open();
            PdfPTable tabla = new PdfPTable(7);
            Paragraph p = new Paragraph("Lista de Productos \n\n", FontFactory.getFont("Arial",16,Font.ITALIC,BaseColor.BLUE));

            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            tabla.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            //tabla.setExtendLastRow(isSelected);
            document.add(new Paragraph(""));

            // dni, nomb, apell ,edad ,sexo, correo, cel,fecha,direcc, dep,prov,dist,estado
            // float[] mediaCeldas ={15.30f,20.50f,30.50f,8.70f,10.70f,15.50f,15.30f,30.50f,20.50f,20.70f,20.70f,20.50f,15.50f};

            tabla.setWidthPercentage(100);

            tabla.addCell(new Paragraph("CODIGO", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("NOMBRE", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("CATEGORIA", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("UNIDAD", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("CANTIDAD", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("PRECIO", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("ESTADO", FontFactory.getFont("Arial",7)));

            ListaEnlazada datos = Lista_Productos.consultar();

            for (int i = 0; i < datos.tamaño(); i++) {

                    Producto cdb = (Producto)datos.Buscar(i);
                
                tabla.addCell(new Paragraph(cdb.getCodProducto(), FontFactory.getFont("Arial",6)));
                tabla.addCell(new Paragraph(cdb.getNombre(), FontFactory.getFont("Arial",6)));
                tabla.addCell(new Paragraph(cdb.getCategoria(), FontFactory.getFont("Arial",6)));
                tabla.addCell(new Paragraph(cdb.getUnidad(), FontFactory.getFont("Arial",6)));
                tabla.addCell(new Paragraph(String.valueOf(cdb.getCantidad()), FontFactory.getFont("Arial",6)));
                tabla.addCell(new Paragraph(String.valueOf(cdb.getPrecio()), FontFactory.getFont("Arial",6)));
                tabla.addCell(new Paragraph(cdb.getEstado(), FontFactory.getFont("Arial",6)));
            }

            document.add(tabla);
            document.close();
            file.close();

        } catch (DocumentException | IOException ex) {
            Logger.getLogger(Registrar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }

        try{
            File file = new File("C://impresiones//Lista de productos.pdf");
            Desktop.getDesktop().open(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public static String generarM() throws Exception {
        String letra = "PRO - ";
        ListaEnlazada lista = Producto.consultarTodo();
            // - - - -
        String num = "" + (lista.tamaño()+ 1);
         num =  Venta.NumeroAleatorios(4, num);
        String codiM = letra + num;
        return codiM;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndd;
    private javax.swing.JComboBox<String> cbocat;
    private javax.swing.JComboBox<String> cbouni;
    private javax.swing.JPanel formProducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtprecio;
    // End of variables declaration//GEN-END:variables
}
