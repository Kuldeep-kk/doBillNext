package doBillNext;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;



public class bill extends JFrame implements Printable {



	private JPanel contentPane;
	private static Connection custconnect=null;
	private static Connection cartConnect=null;
	static PreparedStatement pstp=null;
	static PreparedStatement pstc=null;
	static ResultSet rsp=null;

	String invoice;
	long n;
	private static JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					bill frame = new bill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static public void updatecart() {
		try {

			table.setFont(new Font("Tahoma", Font.PLAIN, 13));
			table.setForeground(new Color(0, 191, 255));
			
			table.setRowHeight(25);
			//table.getColumnModel().getColumn(1).setPreferredWidth(5);



			table.setModel(
					new DefaultTableModel(new Object[][] {}, new String[] { "Product", "Category", "Qty", "Price" }));
			table.getColumnModel().getColumn(0).setPreferredWidth(253);
			table.getColumnModel().getColumn(1).setPreferredWidth(54);
			table.getColumnModel().getColumn(2).setPreferredWidth(25);
			

			String query="select * from cartdb ";
			 pstp=cartConnect.prepareStatement(query);


			rsp=pstp.executeQuery();
			ResultSetMetaData rsmd=rsp.getMetaData();

			DefaultTableModel model= (DefaultTableModel) table.getModel();

			//table.getColumn(1).setPreferredWidth(5);
			int cols=rsmd.getColumnCount();
			//String[] colName= {"Product","Category","Price"};
				//model.setColumnIdentifiers(colName);

				String name,cate,qty,price;


				while(rsp.next()) {
					name=rsp.getString(2);
					cate=rsp.getString(3);
					qty=rsp.getString(4);
					price=rsp.getString(5);



					String[] row= {name,cate,qty,price};

					model.addRow(row);


			}

			pstp.close();
			rsp.close();
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1);
		}




	}
	public void FilePrintClicked(){

        PrinterJob job = PrinterJob.getPrinterJob();

        PageFormat format = job.defaultPage();
        format.setOrientation(PageFormat.PORTRAIT);

        job.setPrintable( this, format);

        try{
            if(job.printDialog()) job.print();
        }
        catch(Exception e){e.printStackTrace();}
        dispose();


    }

