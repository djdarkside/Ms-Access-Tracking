import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.Component;
import java.sql.Connection;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.sql.Connection;

public class AccessDatabaseConnection {

	public static String dbPath = "\\\\S1P-SPECTRA1\\SpectraPhotos\\UNDERCLASS\\FALL 15 UNDERCLASS\\DataBases\\Tracking Database\\Tracking System 15-16.accdb";
	public static String test = "\"\\\\S1P-SPECTRA1\\SpectraPhotos\\UNDERCLASS\\FALL 15 UNDERCLASS\\DataBases\\Tracking Database\\Tracking System 15-16.accdb\"";
	
	public static Connection connect() throws SQLException {
		//String path = "\\\\S1P-SPECTRA1\\SpectraPhotos\\UNDERCLASS\\FALL 15 UNDERCLASS\\DataBases\\Tracking Database\\Tracking System 15-16.accdb";
		String path = dbPath;
		String s = "jdbc:ucanaccess://" + path;		
		Connection con = null;
		try {
			con = DriverManager.getConnection(s);
			System.out.println("Connection Made");
			return con;
		} catch (Exception ex) {
			System.out.println("No Connection");
			return null;
		} 
	}
	
	public static void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 30; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);
	}
}

/*
	public void getData() {
		try {
			Statement stmt = connect().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM MainTable");
			if (rset.next()) {
				//String school = rset.getString("SCHOOL");
				//String job = rset.getString("Job");
				//System.out.println("Name  : " + school + "  Email : " + job);
			}
		} catch (SQLException ex) {
		}

	}
*/
	
