import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class AccessTracking1 {

	private JFrame frame;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccessTracking1 window = new AccessTracking1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	

	public AccessTracking1() throws SQLException {
		initialize();
	}

	private void initialize() throws SQLException {

		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setTitle("Studio One Tracking System");
		frame.setBounds(100, 100, 1200, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);		
		frame.setLocationRelativeTo(null);

		Statement stmt = AccessDatabaseConnection.connect().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT [School] & \" \" & [Job] AS JobNameMainTable.JobType, MainTable.JobType, MainTable.[PCL WO], MainTable.Status, "
		+ "MainTable.[DaTaskToLab Date], MainTable.[DaTaskBackLab Date], MainTable.[Start Date], MainTable.[Due Date] FROM MainTable WHERE (((MainTable.ActiveJob)=Yes)) ORDER BY MainTable.JobType, [School] & \" \" & [Job]");		
		
		table = new JTable(AccessDatabaseConnection.buildTableModel(rs));
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setBounds(0, 25, 1174, 885);
		table.getColumnModel().getColumn(4).setCellRenderer(new DateCellRenderer());
		table.getColumnModel().getColumn(5).setCellRenderer(new DateCellRenderer());
		table.getColumnModel().getColumn(6).setCellRenderer(new DateCellRenderer());
		table.getColumnModel().getColumn(7).setCellRenderer(new DateCellRenderer());
		AccessDatabaseConnection.resizeColumnWidth(table);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 11, 1174, 534);
		frame.getContentPane().add(scrollPane);		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Credits");
		JMenu openDB = new JMenu("Open Tracking");
		openDB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {		
					Runtime run = Runtime.getRuntime();
					System.out.println(AccessDatabaseConnection.test);
					Process pro = run.exec("cmd /c" + AccessDatabaseConnection.test);		
					System.exit(1);
					int retcode = pro.waitFor();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		fileMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Created by Mitchell Baptist 2016", "Studio One Photography", JOptionPane.INFORMATION_MESSAGE);				
			}
		});
		fileMenu.setMnemonic(KeyEvent.VK_C);
		menuBar.add(fileMenu);
		//menuBar.add(openDB);
		frame.setJMenuBar(menuBar);
	}
}
