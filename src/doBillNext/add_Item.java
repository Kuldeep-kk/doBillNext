package doBillNext;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class add_Item extends JFrame {

	private JPanel contentPane;
	private JTextField product;
	private JTextField price;
	private JTextField id;

	static int count=0;

int tx,ty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					add_Item frame = new add_Item();
					//ImageIcon img = new ImageIcon("/addDatalogo.png");
					//frame.setIconImage(img.getImage());

					frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/addDatapic.png")));

					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection seconnect=null;
	Connection connection=null;
	PreparedStatement pst = null;
	static ResultSet rs=null;
	JComboBox comboBox;
	static private  JTable table = new JTable();

	/**
	 * Create the frame.
	 */
	public add_Item() {
		seconnect=databaseconnector.dbConnector();
		connection=databaseconnector.dbConnector();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);



		contentPane.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel(" SAVE");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.repaint();
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(new Color(64, 224, 208));

		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_4.repaint();
				lblNewLabel_4.setOpaque(true);
				lblNewLabel_4.setBackground(new Color(255, 165, 0));


			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_4.repaint();
				lblNewLabel_4.setOpaque(true);
				lblNewLabel_4.setBackground(new Color(64, 224, 208));
			}
			@Override
			public void mouseClicked(MouseEvent e) {


				try {
					String query="insert into shop (id,product_name,category,price) values (?,?,?,?)";
					pst=seconnect.prepareStatement(query);
					pst.setString(1,id.getText());
					pst.setString(2,product.getText());
					pst.setString(3,(String)comboBox.getSelectedItem());
					pst.setString(4,price.getText());

					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null, "Data saved");
					mframe.updateTable();
					dispose();



				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);

				}







			}
		});
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel_4.setBounds(196, 240, 70, 25);
		contentPane.add(lblNewLabel_4);

		 comboBox = new JComboBox();
		comboBox.setBounds(196, 108, 127, 22);
		comboBox.addItem("laptop");
		comboBox.addItem("monitor");
		comboBox.addItem("cpu");
		comboBox.addItem("keyboard");
		comboBox.addItem("mouse");
		comboBox.addItem("cables");
		comboBox.addItem("others");
		contentPane.add(comboBox);

		JLabel lblNewLabel = new JLabel("Category : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(117, 107, 73, 22);
		contentPane.add(lblNewLabel);

		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override

			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() -tx, e.getYOnScreen() -ty);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Product Name : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(83, 140, 106, 22);
		contentPane.add(lblNewLabel_1);

		product = new JTextField();
		product.setBounds(196, 142, 180, 20);
		contentPane.add(product);
		product.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Product ID : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(103, 80, 84, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Price : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(140, 175, 63, 20);
		contentPane.add(lblNewLabel_3);

		price = new JTextField();
		price.setBounds(196, 176, 96, 20);
		contentPane.add(price);
		price.setColumns(10);

		id = new JTextField();
		id.setBounds(196, 78, 51, 20);
		contentPane.add(id);
		id.setColumns(10);

		JLabel mainBorder = new JLabel("");
		mainBorder.setBounds(28, 58, 400, 196);
		mainBorder.setBorder(BorderFactory.createTitledBorder("- ADD DATA -"));
		contentPane.add(mainBorder);

		JLabel lblNewLabel_4_1 = new JLabel("  -  ");
		lblNewLabel_4_1.setForeground(SystemColor.activeCaption);
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_4_1.repaint();
				lblNewLabel_4_1.setOpaque(true);
				lblNewLabel_4_1.setBackground(new Color(0, 191, 255));
				lblNewLabel_4_1.setForeground(Color.WHITE);
				lblNewLabel_4_1.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_4_1.repaint();
				lblNewLabel_4_1.setOpaque(true);
				lblNewLabel_4_1.setBackground(SystemColor.control);
				lblNewLabel_4_1.setForeground(SystemColor.activeCaption);
				lblNewLabel_4_1.repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(Frame.ICONIFIED);

			}
		});
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4_1.setBounds(368, 0, 33, 25);
		contentPane.add(lblNewLabel_4_1);

		JLabel lblNewLabel_5 = new JLabel("");
		ImageIcon imgz=new ImageIcon("myImageData\\addDatalogo.png");
		lblNewLabel_5.setIcon(imgz);

		lblNewLabel_5.setBounds(10, 9, 40, 41);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Add Item to Shop");
		lblNewLabel_6.setForeground(new Color(255, 99, 71));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_6.setBounds(51, 11, 168, 36);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_1_1 = new JLabel(" X ");
		lblNewLabel_1_1.setForeground(SystemColor.activeCaption);
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1_1.repaint();
				lblNewLabel_1_1.setOpaque(true);
				lblNewLabel_1_1.setBackground(Color.RED);
				lblNewLabel_1_1.setForeground(Color.WHITE);
				lblNewLabel_1_1.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1_1.repaint();
				lblNewLabel_1_1.setOpaque(true);
				lblNewLabel_1_1.setBackground(SystemColor.control);
				lblNewLabel_1_1.setForeground(SystemColor.activeCaption);
				lblNewLabel_1_1.repaint();
			}
		});
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(411, 0, 27, 25);
		contentPane.add(lblNewLabel_1_1);




	}
}
