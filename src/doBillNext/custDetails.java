package doBillNext;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class custDetails extends JFrame {

	private JPanel contentPane;
	static JTextField cmob;
	static JTextField cname;
	static JTextArea cadd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					custDetails frame = new custDetails();
					frame.setUndecorated(true);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public custDetails() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createRaisedBevelBorder());

		JLabel lblNewLabel = new JLabel("Customer Details");
		lblNewLabel.setForeground(new Color(255, 99, 71));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(118, 0, 217, 54);
		lblNewLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Customer Name :");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(33, 65, 140, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Customer Address :");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(10, 114, 153, 25);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Mobile No :");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(60, 263, 127, 25);
		contentPane.add(lblNewLabel_1_2);

		cmob = new JTextField();
		cmob.setFont(new Font("Dialog", Font.PLAIN, 17));
		cmob.setColumns(10);
		cmob.setBounds(161, 265, 114, 20);
		contentPane.add(cmob);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(162, 113, 220, 138);
		contentPane.add(scrollPane);

		cadd = new JTextArea();
		scrollPane.setViewportView(cadd);
		cadd.setFont(new Font("Dialog", Font.ITALIC, 17));

		cname = new JTextField();
		cname.setFont(new Font("Dialog", Font.PLAIN, 17));
		cname.setColumns(10);
		cname.setBounds(161, 65, 222, 25);
		contentPane.add(cname);

		JLabel print = new JLabel("Print");
		print.setOpaque(true);
		print.setHorizontalAlignment(SwingConstants.CENTER);
		print.setFont(new Font("Tahoma", Font.BOLD, 17));
		print.setBorder(BorderFactory.createRaisedBevelBorder());
		print.setBackground(new Color(127, 255, 0));
		print.setBounds(90, 326, 127, 25);
		print.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				print.setBackground(new Color(0, 191, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				print.setBackground(new Color(127, 255, 0));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				print.setBorder(BorderFactory.createLoweredBevelBorder());


			}
			@Override
			public void mouseReleased(MouseEvent e) {

				print.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(cadd.getText().length()>0 && cname.getText().length()>0 && cmob.getText().length()>0) {
				bill b=new bill();
				b.setUndecorated(true);

				b.setLocationRelativeTo(null);
				b.setVisible(true);
				dispose();}
				else {
					JOptionPane.showMessageDialog(null, "All Blanks must be Filled!!!");
				}

			}
		});
		contentPane.add(print);

		JLabel lblClose = new JLabel("Close");
		lblClose.setOpaque(true);
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblClose.setBorder(BorderFactory.createRaisedBevelBorder());
		lblClose.setBackground(new Color(255, 127, 80));
		lblClose.setBounds(227, 326, 127, 25);
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblClose.setBackground(new Color(0, 191, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblClose.setBackground(new Color(255, 127, 80));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				lblClose.setBorder(BorderFactory.createLoweredBevelBorder());
				dispose();

			}
			@Override
			public void mouseReleased(MouseEvent e) {

				lblClose.setBorder(BorderFactory.createRaisedBevelBorder());
			}



		});
		contentPane.add(lblClose);
	}
}