//actual printing function
    @Override
	public int print(Graphics g, PageFormat format, int pagenum) {

       if (pagenum > 0){
           return Printable.NO_SUCH_PAGE;
       }

       g.translate((int)format.getImageableX(), (int)format.getImageableY());

       float pageWidth = (float)format.getImageableWidth();
       float pageHeight = (float)format.getImageableHeight();

       float imageHeight = this.getHeight();
       float imageWidth = this.getWidth();

       float scaleFactor = Math.min(pageWidth/imageWidth, pageHeight/imageHeight);

       int scaledWidth = (int)((imageWidth)*scaleFactor);

       int scaledHeight = (int)((imageHeight)*scaleFactor);

       BufferedImage canvas = new BufferedImage( this.getWidth(),  this.getHeight(), BufferedImage.TYPE_INT_RGB);
       Graphics2D gg = canvas.createGraphics();
       this.paint( gg );
       Image img = canvas ;

       g.drawImage(img, 0, 0, scaledWidth, scaledHeight, null );

       return Printable.PAGE_EXISTS;

    }

	/**
	 * Create the frame.
	 */
	public bill() {
		cartConnect=cartconnect.cartConnector();
		custconnect=printdatabase.dbConnector();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 810);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(2, 2, 4, 4, new Color(0, 255, 255)));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Comp_name = new JLabel("ITZone Inc.");
		Comp_name.setForeground(new Color(0, 191, 255));
		Comp_name.setFont(new Font("Dialog", Font.PLAIN, 45));
		Comp_name.setBounds(10, -10, 281, 70);
		contentPane.add(Comp_name);

		JTextPane Comp_add = new JTextPane();
		Comp_add.setForeground(new Color(105, 105, 105));
		Comp_add.setFont(new Font("Dialog", Font.ITALIC, 17));
		Comp_add.setBounds(10, 57, 281, 94);
		Comp_add.setEditable(false);
		contentPane.add(Comp_add);

		JLabel Comp_mob = new JLabel("");
		Comp_mob.setForeground(new Color(105, 105, 105));
		Comp_mob.setFont(new Font("Dialog", Font.ITALIC, 17));
		Comp_mob.setBounds(58, 150, 172, 25);
		contentPane.add(Comp_mob);

		JLabel lblNewLabel = new JLabel("Mob.:");
		lblNewLabel.setForeground(new Color(105, 105, 105));
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 17));
		lblNewLabel.setBounds(10, 150, 48, 25);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("TAX INVOICE");
		lblNewLabel_1.setForeground(new Color(255, 127, 80));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_1.setBounds(176, 174, 149, 37);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(370, -3, 142, 154);
		ImageIcon imgm=new ImageIcon("myImageData\\startpic.png");
		lblNewLabel_2.setIcon(imgm);
		contentPane.add(lblNewLabel_2);

		JLabel billto = new JLabel("Bill to :");
		billto.setForeground(new Color(0, 128, 128));
		billto.setFont(new Font("Dialog", Font.ITALIC, 17));
		billto.setBounds(10, 207, 58, 25);
		contentPane.add(billto);

		JLabel custname = new JLabel("");
		custname.setForeground(new Color(105, 105, 105));
		custname.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		custname.setBounds(10, 243, 220, 25);
		custname.setText(custDetails.cname.getText());
		contentPane.add(custname);

		JTextPane custaddress = new JTextPane();
		custaddress.setForeground(SystemColor.controlDkShadow);
		custaddress.setFont(new Font("Dialog", Font.ITALIC, 17));
		custaddress.setEditable(false);
		custaddress.setBounds(10, 263, 281, 70);
		custaddress.setText((custDetails.cadd.getText()));
		contentPane.add(custaddress);

		JLabel lblNewLabel_3 = new JLabel("Mob.:");
		lblNewLabel_3.setForeground(SystemColor.controlDkShadow);
		lblNewLabel_3.setFont(new Font("Dialog", Font.ITALIC, 17));
		lblNewLabel_3.setBounds(10, 337, 48, 25);
		contentPane.add(lblNewLabel_3);

		JLabel custmobile = new JLabel("");
		custmobile.setForeground(SystemColor.controlDkShadow);
		custmobile.setFont(new Font("Dialog", Font.ITALIC, 17));
		custmobile.setBounds(58, 337, 172, 25);
		custmobile.setText((custDetails.cmob.getText()));
		contentPane.add(custmobile);

		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm:ss a");

		JLabel date = new JLabel("New label");
		date.setForeground(new Color(105, 105, 105));
		date.setFont(new Font("Tahoma", Font.PLAIN, 15));
		date.setText(dateFormat.format(Calendar.getInstance().getTime()));
		date.setBounds(397, 263, 115, 25);
		contentPane.add(date);

		JLabel time = new JLabel("New label");
		time.setForeground(new Color(105, 105, 105));
		time.setFont(new Font("Tahoma", Font.PLAIN, 15));
		time.setText(timeFormat.format(Calendar.getInstance().getTime()));
		time.setBounds(397, 286, 115, 25);
		contentPane.add(time);

		JLabel lblNewLabel_4 = new JLabel("Invoice No :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(301, 244, 101, 20);
		contentPane.add(lblNewLabel_4);

		JLabel inv = new JLabel("24-06-2022");
		inv.setForeground(SystemColor.controlDkShadow);
		inv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inv.setBounds(397, 243, 115, 25);
		contentPane.add(inv);

		JLabel lblNewLabel_4_1 = new JLabel("Date :");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4_1.setBounds(344, 263, 58, 20);
		contentPane.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("Time :");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4_1_1.setBounds(344, 287, 58, 20);
		contentPane.add(lblNewLabel_4_1_1);

		try {

			String query="select * from printdb where cid='1'";
			pstp=custconnect.prepareStatement(query);


			rsp=pstp.executeQuery();

				while(rsp.next()) {

					Comp_name.setText(rsp.getString("cname"));
					Comp_mob.setText(rsp.getString("cmob"));
					Comp_add.setText(rsp.getString("caddress"));
					invoice=rsp.getString("invoice");




			}
				pstp.close();
				rsp.close();
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1);
		}
		inv.setText(invoice);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 373, 519, 216);
		scrollPane.repaint();
		scrollPane.setOpaque(true);
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBackground(SystemColor.control);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_12 = new JLabel("Sub-Total :");
		lblNewLabel_12.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_12.setBounds(236, 590, 142, 33);
		contentPane.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("GST   @18% :");
		lblNewLabel_13.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_13.setBounds(236, 631, 115, 33);
		contentPane.add(lblNewLabel_13);

		JLabel lblNewLabel_12_1 = new JLabel("Grand Total  :");
		lblNewLabel_12_1.setForeground(new Color(0, 255, 127));
		lblNewLabel_12_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_12_1.setBounds(246, 684, 132, 33);
		contentPane.add(lblNewLabel_12_1);

		JLabel gtotal = new JLabel("0.0");
		gtotal.setForeground(new Color(240, 255, 255));
		gtotal.setText(mframe.gtotal.getText());
		gtotal.setFont(new Font("Dialog", Font.BOLD, 17));
		gtotal.setBounds(399, 684, 134, 33);
		contentPane.add(gtotal);

		JLabel gst = new JLabel("0.0");
		gst.setForeground(new Color(218, 165, 32));
		gst.setText(mframe.gst.getText());
		gst.setFont(new Font("Dialog", Font.BOLD, 17));
		gst.setBounds(399, 639, 140, 25);
		contentPane.add(gst);

		JLabel subtotal = new JLabel("0");
		subtotal.setText(mframe.subtotal.getText());
		subtotal.setForeground(new Color(218, 165, 32));
		subtotal.setFont(new Font("Dialog", Font.BOLD, 17));
		subtotal.setBounds(399, 595, 140, 25);
		contentPane.add(subtotal);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBackground(new Color(255, 127, 80));
		lblNewLabel_5.repaint();
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBounds(236, 680, 303, 37);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("AUTHORIZED SIGNATURE");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(313, 780, 216, 17);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("TERMS & CONDITION");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 600, 172, 21);
		contentPane.add(lblNewLabel_7);

		JTextArea lblNewLabel_8 = new JTextArea("");
		lblNewLabel_8.setForeground(new Color(112, 128, 144));
		lblNewLabel_8.setFont(new Font("MS PGothic", Font.PLAIN, 13));
		lblNewLabel_8.setOpaque(true);
		lblNewLabel_8.repaint();
		lblNewLabel_8.setText("It also specifies the jurisdictions \n and governing laws and authorities \n in case of any disputes in payments");
		lblNewLabel_8.setBackground(new Color(245, 245, 245));
		lblNewLabel_8.setBounds(10, 620, 220, 46);
		lblNewLabel_8.setEditable(false);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.repaint();
		lblNewLabel_9.setOpaque(true);
		lblNewLabel_9.setBackground(new Color(245, 245, 245));
		lblNewLabel_9.setBounds(303, 725, 209, 50);
		contentPane.add(lblNewLabel_9);

		JLabel lblClose = new JLabel("Close");
		lblClose.setOpaque(true);
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblClose.setBorder(BorderFactory.createRaisedBevelBorder());
		lblClose.setBackground(new Color(255, 127, 80));
		lblClose.setBounds(21, 750, 101, 25);
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

		JLabel lblPrint = new JLabel("Print");
		lblPrint.setOpaque(true);
		lblPrint.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrint.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPrint.setBorder(BorderFactory.createRaisedBevelBorder());
		lblPrint.setBackground(new Color(255, 215, 0));
		lblPrint.setBounds(21, 722, 101, 25);
		contentPane.add(lblPrint);
		
		JLabel lblNewLabel_3_1 = new JLabel("Bill Generated By:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3_1.setBounds(282, 222, 88, 19);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel user = new JLabel("");
		user.setText(mframe.user.getText());
		user.setForeground(new Color(0, 191, 255));
		user.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		user.setBounds(374, 218, 99, 25);
		contentPane.add(user);
		lblPrint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPrint.setBackground(new Color(0, 191, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblPrint.setBackground(new Color(255, 215, 0));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				lblPrint.setBorder(BorderFactory.createLoweredBevelBorder());
				FilePrintClicked();


			}
			@Override
			public void mouseReleased(MouseEvent e) {

				lblPrint.setBorder(BorderFactory.createRaisedBevelBorder());
			}



		});


		n=Long.parseLong(invoice);
		n=n+1;
		invoice=String.valueOf(n);

		try {
			String query2="update printdb set invoice='"+invoice+"' where cid='1'";
			pstp=custconnect.prepareStatement(query2);
			pstp.execute();
			pstp.close();



		}
		catch(Exception e2) {

			JOptionPane.showMessageDialog(null, e2);


		}

		updatecart();


	}
}
