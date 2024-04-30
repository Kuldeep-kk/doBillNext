package doBillNext;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class forgot extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email;
	private JTextField fabvar;
	private JPasswordField passwordField;
	public int eyeflag=1;
	public int cnfeyeflag=1;
	public int passfocus=0;
	public int userfocus=0;
	public int cnfpassfocus=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forgot frame = new forgot();
					frame.setBackground(null);
					frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
					 frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 30, 30));
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection=null;
	PreparedStatement pst=null;
	PreparedStatement pst1 = null;
	ResultSet rs=null;
	

	/**
	 * Create the frame.
	 */
	public forgot() {
		connection=databaseconnector.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 430);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		
		JPanel panel1=new JPanel();
		panel1.setBounds(-50,0,370,482);
		panel1.setLayout(null);
		
		ImageIcon logoImg=new ImageIcon("myImageData\\forgotposter.png");
		 Image image = logoImg.getImage();
	      Image resizedImage = image.getScaledInstance(370, 350, Image.SCALE_DEFAULT);
	      ImageIcon resizedIcon = new ImageIcon(resizedImage);
		JLabel lblNewLabel =new JLabel(resizedIcon);
		

		lblNewLabel.setBounds(10, 20, 370,430 );
		
		panel1.add(lblNewLabel);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(320,0,445,482);
		panel2.setBackground(new Color(255,255,255));
		panel2.setLayout(null);
		
		contentPane.add(panel2);
		
		JLabel lblNewLabel_cl = new JLabel(" X ");
		lblNewLabel_cl.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_cl.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_cl.setForeground(SystemColor.activeCaption);
		lblNewLabel_cl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_cl.repaint();
				lblNewLabel_cl.setOpaque(true);
				lblNewLabel_cl.setBackground(new Color(247,101,94));
				lblNewLabel_cl.setForeground(Color.WHITE);
				lblNewLabel_cl.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_cl.repaint();
				lblNewLabel_cl.setOpaque(true);
				lblNewLabel_cl.setBackground(Color.WHITE);
				lblNewLabel_cl.setForeground(SystemColor.activeCaption);
				lblNewLabel_cl.repaint();
			}
		});
		lblNewLabel_cl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_cl.setBounds(394,11,30,30);
		
		panel2.add(lblNewLabel_cl);
		
		JLabel lblNewLabel_1 = new JLabel("Forgot Your");
		lblNewLabel_1.setForeground(new Color(108,125,140));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(30, 37, 136, 33);
		panel2.add(lblNewLabel_1);
		
		ImageIcon LineImageIcon=new ImageIcon("myImageData\\ppbottomLine.png");

		 Image Lineimage = LineImageIcon.getImage();

	        Image LineResized = Lineimage.getScaledInstance(100, 20, Image.SCALE_DEFAULT);
	        
	        ImageIcon Lineicon= new ImageIcon(LineResized);
		
		JLabel lblNewLabel_4 = new JLabel("Enter the email address and your favorite song associated with your account to reset your your password.");
		lblNewLabel_4.setBounds(37, 100, 361, 20);
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 12));
		lblNewLabel_4.setForeground(new Color(108,125,140));
		panel2.add(lblNewLabel_4);
		
		ImageIcon nameImageIcon=new ImageIcon("myImageData\\userformIcon.png");

		 Image nameimage = nameImageIcon.getImage();

	        Image nameResized = nameimage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	        
	        ImageIcon nameicon= new ImageIcon(nameResized);
		
		JPanel guesspanel = new JPanel();
		guesspanel.setBounds(114, 199, 234, 33);
		guesspanel.setBackground(Color.WHITE);
		panel2.add(guesspanel);
		guesspanel.setLayout(null);
		
		ImageIcon guessImageIcon=new ImageIcon("myImageData\\guessformIcon.png");

		 Image guessimage = guessImageIcon.getImage();

	        Image guessResized = guessimage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	        
	        ImageIcon guessicon= new ImageIcon(guessResized);
		
		JLabel guessIcon = new JLabel(guessicon);
		
		guessIcon.setBounds(10, 2, 30, 30);
		guesspanel.add(guessIcon);
		
		JSeparator favsap = new JSeparator();
		favsap.setForeground(Color.GRAY);
		favsap.setBounds(114, 232, 234, 2);
		
		panel2.add(favsap);
		
		fabvar = new JTextField();
		fabvar.setColumns(10);
		fabvar.setText("Enter Your Favorite Song");
		fabvar.setForeground(Color.LIGHT_GRAY);
		fabvar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fabvar.setBackground(null);
		fabvar.setColumns(10);
		fabvar.setBorder(null);
		fabvar.setOpaque(true);
		fabvar.repaint();

		fabvar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				guesspanel.setBackground(new Color(246,246,246));
				favsap.setForeground(new Color(0, 255, 255));

				if (fabvar.getText().equals("Enter Your Favorite Song")) {
					fabvar.setText("");
					fabvar.setForeground(Color.BLACK);
					fabvar.setFont(new Font("Tahoma", Font.PLAIN, 16));

				} else {
					fabvar.setForeground(Color.BLACK);
					fabvar.setFont(new Font("Tahoma", Font.PLAIN, 16));

				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				guesspanel.setBackground(Color.WHITE);
				favsap.setForeground(Color.LIGHT_GRAY);

				if (fabvar.getText().equals("")) {
					fabvar.setText("Enter Your Favorite Song");
					fabvar.setForeground(Color.LIGHT_GRAY);
					fabvar.setFont(new Font("Tahoma", Font.PLAIN, 10));
				}

			}
		});
		fabvar.setBounds(58, 0, 166, 33);
		guesspanel.add(fabvar);
		
		
		
		JPanel emailpanel = new JPanel();
		emailpanel.setBackground(Color.WHITE);
		emailpanel.setBounds(114, 150, 234, 33);
		panel2.add(emailpanel);
		emailpanel.setLayout(null);
		
		ImageIcon emailImageIcon=new ImageIcon("myImageData\\emailformIcon.png");

		 Image emailimage = emailImageIcon.getImage();

	        Image emailResized = emailimage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	        
	        ImageIcon emailicon= new ImageIcon(emailResized);
		
		JLabel emailIcon = new JLabel(emailicon);
		emailIcon.setBounds(10, 2, 30, 30);
		emailpanel.add(emailIcon);
		
		JSeparator emailsap = new JSeparator();
		emailsap.setForeground(Color.GRAY);
		emailsap.setBounds(114, 184, 234, 2);
		
		panel2.add(emailsap);
		
		email = new JTextField();
		email.setBounds(58, 0, 166, 33);
		email.setText("Enter Email");

		email.setBorder(null);
		email.setFont(new Font("Tahoma", Font.PLAIN, 10));
		email.setForeground(Color.LIGHT_GRAY);
		email.setBackground(null);
		email.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				emailpanel.setBackground(new Color(246,246,246));
				emailsap.setForeground(new Color(0, 255, 255));

				if (email.getText().equals("Enter Email")) {
					email.setText("");
					email.setForeground(Color.BLACK);
					email.setFont(new Font("Tahoma", Font.PLAIN, 16));

				} else {
					email.setForeground(Color.BLACK);
					email.setFont(new Font("Tahoma", Font.PLAIN, 16));

				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				emailpanel.setBackground(Color.WHITE);
				emailsap.setForeground(new Color(192, 192, 192));

				if (email.getText().equals("")) {
					email.setText("Enter Email");
					email.setForeground(Color.LIGHT_GRAY);
					email.setFont(new Font("Tahoma", Font.PLAIN, 10));
				}

			}
		});
		emailpanel.add(email);
		email.setColumns(10);
		
		
		JPanel passpanel = new JPanel();
		passpanel.setBounds(114, 250, 234, 33);
		passpanel.setBackground(Color.WHITE);
		panel2.add(passpanel);
		passpanel.setLayout(null);
		

		ImageIcon eyeopenimageIcon=new ImageIcon("myImageData\\openeyeIcon.png");
		ImageIcon eyecloseimageIcon=new ImageIcon("myImageData\\closeeyeIcon.png");
		
		
		 Image eyeopenimage = eyeopenimageIcon.getImage(); 
		 Image eyecloseimage = eyecloseimageIcon.getImage();
		 
	        Image eyecloseresizedImage = eyecloseimage.getScaledInstance(24, 28, Image.SCALE_DEFAULT);
	        Image eyeopenresizedImage = eyeopenimage.getScaledInstance(24, 28, Image.SCALE_DEFAULT);
	        
	        ImageIcon eyecloseresizedIcon = new ImageIcon(eyecloseresizedImage);
	        ImageIcon eyeopenresizedIcon = new ImageIcon(eyeopenresizedImage);
		eyeflag=1;
		cnfeyeflag=1;
		
		ImageIcon PassImageIcon=new ImageIcon("myImageData\\passformIcon.png");

		 Image Passimage = PassImageIcon.getImage();

	        Image PassResized = Passimage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	        
	        ImageIcon Passicon= new ImageIcon(PassResized);
		
		JLabel passIcon = new JLabel(Passicon);
		passIcon.setBounds(0, 2, 55, 30);
		passpanel.add(passIcon);
		
		JSeparator passsap = new JSeparator();
		
		passsap.setForeground(Color.GRAY);
		passsap.setBounds(114, 285, 234, 2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(58, 0, 136, 33);
		passpanel.add(passwordField);
		
		passwordField.setText("Enter New Password");
		passwordField.setBorder(null);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		passwordField.setForeground(Color.LIGHT_GRAY);
		passwordField.setBackground(null);;
		passwordField.setEchoChar((char) 0);
		
		JLabel eye = new JLabel(eyecloseresizedIcon);
		eye.setBounds(186, 2, 48, 33);
		passpanel.add(eye);
		
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

		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passpanel.setBackground(new Color(246,246,246));
				passsap.setForeground(new Color(0, 255, 255));
				passwordField.setForeground(Color.BLACK);

				if (passwordField.getText().length() == 0) {
					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);

				} else if (passwordField.getText().length() > 0 && passfocus == 0) {
					passwordField.setEchoChar('●');
					passwordField.setText("");
					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);
					passfocus = 1;
				} else {
					passwordField.setEchoChar('●');

					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);

				}

			}
		});

		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passpanel.setBackground(new Color(246,246,246));
				passsap.setForeground(new Color(0, 255, 255));

				passwordField.setForeground(Color.BLACK);

				if (passwordField.getText().length() == 0) {
					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);

				} else if (passwordField.getText().length() > 0 && passfocus == 0) {
					passwordField.setEchoChar('●');
					passwordField.setText("");
					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);
					passfocus = 1;
				} else {
					passwordField.setEchoChar('●');

					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
					passwordField.setForeground(Color.BLACK);

				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				passpanel.setBackground(Color.WHITE);
				passsap.setForeground(Color.LIGHT_GRAY);

				if (passwordField.getText().length() == 0) {
					passwordField.setEchoChar((char) 0);
					passwordField.setText("Enter New Password");
					passwordField.setForeground(Color.LIGHT_GRAY);
					passwordField.setFont(new Font("Tahoma", Font.PLAIN, 10));
					passfocus = 0;

				}

			}

		});

		
		
		
		
		
		panel2.add(passsap);
		
		ImageIcon PasscnfImageIcon=new ImageIcon("myImageData\\passformIcon.png");

		 Image Passcnfimage = PasscnfImageIcon.getImage();

	        Image PasscnfResized = Passcnfimage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	        
	        ImageIcon Passcnficon= new ImageIcon(PasscnfResized);
		
		
		ImageIcon registerimageIcon=new ImageIcon("myImageData\\registerBtn.png");
		
		
		
		 Image registerimage = registerimageIcon.getImage(); 
		
		 
	        Image registerresizedImage = registerimage.getScaledInstance(150, 50, Image.SCALE_DEFAULT);
	        
	        
	        ImageIcon registerresizedIcon = new ImageIcon(registerresizedImage);
		
		JLabel clearBtn = new JLabel("clear");
		clearBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				clearBtn.requestFocusInWindow();
				
		
				
				
				fabvar.setText("Enter Your Favorite Song");
				fabvar.setForeground(Color.LIGHT_GRAY);
				fabvar.setFont(new Font("Tahoma", Font.PLAIN, 10));
				
				email.setText("Enter Email");
				email.setForeground(Color.LIGHT_GRAY);
				email.setFont(new Font("Tahoma", Font.PLAIN, 10));
				
				passwordField.setEchoChar((char) 0);
				passwordField.setText("Enter New Password");
				passwordField.setForeground(Color.LIGHT_GRAY);
				passwordField.setFont(new Font("Tahoma", Font.PLAIN, 10));
				passfocus = 0;
				
				
				
			}
		});
		clearBtn.setForeground(new Color(255, 102, 51));
		clearBtn.setHorizontalAlignment(SwingConstants.CENTER);
		clearBtn.setVerticalAlignment(SwingConstants.CENTER);
		clearBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		clearBtn.setBounds(190, 357, 97, 33);
		panel2.add(clearBtn);
		
		
		
		
		JLabel lblNewLabel_7 = new JLabel("Already have an account?");
		lblNewLabel_7.setForeground(SystemColor.textInactiveText);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(140, 395, 149, 14);
		panel2.add(lblNewLabel_7);
		
		JLabel signInBtn = new JLabel("SignIn");
		signInBtn.setForeground(new Color(0, 204, 255));
		signInBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		signInBtn.setBounds(300, 395, 48, 14);
		panel2.add(signInBtn);
		
		JLabel lblNewLabel_4_1 = new JLabel("your account to reset your your password.");
		lblNewLabel_4_1.setForeground(new Color(108, 125, 140));
		lblNewLabel_4_1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 12));
		lblNewLabel_4_1.setBounds(37, 115, 361, 20);
		panel2.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(new Color(108, 125, 140));
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(30, 65, 136, 33);
		panel2.add(lblNewLabel_1_1);
		
		ImageIcon forgotImageIcon=new ImageIcon("myImageData\\forgotBtn.png");

		 Image forgotimage = forgotImageIcon.getImage();

	        Image forgotResized = forgotimage.getScaledInstance(150, 40, Image.SCALE_DEFAULT);
	        
	        ImageIcon forgoticon= new ImageIcon(forgotResized);
		
		JLabel forgot = new JLabel(forgoticon);
		
		forgot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					if (!"Enter New Password".equals(passwordField) && passwordField.getText().length() > 0) {
						if(!"Enter Your Favorite Song".equals(fabvar) && fabvar.getText().length()>0) {
						if (!"Enter Email".equals(email) && email.getText().length() > 0
								&& email.getText().contains("@")) {
							
							try {
								String query = "select * from users where email=? and fab=? ";
								pst = connection.prepareStatement(query);
								pst.setString(1, email.getText().trim());
								pst.setString(2, fabvar.getText().trim());
								

								ResultSet rs = pst.executeQuery();
								int count = 0;
								while (rs.next()) {
									count = count + 1;
								}
								if (count == 1) {

									try {
										String query2 = "update users set pass='" + passwordField.getText()
												+ "' where  email='"
												+ email.getText() + "'";
										pst1 = connection.prepareStatement(query2);
										pst1.execute();
										pst1.close();
										JOptionPane.showMessageDialog(null, "Data Updated");
										pst.close();
										rs.close();
										Login t = new Login();
										Login.call();
										dispose();

									} catch (Exception e2) {
										JOptionPane.showMessageDialog(null, e2);

									}
								} else {
									JOptionPane.showMessageDialog(null, ("No existing author found with email=" + email.getText() + " &  Favorite Song=" + fabvar.getText()));
								}
								

							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1);
							}
						} else {
							JOptionPane.showMessageDialog(forgot,"Please enter email correctly");

						}

					} else {
						JOptionPane.showMessageDialog(forgot,"Please enter favorite song correctly");


					}
				}

				else {
					JOptionPane.showMessageDialog(forgot,"Please enter new password correctly");
				
				}
			

			}
		});
		forgot.setBounds(160, 312, 150, 40);
		panel2.add(forgot);
		
		signInBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Login t = new Login();
				Login.call();
				dispose();

			}

		});
		
		
		
		contentPane.add(panel1);

		setContentPane(contentPane);
	}
}
