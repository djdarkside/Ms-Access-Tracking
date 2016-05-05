import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableCellRenderer;

public class DateCellRenderer extends DefaultTableCellRenderer {
    public DateCellRenderer() { super(); }

    public void setValue(Object value) {
        
        	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy : HH:mm");
        	setText((value == null) ? "" : sdf.format(value));        
    }
}
