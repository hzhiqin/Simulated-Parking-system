import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RegisterFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel title = new JLabel("<html><font face='Times New Roman'size='7'>Staff Register</font></html>");
    private JPanel pNorth = new JPanel();
	private JPanel pCenter = new JPanel();
	private JButton BtnReg = new JButton("      Register to the system     ");
	private JButton BtnDel = new JButton("Delete Register Information");
	private JButton BtnBack = new JButton("                      Back                     ");
	
	RegisterFrame() {
		//GUI
		pNorth.add(title);
		this.add(pNorth, BorderLayout.NORTH);
		this.add(new JLabel("                                 "), BorderLayout.WEST);
		this.add(new JLabel("                                 "), BorderLayout.EAST);
		this.add(pCenter, BorderLayout.CENTER);
		pCenter.setLayout(new BoxLayout(pCenter,BoxLayout.Y_AXIS));
		Font bigFont4 = new Font("serif",Font.BOLD,17);
		BtnReg.setFont(bigFont4);
		BtnReg.setBackground(Color.LIGHT_GRAY);
		BtnDel.setFont(bigFont4);
		BtnDel.setBackground(Color.LIGHT_GRAY);
		BtnBack.setFont(bigFont4);
		BtnBack.setBackground(Color.LIGHT_GRAY);
		pCenter.add(new JLabel(" "));
		pCenter.add(BtnReg);
		pCenter.add(new JLabel(" "));
		pCenter.add(BtnDel);
		pCenter.add(new JLabel(" "));
		pCenter.add(BtnBack);
		this.setSize(450, 300);
		this.setLocation(300, 200);
		this.setVisible(true);
		this.setTitle("QM Car Park System");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BtnReg.addMouseListener(new MouseAdapter() {//Go to get staff information
			public void mouseClicked(MouseEvent e) {
				new DoRegisterFrame();
				dispose();
			}
		});

		BtnDel.addMouseListener(new MouseAdapter() {//Delete the staff information
			public void mouseClicked(MouseEvent e) {
				String who = JOptionPane.showInputDialog("Input the Card ID which to cancel register");
				if (null == who || "".equals(who)) {
					return;
				}
				List<String> needRegisters = new ArrayList<String>();
				String text = "";
				FileReader fr = null;
				BufferedReader br = null;
				try {//Read in the staff information
					fr = new FileReader("src/register.dat");
					br = new BufferedReader(fr);
					while ((text = br.readLine()) != null) {
						String[] need = text.split(" ");
						if (!who.equals(need[0])) {
							needRegisters.add(text);//Write the information to the array, expect the one wanted to be deleted
						}
					}
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e3) {
					e3.printStackTrace();
				} finally {
					try {
						br.close();
					} catch (IOException e4) {
						e4.printStackTrace();
					}
				}

				FileWriter fw = null;
				BufferedWriter bw = null;
				try {//Write the staff information back to the file
					fw = new FileWriter("src/register.dat");
					bw = new BufferedWriter(fw);
					for(String newLine : needRegisters){
						bw.append(newLine);
						bw.flush();
						bw.newLine();
					}	
				} catch (IOException e2) {
					e2.printStackTrace();
				} finally {
					try {
						bw.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, "Cancel register " + who + " success!");
			}
		});

		BtnBack.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new AdminChoiceFrame();
				dispose();
			}
		});

	}
}
