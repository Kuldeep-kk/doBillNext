package doBillNext;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;


public class mframe extends JFrame {
	 
	private JLabel modeHint;
	static int i = 0;
	private static JLabel cat_1;
	private static JLabel cat_2;
	private static JLabel cat_3;
	private static JLabel cat_4;
	private static JLabel cat_6;
	private static JLabel cat_5;
	private static JLabel cat_7;
	private JLabel selectedID;
	private JLabel selectedProd;
	private JLabel selectedPrice;
	private JLabel lblNewLabel_7;
	String category;
	JComboBox comboBox;
	int row;
	int cartselrow;
	
	int scrollsel=1;

	static JLabel subtotal;
	static JLabel gst;
	static JLabel gtotal;

	static int sub = 0;
	static double gstcal = 0;
	static double gtotalcal = 0.0;

	JLabel updateT;

	Calendar calendar;
	SimpleDateFormat timeFormat;
	SimpleDateFormat dayFormat;
	SimpleDateFormat dateFormat;
	String time;
	String day;
	String date;

	int cat1_click = 0;
	int cat2_click = 0;
	int cat3_click = 0;
	int cat4_click = 0;
	int cat5_click = 0;
	int cat6_click = 0;
	int cat7_click = 0;

	static JTextField customername;
	static JTextArea customeadd;
	static JTextField customermob;
	JLabel lblNewLabel_17;
	static String custname;

	int tx, ty;
	static JLabel mail ;
	static JLabel user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					mframe frame = new mframe();
					frame.setUndecorated(true);
					

					frame.setLocationRelativeTo(null);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	private static Connection connection = null;
	private static Connection cartConnect = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;
	private static Connection upconncect = null;
	static PreparedStatement pstup = null;
	static ResultSet rsup = null;

	static PreparedStatement pstcart = null;
	static ResultSet rscart = null;

	static PreparedStatement pstcartsel = null;
	static ResultSet rscartsel = null;
	
	private static Connection userconnect = null;
	static PreparedStatement pstuser = null;
	static ResultSet rsuser = null;

	private JLabel lblNewLabel_8;

	public static void cartonstart() {
		try {
			sub = 0;
			gstcal = 0;
			gtotalcal = 0.0;
			String query = "delete from cartdb";
			pstcart = cartConnect.prepareStatement(query);

			pstcart.execute();

			pstcart.close();
		} catch (Exception e22) {
			JOptionPane.showMessageDialog(null, e22);
		}

	}

