package doBillNext;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class projloading extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JProgressBar progressBar = new JProgressBar(0,99);
	JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					projloading frame = new projloading();
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
	
	Thread t=new Thread(new Runnable()  {
		@Override
		public void run() {
		int count=0;
		while(count<=99) {
		progressBar.setValue(count);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count=count+1;
		
		}
		Login l=new Login();
		Login.call();
		dispose();
		
		}

	});

	JLabel lblNewLabel_1;

	Thread t2=new Thread(new Runnable()  {
		@Override
		public void run() {
		int textcount=-200;
		while(textcount<=50) {
			lblNewLabel.setBounds(textcount,100, 200, 170);


		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textcount=textcount+1;
		}

		}
	});
	Thread t3=new Thread(new Runnable()  {
		@Override
		public void run() {
		int textcount=500;

		while(textcount>=250) {
			lblNewLabel_2.setBounds(textcount, 30, 400, 290);

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textcount=textcount-1;

		}
		progressBar.setVisible(true);
		t.start();
		}
	});


	
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel bg;
	private JLabel lblNewLabel_3;

	/**
	 * Create the frame.
	 */
	public projloading() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 380);
		contentPane = new JPanel();
		contentPane.setBackground(null);

		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);


		lblNewLabel_3 = new JLabel("Starting...");
		lblNewLabel_3.setForeground(new Color(0, 191, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_3.setBounds(10, 238, 89, 26);
		lblNewLabel_3.setVisible(false);


		progressBar.setBounds(30, 350, 560, 15);
		contentPane.add(progressBar);

		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(255,110,99,200));
		progressBar.setBorder(null);
		progressBar.setVisible(false);




		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 300, 600, 26);

		contentPane.add(lblNewLabel_1);
		
		ImageIcon logoImg=new ImageIcon("myImageData\\logo.png");

		lblNewLabel =new JLabel(logoImg);

		Image perfectLogoImg=logoImg.getImage().getScaledInstance(200,170,Image.SCALE_SMOOTH);
		ImageIcon finalLogoImg=new ImageIcon(perfectLogoImg);
		lblNewLabel.setIcon(finalLogoImg);
		lblNewLabel.setBounds(30, 70, 136, 97);
		
		contentPane.add(lblNewLabel);
		
		ImageIcon hero=new ImageIcon("myImageData\\loadingImage.png");

		lblNewLabel_2 =new JLabel(hero);

		Image perfectImg=hero.getImage().getScaledInstance(400,290,Image.SCALE_SMOOTH);
		ImageIcon finalImg=new ImageIcon(perfectImg);
		lblNewLabel_2.setIcon(finalImg);
		lblNewLabel_2.setBounds(158, 97,400 ,200);
		contentPane.add(lblNewLabel_2);



		bg = new JLabel("");
		bg.setBounds(-50, -150, 686, 535);
		ImageIcon imgbg=new ImageIcon("myImageData\\lodingpagebg.png");
		bg.setIcon(imgbg);
		

		t2.start();
		t3.start();

		contentPane.add(bg);
	}
	

}
