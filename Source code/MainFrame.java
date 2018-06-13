import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblTitle = new JLabel("<html><font size='7'>QM car park system</font></html>");
	private JLabel lblSymbol = new JLabel();
	private JButton b1 = new JButton("  Administrator  ");
	private JButton b2 = new JButton("Car Park System");
	private JPanel pNorth = new JPanel();
	private JPanel pCenter = new JPanel();
	private JPanel pCenterLeft = new JPanel();
	private JPanel pCenterRight = new JPanel();

	MainFrame(){
		//GUI
		this.add(pNorth,BorderLayout.NORTH);
		this.add(new JLabel("   "),BorderLayout.WEST);
		this.add(new JLabel("         "),BorderLayout.EAST);
		this.add(pCenter,BorderLayout.CENTER);
		pNorth.add(lblTitle);
		pCenter.setLayout(new GridLayout(1,2));
		pCenter.add(pCenterLeft);
		pCenter.add(pCenterRight);
		pCenterRight.setLayout(new FlowLayout());
		Icon iconP = new ImageIcon(getClass().getResource("image/p.png"));
		lblSymbol.setIcon(iconP);
		pCenterRight.add(new JLabel("                                    "));
		Font bigFont = new Font("serif",Font.BOLD,22);//Set the size of the words.
		b1.setFont(bigFont);
		b1.setBackground(Color.LIGHT_GRAY);
		pCenterRight.add(b1);
		pCenterRight.add(new JLabel("                                    "));
		b2.setFont(bigFont);
		b2.setBackground(Color.LIGHT_GRAY);
		pCenterRight.add(b2);
		pCenterLeft.add(lblSymbol);
		this.setSize(450,300);
		this.setLocation(300, 200);
		this.setVisible(true);
		this.setTitle("QM Car Park System");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b1.addMouseListener(new MouseAdapter() {//Go to Admin login
			public void mouseClicked(MouseEvent e) {
				new LoginFrame();
				dispose();
			}
		});
		
		b2.addMouseListener(new MouseAdapter() {//Go to park system
			public void mouseClicked(MouseEvent e) {
				new ServiceFrame();
				dispose();
			}
		});
		
	}
	
	public static void main(String[] args){
		new MainFrame();
	}
}
