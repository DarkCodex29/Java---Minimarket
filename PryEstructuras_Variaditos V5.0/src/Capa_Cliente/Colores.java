package Capa_Cliente;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author Gianx
 */
public class Colores extends DefaultTableCellRenderer{
    private final int columna_patron;
    
    public Colores(int Colpatron) {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        Font font = new Font("Courier", Font.BOLD, 16);
        switch (table.getValueAt(row, columna_patron).toString()) {
            case "Inactivo":              
                setForeground(Color.RED);
                setFont(font);
                break;
            case "Activo":
                setForeground(Color.BLUE);
                setFont(font);
                break;
            default:
                break;
        }
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        return this;
    }
}
