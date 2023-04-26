
package Capa_Cliente;

import Capa_Datos.Lista_Proveedores;
import Capa_Logica.Proveedor;
import ListasAux.ListaEnlazada;
import com.google.gson.*;
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
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gianx
 */
public class Registrar_Proveedor extends javax.swing.JInternalFrame {
    
    public Registrar_Proveedor() throws SQLException {
        initComponents(); 
        this.setTitle("--REGISTRAR NUEVO PROVEEDOR--");
        Deshabilitar();
  
    }   

    void registrarProveedor(){
        Date fechan= dteFInscripcion.getDate();        
        long d = fechan.getTime();
        java.sql.Date fechaI = new java.sql.Date(d);     
        String ruc = txtRuc.getText();
        String nom = txtNom.getText();
        String tel = txtTel.getText();
        String dir = txtdir.getText();
        String estado = txtEstado.getText();
        
        

        if(ruc.equalsIgnoreCase("") || nom.equalsIgnoreCase("") || tel.equalsIgnoreCase("") || dteFInscripcion.getDate()==null ){
            JOptionPane.showMessageDialog(null, "No puede dejar Campos vacíos");
        }else{
            int pos = 0;
            pos = Lista_Proveedores.buscarProveedor(ruc);
            if (pos >= 0) {
                JOptionPane.showMessageDialog(null, "El RUC ingresado ya existe");
                limpiar();
            }else{
            Proveedor cdb = new Proveedor();
            cdb.setRUC(ruc);
            cdb.setNombre(nom);
            cdb.setTelefono(tel);
            cdb.setDireccion(dir);
            cdb.setFInscripcion(fechaI);
    
          if (estado.equalsIgnoreCase("ACTIVO")) {
           cdb.setVigencia(1);
          }
            
            Lista_Proveedores.adicionar(cdb);
            JOptionPane.showMessageDialog(null, "Proveedor Registrado");
            this.btnSave.setText("NUEVO");
            limpiar();
            Deshabilitar();        
        }
        }
    }
    void Deshabilitar(){
        txtRuc.setEnabled(false);
        txtNom.setEnabled(false);
        txtTel.setEnabled(false);
        txtdir.setEnabled(false);
        dteFInscripcion.setEnabled(false);
        btnModificar.setEnabled(false);
        txtEstado.setEnabled(false);
        btnBaja.setEnabled(false);
        btnBuscar.setEnabled(false);
    }
     void Habilitar(){
        txtdir.setEnabled(true);
        txtRuc.setEnabled(true);
        txtNom.setEnabled(true);
        txtTel.setEnabled(true);
        dteFInscripcion.setEnabled(true);
        txtEstado.setEnabled(true);
     
        btnModificar.setEnabled(true);
        btnBaja.setEnabled(true);      
        btnBuscar.setEnabled(true);      
    }
     void limpiar(){
        txtRuc.setText("");
        txtNom.setText("");
        txtTel.setText("");
        txtdir.setText("");
        txtEstado.setText("");
        
        dteFInscripcion.setCalendar(null);

     }
     
     void consultar(){
         DefaultTableModel model = new DefaultTableModel();         
         model.addColumn("RUC");
         model.addColumn("Nombre");
         model.addColumn("Teléfono");
         model.addColumn("Dirección");
         model.addColumn("F. Inscripcion"); 
         model.addColumn("Estado");       
         ListaEnlazada Tdatos = Proveedor.consultarTodos();
         Object Dato[] = new Object[6];           
         String etiqueta;
         for (int i = 0; i < Tdatos.tamaño(); i++) {
             Proveedor cdb = (Proveedor)Tdatos.Buscar(i);
             Dato[0]=cdb.getRUC();
             Dato[1]=cdb.getNombre();
             Dato[2]=cdb.getTelefono();
             Dato[3]=cdb.getDireccion();
             Dato[4]=cdb.getFInscripcion();
             if (cdb.getVigencia()==1) {
                etiqueta = "Activo"; 
                
             }else{
                 etiqueta = "Inactivo";
             }
             Dato[5]=etiqueta;
             
             model.addRow(Dato);
         }
         this.tblDatos.setModel(model);
        
         Colores color = new Colores(5);
         tblDatos.getColumnModel().getColumn(5).setCellRenderer(color);
        
     }  
     
