import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EntranceFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    //Panel Component
    private JLabel lblTitle = new JLabel("<html><font size='5' face='Times New Roman'>QM car park_ENTER</font></html>");
    private JLabel lblTips = new JLabel();
    private JButton btnScanCampusCard = new JButton("Scan  Campus  Card");
    private JButton btnPressForTicket = new JButton("    Press for Ticket    ");
    private JButton btnBarrierOpen = new JButton("<html><font size='5' face='Times New Roman'>      Barrier Open     </font></html>");
    private JButton btnBarrierClosed = new JButton("<html><font size='5' face='Times New Roman'>      Barrier Closed     </font></html>");
    private JPanel pNorth = new JPanel();
    private JPanel pCenter = new JPanel();
    private JPanel pLeft = new JPanel();
    private JPanel pRight = new JPanel();
    private JPanel pBottom = new JPanel();
    private JButton btnBack = new JButton("Back");

    EntranceFrame() {
        this.add(pNorth, BorderLayout.NORTH);
        this.add(pCenter, BorderLayout.CENTER);
        this.add(pLeft, BorderLayout.WEST);
        this.add(pRight, BorderLayout.EAST);
        this.add(pBottom, BorderLayout.SOUTH);

        pLeft.add(new JLabel("                              "));
        pRight.add(new JLabel("                              "));
        pNorth.add(lblTitle);
        pNorth.add(new JLabel("\n"));
        pBottom.add(btnBack);
        pCenter.add(btnScanCampusCard);
        pCenter.add(btnPressForTicket);
        pCenter.add(btnBarrierClosed);
        btnBarrierClosed.setBackground(Color.red);
        btnBarrierClosed.setEnabled(false);

        //Show parking space remains
        int i = 0;
        for (ParkingSpace parkingSpace : Park.CAR_SAPCE) {
            if (null == parkingSpace.getUserNo()) {
                i++;
            }
        }
        String tipsInfo = "Vacant Space: " + i + " of " + Park.TOTAL_SPACE;
        String decTipsInfo = "<html><font size='5' face='Times New Roman'>" + tipsInfo
                + "</font></html>";
        lblTips.setText(decTipsInfo);
        pCenter.add(lblTips);

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

        //Staff function
        btnScanCampusCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isParkFull()) {
                    JOptionPane.showMessageDialog(null, "The park is full!","park is full", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String cardNo = JOptionPane.showInputDialog("input the cardNo");
                if (null == cardNo || "".equals(cardNo)) {
                    return;
                }

                String text = "";
                FileReader fr = null;
                BufferedReader br = null;
                boolean find = false;
                try {
                    fr = new FileReader("src/register.dat");
                    br = new BufferedReader(fr);
                    while ((text = br.readLine()) != null) {
                        String[] need = text.split(" ");
                        if (cardNo.equals(need[0])) {
                            find = true;
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

                // if does not register
                if (!find) {
                    JOptionPane.showMessageDialog(null,
                            "please register this card number first!",
                            "wrong card number", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // card number has register
                // if one card already used
                for (ParkingSpace carSpace : Park.CAR_SAPCE) {
                    if (cardNo.equals(carSpace.getUserNo())) {
                        JOptionPane.showMessageDialog(null,"have already used this card park one car!","wrong card number", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd/HH:mm:ss");
                String nowTime = sdf.format(now);
                int i = 1;
                for (ParkingSpace parkSpace : Park.CAR_SAPCE) {
                    
                    if (parkSpace.getUserNo() == null) { //Record infomation of Parkspace
                        parkSpace.setUserNo(cardNo);
                        parkSpace.setEnterTime(nowTime);

                        pCenter.removeAll();
                        pCenter.add(btnScanCampusCard);
                        pCenter.add(btnPressForTicket);
                        pCenter.add(btnBarrierOpen);
                        btnBarrierOpen.setBackground(Color.green);
                        btnBarrierOpen.setEnabled(false);
                        pCenter.add(lblTips);
                        pCenter.updateUI();
                        JOptionPane.showMessageDialog(null, "Barrier open,please park the car in car space " + i);
                        invisiable();
                        new EntranceFrame();
                        return;
                    }
                    i++;
                }
                if (i == Park.TOTAL_SPACE + 1) {
                    JOptionPane.showMessageDialog(null, "The park is full!", "park is full", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        
        //For The public
        btnPressForTicket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Date now = new Date();
                boolean access = OpenDate.isPermit(now);
                if (!access) {
                    JOptionPane.showMessageDialog(null, "Sorry,the car park is open to the public\n" + "at weekends and public holidays only!", "No permission", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String nowTime = sdf.format(now);
                String ticketNo = "t" + nowTime;
                SimpleDateFormat sdf2 = new SimpleDateFormat(
                        "yyyy-MM-dd/HH:mm:ss");
                String nowTime2 = sdf2.format(now);
                if (isParkFull()) {
                    JOptionPane.showMessageDialog(null, "The park is full!", "park is full", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int i = 1;
                for (ParkingSpace parkSpace : Park.CAR_SAPCE) {
                    if (null == parkSpace.getUserNo()) { //information record
                        parkSpace.setUserNo(ticketNo);
                        parkSpace.setEnterTime(nowTime2);
                        pCenter.removeAll();
                        pCenter.add(btnScanCampusCard);
                        pCenter.add(btnPressForTicket);
                        pCenter.add(btnBarrierOpen);
                        btnBarrierOpen.setBackground(Color.green);
                        btnBarrierOpen.setEnabled(false);
                        pCenter.add(lblTips);
                        pCenter.updateUI();
                        JOptionPane.showMessageDialog(null, "your ticket no is : " + ticketNo + " please take it! Please park your car in car space " + i);
                        invisiable();
                        new EntranceFrame();
                        return;
                    }
                i++;
                }
                
            }
        });

    }

    private void invisiable() {
        this.dispose();
    }

    /**
     * whether the park is full
     *
     * @return true represent full false represent has available car space
     */
    private boolean isParkFull() {
        int i = 1;
        for (ParkingSpace parkingSpace : Park.CAR_SAPCE) {
            if (null == parkingSpace.getUserNo()) {
                break;
            }
            i++;
        }
        if (i == Park.TOTAL_SPACE + 1) {
            return true;
        }
        return false;
    }

}