	static public void updatecart() {
		try {

			table_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			table_1.setSelectionBackground(new Color(255, 99, 71));
			table_1.setSelectionForeground(new Color(255, 255, 255));

			table_1.setRowHeight(15);

			// table.getColumnModel().getColumn(1).setPreferredWidth(5);

			table_1.setModel(
					new DefaultTableModel(new Object[][] {}, new String[] { "Product", "Category", "Qty", "Price" }));
			table_1.getColumnModel().getColumn(0).setPreferredWidth(202);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(54);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(25);
			String query = "select * from cartdb ";
			pstcart = cartConnect.prepareStatement(query);

			rscart = pstcart.executeQuery();
			ResultSetMetaData rsmd = rscart.getMetaData();

			DefaultTableModel model = (DefaultTableModel) table_1.getModel();

			// table.getColumn(1).setPreferredWidth(5);
			int cols = rsmd.getColumnCount();

			// model.setColumnIdentifiers(colName);

			String name, cate, qty, price;

			while (rscart.next()) {
				name = rscart.getString(2);
				cate = rscart.getString(3);
				qty = rscart.getString(4);
				price = rscart.getString(5);

				String[] row = { name, cate, qty, price };

				model.addRow(row);

			}

			pstcart.close();
			rscart.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		gstcal = Math.round((0.18) * sub);
		subtotal.setText(Integer.toString(sub));
		gtotalcal = sub + gstcal;
		gst.setText(Double.toString(gstcal));
		gtotal.setText(Double.toString(gtotalcal));

	}

	static public void updateTable() {
		try {
			table.setBackground(SystemColor.control);

			table.setRowHeight(25);

			// table.getColumnModel().getColumn(1).setPreferredWidth(5);
			table.setSelectionBackground(new Color(0, 255, 0));
			table.setSelectionForeground(new Color(255, 255, 255));
			table.setModel(
					new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Product", "Category", "Price" }));
			
			
			
			JTableHeader tableHeader = table.getTableHeader();
			tableHeader.setFont(new Font("Tahoma", Font.BOLD, 13));
			tableHeader.setBackground(new Color(224, 255, 255));
			tableHeader.setForeground(new Color(255, 99, 71).darker());
			
			DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
			renderer.setHorizontalAlignment(SwingConstants.CENTER);
			
			
			
			

			table.getColumnModel().getColumn(0).setPreferredWidth(25);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);
			table.getColumnModel().getColumn(2).setPreferredWidth(45);
			table.getColumnModel().getColumn(3).setPreferredWidth(45);
			table.setForeground(new Color(105, 105, 105));

			String query = "select * from shop ";
			pst = connection.prepareStatement(query);

			int i = 0;
			rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			DefaultTableModel model = (DefaultTableModel) table.getModel();

			// table.getColumn(1).setPreferredWidth(5);
			int cols = rsmd.getColumnCount();
			// String[] colName=new String[cols];
			// for(int i=0;i<cols;i++)
			// colName[i]=rsmd.getColumnName(i+1);
			// model.setColumnIdentifiers(colName);

			String id, name, cate, price;

			while (rs.next()) {
				id = rs.getString(1);
				name = rs.getString(2);
				cate = rs.getString(3);
				price = rs.getString(4);

				String[] row = { id, name, cate, price };

				model.addRow(row);

			}

			pst.close();
			rs.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}

	}

	Thread t = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				time = timeFormat.format(Calendar.getInstance().getTime());
				dateTime.setText(time);
				day = dayFormat.format(Calendar.getInstance().getTime());
				weakDay.setText(day);

				date = dateFormat.format(Calendar.getInstance().getTime());
				dated.setText(date);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
	static int motion=0;
	static int motionup = 0;
	
	public static void scrolldown() {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				
				while (motionup <= 6) {
					if (motionup == 0) {

						cat_1.setVisible(true);

					}

					else if (motionup == 1) {

						cat_2.setVisible(true);

					} else if (motionup == 2) {
						cat_3.setVisible(true);
					} else if (motionup == 3) {
						cat_4.setVisible(true);

					} else if (motionup == 4) {
						cat_5.setVisible(true);
					} else if (motionup == 5) {
						cat_6.setVisible(true);

					} else if (motionup == 6) {
						cat_7.setVisible(true);
					}

					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					motionup = motionup + 1;
				}
			}
		});
		
		t2.start();
		
	}
	/*Thread t2 = new Thread(new Runnable() {
		@Override
		public void run() {
			
			while (motionup <= 6) {
				if (motionup == 0) {

					cat_1.setVisible(true);

				}

				else if (motionup == 1) {

					cat_2.setVisible(true);

				} else if (motionup == 2) {
					cat_3.setVisible(true);
				} else if (motionup == 3) {
					cat_4.setVisible(true);

				} else if (motionup == 4) {
					cat_5.setVisible(true);
				} else if (motionup == 5) {
					cat_6.setVisible(true);

				} else if (motionup == 6) {
					cat_7.setVisible(true);
				}

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				motionup = motionup + 1;
			}
		}
	});*/
	public static void scrollup() {
	
	Thread t3 = new Thread(new Runnable() {
		@Override
		public void run() {
			
			while (motion <= 6) {
				if (motion == 0) {

					cat_7.setVisible(false);

				}

				else if (motion == 1) {

					cat_6.setVisible(false);

				} else if (motion == 2) {
					cat_5.setVisible(false);
				} else if (motion == 3) {
					cat_4.setVisible(false);

				} else if (motion == 4) {
					cat_3.setVisible(false);
				} else if (motion == 5) {
					cat_2.setVisible(false);

				} else if (motion == 6) {
					cat_1.setVisible(false);
				}

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				motion = motion + 1;
			}
		}
	});
	t3.start();
	}
	

	 // start threads one by one

	 
	//String str = "kk@gmail.com";
	static private JTable table = new JTable();

	private final JLabel lblShowExist = new JLabel("Existing Users");

	private JTextField searchBox;
	private JPanel panel = new JPanel();
	JLabel dateTime;
	JLabel weakDay;
	JLabel dated;

	/**
	 * Create the frame.
	 */
	int kk;
	static private JTable table_1 = new JTable();
	private JTextField dpname = new JTextField();
	private JTextField dprice;
	
	private Image backgroundImage = Toolkit.getDefaultToolkit().getImage("myImageData\\bg1.jpg");

    private JPanel contentPane = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
	
	
	public void changeBackground(String imagePath) {
        backgroundImage = Toolkit.getDefaultToolkit().getImage(imagePath);
        contentPane.repaint();
    }
	


	public mframe() {
		
		/*
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setBounds(100, 100, 450,
		 * 300); contentPane = new JPanel(); contentPane.setBorder(new EmptyBorder(5, 5,
		 * 5, 5)); contentPane.setLayout(new BorderLayout(0, 0));
		 * setContentPane(contentPane);
		 */
		
		userconnect= databaseconnector.dbConnector();
		upconncect = databaseconnector.dbConnector();
		connection = databaseconnector.dbConnector();
		cartConnect = cartconnect.cartConnector();

		updateTable();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setBounds(100, 100, 1200, 750);
	
		

		JLabel wdrag = new JLabel("");
		wdrag.setBounds(0, 0, 1020, 33);
		contentPane.add(wdrag);

		wdrag.addMouseMotionListener(new MouseMotionAdapter() {
			@Override

			public void mouseDragged(MouseEvent e) {

				setLocation(e.getXOnScreen() - tx, e.getYOnScreen() - ty);
			}
		});

		wdrag.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tx = e.getX();
				ty = e.getY();
			}
		});

		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		table_1.setBackground(SystemColor.control);

		table_1.setForeground(new Color(0, 191, 255));

		subtotal = new JLabel("");
		subtotal.setForeground(new Color(32, 178, 170));
		subtotal.setFont(new Font("Sylfaen", Font.BOLD, 24));
		subtotal.setBounds(1041, 508, 140, 38);
		contentPane.add(subtotal);
		user = new JLabel("");
		user.setForeground(new Color(0, 191, 255));
		user.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		user.setBounds(1100, 30, 99, 25);
		contentPane.add(user);
		
		try {
			String query = "select username from users where email='" +Login.gmail + "'";
			 pstuser = userconnect.prepareStatement(query);

			rsuser= pstuser.executeQuery();
			user.setText(rsuser.getString(1));
			
			rsuser.close();
			pstuser.close();
			

		} catch (Exception e1) {

			JOptionPane.showMessageDialog(null, e1);

		}

		UIManager.put("TabbedPane.selected", Color.ORANGE);

		JLabel lblNewLabel_13 = new JLabel("GST   @18% :");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_13.setBounds(825, 549, 140, 33);
		contentPane.add(lblNewLabel_13);

		gst = new JLabel("");
		gst.setForeground(new Color(72, 209, 204));
		gst.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 19));
		gst.setBounds(1041, 557, 140, 25);
		contentPane.add(gst);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(825, 593, 356, 2);
		contentPane.add(separator_1);

		JLabel lblNewLabel_12_1 = new JLabel("Grand Total  :");
		lblNewLabel_12_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_12_1.setBounds(825, 602, 195, 33);
		contentPane.add(lblNewLabel_12_1);

		gtotal = new JLabel("");
		gtotal.setForeground(new Color(255, 99, 71));
		gtotal.setFont(new Font("Times New Roman", Font.BOLD, 26));
		gtotal.setBounds(1041, 602, 134, 33);
		contentPane.add(gtotal);
		dpname = new JTextField("");
		dpname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dpname.setBounds(263, 156, 184, 20);
		panel.setBackground(SystemColor.control);
		panel.add(dpname);
		dpname.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));

		comboBox.addItem("laptop");
		comboBox.addItem("monitor");
		comboBox.addItem("cpu");
		comboBox.addItem("keyboard");
		comboBox.addItem("mouse");
		comboBox.addItem("cables");
		comboBox.addItem("others");

		comboBox.setBounds(263, 198, 89, 22);
		panel.add(comboBox);

		dprice = new JTextField("");
		dprice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dprice.setColumns(10);
		dprice.setBounds(263, 250, 131, 20);
		panel.add(dprice);

		cartonstart();
		updatecart();

		JLabel deletebut = new JLabel("");
		deletebut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int delact = JOptionPane.showConfirmDialog(null, "Do you really want to DELETE", "DELETE",
						JOptionPane.YES_NO_OPTION);
				if (delact == 0) {
					try {

						String query = "delete from shop where id='" + selectedID.getText() + "' and product_name='"
								+ selectedProd.getText() + "' and price='" + selectedPrice.getText() + "'";
						pst = connection.prepareStatement(query);

						pst.execute();
						JOptionPane.showMessageDialog(null, "Deleted");
						pst.close();

					} catch (Exception e22) {
						JOptionPane.showMessageDialog(null, e22);
					}
					selectedID.setText(null);
					selectedProd.setText(null);
					selectedPrice.setText(null);
					updateTable();
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_17.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_17.setVisible(false);
			}
		});

		deletebut.setBounds(146, 582, 29, 50);
		ImageIcon imgbut = new ImageIcon("myImageData\\deletelogo.png");

		JLabel cutsearch = new JLabel(" X");
		cutsearch.repaint();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(SystemColor.control);

		scrollPane_1.setBounds(815, 212, 366, 285);
		contentPane.add(scrollPane_1);

		scrollPane_1.setViewportView(table_1);
		cutsearch.setOpaque(true);
		cutsearch.setBackground(new Color(169, 169, 169));
		cutsearch.setForeground(Color.WHITE);
		cutsearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cutsearch.setBounds(1117, 151, 16, 25);
		contentPane.add(cutsearch);
		cutsearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBox.setText("");
				updateTable();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				cutsearch.repaint();
				cutsearch.setOpaque(true);
				cutsearch.setBackground(new Color(128, 128, 128));
				cutsearch.setForeground(Color.WHITE);
				cutsearch.repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cutsearch.repaint();
				cutsearch.setOpaque(true);
				cutsearch.setBackground(new Color(169, 169, 169));
				cutsearch.setForeground(Color.WHITE);
				cutsearch.repaint();
			}
		});

		deletebut.setIcon(imgbut);
		contentPane.add(deletebut);

		JLabel lblNewLabel_9 = new JLabel("");

		lblNewLabel_9
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.green), "Check Out"));
		lblNewLabel_9.setBounds(811, 197, 375, 300);
		contentPane.add(lblNewLabel_9);

		weakDay = new JLabel("New label");
		weakDay.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		weakDay.setBounds(558, 30, 106, 25);
		contentPane.add(weakDay);

		dateTime = new JLabel("New label");
		dateTime.setForeground(new Color(50, 205, 50));
		dateTime.setBorder(BorderFactory.createRaisedBevelBorder());
		dateTime.setFont(new Font("Tahoma", Font.BOLD, 13));
		dateTime.setBounds(550, 0, 93, 33);
		contentPane.add(dateTime);

		dated = new JLabel("New label");
		dated.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		dated.setBounds(547, 49, 117, 27);
		contentPane.add(dated);

		timeFormat = new SimpleDateFormat("hh:mm:ss a");
		dayFormat = new SimpleDateFormat("EEEE");
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		t.start();

		JLabel lblNewLabel_1 = new JLabel(" X ");
		lblNewLabel_1.repaint();
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(SystemColor.activeCaption);
		lblNewLabel_1.setBackground(new Color(224, 255, 255));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					connection = null;
					pst.close();
					rs.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.repaint();
				lblNewLabel_1.setOpaque(true);
				lblNewLabel_1.setBackground(Color.RED);
				lblNewLabel_1.setForeground(Color.WHITE);
				lblNewLabel_1.repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.repaint();
				lblNewLabel_1.setOpaque(true);
				lblNewLabel_1.setBackground(new Color(224, 255, 255));
				lblNewLabel_1.setForeground(SystemColor.activeCaption);
				lblNewLabel_1.repaint();
			}
		});

		JLabel addToCart = new JLabel("");
		addToCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_7.setVisible(true);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_7.setVisible(false);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					String query = "select * from cartdb where Product=? and Price=?";
					pstcart = cartConnect.prepareStatement(query);

					pstcart.setString(1, selectedProd.getText());

					pstcart.setString(2, selectedPrice.getText());

					rscart = pstcart.executeQuery();
					int count = 0;
					while (rscart.next()) {
						count = count + 1;
					}
					if (count == 0) {

						try {
							String query2 = "insert into cartdb (Id,Product,Category,Qty,Price) values (?,?,?,?,?)";
							pstcart = cartConnect.prepareStatement(query2);
							pstcart.setString(1, selectedID.getText());
							pstcart.setString(2, selectedProd.getText());
							pstcart.setString(3, category);
							pstcart.setString(4, Integer.toString(1));
							pstcart.setString(5, selectedPrice.getText());

							sub = sub + Integer.parseInt(selectedPrice.getText());

							pstcart.execute();
							pstcart.close();
							JOptionPane.showMessageDialog(null, "Data Added to Your Cart");

						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "You Can't add data to cart!!!");

						}

					} else if (count >= 1) {
						try {
							String query3 = "update cartdb set qty=qty+1 where Id='" + selectedID.getText()
									+ "' and Product='" + selectedProd.getText() + "'";
							pstcart = cartConnect.prepareStatement(query3);
							pstcart.execute();
							pstcart.close();

							JOptionPane.showMessageDialog(null, "Data Updated");
							sub = sub + Integer.parseInt(selectedPrice.getText());

						} catch (Exception e2) {

							JOptionPane.showMessageDialog(null, e2);

						}

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "You Can't add data to cart!!!");

				}

				updatecart();

			}
		});
		addToCart.setBounds(800, 150, 48, 40);

		ImageIcon imgcart = new ImageIcon("myImageData\\addcart.png");
		addToCart.setIcon(imgcart);
		contentPane.add(addToCart);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 127, 80));
		separator.setBounds(0, 131, 1300, 14);
		contentPane.add(separator);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(1175, 0, 27, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_4 = new JLabel("  -  ");
		lblNewLabel_4.repaint();
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(new Color(224, 255, 255));
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
				lblNewLabel_4.setBackground(new Color(224, 255, 255));
				lblNewLabel_4.setForeground(SystemColor.activeCaption);
				lblNewLabel_4.repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// frame.setState(JFrame.ICONIFIED);
				setState(Frame.ICONIFIED);

			}
		});
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(1142, 0, 33, 25);
		contentPane.add(lblNewLabel_4);
		ImageIcon wimg = new ImageIcon("myImageData\\whitedot.png");
		ImageIcon bimg = new ImageIcon("myImageData\\blackdot.png");

		JLabel userlog = new JLabel("");
		ImageIcon img1 = new ImageIcon("myImageData\\us.png");
		userlog.setIcon(img1);
		userlog.setBounds(970, 30, 50, 50);
		contentPane.add(userlog);

		modeHint = new JLabel("");
		modeHint.setBounds(1117, 126, 64, 14);
		contentPane.add(modeHint);

		
		

		mail = new JLabel("");
		mail.setForeground(new Color(0, 191, 255));
		mail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		mail.setBounds(1100, 54, 99, 25);
		mail.setText(Login.gmail);
		contentPane.add(mail);

		
		JLabel lblNewLabel_3 = new JLabel("Username :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(1025, 35, 69, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Email           :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(1025, 60, 69, 14);
		contentPane.add(lblNewLabel_5);

		JLabel mainlogo = new JLabel("");
		mainlogo.setBounds(10, 9, 353, 115);
		ImageIcon imglogo = new ImageIcon("myImageData\\mainlogo.png");
		mainlogo.setIcon(imglogo);
		contentPane.add(mainlogo);

		cat_1 = new JLabel(" Laptop ");

		cat_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
		ImageIcon lapyimg = new ImageIcon("myImageData\\lapy.png");
		cat_1.setIcon(lapyimg);
		cat_1.setVisible(false);

		cat_1.setBounds(43, 185, 99, 39);

		contentPane.add(cat_1);

		cat_2 = new JLabel(" Monitor ");
		cat_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		ImageIcon monimg = new ImageIcon("myImageData\\monitorpic.png");
		cat_2.setIcon(monimg);
		cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
		cat_2.setVisible(false);
		cat_2.setBounds(43, 235, 99, 39);

		contentPane.add(cat_2);

		cat_3 = new JLabel(" CPU ");
		cat_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		ImageIcon cpuimg = new ImageIcon("myImageData\\cpupic.png");
		cat_3.setIcon(cpuimg);
		cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
		cat_3.setBounds(43, 284, 99, 39);
		cat_3.setVisible(false);
		contentPane.add(cat_3);

		cat_4 = new JLabel(" Mouse ");
		cat_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		ImageIcon mouseimg = new ImageIcon("myImageData\\mousepic.png");
		cat_4.setIcon(mouseimg);
		cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
		cat_4.setBounds(43, 331, 99, 39);
		cat_4.setVisible(false);
		contentPane.add(cat_4);

		cat_5 = new JLabel(" Cables ");
		cat_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		cat_5.setBorder(BorderFactory.createRaisedBevelBorder());
		ImageIcon cableimg = new ImageIcon("myImageData\\cablepic.png");
		cat_5.setIcon(cableimg);
		cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
		cat_5.setBounds(43, 381, 99, 39);
		cat_5.setVisible(false);
		contentPane.add(cat_5);

		cat_6 = new JLabel(" Keyboard ");
		cat_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		ImageIcon keyimg = new ImageIcon("myImageData\\keyboardpic.png");
		cat_6.setIcon(keyimg);
		cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
		cat_6.setBounds(43, 430, 134, 39);
		cat_6.setVisible(false);
		contentPane.add(cat_6);

		cat_7 = new JLabel(" Others ");
		cat_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		ImageIcon pendimg = new ImageIcon("myImageData\\pendrivepic.png");
		cat_7.setIcon(pendimg);
		cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
		cat_7.setBounds(43, 480, 134, 39);
		cat_7.setVisible(false);
		contentPane.add(cat_7);

		scrolldown();

		cat_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cat1_click == 0) {

					cat_1.setBounds(60, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, new Color(0, 191, 255)));
					cat_1.setForeground(new Color(0, 191, 255));

					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);
					try {
						table.setModel(new DefaultTableModel(null, new String[] {}));
						table.setRowHeight(25);

						// table.getColumnModel().getColumn(1).setPreferredWidth(5);
						table.setSelectionBackground(new Color(0, 255, 0));
						table.setSelectionForeground(new Color(255, 255, 255));
						table.setModel(new DefaultTableModel(new Object[][] {},
								new String[] { "Id", "Product", "Category", "Price" }));

						table.getColumnModel().getColumn(0).setPreferredWidth(25);
						table.getColumnModel().getColumn(1).setPreferredWidth(250);
						table.getColumnModel().getColumn(2).setPreferredWidth(45);
						table.getColumnModel().getColumn(3).setPreferredWidth(45);
						table.setForeground(new Color(105, 105, 105));
						String query = "select * from shop where category=?";
						pst = connection.prepareStatement(query);
						pst.setString(1, "laptop");

						rs = pst.executeQuery();
						ResultSetMetaData rsmd = rs.getMetaData();

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						int cols = rsmd.getColumnCount();

						String id, name, cate, price;

						while (rs.next()) {
							id = rs.getString(1);
							name = rs.getString(2);
							cate = rs.getString(3);
							price = rs.getString(4);

							String[] row = { id, name, cate, price };

							model.addRow(row);

						}
						pst.close();
						rs.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}

					cat1_click = 1;
					cat2_click = 0;
					cat3_click = 0;
					cat4_click = 0;
					cat5_click = 0;
					cat6_click = 0;
					cat7_click = 0;
				} else if (cat1_click == 1) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);
					cat1_click = 0;
					updateTable();

				}

			}

		});

		cat_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cat2_click == 0) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(60, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, new Color(0, 191, 255)));
					cat_2.setForeground(new Color(0, 191, 255));

					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);

					try {
						table.setModel(new DefaultTableModel(null, new String[] {}));
						table.setRowHeight(25);

						// table.getColumnModel().getColumn(1).setPreferredWidth(5);
						table.setSelectionBackground(new Color(0, 255, 0));
						table.setSelectionForeground(new Color(255, 255, 255));
						table.setModel(new DefaultTableModel(new Object[][] {},
								new String[] { "Id", "Product", "Category", "Price" }));

						table.getColumnModel().getColumn(0).setPreferredWidth(25);
						table.getColumnModel().getColumn(1).setPreferredWidth(250);
						table.getColumnModel().getColumn(2).setPreferredWidth(45);
						table.getColumnModel().getColumn(3).setPreferredWidth(45);
						table.setForeground(new Color(105, 105, 105));
						String query = "select * from shop where category=?";
						pst = connection.prepareStatement(query);
						pst.setString(1, "monitor");

						rs = pst.executeQuery();
						ResultSetMetaData rsmd = rs.getMetaData();

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						int cols = rsmd.getColumnCount();

						String id, name, cate, price;

						while (rs.next()) {
							id = rs.getString(1);
							name = rs.getString(2);
							cate = rs.getString(3);
							price = rs.getString(4);

							String[] row = { id, name, cate, price };

							model.addRow(row);

						}
						pst.close();
						rs.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}

					cat1_click = 0;
					cat2_click = 1;
					cat3_click = 0;
					cat4_click = 0;
					cat5_click = 0;
					cat6_click = 0;
					cat7_click = 0;
				} else if (cat2_click == 1) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);
					cat2_click = 0;
					updateTable();

				}

			}
		});

		cat_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (cat3_click == 0) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(60, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, new Color(0, 191, 255)));
					cat_3.setForeground(new Color(0, 191, 255));

					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);

					try {
						table.setModel(new DefaultTableModel(null, new String[] {}));
						table.setRowHeight(25);

						// table.getColumnModel().getColumn(1).setPreferredWidth(5);
						table.setSelectionBackground(new Color(0, 255, 0));
						table.setSelectionForeground(new Color(255, 255, 255));
						table.setModel(new DefaultTableModel(new Object[][] {},
								new String[] { "Id", "Product", "Category", "Price" }));

						table.getColumnModel().getColumn(0).setPreferredWidth(25);
						table.getColumnModel().getColumn(1).setPreferredWidth(250);
						table.getColumnModel().getColumn(2).setPreferredWidth(45);
						table.getColumnModel().getColumn(3).setPreferredWidth(45);
						table.setForeground(new Color(105, 105, 105));
						String query = "select * from shop where category=?";
						pst = connection.prepareStatement(query);
						pst.setString(1, "cpu");

						rs = pst.executeQuery();
						ResultSetMetaData rsmd = rs.getMetaData();

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						int cols = rsmd.getColumnCount();

						String id, name, cate, price;

						while (rs.next()) {
							id = rs.getString(1);
							name = rs.getString(2);
							cate = rs.getString(3);
							price = rs.getString(4);

							String[] row = { id, name, cate, price };

							model.addRow(row);

						}
						pst.close();
						rs.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
					cat1_click = 0;
					cat2_click = 0;
					cat3_click = 1;
					cat4_click = 0;
					cat5_click = 0;
					cat6_click = 0;
					cat7_click = 0;

				} else if (cat3_click == 1) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);
					cat3_click = 0;
					updateTable();

				}
			}
		});

		cat_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cat4_click == 0) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(60, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, new Color(0, 191, 255)));
					cat_4.setForeground(new Color(0, 191, 255));

					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);

					try {
						table.setModel(new DefaultTableModel(null, new String[] {}));
						table.setRowHeight(25);

						// table.getColumnModel().getColumn(1).setPreferredWidth(5);
						table.setSelectionBackground(new Color(0, 255, 0));
						table.setSelectionForeground(new Color(255, 255, 255));
						table.setModel(new DefaultTableModel(new Object[][] {},
								new String[] { "Id", "Product", "Category", "Price" }));

						table.getColumnModel().getColumn(0).setPreferredWidth(25);
						table.getColumnModel().getColumn(1).setPreferredWidth(250);
						table.getColumnModel().getColumn(2).setPreferredWidth(45);
						table.getColumnModel().getColumn(3).setPreferredWidth(45);
						table.setForeground(new Color(105, 105, 105));
						String query = "select * from shop where category=?";
						pst = connection.prepareStatement(query);
						pst.setString(1, "mouse");

						rs = pst.executeQuery();
						ResultSetMetaData rsmd = rs.getMetaData();

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						int cols = rsmd.getColumnCount();

						String id, name, cate, price;

						while (rs.next()) {
							id = rs.getString(1);
							name = rs.getString(2);
							cate = rs.getString(3);
							price = rs.getString(4);

							String[] row = { id, name, cate, price };

							model.addRow(row);

						}
						pst.close();
						rs.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
					cat1_click = 0;
					cat2_click = 0;
					cat3_click = 0;
					cat4_click = 1;
					cat5_click = 0;
					cat6_click = 0;
					cat7_click = 0;

				} else if (cat4_click == 1) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);
					cat4_click = 0;
					updateTable();

				}

			}
		});

		cat_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cat6_click == 0) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(60, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, new Color(0, 191, 255)));
					cat_6.setForeground(new Color(0, 191, 255));

					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);

					try {
						table.setModel(new DefaultTableModel(null, new String[] {}));
						table.setRowHeight(25);

						// table.getColumnModel().getColumn(1).setPreferredWidth(5);
						table.setSelectionBackground(new Color(0, 255, 0));
						table.setSelectionForeground(new Color(255, 255, 255));
						table.setModel(new DefaultTableModel(new Object[][] {},
								new String[] { "Id", "Product", "Category", "Price" }));

						table.getColumnModel().getColumn(0).setPreferredWidth(25);
						table.getColumnModel().getColumn(1).setPreferredWidth(250);
						table.getColumnModel().getColumn(2).setPreferredWidth(45);
						table.getColumnModel().getColumn(3).setPreferredWidth(45);
						table.setForeground(new Color(105, 105, 105));
						String query = "select * from shop where category=?";
						pst = connection.prepareStatement(query);
						pst.setString(1, "keyboard");

						rs = pst.executeQuery();
						ResultSetMetaData rsmd = rs.getMetaData();

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						int cols = rsmd.getColumnCount();

						String id, name, cate, price;

						while (rs.next()) {
							id = rs.getString(1);
							name = rs.getString(2);
							cate = rs.getString(3);
							price = rs.getString(4);

							String[] row = { id, name, cate, price };

							model.addRow(row);

						}
						pst.close();
						rs.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}

					cat1_click = 0;
					cat2_click = 0;
					cat3_click = 0;
					cat4_click = 0;
					cat5_click = 0;
					cat6_click = 1;
					cat7_click = 0;

				} else if (cat6_click == 1) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);
					cat6_click = 0;
					updateTable();

				}

			}
		});

		cat_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cat5_click == 0) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(60, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, new Color(0, 191, 255)));
					cat_5.setForeground(new Color(0, 191, 255));

					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);

					try {
						table.setModel(new DefaultTableModel(null, new String[] {}));
						table.setRowHeight(25);

						// table.getColumnModel().getColumn(1).setPreferredWidth(5);
						table.setSelectionBackground(new Color(0, 255, 0));
						table.setSelectionForeground(new Color(255, 255, 255));
						table.setModel(new DefaultTableModel(new Object[][] {},
								new String[] { "Id", "Product", "Category", "Price" }));

						table.getColumnModel().getColumn(0).setPreferredWidth(25);
						table.getColumnModel().getColumn(1).setPreferredWidth(250);
						table.getColumnModel().getColumn(2).setPreferredWidth(45);
						table.getColumnModel().getColumn(3).setPreferredWidth(45);
						table.setForeground(new Color(105, 105, 105));
						String query = "select * from shop where category=?";
						pst = connection.prepareStatement(query);
						pst.setString(1, "cables");

						rs = pst.executeQuery();
						ResultSetMetaData rsmd = rs.getMetaData();

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						int cols = rsmd.getColumnCount();

						String id, name, cate, price;

						while (rs.next()) {
							id = rs.getString(1);
							name = rs.getString(2);
							cate = rs.getString(3);
							price = rs.getString(4);

							String[] row = { id, name, cate, price };

							model.addRow(row);

						}
						pst.close();
						rs.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
					cat1_click = 0;
					cat2_click = 0;
					cat3_click = 0;
					cat4_click = 0;
					cat5_click = 1;
					cat6_click = 0;
					cat7_click = 0;

				} else if (cat5_click == 1) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);
					cat5_click = 0;
					updateTable();

				}

			}
		});

		cat_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cat7_click == 0) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(60, 480, 134, 39);

					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, new Color(0, 191, 255)));
					cat_7.setForeground(new Color(0, 191, 255));

					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					try {
						table.setModel(new DefaultTableModel(null, new String[] {}));
						table.setRowHeight(25);

						// table.getColumnModel().getColumn(1).setPreferredWidth(5);
						table.setSelectionBackground(new Color(0, 255, 0));
						table.setSelectionForeground(new Color(255, 255, 255));
						table.setModel(new DefaultTableModel(new Object[][] {},
								new String[] { "Id", "Product", "Category", "Price" }));

						table.getColumnModel().getColumn(0).setPreferredWidth(25);
						table.getColumnModel().getColumn(1).setPreferredWidth(250);
						table.getColumnModel().getColumn(2).setPreferredWidth(45);
						table.getColumnModel().getColumn(3).setPreferredWidth(45);
						table.setForeground(new Color(105, 105, 105));
						String query = "select * from shop where category=?";
						pst = connection.prepareStatement(query);
						pst.setString(1, "others");

						rs = pst.executeQuery();
						ResultSetMetaData rsmd = rs.getMetaData();

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						int cols = rsmd.getColumnCount();

						String id, name, cate, price;

						while (rs.next()) {
							id = rs.getString(1);
							name = rs.getString(2);
							cate = rs.getString(3);
							price = rs.getString(4);

							String[] row = { id, name, cate, price };

							model.addRow(row);

						}
						pst.close();
						rs.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
					cat1_click = 0;
					cat2_click = 0;
					cat3_click = 0;
					cat4_click = 0;
					cat5_click = 0;
					cat6_click = 0;
					cat7_click = 1;

				} else if (cat7_click == 1) {
					cat_1.setBounds(43, 185, 99, 39);
					cat_2.setBounds(43, 235, 99, 39);
					cat_3.setBounds(43, 284, 99, 39);
					cat_4.setBounds(43, 331, 99, 39);
					cat_5.setBounds(43, 381, 99, 39);
					cat_6.setBounds(43, 430, 134, 39);
					cat_7.setBounds(43, 480, 134, 39);

					cat_1.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_1.setForeground(Color.BLACK);
					cat_2.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_2.setForeground(Color.BLACK);
					cat_3.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_3.setForeground(Color.BLACK);
					cat_4.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_4.setForeground(Color.BLACK);
					cat_6.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_6.setForeground(Color.BLACK);
					cat_5.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_5.setForeground(Color.BLACK);
					cat_7.setBorder(BorderFactory.createMatteBorder(0, 5, 1, 0, Color.LIGHT_GRAY));
					cat_7.setForeground(Color.BLACK);
					cat7_click = 0;
					updateTable();

				}

			}
		});
		
		JLabel cat = new JLabel("Categories");
		cat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollsel=scrollsel+1;
				if(scrollsel%2==0) {
					cat.setBorder(BorderFactory.createRaisedBevelBorder());
					scrollsel=0;
					motion=0;
					scrollup();
					updateTable();
					
					
				}
				else  {cat.setBorder(BorderFactory.createLoweredBevelBorder());
					scrollsel=1;
					motionup=0;
					
					scrolldown();
					updateTable();
					
					
				}
			}
		});

		cat.setFont(new Font("Tahoma", Font.BOLD, 11));
		cat.setBorder(BorderFactory.createLoweredBevelBorder());
		ImageIcon imgitz = new ImageIcon("myImageData\\ctz.png");
		cat.setIcon(imgitz);

		cat.setBounds(10, 135, 134, 39);
		contentPane.add(cat);

		JLabel usrdata = new JLabel("");
		usrdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblShowExist.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblShowExist.setVisible(false);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				userDetails usd = new userDetails();
				usd.setUndecorated(true);

				usd.setVisible(true);

			}
		});
		usrdata.setBounds(10, 582, 50, 50);
		ImageIcon imgusrd = new ImageIcon("myImageData\\usrdetailpic.png");
		usrdata.setIcon(imgusrd);
		contentPane.add(usrdata);

		JLabel lblNewLabel_6 = new JLabel(" Add Data");
		lblNewLabel_6.repaint();
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setFont(getFont());
		lblNewLabel_6.setBackground(Color.WHITE);
		lblNewLabel_6.setBounds(67, 637, 64, 14);
		lblNewLabel_6.setVisible(false);
		contentPane.add(lblNewLabel_6);

		JLabel addDataLog = new JLabel("");
		addDataLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_6.setVisible(true);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_6.setVisible(false);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				add_Item ai = new add_Item();
				ai.setUndecorated(true);
				
				ai.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/addDatapic.png")));
				ai.setVisible(true);
			}
		});
		addDataLog.setBounds(83, 582, 40, 50);
		ImageIcon imgadd = new ImageIcon("myImageData\\addDatalogo.png");
		addDataLog.setIcon(imgadd);
		contentPane.add(addDataLog);

		lblShowExist.repaint();
		lblShowExist.setOpaque(true);
		lblShowExist.setVisible(false);
		lblShowExist.setFont(getFont());
		lblShowExist.setBackground(Color.WHITE);
		lblShowExist.setBounds(0, 635, 83, 19);
		contentPane.add(lblShowExist);

		JLabel selected = new JLabel("");
		selected.setBounds(195, 140, 610, 55);
		selected.setBorder(BorderFactory.createTitledBorder("Selected Item"));
		contentPane.add(selected);

		JLabel pidlabel = new JLabel("Product Id :");
		pidlabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		pidlabel.setBounds(205, 160, 69, 18);
		contentPane.add(pidlabel);

		selectedID = new JLabel("");
		selectedID.setForeground(new Color(0, 191, 255));
		selectedID.setFont(new Font("Tahoma", Font.BOLD, 11));
		selectedID.setBounds(276, 160, 25, 18);
		contentPane.add(selectedID);

		JLabel pnamelabel = new JLabel("Product Name :");
		pnamelabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		pnamelabel.setBounds(321, 160, 93, 18);
		contentPane.add(pnamelabel);

		selectedProd = new JLabel("");
		selectedProd.setForeground(new Color(0, 191, 255));
		selectedProd.setFont(new Font("Tahoma", Font.BOLD, 11));
		selectedProd.setBounds(412, 160, 219, 18);
		contentPane.add(selectedProd);

		JLabel pricelabel = new JLabel("Price :");
		pricelabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		pricelabel.setBounds(641, 160, 50, 18);
		contentPane.add(pricelabel);

		selectedPrice = new JLabel("");
		selectedPrice.setForeground(new Color(0, 191, 255));
		selectedPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		selectedPrice.setBounds(686, 160, 69, 18);
		contentPane.add(selectedPrice);

		lblNewLabel_7 = new JLabel(" Add to Cart");
		lblNewLabel_7.setBounds(751, 131, 77, 14);
		lblNewLabel_7.setFont(getFont());
		lblNewLabel_7.repaint();
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setBackground(Color.WHITE);
		lblNewLabel_7.setVisible(false);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_10 = new JLabel("Clear All");
		lblNewLabel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cartonstart();
				updatecart();
			}
		});
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_10.setBounds(1099, 187, 58, 14);
		contentPane.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel(" X ");
		lblNewLabel_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int delacta = JOptionPane.showConfirmDialog(null, "Do you really want to DELETE", "DELETE",
						JOptionPane.YES_NO_OPTION);
				if (delacta == 0) {

					String getpname = (table_1.getModel().getValueAt(cartselrow, 0)).toString();
					String getcat = (table_1.getModel().getValueAt(cartselrow, 1)).toString();
					String getqty = (table_1.getModel().getValueAt(cartselrow, 2)).toString();
					String getprice = (table_1.getModel().getValueAt(cartselrow, 3)).toString();
					if (Integer.parseInt(getqty) == 1) {
						try {
							String query = "delete from cartdb where Product='" + getpname + "' and Category='" + getcat
									+ "' and Price='" + getprice + "'";
							pstcart = cartConnect.prepareStatement(query);

							pstcart.execute();
							JOptionPane.showMessageDialog(null, "Deleted");
							pstcart.close();

						} catch (Exception e22) {
							JOptionPane.showMessageDialog(null, e22);
						}
						sub = sub - Integer.parseInt(getprice);
						updatecart();
					} else if (Integer.parseInt(getqty) > 1) {
						try {
							String querya = "update cartdb set qty=qty-1 where Product='" + getpname + "' and Price='"
									+ getprice + "'";
							pstcart = cartConnect.prepareStatement(querya);
							pstcart.execute();
							pstcart.close();

							JOptionPane.showMessageDialog(null, "Data Updated");
							sub = sub - Integer.parseInt(getprice);

						} catch (Exception e2) {

							JOptionPane.showMessageDialog(null, e2);

						}
						updatecart();
					}

				}
			}
		});

		lblNewLabel_11.setBackground(new Color(255, 250, 205));
		lblNewLabel_11.setForeground(new Color(255, 0, 0));
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_11.setBounds(1158, 187, 23, 14);
		contentPane.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Sub-Total :");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_12.setBounds(825, 508, 195, 33);
		contentPane.add(lblNewLabel_12);

		JLabel totalbg = new JLabel("");
		totalbg.repaint();
		totalbg.setOpaque(true);
		totalbg.setBackground(new Color(192, 192, 192));
		totalbg.setBounds(825, 598, 356, 40);
		contentPane.add(totalbg);

		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cartselrow = table_1.getSelectedRow();
				table_1.setRowHeight(15);
				table_1.setRowHeight(cartselrow, 25);

			}
		});

		JLabel print = new JLabel("Print");
		print.setFont(new Font("Tahoma", Font.BOLD, 17));
		print.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imgplogo = new ImageIcon("myImageData//printlogo.png");
		print.setIcon(imgplogo);
		print.repaint();
		print.setOpaque(true);
		print.setBackground(new Color(254, 204, 0, 255));
		print.setBorder(BorderFactory.createRaisedBevelBorder());
		print.setBounds(1012, 660, 162, 40);
		print.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				print.setBackground(new Color(0, 191, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				print.setBackground(new Color(254, 204, 0, 255));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (sub > 0) {
					contentPane.setFocusable(false);
					print.setBorder(BorderFactory.createLoweredBevelBorder());
					custDetails c = new custDetails();
					c.setLocationRelativeTo(null);
					c.setUndecorated(true);
					c.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Add data to cart first");
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				print.setBorder(BorderFactory.createRaisedBevelBorder());
			}

		});
		contentPane.add(print);

		JLabel lblReset = new JLabel("Reset");
		lblReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReset.setBackground(new Color(0, 191, 255));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblReset.setBackground(new Color(255, 140, 0));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				lblReset.setBorder(BorderFactory.createLoweredBevelBorder());

				sub = 0;
				gstcal = 0;
				gtotalcal = 0.0;

				cartonstart();
				updatecart();

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				lblReset.setBorder(BorderFactory.createRaisedBevelBorder());
			}
		});
		lblReset.setOpaque(true);
		ImageIcon imgreset = new ImageIcon("myImageData\\resetlogo.png");
		lblReset.setBorder(BorderFactory.createRaisedBevelBorder());
		lblReset.setIcon(imgreset);
		lblReset.setHorizontalAlignment(SwingConstants.CENTER);
		lblReset.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblReset.setBackground(new Color(255, 140, 0));

		lblReset.setBounds(836, 660, 162, 40);
		contentPane.add(lblReset);

		ImageIcon Listimg = new ImageIcon("myImageData\\listlogo.png");
		ImageIcon imglist = new ImageIcon("myImageData\\listlogo.png");

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				for (int i = 0; i < tabbedPane.getTabCount(); i++) {
					tabbedPane.setBackgroundAt(i, Color.WHITE);

				}
				int pos = tabbedPane.getSelectedIndex();
				tabbedPane.setIconAt(pos, Listimg);
				System.out.println(pos);
				// tabbedPane.setBackgroundAt(pos, Color.ORANGE);
			}
		});
		tabbedPane.setBounds(195, 201, 606, 503);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		// tabbedPane.setIconAt(i, Listimg);
		tabbedPane.addTab("     List     ", Listimg, scrollPane, null);
		tabbedPane.addTab(" Express / Manual ", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("Add Data To Cart ");
		lblNewLabel_14.setBackground(SystemColor.control);
		lblNewLabel_14.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 21));
		lblNewLabel_14.repaint();

		lblNewLabel_14.setOpaque(true);
		lblNewLabel_14.setForeground(new Color(218, 165, 32));
		lblNewLabel_14.setBounds(89, 49, 184, 51);
		panel.add(lblNewLabel_14);

		JLabel dnamelabel = new JLabel("Product Name :");
		dnamelabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dnamelabel.setBounds(142, 148, 131, 32);
		panel.add(dnamelabel);

		JLabel dcategorylabel = new JLabel("Category        :");
		dcategorylabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dcategorylabel.setBounds(142, 191, 131, 32);
		panel.add(dcategorylabel);

		JLabel dpricelabel = new JLabel("Price              :");
		dpricelabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dpricelabel.setBounds(142, 242, 131, 32);
		panel.add(dpricelabel);

		JLabel lblAddToCart = new JLabel("Add To Cart");
		lblAddToCart.setForeground(new Color(0, 191, 255));

		lblAddToCart.setOpaque(true);
		lblAddToCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddToCart.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAddToCart.setBorder(BorderFactory.createRaisedBevelBorder());
		ImageIcon imgdcart = new ImageIcon("myImageData\\directcart.png");
		lblAddToCart.setIcon(imgdcart);
		lblAddToCart.setBackground(Color.LIGHT_GRAY);
		lblAddToCart.setBounds(214, 367, 190, 40);
		panel.add(lblAddToCart);

		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setBounds(78, 75, 446, 312);
		lblNewLabel_15
				.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
						BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));

		panel.add(lblNewLabel_15);

		searchBox = new JTextField();
		searchBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					table.setModel(new DefaultTableModel(null, new String[] {}));
					String query = "select * from shop where id like '" + searchBox.getText()
							+ "%' or product_name like '" + searchBox.getText() + "%' or category like '"
							+ searchBox.getText() + "%' or price like '" + searchBox.getText() + "%'";
					pst = connection.prepareStatement(query);
					if (searchBox.getText().length() > 0) {
						// pst.setString(1, searchBox.getText());
						// pst.setString(2, searchBox.getText());
						// pst.setString(3, searchBox.getText());
						// pst.setString(4, searchBox.getText());

						rs = pst.executeQuery();
						ResultSetMetaData rsmd = rs.getMetaData();

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						int cols = rsmd.getColumnCount();
						String[] colName = new String[cols];
						for (int i = 0; i < cols; i++)
							colName[i] = rsmd.getColumnName(i + 1);
						model.setColumnIdentifiers(colName);

						String id, name, cate, price;

						while (rs.next()) {
							id = rs.getString(1);
							name = rs.getString(2);
							cate = rs.getString(3);
							price = rs.getString(4);

							String[] row = { id, name, cate, price };

							model.addRow(row);

						}

						pst.close();
						rs.close();
					} else {
						updateTable();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});

		searchBox.setBounds(955, 151, 162, 24);
		contentPane.add(searchBox);
		searchBox.setColumns(10);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					row = table.getSelectedRow();

					table.setRowHeight(25);
					table.setRowHeight(row, 45);

					String getId = (table.getModel().getValueAt(row, 0)).toString();
					String query = "select * from shop where id='" + getId + "'";
					pst = connection.prepareStatement(query);

					rs = pst.executeQuery();

					while (rs.next()) {
						selectedID.setText(rs.getString("id"));
						selectedProd.setText(rs.getString("product_name"));
						selectedPrice.setText(rs.getString("price"));
						category = (rs.getString("category"));

					}
					pst.close();
					rs.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}

			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		JLabel lblSetting = new JLabel("Setting");
		lblSetting.setOpaque(true);

		ImageIcon imgps = new ImageIcon("myImageData\\printsettinglogo.png");
		lblSetting.setIcon(imgps);
		lblSetting.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetting.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSetting.setBorder(BorderFactory.createRaisedBevelBorder());
		lblSetting.setBackground(new Color(175, 238, 238));
		lblSetting.setBounds(10, 662, 117, 33);
		lblSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSetting.setBackground(new Color(0, 191, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblSetting.setBackground(new Color(175, 238, 238));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				lblSetting.setBorder(BorderFactory.createLoweredBevelBorder());
				printDetail p1 = new printDetail();
				p1.setUndecorated(true);
				p1.setLocationRelativeTo(null);
				p1.setVisible(true);
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				lblSetting.setBorder(BorderFactory.createRaisedBevelBorder());
			}
		});
		contentPane.add(lblSetting);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.green);
		separator_2.setBounds(0, 125, 1188, 2);
		contentPane.add(separator_2);

		JLabel lblNewLabel_16 = new JLabel("Search :");
		lblNewLabel_16.setForeground(new Color(105, 105, 105));
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_16.setBounds(894, 156, 63, 14);
		contentPane.add(lblNewLabel_16);

		lblNewLabel_17 = new JLabel("Delete Selected Data");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_17.setVisible(false);
		lblNewLabel_17.setOpaque(true);
		lblNewLabel_17.repaint();
		lblNewLabel_17.setBackground(Color.WHITE);
		lblNewLabel_17.setBounds(83, 637, 112, 14);
		contentPane.add(lblNewLabel_17);

		JLabel bgcolory = new JLabel("");
		bgcolory.setOpaque(true);
		bgcolory.setBackground(new Color(210, 180, 140));
		bgcolory.setBounds(1140, 94, 20, 20);
		bgcolory.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		contentPane.add(bgcolory);
		bgcolory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// lblNewLabel_8.setIcon(new ImageIcon("E:\\Java
				// Project\\ITZoneLogin\\imgsrc\\darkmainbg.png"));
				changeBackground("myImageData\\bgblue.jpg");
				table_1.setBackground(new Color(135, 206, 235));

				table_1.setForeground(Color.white);
				table.setBackground(Color.gray);
				table.setForeground(Color.white);
			}

		});

		JLabel bgcolord = new JLabel("");
		bgcolord.setOpaque(true);
		bgcolord.setBackground(SystemColor.control);
		bgcolord.setBounds(1115, 94, 20, 20);
		bgcolord.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		contentPane.add(bgcolord);
		bgcolord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				changeBackground("myImageData\\bg1.jpg");
				table_1.setBackground(SystemColor.control);

				table_1.setForeground(new Color(0, 191, 255));
				table.setBackground(SystemColor.control);
				table.setForeground(new Color(105, 105, 105));
			}

		});

		JLabel bgcolordd = new JLabel("");
		bgcolordd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 changeBackground("myImageData\\bgpink.jpg");

				table_1.setBackground(new Color(235, 206, 235,100));

				table_1.setForeground(Color.white);
				table.setBackground(SystemColor.control);
				table.setForeground(new Color(105, 105, 105));
			}
		});
		bgcolordd.setOpaque(true);
		bgcolordd.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		bgcolordd.setBackground(new Color(216, 191, 216));
		bgcolordd.setBounds(1090, 94, 20, 20);
		contentPane.add(bgcolordd);

		JLabel lblNewLabel = new JLabel("Background :");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(997, 92, 93, 24);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Update Selected Data");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.repaint();
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(90, 700, 112, 14);
		contentPane.add(lblNewLabel_2);

		updateT = new JLabel("");
		updateT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JTextField id = new JTextField();
				JTextField productname = new JTextField();
				JComboBox comboBox = new JComboBox();
				comboBox.addItem("laptop");
				comboBox.addItem("monitor");
				comboBox.addItem("cpu");
				comboBox.addItem("keyboard");
				comboBox.addItem("mouse");
				comboBox.addItem("cables");
				comboBox.addItem("others");
				JTextField price = new JTextField();

				Object[] message = { "id:", id, "Product:", productname, "category:", comboBox, "Price:", price };

				id.setText(selectedID.getText());
				productname.setText(selectedProd.getText());
				comboBox.setSelectedItem(category);
				price.setText(selectedPrice.getText());
				int option = JOptionPane.showConfirmDialog(null, message, "Update Item", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					if (id.getText().length() > 0 && productname.getText().length() > 0
							|| price.getText().length() > 0) {
						try {
							String query2 = "update shop set id='" + id.getText() + " ',product_name='"
									+ productname.getText() + " ',category='" + comboBox.getSelectedItem()
									+ "' ,price='" + price.getText() + "' where id='" + selectedID.getText()
									+ "' and product_name='" + selectedProd.getText() + "'";
							pstup = upconncect.prepareStatement(query2);
							pstup.execute();
							pstup.close();
							JOptionPane.showMessageDialog(null, "Data Updated");

						} catch (Exception e2) {

							JOptionPane.showMessageDialog(null, e2);

						}
						updateTable();

					} else {
						JOptionPane.showMessageDialog(null, "All Blanks must be filled!!!");
					}

				} else {
					System.out.println("updation canceled");
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setVisible(true);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2.setVisible(false);
			}
		});
		ImageIcon imgpss = new ImageIcon("myImageData\\updateTable.png");

		updateT.setIcon(imgpss);
		updateT.setBounds(146, 650, 48, 50);
		contentPane.add(updateT);

		JLabel lblNewLabel_18 = new JLabel("Logout");
		lblNewLabel_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login log = new Login();
				log.call();
				dispose();
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_18.setBackground(new Color(224, 255, 255));
				lblNewLabel_18.setForeground(new Color(255, 99, 71));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_18.setBackground(new Color(255, 99, 71));
				lblNewLabel_18.setForeground(new Color(224, 255, 255));
			}
		});
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel_18.setBackground(new Color(255, 99, 71));
		lblNewLabel_18.setForeground(new Color(224, 255, 255));
		lblNewLabel_18.setOpaque(true);
		lblNewLabel_18.repaint();
		lblNewLabel_18.setFont(new Font("ROG Fonts", Font.PLAIN, 17));
		lblNewLabel_18.setBounds(1012, 0, 105, 25);
		contentPane.add(lblNewLabel_18);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 633, 189, 2);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(66, 576, 7, 50);
		contentPane.add(separator_4);

		JSeparator separator_4_1 = new JSeparator();
		separator_4_1.setOrientation(SwingConstants.VERTICAL);
		separator_4_1.setBounds(133, 576, 7, 50);
		contentPane.add(separator_4_1);
		

		lblAddToCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String query = "select * from cartdb where Product=? and Price=?";
					pstcart = cartConnect.prepareStatement(query);

					pstcart.setString(1, dpname.getText());

					pstcart.setString(2, dprice.getText());

					rscart = pstcart.executeQuery();
					int count = 0;
					while (rscart.next()) {
						count = count + 1;
					}
					if (count == 0) {
						int demoid = 0;

						try {
							String query2 = "insert into cartdb (Id,Product,Category,Qty,Price) values (?,?,?,?,?)";
							pstcart = cartConnect.prepareStatement(query2);

							pstcart.setString(1, Integer.toString(demoid + 1));
							pstcart.setString(2, dpname.getText());
							pstcart.setString(3, (String) comboBox.getSelectedItem());
							pstcart.setString(4, Integer.toString(1));
							pstcart.setString(5, dprice.getText());

							sub = sub + Integer.parseInt(dprice.getText());

							pstcart.execute();
							pstcart.close();
							JOptionPane.showMessageDialog(null, "Data Added to Your Cart");

						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "You Can't add data to cart!!!");

						}
						updatecart();

					} else if (count >= 1) {
						try {
							String querya = "update cartdb set qty=qty+1 where Product='" + dpname.getText()
									+ "' and Price='" + dprice.getText() + "'";
							pstcart = cartConnect.prepareStatement(querya);
							pstcart.execute();
							pstcart.close();

							JOptionPane.showMessageDialog(null, "Data Updated");
							sub = sub + Integer.parseInt(dprice.getText());

						} catch (Exception e2) {

							JOptionPane.showMessageDialog(null, e2);

						}
						updatecart();

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "You Can't add data to cart!!!");

				}
				

				updatecart();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblAddToCart.setBackground(new Color(250, 128, 114));
				lblAddToCart.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblAddToCart.setBackground(new Color(128, 128, 128));
				lblAddToCart.setForeground(new Color(0, 191, 255));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				lblAddToCart.setBorder(BorderFactory.createLoweredBevelBorder());

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				lblAddToCart.setBorder(BorderFactory.createRaisedBevelBorder());
			}
		});
		

	}
}

