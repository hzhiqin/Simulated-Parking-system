import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class MaintanenceFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel lblUser = new JLabel("<html><font size='3' face='Times New Roman'>User: Maintainer</font></html>");
    private JLabel lblTips = new JLabel("<html><font size='5' face='Times New Roman'>Please record the maintanence information</font></html>");
    private JPanel panN = new JPanel();
    private JPanel panC = new JPanel();
    private JPanel panCC = new JPanel();
    private JPanel panS = new JPanel();

    private JTextField textDate = new JTextField(10);
    private JTextField textPaymentCollection = new JTextField(10);
    private JTextField textBlankTickets = new JTextField(10);
    private TextArea textRemarks = new TextArea(2, 12);
    private JButton btnSave = new JButton("Save");
    private JButton btnBack = new JButton("Back");

    MaintanenceFrame() {
        this.setLayout(new BorderLayout());
        this.add(panN, BorderLayout.NORTH);
        panN.add(lblTips);
        panN.add(lblUser);
        this.add(new JLabel("        "), BorderLayout.WEST);
        this.add(new JLabel("        "), BorderLayout.EAST);
        this.add(panC, BorderLayout.CENTER);
        panC.setLayout(new BorderLayout());
        panC.add(panCC, BorderLayout.CENTER);
        panC.add(new JLabel("        "), BorderLayout.WEST);
        panC.add(new JLabel("        "), BorderLayout.EAST);
        panCC.setLayout(new GridLayout(4, 2, 5, 5));
        panCC.add(new JLabel("Date: "));
        panCC.add(textDate);
        panCC.add(new JLabel("Payment Collection: "));
        panCC.add(textPaymentCollection);
        panCC.add(new JLabel("Blank Tickets: "));
        panCC.add(textBlankTickets);
        panCC.add(new JLabel("Remarks: "));
        panCC.add(textRemarks);

        this.add(panS, BorderLayout.SOUTH);
        panS.add(btnSave);
        panS.add(btnBack);

        this.setSize(450, 300);
        this.setLocation(300, 200);
        this.setVisible(true);
        this.setTitle("QM Car Parking System");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Gather Input information
                String date = textDate.getText();
                String paymentCollection = textPaymentCollection.getText();
                String blankTickets = textBlankTickets.getText();
                String remarks = textRemarks.getText();

                //Create file
                Date d = new Date();
                DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String fileName = df.format(d) + ".txt";

                //write into file
                FileWriter fw = null;
                BufferedWriter bw = null;
                try {
                    fw = new FileWriter("src/" + fileName);
                    bw = new BufferedWriter(fw);
                    bw.append("date:" + date + " & ");
                    bw.append("payment collection:" + paymentCollection + " & ");
                    bw.append("blank tickets:" + blankTickets + " & ");
                    bw.append("remarks:" + remarks + " & ");
                    bw.newLine();
                    bw.flush();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } finally {
                    try {
                        bw.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(null, "save success!");
            }

        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminChoiceFrame();
                dispose();
            }
        });
    }
}
