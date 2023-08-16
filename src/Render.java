import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class Render extends DefaultTableCellRenderer {

    //@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ){

        if(value instanceof JCheckBox){
            JCheckBox jCheckBox = (JCheckBox)value;
            return jCheckBox;
        }


        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row,column);

    }
    
}
