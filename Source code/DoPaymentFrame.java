import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DoPaymentFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	private JLabel lblTitle = new JLabel("<html><font size='5' face='Times New Roman'>QM car park_EXIT</font></html>");
	private JPanel pNorth = new JPanel();
	private JPanel pLeft = new JPanel();
	private JPanel pUnder = new JPanel();
	private JPanel pRight = new JPanel();
	private JPanel pCenter = new JPanel();
	private JPanel pCenterLeft = new JPanel();
	private JPanel pCenterRight = new JPanel();	
	JButton btn50p = new JButton("50P");	
	JButton btn1f = new JButton(" f1 ");	
	JButton btn2f = new JButton("f2");
	private JLabel lblToPay = new JLabel();
	JButton btnTicketOut = new JButton("Ticket Out");
	private JButton btnBack = new JButton("Back");	
	String ticketNumUse;
	float fee = 0;
	float currentFee;
	
	public String getticketNum() {
		return ticketNumUse;
	}
	public void setticketNum(String ticketNum) {
		this.ticketNumUse = ticketNum;
	}		
	
	DoPaymentFrame(String ticketNum){

		setticketNum(ticketNum);
		this.setSize(450,300);
	    this.setLocation(300, 200);
	    this.setVisible(true);
	    this.setTitle("QM Car Park System");
	    this.setResizable(true);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.add(pCenter,BorderLayout.CENTER);
		this.add(pNorth,BorderLayout.NORTH);
		this.add(pLeft,BorderLayout.WEST);
                this.add(pRight,BorderLayout.EAST);
                this.add(pUnder,BorderLayout.SOUTH);
		pNorth.add(lblTitle);
		pLeft.add(new JLabel("                       "));
        pRight.add(new JLabel("                        "));
		pCenter.setLayout(new GridLayout(1, 2));
		pCenter.add(pCenterLeft);
		pCenter.add(pCenterRight);
		pCenterLeft.setLayout(new FlowLayout());
		pCenterLeft.add(btn50p);
		pCenterLeft.add(btn1f);
		pCenterLeft.add(btn2f);
		
		int times = PaymentUtil.parkTimes(ticketNumUse);//Call parkTimes method in PaymentUtil class to obtain passed hour of a user.
		int passHour = times / 60;
    	int passMinute = times % 60;
    	int countedHour = 0;
		if(passMinute > 0){
			countedHour = passHour + 1; //For example : regarding 2:50 as 3 hours.
		}
		fee = PaymentUtil.countFee(countedHour);//Money should pay
		
		pCenterRight.add(new JLabel("Fee:"));
		lblToPay.setText(fee + "pounds");
		pCenterRight.add(lblToPay);
		btnTicketOut.setEnabled(false);
		pCenterRight.add(btnTicketOut);
		pUnder.add(btnBack);		
		
		btn50p.addActionListener(new BtnListener(0.5f,this));
		btn1f.addActionListener(new BtnListener(1,this));
		btn2f.addActionListener(new BtnListener(2,this));
		
		
		btnTicketOut.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  for(ParkingSpace parkingSpace : Park.CAR_SAPCE){
		  			if(getticketNum().equals(parkingSpace.getUserNo())){
		  				parkingSpace.setPay(true); //Set this space state to paid
		  				JOptionPane.showMessageDialog(null, "Please take your new ticket and draw your Car");
		  			}
		  		  }
		      }
		});
		
		btnBack.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  new ServiceFrame();
		    	  invisiable();
		      }
		});
	}
	
	private void invisiable(){
		this.dispose();
	}
	
}


class BtnListener implements ActionListener{
	
	float paidValue = 0;
	DoPaymentFrame doPaymentFrame = null;
	BtnListener(float paidValue,DoPaymentFrame doPaymentFrame){
		this.paidValue = paidValue;
		this.doPaymentFrame = doPaymentFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.doPaymentFrame.currentFee += paidValue;
		if(doPaymentFrame.fee <= this.doPaymentFrame.currentFee){
			doPaymentFrame.btn50p.setEnabled(false);
			doPaymentFrame.btn1f.setEnabled(false);
			doPaymentFrame.btn2f.setEnabled(false);
			doPaymentFrame.btnTicketOut.setEnabled(true);
		}
		
	}
}