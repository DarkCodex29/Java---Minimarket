 package Capa_Logica;

import Capa_Datos.Lista_Ventas;
import ListasAux.ListaEnlazada;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
 
/**
 *
 * @author Gianx
 */
public class Venta implements Serializable{
 private String codVenta ;
 private Date fecha;
 private boolean estado;
 private String forma_pago;
 private String codVendedor;
 private String codCliente;
 private float totalVenta;
    public Venta(String codVenta, Date fecha, boolean estado, String forma_pago, float totalVenta) {
        this.codVenta = codVenta;
        this.fecha = fecha;
        this.estado = estado;
        this.forma_pago = forma_pago;
        this.totalVenta = totalVenta;
    }

    public boolean isEstado() {
        return estado;
    }
    public float getTotalVenta() {
        return totalVenta;
    }
    public String getCodVendedor() {
        return codVendedor;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodVendedor(String codVendedor) {
        this.codVendedor = codVendedor;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }
  
    public String getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(String codVenta) {
        this.codVenta = codVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }
    
    public static ListaEnlazada listarVentas(){    
        return  Lista_Ventas.obtener();
    }
    public static ListaEnlazada listarVentasxCliente(String dniCliente){
        ListaEnlazada lista = new ListaEnlazada();
        return lista;
    }
    public static String generarCod(){
       String ma = "ve";
        ma = NumeroAleatorios(4, ma);
        boolean  retu = VerificarCodventa(ma);
        //verificar serie = false (hay uno igual)
        while (!retu) {

            ma = NumeroAleatorios(4, ma);
            retu =  VerificarCodventa(ma);
        }
        return ma;
        
    }
       public static boolean VerificarCodventa(String ma) {
        for (int i = 0; i < Lista_Ventas.obtener().tamaño(); i++) {
            Venta objVenta = (Venta) Lista_Ventas.obtener().Buscar(i);
            if (objVenta.getCodVenta().equalsIgnoreCase(ma)) {
                return false;
            }

        }
        return true;
    }
    public static String NumeroAleatorios(int ref, String ma) {
        for (int i = 0; i < ref; i++) {
            int ramdon = (int) (Math.random() * 9 + 1);
            ma = ma + ramdon;
        }
        return ma;
    }
   
///utils
   public static Date obtenerFechaSistema() {
        Calendar calendario = new GregorianCalendar();
        int dia = calendario.get(Calendar.DATE);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int año = calendario.get(Calendar.YEAR);
        String fecha = año + "-" + mes + "-" + dia;
        java.util.Date retorno = java.sql.Date.valueOf(fecha);
        System.out.println("" + retorno);
        return retorno;

    }    
    

}