        void buscarRuc(){
         String ruc = txtRuc.getText();
         if (ruc.isEmpty()) {
             JOptionPane.showMessageDialog(null, "Ingrese RUC", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
         }else{
             if (ruc.length()== 11) {
                  ListaEnlazada datos  = Proveedor.BuscarRucProveedor(ruc);
                 for (int i = 0; i < datos.tamaño(); i++) {
                     Proveedor cdb = (Proveedor)datos.Buscar(i);
                     txtNom.setText(cdb.getNombre());
                      txtTel.setText(cdb.getTelefono());
                     txtdir.setText(cdb.getDireccion());
                     
                     Date fechan= cdb.getFInscripcion();      
                    long d = fechan.getTime();
                    java.sql.Date fechaIn = new java.sql.Date(d);
                     dteFInscripcion.setDate(fechaIn);
                     if (cdb.getVigencia()==1) {
                          txtEstado.setText("Activo");
                     }else{
                     
                            txtEstado.setText("Inactivo");
                        }
                    }
             } else {                
                     JOptionPane.showMessageDialog(null, "Ingrese un RUC de 11 digitos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                 }        
        }
     } 
     
     
     
    void consultarRuc() throws ParseException{
         String ruc = txtRuc.getText();
         if (ruc.isEmpty()) {
             JOptionPane.showMessageDialog(null, "Ingrese RUC", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
         }else{
             if (ruc.length()== 11) {
                  String enlace = "https://api.apis.net.pe/v1/ruc?numero="+ruc;
           
                                try {

                                    URL url= new URL(enlace);

                                    URLConnection request= url.openConnection();
                                    request.connect();

                                    JsonParser jp= new JsonParser();
                                    JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
                                    JsonObject rootObj = root.getAsJsonObject();
                                    String rSocial= rootObj.get("nombre").getAsString();            
                                    String estado= rootObj.get("estado").getAsString();
                                    String direccion= rootObj.get("direccion").getAsString();
                                    //String fecha= rootObj.get("fecha_inscripcion").getAsString();

                                     //SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                                    // Date fechaDate = formato.parse(fecha);

                                    txtNom.setText(rSocial);
                                    txtEstado.setText(estado);
                                    txtdir.setText(direccion);
                                    //dteFInscripcion.setDate(fechaDate);

                                } catch (MalformedURLException ex) {
                                    Logger.getLogger(Registrar_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Registrar_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
                                }

             } else {                
                     JOptionPane.showMessageDialog(null, "Ingrese un RUC de 11 digitos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }        
        }
     }
     
     void darBaja(){
       Date fechan= dteFInscripcion.getDate();
        long d = fechan.getTime();
        java.sql.Date fechaIn = new java.sql.Date(d);
        String ruc = txtRuc.getText();
        String nom = txtNom.getText();
        String tel = txtTel.getText();
        String dir = txtdir.getText();
        int pos =0;
        pos = Lista_Proveedores.buscarProveedor(ruc);
                  
        if(pos >=0){
            Proveedor cdb = new Proveedor();            
            cdb.setRUC(ruc);
            cdb.setNombre(nom);
            cdb.setTelefono(tel);
            cdb.setFInscripcion(fechaIn);
            cdb.setDireccion(dir);
            cdb.setVigencia(0);
            Lista_Proveedores.modificar(cdb,pos);            
            JOptionPane.showMessageDialog(rootPane, "Registro se dió de baja correctamente");
            limpiar();
        }else
            JOptionPane.showMessageDialog(rootPane, "El proceso no se realizó");
        limpiar();
       
     } 
     void modificar(){
        Date fechan= dteFInscripcion.getDate();
        long d = fechan.getTime();
        java.sql.Date fechaI = new java.sql.Date(d);
        String ruc = txtRuc.getText();
        String nom = txtNom.getText();
        String tel = txtTel.getText();
        String dir = txtdir.getText(); 
        int pos =0;
        pos = Lista_Proveedores.buscarProveedor(ruc);
                  
        if(pos >=0){
            Proveedor cdb = new Proveedor();            
            cdb.setRUC(ruc);
            cdb.setNombre(nom);
            cdb.setTelefono(tel);
            cdb.setFInscripcion(fechaI);
            cdb.setDireccion(dir);
            cdb.setVigencia(1);
            Lista_Proveedores.modificar(cdb,pos);            
            JOptionPane.showMessageDialog(rootPane, "Registro modificado correctamente");
            limpiar();
        }else
            JOptionPane.showMessageDialog(rootPane, "La modificacion no se realizó");
        limpiar();
    
     }
 
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblRuc = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        txtdir = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dteFInscripcion = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jButton1.setText("jButton1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRAR PROVEEDOR");
        setToolTipText("");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblRuc.setText("RUC:");

        jLabel2.setText("Nombre:");

        txtRuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucActionPerformed(evt);
            }
        });
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });

        txtNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomKeyTyped(evt);
            }
        });

        jLabel4.setText("Dirección:");

        txtTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelKeyTyped(evt);
            }
        });

        txtdir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdirKeyTyped(evt);
            }
        });

        jLabel9.setText("Telefono:");

        jLabel11.setText("Fecha de Inscripcion:");

        jLabel1.setText("Estado:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscar16.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblRuc)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(txtRuc, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dteFInscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdir)))
                    .addComponent(jLabel9))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRuc)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRuc, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel1))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dteFInscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(txtEstado))
                .addContainerGap())
        );

        tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDatosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDatos);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono pdf.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSave.setText("NUEVO");
        btnSave.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                btnSaveStateChanged(evt);
            }
        });
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSaveMousePressed(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnModificar.setText("MODIFICAR");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnModificarMousePressed(evt);
            }
        });
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBaja.setText("DAR BAJA");
        btnBaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBajaMousePressed(evt);
            }
        });
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        Habilitar();
        this.btnSave.setText("GUARDAR");
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
       
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_btnSaveStateChanged
      
    }//GEN-LAST:event_btnSaveStateChanged

    private void btnSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMousePressed
        // TODO add your handling code here:
        if(btnSave.getText().equalsIgnoreCase("guardar")){           
            registrarProveedor();            
        }
        consultar();
    }//GEN-LAST:event_btnSaveMousePressed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        lblRuc.setText("Buscar RUC:");
        btnModificar.setText("ACTUALIZAR");
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblDatosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMousePressed
        // TODO add your handling code here:
        int item= tblDatos.getSelectedRow();
        if (item!=-1) {
            txtRuc.setText(tblDatos.getValueAt(item,0).toString());
            txtNom.setText(tblDatos.getValueAt(item,1).toString());
            dteFInscripcion.setDate((Date) tblDatos.getValueAt(item,4));
            txtTel.setText(tblDatos.getValueAt(item,2).toString());
            txtdir.setText(tblDatos.getValueAt(item,3).toString());
            txtEstado.setText(tblDatos.getValueAt(item,5).toString());
            btnModificar.setText("ACTUALIZAR");
            btnBaja.setText("DAR DE BAJA");

        }
    }//GEN-LAST:event_tblDatosMousePressed

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        // TODO add your handling code here:
         char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "INGRESAR SOLO NUMEROS", "SISTEMA", JOptionPane.ERROR_MESSAGE);
        }
        if (txtRuc.getText().length()==11) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtRucKeyTyped

    private void txtTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "INGRESAR SOLO NUMEROS", "SISTEMA", JOptionPane.ERROR_MESSAGE);
        }
        if (txtTel.getText().length()>=9) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtTelKeyTyped

    private void txtNomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomKeyTyped
        // TODO add your handling code here
        char c = evt.getKeyChar();
        if(Character.isDigit(c)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo letras", "Advertencia", WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txtNomKeyTyped

    private void btnModificarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMousePressed
        if(btnModificar.getText().equalsIgnoreCase("ACTUALIZAR")){
            modificar();
            consultar();
        }
        
        
    }//GEN-LAST:event_btnModificarMousePressed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        // TODO add your handling code here:lblDni.setText("Buscar DNI:");
        btnBaja.setText("DAR DE BAJA");
        lblRuc.setText("Buscar RUC:");
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnBajaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBajaMousePressed
        // TODO add your handling code here:
        if(btnBaja.getText().equalsIgnoreCase("DAR DE BAJA")){
            darBaja();
            consultar();
        }
    }//GEN-LAST:event_btnBajaMousePressed

    private void txtRucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucActionPerformed

    private void txtdirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdirKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if((c< 'a' || c>'z') && (c< 'A' || c>'Z')&& (c>' ')&&(c<'á'||c>'ú')&&(c<'Á'||c>'Ú') && txtdir.getText().length()==50 && (c>='0'||c<='9') && (c>'.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdirKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
   
        if (lblRuc.getText().equalsIgnoreCase("RUC:")) {
            try {
                consultarRuc();
            } catch (ParseException ex) {
                Logger.getLogger(Registrar_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            buscarRuc();
        }

            
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{

            OutputStream file = new FileOutputStream(new File("C://impresiones//Lista de Proveedores.pdf"));
            Document document = new Document(PageSize.A4,2,2,2,2);

            PdfWriter.getInstance(document, file);
            document.open();
            PdfPTable tabla = new PdfPTable(6);
            Paragraph p = new Paragraph("Lista de Proveedores \n\n", FontFactory.getFont("Arial",16,Font.ITALIC,BaseColor.BLUE));

            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            tabla.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
            //tabla.setExtendLastRow(isSelected);
            document.add(new Paragraph(""));

            // dni, nomb, apell ,edad ,sexo, correo, cel,fecha,direcc, dep,prov,dist,estado
            // float[] mediaCeldas ={15.30f,20.50f,30.50f,8.70f,10.70f,15.50f,15.30f,30.50f,20.50f,20.70f,20.70f,20.50f,15.50f};

            tabla.setWidthPercentage(100);

            tabla.addCell(new Paragraph("RUC", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("NOMBRE", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("TELEFONO", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("DIRECCION", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("F. DE INSCRIPCION", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("ESTADO", FontFactory.getFont("Arial",7)));
            

            ListaEnlazada datos = Lista_Proveedores.consultar();
            String etiqueta;

            for (int i = 0; i < datos.tamaño(); i++) {

                Proveedor cdb = (Proveedor)datos.Buscar(i);

                tabla.addCell(new Paragraph(cdb.getRUC(), FontFactory.getFont("Arial",6)));
                tabla.addCell(new Paragraph(cdb.getNombre(), FontFactory.getFont("Arial",6)));
                tabla.addCell(new Paragraph(cdb.getTelefono(), FontFactory.getFont("Arial",6)));                
                tabla.addCell(new Paragraph(cdb.getDireccion(), FontFactory.getFont("Arial",6)));
                tabla.addCell(new Paragraph(String.valueOf(cdb.getFInscripcion()), FontFactory.getFont("Arial",6)));

                if (cdb.getVigencia()==1) {
                    etiqueta = "Activo";
                    tabla.addCell(new Paragraph(etiqueta, FontFactory.getFont("Arial",6)));
                }else{
                    etiqueta = "Inactivo";
                    tabla.addCell(new Paragraph(etiqueta, FontFactory.getFont("Arial",6)));
                }

            }

            document.add(tabla);
            document.close();
            file.close();

        } catch (DocumentException | IOException ex) {
            Logger.getLogger(Registrar_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try{
            File file = new File("C://impresiones//Lista de proveedores.pdf");
            Desktop.getDesktop().open(file);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSave;
    private com.toedter.calendar.JDateChooser dteFInscripcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtdir;
    // End of variables declaration//GEN-END:variables
}
