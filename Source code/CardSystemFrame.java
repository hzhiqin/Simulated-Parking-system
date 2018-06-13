import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class CardSystemFrame extends JFrame {
        // Build the Card System

	private static final long serialVersionUID = 1L;

	Vector<String> columnNames;
	
	JTable jt = null;
	
	JScrollPane jsp = null;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	CardSystemFrame(){
		 // Set up the column element
		columnNames = new Vector<String>();
		columnNames.add("Card ID");
		columnNames.add("Staff Name");
		columnNames.add("License Plate Number");
		columnNames.add("Account Number");
		columnNames.add("Phone Number");
			
		Vector rowData = new Vector();
		String text = "";
		FileReader fr = null;
		BufferedReader br = null;
                // Read the Data from the "src/register.dat" file
		try {
			fr = new FileReader("src/register.dat");
			br = new BufferedReader(fr);
			while((text = br.readLine()) != null){
				Vector<String> lineInfos = new Vector<String>();
				String[] tmp = text.split(" ");
				for(String str : tmp){
					lineInfos.add(str);
				}
				rowData.add(lineInfos);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		jt = new JTable(rowData,columnNames);
		jsp = new JScrollPane(jt);
		this.add(new JLabel("Card Information:"),BorderLayout.NORTH);
		this.add(jsp);
		this.setSize(700,300);
		this.setLocation(300,200);
		this.setVisible(true);
		this.setTitle("QM Car Park System");
		this.setResizable(false);
	}

}

