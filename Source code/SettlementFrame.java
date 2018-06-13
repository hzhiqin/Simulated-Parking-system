import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

import javax.swing.*;


public class SettlementFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel title = new JLabel("<html><font face='Times New Roman'size='7'>Make Settlement</font></html>");
    
    private JPanel pNorth = new JPanel();
	private JPanel pCenter = new JPanel();
	
	private JButton GenerateBill = new JButton("      Generate Monthly Bill   ");
	private JButton SalaryCard = new JButton("       Salary Card System         ");
	private JButton Back = new JButton("                      Back                     ");
	
	SettlementFrame() {
                //GUI
		pNorth.add(title);
		this.add(pNorth, BorderLayout.NORTH);
		this.add(new JLabel("                                 "), BorderLayout.WEST);
		this.add(new JLabel("                                 "), BorderLayout.EAST);
		this.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BoxLayout(pCenter,BoxLayout.Y_AXIS));
		Font bigFont4 = new Font("serif",Font.BOLD,17);
		GenerateBill.setFont(bigFont4);
		GenerateBill.setBackground(Color.LIGHT_GRAY);
		SalaryCard.setFont(bigFont4);
		SalaryCard.setBackground(Color.LIGHT_GRAY);
		Back.setFont(bigFont4);
		Back.setBackground(Color.LIGHT_GRAY);
		pCenter.add(new JLabel(" "));
		pCenter.add(GenerateBill);
		pCenter.add(new JLabel(" "));
		pCenter.add(SalaryCard);
		pCenter.add(new JLabel(" "));
		pCenter.add(Back);
	
		this.setSize(450,300);
		this.setLocation(300, 200);
		this.setVisible(true);
		this.setTitle("QM Car Park System");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GenerateBill.addMouseListener(new MouseAdapter() {//Go to Generate Monthly Bill
			public void mouseClicked(MouseEvent e) {
				new MonthlyBillFrame();
			}
		});
		
		SalaryCard.addMouseListener(new MouseAdapter() {// Search the Salary Card System
			public void mouseClicked(MouseEvent e) {
				new CardSystemFrame();
			}
		});
		
		Back.addMouseListener(new MouseAdapter() {// Go Back to the Main Menu
 			public void mouseClicked(MouseEvent e) {
				new AdminChoiceFrame();
				dispose();
			}
		});
	}
}
