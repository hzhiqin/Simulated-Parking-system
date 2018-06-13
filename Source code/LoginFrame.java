import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JLabel lblTitle = new JLabel("<html><font face='Times New Roman'size='7'>Administrator Login</font></html>");
	private JLabel lblSymbol = new JLabel();
	private JLabel admin = new JLabel("Administrator ID:    ");
	private JLabel pin = new JLabel("Password:                ");
	private JPanel pNorth = new JPanel();
	private JPanel pCenter = new JPanel();
	private JPanel pCenterLeft = new JPanel();
	private JPanel pCenterRight = new JPanel();
	private JTextField textUser = new JTextField(15);
	private JPasswordField textPin = new JPasswordField(15);//The input will only be seen as *****
	private JButton btnLogin = new JButton("Login");
	private JButton btnBack = new JButton("Back");
	
	LoginFrame(){
		btnLogin.addActionListener(new ActionListener() {
		    @SuppressWarnings("deprecation")//Eclipse does it itself
			public void actionPerformed(ActionEvent e) {
		    	  String id = textUser.getText();
		    	  String pwd = textPin.getText();
		    	  if(null == id || "".equals(id)){//JOptionPane.showMessageDialog open a new window itself
		    		  JOptionPane.showMessageDialog(null, "User can not be blank!","Wrong information",JOptionPane.ERROR_MESSAGE);
		    		  return;
		    	  }//If the input is empty, message will be given.
		    	  if(null == pwd || "".equals(pwd)){
		    		  JOptionPane.showMessageDialog(null, "Password can not be blank!","Wrong information",JOptionPane.ERROR_MESSAGE);
		    		  return;
		    	  }
		    	  //6 Administrators, ID and password: 2012210001,12070001  2012210002,12070002  2012210003,12070003
		    	  if("2012210001".equals(id) && "12070001".equals(pwd)){
		    		  new AdminChoiceFrame();
		    		  dispose();
		    		  return;
		    	  }
		    	  if("2012210002".equals(id) && "12070002".equals(pwd)){
		    		  new AdminChoiceFrame();
		    		  dispose();
		    		  return;
		    	  }
		    	  if("2012210003".equals(id) && "12070003".equals(pwd)){
		    		  new AdminChoiceFrame();
		    		  dispose();
		    		  return;
		    	  }
		    	  
		    	  JOptionPane.showMessageDialog(null, "User or password input wrong!","Wrong information",JOptionPane.ERROR_MESSAGE);
		      }
		    });
		
		btnBack.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  new MainFrame();
		    	  dispose();//Delete the window
		      }
		    });
		//GUI
		this.add(pNorth,BorderLayout.NORTH);
		this.add(new JLabel("      "),BorderLayout.WEST);
		this.add(pCenter,BorderLayout.CENTER);
		pNorth.add(lblTitle);
		pCenter.setLayout(new GridLayout(1,2));
		pCenter.add(pCenterLeft);
		pCenter.add(pCenterRight);
		pCenterRight.setLayout(new FlowLayout());
		pCenterRight.add(new JLabel("                                           "));
		Font bigFont1 = new Font("serif",Font.BOLD,20);
		Font bigFont2 = new Font("serif",Font.BOLD,16);
		admin.setFont(bigFont1);
		pCenterRight.add(admin);
		pCenterRight.add(textUser);
		pin.setFont(bigFont1);
		pCenterRight.add(pin);
		pCenterRight.add(textPin);
		btnLogin.setFont(bigFont2);
		pCenterRight.add(btnLogin);
		pCenterRight.add(new JLabel("      "));
		btnBack.setFont(bigFont2);
		pCenterRight.add(btnBack);
		Icon iconAdmin = new ImageIcon(getClass().getResource("image/Admin.png"));
		lblSymbol.setIcon(iconAdmin);
		pCenterLeft.add(lblSymbol);
		this.setSize(450,300);
		this.setLocation(300, 200);
		this.setVisible(true);
		this.setTitle("QM Car Park System");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
