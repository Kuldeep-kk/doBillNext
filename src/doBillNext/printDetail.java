package doBillNext;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class printDetail extends JFrame {

	private JPanel contentPane;
	private static JTextField txtItzoneInc;
	private static JTextField textField_1;
	private JTable table;
	private static JTextArea address;



	String currentname;
	String currentaddress;
	String currentmob;


	static String Skname;
	static String Skadd;
	static String Skmob;

	private static Connection connection=null;
	static PreparedStatement pstp=null;
	static PreparedStatement pstc=null;
	static ResultSet rsp=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					printDetail frame = new printDetail();
					frame.setUndecorated(true);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void updatedtl() {
try {

			String query="select * from printdb where cid='1'";
			pstp=connection.prepareStatement(query);


			rsp=pstp.executeQuery();

				while(rsp.next()) {

					txtItzoneInc.setText(rsp.getString("cname"));
					textField_1.setText(rsp.getString("cmob"));
					address.setText(rsp.getString("caddress"));




			}
				pstp.close();
				rsp.close();
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	/**
	 * Create the frame.
	 */
	public printDetail() {

		connection=printdatabase.dbConnector();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createMatteBorder( 2, 2, 4, 4, new Color(225, 127, 80)));


		JLabel lblNewLabel = new JLabel("Shopkeeper Receipt Details");
		lblNewLabel.setForeground(new Color(0, 191, 255));
		lblNewLabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 40));
		lblNewLabel.setBounds(10, 23, 518, 49);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 127, 80));
		separator.setBounds(10, 70, 617, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 255, 0));
		separator_1.setBounds(10, 75, 617, 2);
		contentPane.add(separator_1);

		JLabel lblNewLabel_1 = new JLabel("Shop Name :");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(130, 106, 127, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Shop Address :");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(117, 150, 140, 25);
		contentPane.add(lblNewLabel_1_1);

		txtItzoneInc = new JTextField();

		txtItzoneInc.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtItzoneInc.setBounds(245, 111, 222, 20);
		contentPane.add(txtItzoneInc);
		txtItzoneInc.setColumns(10);

		JLabel lblNewLabel_1_2 = new JLabel("Mobile No :");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(144, 304, 127, 25);
		contentPane.add(lblNewLabel_1_2);

		textField_1 = new JTextField();

		textField_1.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_1.setColumns(10);
		textField_1.setBounds(245, 306, 114, 20);
		contentPane.add(textField_1);

		table = new JTable();
		table.setBounds(322, 212, 1, 1);
		contentPane.add(table);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(245, 153, 222, 140);
		contentPane.add(scrollPane);

		address = new JTextArea();


		address.setFont(new Font("Dialog", Font.ITALIC, 17));
		scrollPane.setViewportView(address);

		updatedtl();



		currentname=txtItzoneInc.getText();
		currentaddress=address.getText();
		currentmob=textField_1.getText();


		if(txtItzoneInc.getText().length()>0)
			currentname=txtItzoneInc.getText();

		if(address.getText().length()>0)
		currentaddress=address.getText();

		if(textField_1.getText().length()>0)
		currentmob=textField_1.getText();

		Button button = new Button("Update");
		button.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 20));
		button.setForeground(new Color(255, 127, 80));
		button.setBackground(new Color(0, 191, 255));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(address.getText());
				if(txtItzoneInc.getText().length()>0)
					currentname=txtItzoneInc.getText();
				else
					JOptionPane.showMessageDialog(null, "You Have Not Entered Any Data To (Shop Name) Previous Data Loaded");
				txtItzoneInc.setText(currentname);

				if(address.getText().length()>0)
				currentaddress=address.getText();
				else
					JOptionPane.showMessageDialog(null, "You Have Not Entered Any Data To (Shop Address) Previous Data Loaded");
					address.setText(currentaddress);

				if(textField_1.getText().length()>0)
				currentmob=textField_1.getText();
				else
					JOptionPane.showMessageDialog(null, "You Have Not Entered Any Data To (Mobile No) Previous Data Loaded");
				textField_1.setText(currentmob);



				try {
					String query2="update printdb set cname='"+currentname+" ',caddress='"+currentaddress+"' ,cmob='"+currentmob+"' where cid='1'";
					pstc=connection.prepareStatement(query2);
					pstc.execute();
					pstc.close();
					JOptionPane.showMessageDialog(null, "Data Updated");



				}
				catch(Exception e2) {

					JOptionPane.showMessageDialog(null, e2);


				}


			}

		});
		button.setBounds(274, 354, 85, 31);
		contentPane.add(button);

		JLabel lblNewLabel_1_3 = new JLabel(" X ");
		lblNewLabel_1_3.repaint();
		lblNewLabel_1_3.setOpaque(true);
		lblNewLabel_1_3.setForeground(SystemColor.activeCaption);
		lblNewLabel_1_3.setBackground(new Color(224, 255, 255));
		lblNewLabel_1_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {



				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1_3.repaint();
				lblNewLabel_1_3.setOpaque(true);
				lblNewLabel_1_3.setBackground(Color.RED);
				lblNewLabel_1_3.setForeground(Color.WHITE);
				lblNewLabel_1_3.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1_3.repaint();
				lblNewLabel_1_3.setOpaque(true);
				lblNewLabel_1_3.setBackground(new Color(224, 255, 255));
				lblNewLabel_1_3.setForeground(SystemColor.activeCaption);
				lblNewLabel_1_3.repaint();
			}
		});
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblNewLabel_1_3.setBounds(621, 0, 27, 25);
		contentPane.add(lblNewLabel_1_3);


	}
}
