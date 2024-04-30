package doBillNext;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JTextField uservar;
	private JPasswordField passwordField;
	JLabel loginbtn;
	private JLabel lblNewLabel;

	public int eyeflag=1;
	public int passfocus=0;
	public int userfocus=0;
	static String gmail="";

	PreparedStatement pst=null;
	ResultSet rs=null;
	Connection connection=null;

	static StringBuffer s1=new StringBuffer();

	String color[] ={"BLACK", "BLUE", "BROWN", "GREEN"};
	int i=0;

	/**
	 * Launch the application.

	/**
	 * Create the frame.
	 */

	public static void call() {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Login window = new Login();

					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	public Login() {
		initialize();
	}
	private void initialize() {
		connection=databaseconnector.dbConnector();
		
		frame = new JFrame();

		frame.getContentPane().setBackground(Color.WHITE);


		frame.setUndecorated(true);
		frame.setBounds(100, 100, 765, 443);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(197,201,203,215));
		panel.setLayout(null);

		panel.setBounds(0, 0, 370, 482);
		
		ImageIcon logoImg=new ImageIcon("myImageData\\loginpage.gif");
		 Image image = logoImg.getImage();
	        Image resizedImage = image.getScaledInstance(500, 400, Image.SCALE_DEFAULT);
	        ImageIcon resizedIcon = new ImageIcon(resizedImage);
		lblNewLabel =new JLabel(resizedIcon);

		lblNewLabel.setBounds(-30, 70, 450, 380);
		panel.add(lblNewLabel);		
		
		frame.getContentPane().add(panel);
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setLayout(null);

		panel2.setBounds(370, 0, 395, 482);
		
		JLabel lblNewLabel_1 = new JLabel(" X ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(SystemColor.activeCaption);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.repaint();
				lblNewLabel_1.setOpaque(true);
				lblNewLabel_1.setBackground(new Color(247,101,94));
				lblNewLabel_1.setForeground(Color.WHITE);
				lblNewLabel_1.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.repaint();
				lblNewLabel_1.setOpaque(true);
				lblNewLabel_1.setBackground(Color.WHITE);
				lblNewLabel_1.setForeground(SystemColor.activeCaption);
				lblNewLabel_1.repaint();
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(355,11,30,30);
		
		panel2.add(lblNewLabel_1);
		
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Welcome Back :)");
		lblNewLabel_2.setForeground(new Color(108,125,140));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(34, 63, 245, 30);
		panel2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_22 = new JLabel("To keep connected with us please login with your personal");
		lblNewLabel_22.setBounds(34, 104, 333, 30);
		lblNewLabel_22.setForeground(new Color(108,125,140));
		lblNewLabel_22.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 12));
		panel2.add(lblNewLabel_22);
		
		JLabel lblNewLabel_4 = new JLabel("information by email addressand password...");
		lblNewLabel_4.setForeground(new Color(108,125,140));
		lblNewLabel_4.setBounds(34, 129, 245, 21);
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 12));
		
		panel2.add(lblNewLabel_4);
		
		uservar = new JTextField();
		uservar.setText("Enter Registered Email");
		uservar.setBorder(null);
		
		JSeparator usersap = new JSeparator();
		usersap.setForeground(Color.LIGHT_GRAY);
		usersap.setBounds(123, 247, 177, 11);


		uservar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		uservar.setForeground(Color.LIGHT_GRAY);
		uservar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				usersap.setForeground(new Color(11, 218, 81));
				if(uservar.getText().equals("Enter Registered Email")) {
					uservar.setText("");
					uservar.setForeground(Color.BLACK);
					uservar.setFont(new Font("Tahoma", Font.PLAIN, 16));

				}
				else {
					uservar.setForeground(Color.BLACK);
					uservar.setFont(new Font("Tahoma", Font.PLAIN, 16));

				}

			}
			@Override
			public void focusLost(FocusEvent e) {
				usersap.setForeground(new Color(192, 192, 192));

				if(uservar.getText().equals("")) {
					uservar.setText("Enter Registered Email");
					uservar.setForeground(Color.LIGHT_GRAY);
					uservar.setFont(new Font("Tahoma", Font.PLAIN, 10));}

			}
		});


		uservar.setBounds(123, 220, 177, 25);
		panel2.add(uservar);
		panel2.add(usersap);
		uservar.setColumns(10);

		JLabel msg = new JLabel("");
		msg.setBounds(138, 251, 141, 20);
		panel2.add(msg);
		
		JSeparator passsap = new JSeparator();
		passsap.setForeground(Color.LIGHT_GRAY);
		passsap.setBounds(123, 297, 177, 11);
		
		
		passwordField = new JPasswordField();
		passwordField.setText("Enter Password");
		passwordField.setBorder(null);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		passwordField.setForeground(Color.LIGHT_GRAY);
		passwordField.setEchoChar((char)0);

		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passsap.setForeground(new Color(11, 218, 81));
