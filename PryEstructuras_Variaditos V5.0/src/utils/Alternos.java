/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Gianx
 */
public class Alternos {
       public static String AcomodaNombre(String nombre) {
        //hacer minuscula todas las letras

        String[] nombres = nombre.split(" ");

        String nombreAc = "";
        for (int i = 0; i < nombres.length; i++) {
            int n = nombres[i].length();
            nombreAc = nombreAc + nombres[i].substring(0, 1).toUpperCase() + nombres[i].substring(1, n) + " ";
        }
        return nombreAc;
    }
           public static void limitarSoloMontos(JTextField texto) {
        texto.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.')) {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
    }
   //limita un numero hasta el tamaño que se le indique
    public static void limitarSoloNumeroRes(JTextField texto, int res) {
        texto.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                String dni = texto.getText();
                char caracter = e.getKeyChar();
                // Verificar si la tecla pulsada no es un digito
                if (dni.length() >= res) {
                    e.consume();
                }
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
                    e.consume();  // ignorar el evento de teclado
                }

            }
        });
    }
     public static void message(String messge){
          JOptionPane.showMessageDialog(null,messge );
     }
    
       public static boolean Probar(JPanel panel) {
        boolean verificacion = true;
        for (int i = 0; i < panel.getComponents().length; i++) {

            if (panel.getComponents()[i] instanceof JTextField) {
                JTextField caja = (JTextField) panel.getComponents()[i];
                if (caja.getText().equals("")) {

                    return false;
                }

            } else {
                if (panel.getComponents()[i] instanceof JComboBox) {
                    JComboBox cbo = (JComboBox) panel.getComponents()[i];
                    if (cbo.getSelectedIndex() == -1) {

                        return false;

                    }
                } else {
                    if (panel.getComponents()[i] instanceof JPasswordField) {
                        JPasswordField campoContraseña = (JPasswordField) panel.getComponents()[i];
                        String contraseña = new String(campoContraseña.getPassword());
                        if (contraseña.equals("") || contraseña.length() <= 5) {
                            return false;
                        }

                    } else {
                        if (panel.getComponents()[i] instanceof JDateChooser) {
                            JDateChooser datoFecha = (JDateChooser) panel.getComponents()[i];
                            if (((JTextField) datoFecha.getDateEditor().getUiComponent()).getText().equalsIgnoreCase("")) {
                                return false;
                            }
                        }
                    }
                }

            }

        }
        return verificacion;
    }
        public static java.util.Date formatoFecha(Date dato) {
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
        String fecha = formato.format(dato);
        java.util.Date retorno = java.sql.Date.valueOf(fecha);
        System.out.println("" + retorno);
        return retorno;
    }
        
     public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    } 
    public static void LimpiarCajas(JPanel panel) {
        for (int i = 0; i < panel.getComponents().length; i++) {
            if (panel.getComponents()[i] instanceof JTextField) {
                JTextField caja = (JTextField) panel.getComponents()[i];
                caja.setText("");

            }
            if (panel.getComponents()[i] instanceof JComboBox) {
                JComboBox cbo = (JComboBox) panel.getComponents()[i];
                cbo.setSelectedIndex(-1);

            }
            if (panel.getComponents()[i] instanceof JTextArea) {
                JTextArea textArea = (JTextArea) panel.getComponents()[i];
                textArea.setText("");
            }
            if (panel.getComponents()[i] instanceof JPasswordField) {
                JPasswordField campoContraseña = (JPasswordField) panel.getComponents()[i];
                campoContraseña.setText("");

            }

        }
    }
    
        public static java.util.List<Date> getIntervaloFechas(java.util.Date fecha_inicio, java.util.Date fecha_fin, int a) {
        Calendar fechaInicio = Calendar.getInstance();//convertir la fechas a calendar por que es mas comodo manejar fechas
        fechaInicio.setTime(fecha_inicio);
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.setTime(fecha_fin);
        java.util.List<Date> listaFechas = new java.util.ArrayList<Date>();
        while (!fechaInicio.after(fechaFin)) {
            listaFechas.add(fechaInicio.getTime());
            fechaInicio.add(a, 1);
        }

        return listaFechas;
    }
       
   public static Date obtenerFechaSistema() {
        Calendar calendario = new GregorianCalendar();
        int dia = calendario.get(Calendar.DATE);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int año = calendario.get(Calendar.YEAR);
        System.out.println("");
        String fecha = año + "-" + mes + "-" + dia;
        java.util.Date retorno = java.sql.Date.valueOf(fecha);
        System.out.println("" + retorno);
        return retorno;

    }
        //lista 
        /// fecha  --  fecha2
        /// 30 fechac 
        // venta.fecha =  fecha
        // detalle.fecha =venta.fecha
        // venta.cliente 
      public static int retornaEdad(Date OBJDate) {
        Date sistema = Alternos.obtenerFechaSistema();

              List<Date> listaFechass = getIntervaloFechas(OBJDate, sistema, Calendar.YEAR);
              List<Integer>  datos = new ArrayList();
      
               
        return listaFechass.size();

    }
          
          
          
        public static String RetornarMes(Date fecha) {
        String mes = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int mesAux = calendar.get(Calendar.MONTH);
        switch (mesAux) {
            case Calendar.JANUARY:
                mes = "Enero";
                break;
            case Calendar.FEBRUARY:
                mes = "Febrero";
                break;
            case Calendar.MARCH:
                mes = "Marzo";
                break;
            case Calendar.APRIL:
                mes = "Abril";
                break;
            case Calendar.MAY:
                mes = "Mayo";
                break;
            case Calendar.JUNE:
                mes = "Junio";
                break;
            case Calendar.JULY:
                mes = "Julio";
                break;
            case Calendar.AUGUST:
                mes = "Agosto";
                break;
            case Calendar.SEPTEMBER:
                mes = "Septiembre";
                break;
            case Calendar.OCTOBER:
                mes = "Octubre";
                break;
            case Calendar.NOVEMBER:
                mes = "Noviembre";
                break;
            case Calendar.DECEMBER:
                mes = "Diciembre";
                break;
            default:
                break;

        }
        return mes;
    }
     public static String RetornarMes(int aux) {
        String mes = null;
        int mesAux = aux;
        switch (mesAux) {
            case Calendar.JANUARY:
                mes = "Enero";
                break;
            case Calendar.FEBRUARY:
                mes = "Febrero";
                break;
            case Calendar.MARCH:
                mes = "Marzo";
                break;
            case Calendar.APRIL:
                mes = "Abril";
                break;
            case Calendar.MAY:
                mes = "Mayo";
                break;
            case Calendar.JUNE:
                mes = "Junio";
                break;
            case Calendar.JULY:
                mes = "Julio";
                break;
            case Calendar.AUGUST:
                mes = "Agosto";
                break;
            case Calendar.SEPTEMBER:
                mes = "Septiembre";
                break;
            case Calendar.OCTOBER:
                mes = "Octubre";
                break;
            case Calendar.NOVEMBER:
                mes = "Noviembre";
                break;
            case Calendar.DECEMBER:
                mes = "Diciembre";
                break;
            default:
                break;

        }
        return mes;
    }
}
