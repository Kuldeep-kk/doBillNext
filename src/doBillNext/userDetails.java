package doBillNext;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class userDetails extends JFrame {

	private JPanel contentPane;

	private JTable table;
	int tx, ty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					userDetails frame = new userDetails();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	PreparedStatement pst=null;
	ResultSet rs=null;

	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Create the frame.
	 */
	public userDetails() {
		connection=databaseconnector.dbConnector();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);

		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.cyan));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 50, 50);
		
		ImageIcon imgusrd=new ImageIcon("myImageData\\usrdetailpic.png");
		lblNewLabel_1.setIcon(imgusrd);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 41, 418, 243);
		contentPane.add(scrollPane);

		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override

			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() -tx, e.getYOnScreen() -ty);
			}
		});

		table = new JTable();
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("  CLOSE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();


			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.repaint();
				lblNewLabel.setOpaque(true);
				lblNewLabel.setBackground(Color.RED);
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.repaint();
				lblNewLabel.setOpaque(true);
				lblNewLabel.setBackground(SystemColor.control);
				lblNewLabel.setForeground(SystemColor.activeCaption);
				lblNewLabel.repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});


		lblNewLabel.setBounds(181, 290, 70, 30);

		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("  -  ");
		lblNewLabel_4.setForeground(SystemColor.activeCaption);
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_4.repaint();
				lblNewLabel_4.setOpaque(true);
				lblNewLabel_4.setBackground(new Color(0, 191, 255));
				lblNewLabel_4.setForeground(Color.WHITE);
				lblNewLabel_4.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_4.repaint();
				lblNewLabel_4.setOpaque(true);
				lblNewLabel_4.setBackground(SystemColor.control);
				lblNewLabel_4.setForeground(SystemColor.activeCaption);
				lblNewLabel_4.repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(Frame.ICONIFIED);

			}
		});
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(405, 11, 33, 25);
		contentPane.add(lblNewLabel_4);

		lblNewLabel_2 = new JLabel("Existing Users");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setBounds(48, 5, 126, 25);
		contentPane.add(lblNewLabel_2);


		try {
			String query="select * from users ";
			pst=connection.prepareStatement(query);


			rs=pst.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();

			DefaultTableModel model= (DefaultTableModel) table.getModel();

			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i=0;i<cols;i++)
				colName[i]=rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colName);

				String nam,fab,pas,eml;


				while(rs.next()) {
					nam=rs.getString(1);
					fab=rs.getString(2);
					pas=rs.getString(3);
					eml=rs.getString(4);
					String s="";
					String f="";
					for(int i=0; i<pas.length();i++){
				        s=s+'◉';
				        
				}
				        for(int k=0; k<fab.length();k++){
					        f=f+'◉';
				        }


					String[] row= {nam,f,s,eml};
					model.addRow(row);


			}
				rs.close();
				pst.close();




		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1);
		}


	}

}
