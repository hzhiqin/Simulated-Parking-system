import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ExitFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel lblTitle = new JLabel("<html><font size='5' face='Times New Roman'>QM car park_EXIT</font></html>");
    private JButton btnScanCampusCard = new JButton("Scan Campus Card");
    private JButton btnInsertTicket = new JButton("      Insert Ticket      ");
    private JButton btnBarrierOpen = new JButton("<html><font size='5' face='Times New Roman'>      Barrier Open     </font></html>");
    private JButton btnBarrierClosed = new JButton("<html><font size='5' face='Times New Roman'>Barrier Closed</font></html>");
    private JButton btnBack = new JButton("back");
    private JPanel pNorth = new JPanel();
    private JPanel pCenter = new JPanel();
    private JPanel pLeft = new JPanel();
    private JPanel pRight = new JPanel();
    private JPanel pBottom = new JPanel();

    ExitFrame() {
        this.setSize(450, 300);
        this.setLocation(300, 200);
        this.setVisible(true);
        this.setTitle("QM Car Park System");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(pNorth, BorderLayout.NORTH);
        this.add(pCenter, BorderLayout.CENTER);
        this.add(pLeft, BorderLayout.WEST);
        this.add(pRight, BorderLayout.EAST);
        this.add(pBottom, BorderLayout.SOUTH);
        pNorth.add(lblTitle);
        pLeft.add(new JLabel("                              "));
        pRight.add(new JLabel("                              "));
        pCenter.add(btnScanCampusCard);
        pCenter.add(btnInsertTicket);
        pCenter.add(btnBarrierClosed);
        pBottom.add(btnBack);
        btnBarrierClosed.setBackground(Color.red);
        btnBarrierClosed.setEnabled(false);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ServiceFrame();
                invisiable();
            }
        });

        btnScanCampusCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cardNo = JOptionPane.showInputDialog("input the cardNo");
                if (null == cardNo || "".equals(cardNo)) {
                    return;
                }
                
                //Input match check
                int i = 0;
                for (ParkingSpace parkingSpace : Park.CAR_SAPCE) {
                    if (cardNo.equals(parkingSpace.getUserNo())) {
                        if (!cardNo.startsWith("t")) {
                            String enterTime = parkingSpace.getEnterTime();
                            String needDate = enterTime.substring(0, 10);
                            System.out.println(needDate);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date enterDate = null;
                            Date exitDate = new Date();
                            try {
                                enterDate = sdf.parse(needDate);
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            long daysBetween = (exitDate.getTime() - enterDate.getTime()) / (3600 * 24 * 1000) + 1;

                            int nowFee = 0;
                            boolean alreadyUse = false;
                            for (Bill b : FeeManager.BILL_LIST) {
                                if (b.getCardNo().equals(parkingSpace.getUserNo())) {
                                    nowFee = b.getTotalFee();
                                    b.setTotalFee(nowFee + (int) daysBetween);
                                    alreadyUse = true;
                                    parkingSpace.setUserNo(null);
                                    parkingSpace.setEnterTime(null);
                                    parkingSpace.setPay(false);
                                    JOptionPane.showMessageDialog(null, "Days:" + String.valueOf(daysBetween) + " Fee:f" + String.valueOf(daysBetween)
                                            + "Total Fee this month:f" + String.valueOf(b.getTotalFee()));
                                    pCenter.removeAll();
                                    pCenter.add(btnScanCampusCard);
                                    pCenter.add(btnInsertTicket);
                                    pCenter.add(btnBarrierOpen);
                                    btnBarrierOpen.setBackground(Color.green);
                                    btnBarrierOpen.setEnabled(false);
                                    pCenter.updateUI();
                                    return;
                                }
                            }
                            if (!alreadyUse) {
                                Bill bill = new Bill();
                                bill.setCardNo(parkingSpace.getUserNo());
                                bill.setTotalFee((int) daysBetween);
                                FeeManager.BILL_LIST.add(bill);
                                parkingSpace.setUserNo(null);
                                parkingSpace.setEnterTime(null);
                                parkingSpace.setPay(false);
                                JOptionPane.showMessageDialog(null, "Days:" + String.valueOf(daysBetween) + " Fee:f" + String.valueOf(daysBetween)
                                        + "Total Fee this month:f" + String.valueOf(bill.getTotalFee()));
                                pCenter.removeAll();
                                pCenter.add(btnScanCampusCard);
                                pCenter.add(btnInsertTicket);
                                pCenter.add(btnBarrierOpen);
                                btnBarrierOpen.setBackground(Color.green);
                                btnBarrierOpen.setEnabled(false);
                                pCenter.updateUI();
                                return;
                            }
                        }
                    }
                    i++;
                }
                if (i == Park.TOTAL_SPACE) {
                    JOptionPane.showMessageDialog(null, "does not exist!",
                            "tips", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnInsertTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                String ticketNo = JOptionPane
                        .showInputDialog("input the ticketNo");
                if (null == ticketNo || "".equals(ticketNo)) {
                    return;
                }

                int i = 0;
                // find the exit car space
                for (ParkingSpace parkingSpace : Park.CAR_SAPCE) {

                    if (ticketNo.equals(parkingSpace.getUserNo())) {

                        // is public custom
                        if (ticketNo.startsWith("t")) {
                            if (!parkingSpace.isPay()) {
                                JOptionPane
                                        .showMessageDialog(
                                                null,
                                                "you need to pay at payment station first!",
                                                "tips",
                                                JOptionPane.ERROR_MESSAGE);
                                return;
                            } else {
                                parkingSpace.setUserNo(null);
                                parkingSpace.setEnterTime(null);
                                parkingSpace.setPay(false);
                                pCenter.removeAll();
                                pCenter.add(btnScanCampusCard);
                                pCenter.add(btnInsertTicket);
                                pCenter.add(btnBarrierOpen);
                                btnBarrierOpen.setBackground(Color.green);
                                btnBarrierOpen.setEnabled(false);
                                pCenter.updateUI();
                                // show exit success frame

                                return;
                            }
                        }
                    }
                    i++;
                }
                if (i == Park.TOTAL_SPACE) {
                    JOptionPane.showMessageDialog(null, "does not exist!",
                            "tips", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void invisiable() {
        this.dispose();
    }

}