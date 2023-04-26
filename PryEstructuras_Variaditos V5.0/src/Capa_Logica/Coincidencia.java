/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Capa_Logica;

import java.util.Date;
import utils.Alternos;

/**
 *
 * @author Gianx
 */
public class Coincidencia {
    public  int mes;
    public String codVenta;
    public Date fecha;
    public float monto = 0;
    public String getMes(){
        return Alternos.RetornarMes(mes -1);
    }
}
