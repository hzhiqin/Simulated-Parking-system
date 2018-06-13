import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DoRegisterFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JLabel lblTips = new JLabel("<html><font face='Times New Roman'size='6'>Input the staff's information</font></html>");
	private JLabel lblName = new JLabel("Staff Name                    :");
	private JLabel lblCardId = new JLabel("Campus Card ID           :");
	private JLabel lblPlate = new JLabel("License Plate Number:");
	private JLabel lblAccount = new JLabel("Account Number         :");
	private JLabel lblPhoneNumber = new JLabel("Phone Number            :");
	private JTextField textName = new JTextField(16);
	private JTextField textCardId = new JTextField(16);
	private JTextField textPlate = new JTextField(16);
	private JTextField textAccount = new JTextField(16);
	private JTextField textPhoneNumber = new JTextField(16);
	private JPanel pNorth = new JPanel();
	private JPanel pCenter = new JPanel();
	private JButton btnRegister = new JButton("Register");
	private JButton btnBack = new JButton("Back");
	
	DoRegisterFrame(){
		//GUI
		pNorth.add(lblTips);
		this.add(pNorth,BorderLayout.NORTH);
		this.add(new JLabel("                     "),BorderLayout.WEST);
		this.add(new JLabel("                     "),BorderLayout.EAST);
		this.add(pCenter,BorderLayout.CENTER);
		pCenter.add(new JLabel("                                                                      "));
		pCenter.add(lblName);
		pCenter.add(textName);
		pCenter.add(lblCardId);
		pCenter.add(textCardId);
		pCenter.add(lblPlate);
		pCenter.add(textPlate);
		pCenter.add(lblAccount);
		pCenter.add(textAccount);
		pCenter.add(lblPhoneNumber);
		pCenter.add(textPhoneNumber);
		pCenter.add(new JLabel("                                                                               "));
		pCenter.add(btnRegister);
		pCenter.add(new JLabel("   "));
		pCenter.add(btnBack);
		this.setSize(450,300);
		this.setLocation(300, 200);
		this.setVisible(true);
		this.setTitle("QM Car Park System");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnRegister.addActionListener(new ActionListener() {//Get the input in and record them in the dat file
		      public void actionPerformed(ActionEvent e) {
		    	  String cardId = textCardId.getText();
		    	  String name = textName.getText();
		    	  String plate = textPlate.getText();
		    	  String account = textAccount.getText();
		    	  String phoneNumber = textPhoneNumber.getText();
		    	  String newLine = cardId + " " + name + " " + plate + " " + account + " " + phoneNumber;
		    	  if(null == cardId || "".equals(cardId)){
		    		  JOptionPane.showMessageDialog(null, "Card ID should not be blank!","wrong Information",JOptionPane.ERROR_MESSAGE);
		    		  return;
		    	  }
		    	  if(null == name || "".equals(name)){
		    		  JOptionPane.showMessageDialog(null, "Staff name should not be blank!","wrong Information",JOptionPane.ERROR_MESSAGE);
		    		  return;
		    	  }
		    	  if(null == plate || "".equals(plate)){
		    		  JOptionPane.showMessageDialog(null, "License plate number should not be blank!","wrong Information",JOptionPane.ERROR_MESSAGE);
		    		  return;
		    	  }if(null == account || "".equals(account)){
		    		  JOptionPane.showMessageDialog(null, "Account number should not be blank!","wrong Information",JOptionPane.ERROR_MESSAGE);
		    		  return;
		    	  }
		    	  if(null == phoneNumber || "".equals(phoneNumber)){
		    		  JOptionPane.showMessageDialog(null, "phone number should not be blank!","wrong Information",JOptionPane.ERROR_MESSAGE);
		    		  return;
		    	  }
		  		  FileWriter fw = null;
		  		  BufferedWriter bw = null;
		  	      try {
		  			  fw = new FileWriter("src/register.dat",true);
		  			  bw = new BufferedWriter(fw);
		  			  bw.append(newLine);
		  			  bw.flush();//Output the data in the buffer
		  			  bw.newLine();
		  		  } catch (IOException e2) {
		  			  e2.printStackTrace();
		  		  } finally{
		  			  try {
		  				  bw.close();
		  			  } catch (IOException e3) {
		  				  e3.printStackTrace();
		  			  }
		  		  }
		  		JOptionPane.showMessageDialog(null, "Register success!");
		  		textCardId.setText("");
		  		textName.setText("");
		  		textPlate.setText("");
		  		textAccount.setText("");
		  		textPhoneNumber.setText("");//Clear the textfield
		      }
		    });
		
		btnBack.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  new RegisterFrame();
		    	  dispose();
		      }
		    });
	}
}
