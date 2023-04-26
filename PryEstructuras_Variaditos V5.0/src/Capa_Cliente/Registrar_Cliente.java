
package Capa_Cliente;

import static Capa_Cliente.Frm_Principal.contenedor;
import Capa_Datos.Controller_ubigeo;
import Capa_Datos.Lista_Clientes;
import Capa_Logica.Cliente;
import Capa_Logica.clsExportarExcel;
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
import javax.swing.table.DefaultTableModel;
import utils.Alternos;

/**
 *
 * @author Gianx
 */
public class Registrar_Cliente extends javax.swing.JInternalFrame {
    Colores color;
    Controller_ubigeo ubi = new Controller_ubigeo();
    clsExportarExcel obj;
    
    public Registrar_Cliente() throws SQLException {
        initComponents(); 
        this.setTitle("--REGISTRAR NUEVO CLIENTE--");
        ubi.Sql="SELECT departamento FROM ubdepartamento ORDER BY departamento ASC";
        ubi.llenarComboUBIGEO(cbodepa, ubi.Sql,1);
        Deshabilitar();
  
    }   
    void provincia(){
        cboprov.addItem("Seleccionar");
        if(cbodepa.getSelectedIndex()>=1){
            ubi.Sql="select p.provincia from ubdepartamento d, ubprovincia p "
                    + "where d.idDepa=p.idDepa AND d.departamento='"+cbodepa.getSelectedItem().toString()+"' "
                    + "ORDER BY p.provincia ASC";
            ubi.llenarComboUBIGEO(cboprov, ubi.Sql, 1);            
            cbodist.removeAllItems();            
        }
    }
    void distrito(){       
        cbodist.addItem("Seleccionar");
            if(cboprov.getSelectedIndex()>=1){
                ubi.Sql="select di.distrito from ubdepartamento d, ubprovincia p, ubdistrito di "
                        + "where d.idDepa=p.idDepa AND p.idProv=di.idProv AND p.provincia='"+cboprov.getSelectedItem().toString()+"' "
                        + "ORDER BY di.distrito ASC";
                ubi.llenarComboUBIGEO(cbodist, ubi.Sql, 1);
            }
        
    }
    void registrarCliente(){
      
        String dni = txtDni.getText();
        String nom = txtNom.getText();
        String ape = txtApe.getText();
        byte edad = Byte.parseByte(txtEdad.getText());
        String sex = cboSexo.getSelectedItem().toString();
        String correo = txtCorreo.getText();
        String cel = txtCel.getText();
        Date fechan= txtFnacimiento.getDate();        
        long d = fechan.getTime();
        java.sql.Date fechaNac = new java.sql.Date(d);
        
        
        String dir = txtdir.getText();

        String dep = cbodepa.getSelectedItem().toString();
        String pro = cboprov.getSelectedItem().toString();
        String dis = cbodist.getSelectedItem().toString();
        
        if(dni.equalsIgnoreCase("") && nom.equalsIgnoreCase("") && ape.equalsIgnoreCase("") && cel.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "No puede dejar Campos vacíos");
        }else{
            int pos = 0;
            pos = Lista_Clientes.buscarCliente(dni);
            if (pos >= 0) {
                JOptionPane.showMessageDialog(null, "El DNI ingresado ya existe");
                limpiar();
            }else{
            Cliente cdb = new Cliente();
            cdb.setDNI(dni);
            cdb.setNombre(nom);
            cdb.setApellidos(ape);
            cdb.setEdad(edad);
            cdb.setSexo(sex);
            cdb.setCorreo(correo);
            cdb.setCelular(cel);
            cdb.setFnac(fechaNac);
            cdb.setDireccion(dir);
            cdb.setDep(dep);
            cdb.setProv(pro);
            cdb.setDist(dis);
            cdb.setEstado(1);            
            Lista_Clientes.adicionar(cdb);
         
            Alternos.message("Cliente registrado");
            this.btnSave.setText("NUEVO");
            limpiar();
            Deshabilitar();        
        }
        }
    }
    void Deshabilitar(){
        txtDni.setEnabled(false);
        txtNom.setEnabled(false);
        txtApe.setEnabled(false);
        txtCel.setEnabled(false);
        txtEdad.setEnabled(false);
        txtdir.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtFnacimiento.setEnabled(false);
        cboSexo.setEnabled(false);
        cbodepa.setEnabled(false);
        cboprov.setEnabled(false);
        cbodist.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBaja.setEnabled(false);
        btnBuscarDni.setEnabled(false);
    }
     void Habilitar(){
        txtdir.setEnabled(true);
        txtDni.setEnabled(true);
        txtNom.setEnabled(true);
        txtApe.setEnabled(true);
        txtEdad.setEnabled(true);
        txtCel.setEnabled(true);
        txtCorreo.setEnabled(true);
        txtFnacimiento.setEnabled(true);
        cboSexo.setEnabled(true);
        cbodepa.setEnabled(true);
        cboprov.setEnabled(true);
        cbodist.setEnabled(true);
        btnModificar.setEnabled(true);
        btnBaja.setEnabled(true);
        btnBuscarDni.setEnabled(true);       
    }
     void limpiar(){
        txtDni.setText("");
        txtNom.setText("");
        txtApe.setText("");
        txtEdad.setText("");
        txtCel.setText("");
        txtdir.setText("");
        txtCorreo.setText("");
        txtFnacimiento.setCalendar(null);
        cboSexo.setSelectedIndex(-1);
        cbodepa.setSelectedIndex(-1);
        cboprov.setSelectedIndex(-1);
        cbodist.setSelectedIndex(-1);
       
     }
     
     void consultar(){
         DefaultTableModel model = new DefaultTableModel();         
         model.addColumn("DNI");
         model.addColumn("NOMBRES");
         model.addColumn("APELLIDOS");
         model.addColumn("EDAD");
         model.addColumn("SEXO");
         model.addColumn("CORREO");
         model.addColumn("CELULAR");
         model.addColumn("FECHA NAC");
         model.addColumn("DIRECCION");
         model.addColumn("DEP");
         model.addColumn("PROV");
         model.addColumn("DIST");         
         model.addColumn("ESTADO");         
         ListaEnlazada datos = Cliente.consultarTodo();
         Object Dato[] = new Object[13];           
         String etiqueta;
         for (int i = 0; i < datos.tamaño(); i++) {
             Cliente cdb = (Cliente)datos.Buscar(i);
             Dato[0]=cdb.getDNI();
             Dato[1]=cdb.getNombre();
             Dato[2]=cdb.getApellidos();
             Dato[3]=cdb.getEdad();
             Dato[4]=cdb.getSexo();
             Dato[5]=cdb.getCorreo();
             Dato[6]=cdb.getCelular();
             Dato[7]=cdb.getFnac();
             Dato[8]=cdb.getDireccion();
             Dato[9]=cdb.getDep();
             Dato[10]=cdb.getProv();
             Dato[11]=cdb.getDist();
             if (cdb.getEstado()==1) {
                etiqueta = "Activo"; 
                
             }else{
                 etiqueta = "Inactivo";
             }
             Dato[12]=etiqueta;
             
             model.addRow(Dato);
         }
         this.tblDatos.setModel(model);
         
        Colores color = new Colores(12);
        tblDatos.getColumnModel().getColumn(12).setCellRenderer(color);
    
     }     
     void buscarDni(){
         String dni = txtDni.getText();
         if (dni.isEmpty()) {
             JOptionPane.showMessageDialog(null, "Ingrese Dni", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
         }else{
             if (dni.length()== 8) {
                  ListaEnlazada datos  = Cliente.BuscarDniCliente(dni);
                 for (int i = 0; i < datos.tamaño(); i++) {
                     Cliente cdb = (Cliente)datos.Buscar(i);
                     txtNom.setText(cdb.getNombre());
                     txtApe.setText(cdb.getApellidos());                     
                     txtEdad.setText(String.valueOf(cdb.getEdad()));                     
                     Date fechan= cdb.getFnac();      
                    long d = fechan.getTime();
                    java.sql.Date fechaNac = new java.sql.Date(d);
                     txtFnacimiento.setDate(fechaNac);
                     txtCorreo.setText(cdb.getCorreo());
                     txtCel.setText(cdb.getCelular());
                     txtdir.setText(cdb.getDireccion());
                     cboSexo.setSelectedItem(cdb.getSexo());
                     cbodepa.setSelectedItem(cdb.getDep());
                     cboprov.setSelectedItem(cdb.getProv());
                     cbodist.setSelectedItem(cdb.getDist());                     
                 }
             } else {                
                     JOptionPane.showMessageDialog(null, "Ingrese Dni de 8 digitos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                 }        
        }
     }
     void consultarDni() throws ParseException {
           String dni= txtDni.getText();
           String enlace = "https://api.apis.net.pe/v1/dni?numero="+dni;
            
        try {
            
            URL url= new URL(enlace);
            URLConnection request= url.openConnection();
            request.connect();
            
            JsonParser jp= new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
            JsonObject rootObj = root.getAsJsonObject();
           // System.out.println(""+rootObj);
            String paterno= rootObj.get("apellidoPaterno").getAsString();
            String materno= rootObj.get("apellidoMaterno").getAsString();
            String nombres= rootObj.get("nombres").getAsString();
            
            txtApe.setText(paterno +" "+ materno);
            txtNom.setText(nombres);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Registrar_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Registrar_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     void darBaja(){
       Date fechan= txtFnacimiento.getDate();
        long d = fechan.getTime();
        java.sql.Date fechaNac = new java.sql.Date(d);
        String dni = txtDni.getText();
        String nom = txtNom.getText();
        String ape = txtApe.getText();
        byte edad = Byte.parseByte(txtEdad.getText());
        String tel = txtCel.getText();
        String dir = txtdir.getText();
        String correo = txtCorreo.getText();
        String sex = cboSexo.getSelectedItem().toString();
        String dep = cbodepa.getSelectedItem().toString();
        String pro = cboprov.getSelectedItem().toString();
        String dis = cbodist.getSelectedItem().toString();   
        int pos =0;
        pos = Lista_Clientes.buscarCliente(dni);
                  
        if(pos >=0){
            Cliente cdb = new Cliente();            
            cdb.setDNI(dni);
            cdb.setNombre(nom);
            cdb.setApellidos(ape);
            cdb.setEdad(edad);
            cdb.setCorreo(correo);
            cdb.setCelular(tel);
            cdb.setSexo(sex);
            cdb.setFnac(fechaNac);
            cdb.setDireccion(dir);
            cdb.setDep(dep);
            cdb.setProv(pro);
            cdb.setDist(dis);
            cdb.setEstado(0);
            Lista_Clientes.modificar(cdb,pos);            
            JOptionPane.showMessageDialog(rootPane, "Registro se dió de baja correctamente");
            limpiar();
        }else
            JOptionPane.showMessageDialog(rootPane, "El proceso no de realizó");
        limpiar();
       
     } 
     void modificar(){
        Date fechan= txtFnacimiento.getDate();
        long d = fechan.getTime();
        java.sql.Date fechaNac = new java.sql.Date(d);
        String dni = txtDni.getText();
        String nom = txtNom.getText();
        String ape = txtApe.getText();
        byte edad = Byte.parseByte(txtEdad.getText());
        String cel = txtCel.getText();
        String dir = txtdir.getText();
        String correo = txtCorreo.getText();
        String sex = cboSexo.getSelectedItem().toString();
        String dep = cbodepa.getSelectedItem().toString();
        String pro = cboprov.getSelectedItem().toString();
        String dis = cbodist.getSelectedItem().toString();   
        int pos =0;
        pos = Lista_Clientes.buscarCliente(dni);
                  
        if(pos >=0){
            Cliente cdb = new Cliente();            
            cdb.setDNI(dni);
            cdb.setNombre(nom);
            cdb.setApellidos(ape);
            cdb.setEdad(edad);
            cdb.setCorreo(correo);
            cdb.setCelular(cel);
            cdb.setSexo(sex);
            cdb.setFnac(fechaNac);
            cdb.setDireccion(dir);
            cdb.setDep(dep);
            cdb.setProv(pro);
            cdb.setDist(dis);
            cdb.setEstado(1);
            Lista_Clientes.modificar(cdb,pos);            
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
        formulario = new javax.swing.JPanel();
        lblDni = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        btnBuscarDni = new javax.swing.JButton();
        txtNom = new javax.swing.JTextField();
        txtApe = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCel = new javax.swing.JTextField();
        cboSexo = new javax.swing.JComboBox();
        cbodepa = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cboprov = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cbodist = new javax.swing.JComboBox();
        txtdir = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFnacimiento = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        btnExportarPDF = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        btnExportareExcel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jButton1.setText("jButton1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRAR CLIENTE");

        formulario.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDni.setText("DNI:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellidos:");

        txtDni.setForeground(new java.awt.Color(204, 204, 204));
        txtDni.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        btnBuscarDni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscadorOriginal (1).png"))); // NOI18N
        btnBuscarDni.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscarDni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBuscarDniMousePressed(evt);
            }
        });
        btnBuscarDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDniActionPerformed(evt);
            }
        });

        txtNom.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomKeyTyped(evt);
            }
        });

        txtApe.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtApe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApeKeyTyped(evt);
            }
        });

        jLabel4.setText("Dirección:");

        jLabel5.setText("Sexo:");

        jLabel6.setText("Departamento:");

        txtCel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtCel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelKeyTyped(evt);
            }
        });

        cboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));
        cboSexo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cbodepa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cbodepa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbodepaItemStateChanged(evt);
            }
        });
        cbodepa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbodepaActionPerformed(evt);
            }
        });

        jLabel7.setText("Provincia:");

        cboprov.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cboprov.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboprovItemStateChanged(evt);
            }
        });

        jLabel8.setText("Distrito");

        cbodist.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setText("Celular:");

        jLabel10.setText("Correo:");

        txtCorreo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
        });

        jLabel11.setText("Fecha de nacimiento:");

        txtFnacimiento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Edad:");

        txtEdad.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout formularioLayout = new javax.swing.GroupLayout(formulario);
        formulario.setLayout(formularioLayout);
        formularioLayout.setHorizontalGroup(
            formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formularioLayout.createSequentialGroup()
                        .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDni)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(cbodepa, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboprov, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addGroup(formularioLayout.createSequentialGroup()
                                .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(txtdir, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtFnacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(36, 36, 36)
                                .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApe, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8)
                                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(cbodist, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel9)
                    .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnBuscarDni, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCel, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formularioLayout.setVerticalGroup(
            formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDni)
                    .addComponent(btnBuscarDni, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txtNom)
                    .addComponent(txtApe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel1))
                .addGap(7, 7, 7)
                .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFnacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(formularioLayout.createSequentialGroup()
                        .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formularioLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbodepa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioLayout.createSequentialGroup()
                        .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboprov, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbodist, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDatosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDatos);

        btnExportarPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pdfOriginal.png"))); // NOI18N
        btnExportarPDF.setText("EXPORTAR A PDF");
        btnExportarPDF.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExportarPDF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExportarPDF.setIconTextGap(10);
        btnExportarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarPDFActionPerformed(evt);
            }
        });

        btnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gmailOriginal.png"))); // NOI18N
        btnEnviar.setText("ENVIAR A GMAIL");
        btnEnviar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEnviar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEnviar.setIconTextGap(10);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnExportareExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excelOriginal.png"))); // NOI18N
        btnExportareExcel.setText("EXPORTAR EXCEL");
        btnExportareExcel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExportareExcel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExportareExcel.setIconTextGap(10);
        btnExportareExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportareExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExportareExcel)
                .addGap(62, 62, 62)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportarPDF)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExportareExcel)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnExportarPDF))
                .addContainerGap())
        );

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addOriginal.png"))); // NOI18N
        btnSave.setText("NUEVO");
        btnSave.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.setIconTextGap(10);
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

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editOriginal.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModificar.setIconTextGap(10);
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

        btnBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deleteOriginal.png"))); // NOI18N
        btnBaja.setText("DAR BAJA");
        btnBaja.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBaja.setIconTextGap(10);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(formulario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnModificar)
                    .addComponent(btnBaja))
                .addGap(18, 18, 18)
                .addComponent(formulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDniActionPerformed
        if (lblDni.getText().equalsIgnoreCase("DNI:")) {
            try {
                consultarDni();
            } catch (ParseException ex) {
                Logger.getLogger(Registrar_Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            buscarDni();
        } 
    }//GEN-LAST:event_btnBuscarDniActionPerformed

    private void cbodepaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbodepaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbodepaActionPerformed

    private void cboprovItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboprovItemStateChanged
        // TODO add your handling code here:
      distrito();
    }//GEN-LAST:event_cboprovItemStateChanged

    private void cbodepaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbodepaItemStateChanged
        // TODO add your handling code here:
       provincia();
    }//GEN-LAST:event_cbodepaItemStateChanged

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
            registrarCliente();            
        }
        consultar();
    }//GEN-LAST:event_btnSaveMousePressed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        lblDni.setText("Buscar DNI:");
        btnModificar.setText("ACTUALIZAR");
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBuscarDniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarDniMousePressed
        
        if (lblDni.getText().equalsIgnoreCase("DNI:")) {
            try {
                consultarDni();
            } catch (ParseException ex) {
                Logger.getLogger(Registrar_Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            buscarDni();
        }
    }//GEN-LAST:event_btnBuscarDniMousePressed

    private void tblDatosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMousePressed
        // TODO add your handling code here:
        int item= tblDatos.getSelectedRow();
        if (item!=-1) {
            txtDni.setText(tblDatos.getValueAt(item,0).toString());
            txtNom.setText(tblDatos.getValueAt(item,1).toString());
            txtApe.setText(tblDatos.getValueAt(item,2).toString());  
            txtEdad.setText(tblDatos.getValueAt(item,3).toString());  
            cboSexo.setSelectedItem(tblDatos.getValueAt(item,4).toString());
            txtCorreo.setText(tblDatos.getValueAt(item,5).toString());
            txtCel.setText(tblDatos.getValueAt(item,6).toString());
            txtFnacimiento.setDate((Date) tblDatos.getValueAt(item,7));
            txtdir.setText(tblDatos.getValueAt(item,8).toString());
            cbodepa.setSelectedItem(tblDatos.getValueAt(item,9).toString());
            cboprov.setSelectedItem(tblDatos.getValueAt(item,10).toString());
            cbodist.setSelectedItem(tblDatos.getValueAt(item,11).toString());
            btnModificar.setText("ACTUALIZAR");
            btnBaja.setText("DAR DE BAJA");
        }
    }//GEN-LAST:event_tblDatosMousePressed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
         char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "INGRESAR SOLO NUMEROS", "SISTEMA", JOptionPane.ERROR_MESSAGE);
        }
        if (txtDni.getText().length()>=8) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtCelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(rootPane, "INGRESAR SOLO NUMEROS", "SISTEMA", JOptionPane.ERROR_MESSAGE);
        }
        if (txtCel.getText().length()>=9) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtCelKeyTyped

    private void txtNomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomKeyTyped
        // TODO add your handling code here:
        char cdb = evt.getKeyChar();
        if ((cdb<'a'||cdb>'z') && (cdb<'A'| cdb>'Z')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNomKeyTyped

    private void txtApeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeKeyTyped
        // TODO add your handling code here:
        char cdb = evt.getKeyChar();
        if ((cdb<'a'||cdb>'z') && (cdb<'A'| cdb>'Z')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApeKeyTyped

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txtCorreoKeyReleased

    private void btnModificarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMousePressed
        if(btnModificar.getText().equalsIgnoreCase("ACTUALIZAR")){
            modificar();
            consultar();
        }
        
        
    }//GEN-LAST:event_btnModificarMousePressed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        // TODO add your handling code here:lblDni.setText("Buscar DNI:");
        btnBaja.setText("DAR DE BAJA");
        lblDni.setText("Buscar DNI:");
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnBajaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBajaMousePressed
        // TODO add your handling code here:
        if(btnBaja.getText().equalsIgnoreCase("DAR DE BAJA")){
            darBaja();
            consultar();
        }
    }//GEN-LAST:event_btnBajaMousePressed

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadKeyTyped

    private void btnExportarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarPDFActionPerformed
     try{

      OutputStream file = new FileOutputStream(new File("C://impresiones//Lista de Clientes.pdf"));
      Document document = new Document(PageSize.A4,2,2,2,2);
          

      PdfWriter.getInstance(document, file);  
      document.open();
      PdfPTable tabla = new PdfPTable(13);
      Paragraph p = new Paragraph("Lista de Clientes \n\n", FontFactory.getFont("Arial",16,Font.ITALIC,BaseColor.BLUE));
                                    
         p.setAlignment(Element.ALIGN_CENTER);
         document.add(p);
        
         tabla.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
         //tabla.setExtendLastRow(isSelected);
         document.add(new Paragraph(""));
         
                                  // dni, nomb, apell ,edad ,sexo, correo, cel,fecha,direcc, dep,prov,dist,estado
          // float[] mediaCeldas ={15.30f,20.50f,30.50f,8.70f,10.70f,15.50f,15.30f,30.50f,20.50f,20.70f,20.70f,20.50f,15.50f};

           tabla.setWidthPercentage(100);
            
            tabla.addCell(new Paragraph("DNI", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("NOMBRES", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("APELLIDOS", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("EDAD", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("SEXO", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("CORREO", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("CELULAR", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("FECHA N.", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("DIRECCION", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("DEP.", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("PROV.", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("DIST.", FontFactory.getFont("Arial",7)));
            tabla.addCell(new Paragraph("ESTADO", FontFactory.getFont("Arial",7)));
              
             
        ListaEnlazada datos = Lista_Clientes.consultar();
        String etiqueta;
         
         for (int i = 0; i < datos.tamaño(); i++) {
             
             Cliente cdb = (Cliente)datos.Buscar(i);
         
             tabla.addCell(new Paragraph(cdb.getDNI(), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(cdb.getNombre(), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(cdb.getApellidos(), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(String.valueOf(cdb.getEdad()), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(cdb.getSexo(), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(cdb.getCorreo(), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(cdb.getCelular(), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(String.valueOf(cdb.getFnac()), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(cdb.getDireccion(), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(cdb.getDep(), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(cdb.getProv(), FontFactory.getFont("Arial",6)));                  
             tabla.addCell(new Paragraph(cdb.getDist(), FontFactory.getFont("Arial",6)));                  
         //   tabla.addCell(new Paragraph(cdb.getEstado(), FontFactory.getFont("Arial",10)));                  
         
             if (cdb.getEstado()==1) {
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
            Logger.getLogger(Registrar_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      try{
          File file = new File("C://impresiones//Lista de clientes.pdf");
          Desktop.getDesktop().open(file);
       }   
      catch (IOException e){
        e.printStackTrace();
       } 
    }//GEN-LAST:event_btnExportarPDFActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
    
        Enviar_Gmail enviarGmail;
        try {
            enviarGmail = new Enviar_Gmail();
            Frm_Principal.contenedor.add(enviarGmail);
            enviarGmail.setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(Registrar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnExportareExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportareExcelActionPerformed

        try {
            obj = new clsExportarExcel();
            obj.exportarExcel(tblDatos);
        } catch (IOException ex) {
            Logger.getLogger(frm_Report_Compra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnExportareExcelActionPerformed

        


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBuscarDni;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnExportarPDF;
    public javax.swing.JButton btnExportareExcel;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboSexo;
    private javax.swing.JComboBox cbodepa;
    private javax.swing.JComboBox cbodist;
    private javax.swing.JComboBox cboprov;
    private javax.swing.JPanel formulario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblDni;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtApe;
    private javax.swing.JTextField txtCel;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEdad;
    private com.toedter.calendar.JDateChooser txtFnacimiento;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtdir;
    // End of variables declaration//GEN-END:variables
}
