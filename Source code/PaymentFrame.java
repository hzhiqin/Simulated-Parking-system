
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

public class PaymentFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel pCenter = new JPanel();
    private JPanel pCenterLeft = new JPanel();
    private JPanel pCenterRight = new JPanel();
    private JPanel pBottom = new JPanel();
    private JButton btnInsertTicket = new JButton("Insert Ticket");
    private JLabel lblTariff = new JLabel();
    private JButton btnBack = new JButton("Back");

    PaymentFrame() {
        this.add(pBottom, BorderLayout.SOUTH);
        this.add(pCenter, BorderLayout.CENTER);
        pCenter.setLayout(new GridLayout(1, 2));
        pCenter.add(pCenterLeft);
        pCenter.add(pCenterRight);
        pCenterLeft.setLayout(new FlowLayout());
        pCenterLeft.add(new JLabel("<html><font size='5' color='green' face='Times New Roman'>Tarriff</font></html>"));
        Icon iconTariff = new ImageIcon(getClass().getResource("image/tariff.png"));
        lblTariff.setIcon(iconTariff);
        pCenterLeft.add(lblTariff);
        pCenterLeft.add(btnInsertTicket);
        pCenterRight.add(new JLabel("                                    "));
        pCenterRight.add(new JLabel("<html><font size='4' face='Times New Roman'>This page is for the public ONLY!</font></html>"));
        pCenterRight.add(new JLabel("<html><font size='4' face='Times New Roman'>Please insert your ticket.</font></html>"));
        pBottom.add(btnBack);
        this.setSize(450, 300);
        this.setLocation(300, 200);
        this.setVisible(true);
        this.setTitle("QM Car Park System");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ServiceFrame();
                invisiable();
            }
        });

        btnInsertTicket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ticketNo = JOptionPane.showInputDialog("input the ticket number you have got");
                if (null == ticketNo || "".equals(ticketNo)) {
                    return;
                }
                //Validating the ticket
                boolean find = false;
                for (ParkingSpace parkingSpace : Park.CAR_SAPCE) {
                    if (ticketNo.equals(parkingSpace.getUserNo())) {
                        find = true;
                        break;
                    }
                }

                //If it is ture
                if (find) {
                    new DoPaymentFrame(ticketNo);
                    invisiable();
                } else {
                    JOptionPane.showMessageDialog(null, "invalid ticket!","invalid ticket", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void invisiable() {
        this.dispose();
    }

}
