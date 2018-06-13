
import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class MonthlyBillFrame extends JFrame {
        //Build the Monthly Bill

	private static final long serialVersionUID = 1L;

	Vector<String> columnNames;
	
	JTable jt = null;
	
	JScrollPane jsp = null;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	MonthlyBillFrame(){
		// Set up the column element
		columnNames = new Vector<String>();
		columnNames.add("Card ID");
		columnNames.add("Total Fee this month up to now");
		Vector rowData = new Vector();
		for(Bill bill : FeeManager.BILL_LIST){
			Vector<String> lineInfos = new Vector<String>();
			lineInfos.add(bill.getCardNo());
			lineInfos.add("f" + String.valueOf(bill.getTotalFee()));
			rowData.add(lineInfos);
		}
		
		jt = new JTable(rowData,columnNames);
		jsp = new JScrollPane(jt);
		this.add(new JLabel("Monthly Bill:"),BorderLayout.NORTH);
		this.add(jsp);
		this.setSize(400,300);
		this.setLocation(300,200);
		this.setVisible(true);
		this.setTitle("QM Car Park System");
		this.setResizable(false);
	}


}