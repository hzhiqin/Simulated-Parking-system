import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class AdminChoiceFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel pan = new JPanel();
	private JPanel panN = new JPanel();
	private JLabel title = new JLabel("<html><font size='7' face='Times New Roman'>Administrator Function</font></html>");
	private JButton choBtn1 = new JButton("             Register             ");
	private JButton choBtn2 = new JButton("       Make Settlement     ");
	private JButton choBtn3 = new JButton("   Record Maintanence   ");
	private JButton choBtn4 = new JButton("               Log Out             ");
	
	AdminChoiceFrame(){
		//GUI
		this.setLayout(new BorderLayout());
		panN.add(title);
		this.add(panN,BorderLayout.NORTH);
		this.add(new JLabel("  "),BorderLayout.SOUTH);
		this.add(new JLabel("                                       "),BorderLayout.WEST);
		this.add(new JLabel("                                       "),BorderLayout.EAST);
		pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
		Font bigFont3 = new Font("serif",Font.BOLD,16);
		choBtn1.setFont(bigFont3);
		choBtn1.setBackground(Color.LIGHT_GRAY);
		choBtn2.setFont(bigFont3);
		choBtn2.setBackground(Color.LIGHT_GRAY);
		choBtn3.setFont(bigFont3);
		choBtn3.setBackground(Color.LIGHT_GRAY);
		choBtn4.setFont(bigFont3);
		choBtn4.setBackground(Color.LIGHT_GRAY);
		pan.add(choBtn1);
		pan.add(new JLabel(" "));
		pan.add(choBtn2);
		pan.add(new JLabel(" "));
		pan.add(choBtn3);
		pan.add(new JLabel(" "));
		pan.add(choBtn4);
		this.add(pan,BorderLayout.CENTER);
		this.setSize(450,300);
	    this.setLocation(300, 200);
	    this.setVisible(true);
	    this.setTitle("QM Car Park System");
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    choBtn1.addMouseListener(new MouseAdapter(){//Go to register frame
	    	public void mouseClicked(MouseEvent e) {
				new RegisterFrame();
				dispose();
			}
	    });
	    choBtn2.addMouseListener(new MouseAdapter(){//Go to make settlement frame
	    	public void mouseClicked(MouseEvent e) {
				new SettlementFrame();
				dispose();
			}
	    });
	    choBtn3.addMouseListener(new MouseAdapter(){//Go to Record maintanence frame
	    	public void mouseClicked(MouseEvent e) {
				new MaintanenceFrame();
				dispose();
			}
	    });
	    choBtn4.addMouseListener(new MouseAdapter(){//Go to MainFrame
	    	public void mouseClicked(MouseEvent e) {
				new MainFrame();
				dispose();
			}
	    });
	}
}