passwordField.setForeground(Color.BLACK);


				if(passwordField.getText().length()==0) {
					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);




				}
				else if(passwordField.getText().length()>0 && passfocus==0) {
					passwordField.setEchoChar('●');
					passwordField.setText("");
					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);
					passfocus=1;
				}
				else {
					passwordField.setEchoChar('●');

					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);

				}




			}
		});


		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passsap.setForeground(new Color(0, 255, 255));

				passwordField.setForeground(Color.BLACK);

				if(passwordField.getText().length()==0) {
					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);




				}
				else if(passwordField.getText().length()>0 && passfocus==0) {
					passwordField.setEchoChar('●');
					passwordField.setText("");
					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);
					passfocus=1;
				}
				else {
					passwordField.setEchoChar('●');

					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);

				}



			}

			@Override
			public void focusLost(FocusEvent e) {

				passsap.setForeground(Color.LIGHT_GRAY);
					if(passwordField.getText().length()==0) {
						passwordField.setEchoChar((char)0);
						passwordField.setText("Enter Password");
						passwordField.setForeground(Color.LIGHT_GRAY);
						passwordField.setFont(new Font("Tahoma", Font.PLAIN, 10));
						passfocus=0;

					}

				}



		});
		passwordField.setBounds(123, 272, 177, 25);
		
		
		
		
		panel2.add(passwordField);
		panel2.add(passsap);
		
		ImageIcon eyecloseimageIcon=new ImageIcon("myImageData\\closeeyeIcon.png");
		ImageIcon eyeopenimageIcon=new ImageIcon("myImageData\\openeyeIcon.png");
		
		
		 Image eyeopenimage = eyeopenimageIcon.getImage(); 
		 Image eyecloseimage = eyecloseimageIcon.getImage();
		 
	        Image eyecloseresizedImage = eyecloseimage.getScaledInstance(24, 28, Image.SCALE_DEFAULT);
	        Image eyeopenresizedImage = eyeopenimage.getScaledInstance(24, 28, Image.SCALE_DEFAULT);
	        
	        ImageIcon eyecloseresizedIcon = new ImageIcon(eyecloseresizedImage);
	        ImageIcon eyeopenresizedIcon = new ImageIcon(eyeopenresizedImage);
	        
	        JLabel eye =new JLabel(eyecloseresizedIcon);


		eyeflag=1;

		eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(eyeflag==1) {
					
					eye.setIcon(eyeopenresizedIcon);
				passwordField.setEchoChar((char)0);
				eyeflag=0;
				}
				else if(eyeflag==0) {
					
					eye.setIcon(eyecloseresizedIcon);
					eyeflag=1;
					passwordField.setEchoChar('●');


				}



			}
		});


		eye.setBounds(310, 272, 24, 28);
		panel2.add(eye);
		
		
		JLabel lblNewLabel_3 = new JLabel("Forgot your Password ?");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_3.repaint();
				lblNewLabel_3.setOpaque(true);
				lblNewLabel_3.setBackground(Color.WHITE);
				lblNewLabel_3.setForeground(Color.RED);
				lblNewLabel_3.repaint();
				frame.setLocation(500, 175);

				forgot f1=new forgot();
				f1.setLocationRelativeTo(null);
				f1.setUndecorated(true);
				f1.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 30, 30));
				f1.setVisible(true);
				frame.dispose();


			}


			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_3.repaint();
				lblNewLabel_3.setOpaque(true);
				lblNewLabel_3.setBackground(Color.WHITE);
				lblNewLabel_3.setForeground(new Color(0, 191, 255));
				lblNewLabel_3.repaint();
			}
		});
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setForeground(new Color(0, 191, 255));
		lblNewLabel_3.setBounds(188, 299, 112, 20);
		panel2.add(lblNewLabel_3);
		
		ImageIcon LoginImageIcon=new ImageIcon("myImageData\\login.png");

		 Image Loginimage = LoginImageIcon.getImage();

	        Image LoginResized = Loginimage.getScaledInstance(120, 40, Image.SCALE_DEFAULT);
	        
	        ImageIcon LoginBtnicon= new ImageIcon(LoginResized);
	        
	         loginbtn=new JLabel(LoginBtnicon);
	        
	        loginbtn.setBounds(150,340,120,40);
	        loginbtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					 
					try {
						String query="select * from users where  email=? and pass=? ";
					 pst=connection.prepareStatement(query);
					 
					
						pst.setString(1,uservar.getText().trim());
						pst.setString(2,passwordField.getText().trim());

						rs=pst.executeQuery();
						int count=0;
						while(rs.next()) {
							count=count+1;
						}
						pst.close();
						rs.close();
						if(count == 1) {



							uservar.setForeground(new Color(32, 178, 170));
							passwordField.setForeground(new Color(32, 178, 170));

							s1.append(uservar.getText());
							gmail=uservar.getText();



							frame.dispose();
							mframe m1=new mframe();
			
							m1.setLocationRelativeTo(null);
							m1.setUndecorated(true);
							m1.setShape(new RoundRectangle2D.Double(0, 0, 1200, 750, 30, 30));
								

							
							m1.setVisible(true);
							



						}
						else if(count>1) {
							JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
						}
						else
						{
							uservar.setForeground(new Color(250, 128, 114));
							passwordField.setForeground(new Color(250, 128, 114));
							msg.setForeground(new Color(250, 128, 114));

							msg.setText("Try Again!!!");

						}
						
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, e1);
					}
					  

				}
			});
	       panel2.add(loginbtn);
	        
		frame.getContentPane().add(panel2);
		
		ImageIcon AvatarImageIcon=new ImageIcon("myImageData\\usernameIcon.png");

		 Image Avatarimage = AvatarImageIcon.getImage();

	        Image AvatarResized = Avatarimage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	        
	        ImageIcon Avataricon= new ImageIcon(AvatarResized);
		
		JLabel lblNewLabel_5 = new JLabel(Avataricon);
		lblNewLabel_5.setBounds(83, 220, 30, 30);
		panel2.add(lblNewLabel_5);
		
		ImageIcon PassImageIcon=new ImageIcon("myImageData\\passkey.png");

		 Image Passimage = PassImageIcon.getImage();

	        Image PassResized = Passimage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	        
	        ImageIcon Passicon= new ImageIcon(PassResized);
		
		JLabel lblNewLabel_61 = new JLabel(Passicon);
		lblNewLabel_61.setBounds(83, 272, 30, 30);
		panel2.add(lblNewLabel_61);
		
		JLabel lblNewLabel_7 = new JLabel("Don't have an account ?");
		lblNewLabel_7.setBounds(123, 402, 141, 21);
		lblNewLabel_7.setForeground(new Color(108,125,140));
		panel2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_6 = new JLabel("Sign Up");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_6.repaint();
				lblNewLabel_6.setOpaque(true);
				lblNewLabel_6.setBackground(Color.WHITE);
				lblNewLabel_6.setForeground(Color.GREEN);
				lblNewLabel_6.repaint();
				frame.setLocation(500, 175);

				signup s=new signup();

				s.setLocationRelativeTo(null);
				s.setUndecorated(true);
				s.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 30, 30));
					

				s.setVisible(true);
				frame.dispose();


			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_6.repaint();
				lblNewLabel_6.setOpaque(true);
				lblNewLabel_6.setBackground(Color.WHITE);
				lblNewLabel_6.setForeground(new Color(0, 191, 255));
				lblNewLabel_6.repaint();
			}
		});
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setForeground(new Color(0, 191, 255));
		lblNewLabel_6.setBounds(263, 401, 49, 20);
		
		panel2.add(lblNewLabel_6);
		
		frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 30, 30));
		
		
		
	}
	
	public static StringBuffer tokk() {
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s1;
	}

}

