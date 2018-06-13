
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServiceFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lblTips1 = new JLabel();
    private JLabel lblTips2 = new JLabel();
    private JLabel lblTips3 = new JLabel();
    private JLabel lblTips4 = new JLabel();
    private JButton btnEntrance = new JButton("             Enter             ");
    private JButton btnExit = new JButton("               Exit              ");
    private JButton btnPaymentStation = new JButton("To payment Station");
    private JButton btnBack = new JButton("     Back to Menu     ");
    private JPanel pNorth = new JPanel();
    private JPanel pCenter = new JPanel();
    private JPanel pLeft = new JPanel();
    private JPanel pRight = new JPanel();
    private JPanel pBottom = new JPanel();

    ServiceFrame() {
        //Construct a frame with BorderLayout.
        this.add(pNorth, BorderLayout.NORTH);
        this.add(pCenter, BorderLayout.CENTER);
        this.add(pLeft, BorderLayout.WEST);
        this.add(pRight, BorderLayout.EAST);
        this.add(pBottom, BorderLayout.SOUTH);

        //Display preparation
        Date now = new Date();//Obtain current time
        String nowTime = now.toString();//print time to String nowTime
        String[] need = nowTime.split(" ");//construct new array by space including information we need
        String tipsInfo1 = "Welcome! Today is " + need[1] + "," + need[2] + "th  " + need[5];//display main information
        String disTipsInfo1 = "<html><font size='5' color='blue' face='Times New Roman'>" + tipsInfo1 + "</font></html>";
        lblTips1.setText(disTipsInfo1);
        // how many parking space remains
        int i = 0;
        for (ParkingSpace parkingSpace : Park.CAR_SAPCE) {
            if (null == parkingSpace.getUserNo()) {
                i++;
            }
        }
        //Remained space display
        String tips2Info = "Vaccant Space: " + i + " of " + Park.TOTAL_SPACE;
        String disTips2Info = "<html><font size='5' face='Times New Roman'>" + tips2Info + "</font></html>";
        lblTips2.setText(disTips2Info);

        //Open infomation display
        String tips3Info = "The car park is open to the public at";
        String disTips3Info = "<html><font size='4' color='red' face='Times New Roman'>" + tips3Info + "</font></html>";
        lblTips3.setText(disTips3Info);
        String tips4Info = "weekends and public holidays ONLY ";
        String disTips4Info = "<html><font size='4' color='red' face='Times New Roman'>" + tips4Info + "</font></html>";
        lblTips4.setText(disTips4Info);

        //Window construction
        pNorth.add(lblTips1);
        pLeft.add(new JLabel("           "));
        pCenter.add(lblTips3);
        pCenter.add(lblTips4);
        pCenter.add(btnEntrance);
        pCenter.add(btnPaymentStation);
        pCenter.add(btnExit);
        pCenter.add(btnBack);
        pRight.add(new JLabel("           "));
        pBottom.add(lblTips2);
        this.setSize(450, 300);
        this.setLocation(300, 200);
        this.setVisible(true);
        this.setTitle("QM Car Parking System");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Some listener of button
        btnBack.addActionListener(new ActionListener() {//go back button
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                invisiable();
            }
        });
        btnEntrance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EntranceFrame();
                invisiable();
            }
        });
        btnPaymentStation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PaymentFrame();
                invisiable();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ExitFrame();
                invisiable();
            }
        });
    }

    //Using to set this window invisiable when call others
    private void invisiable() {
        this.dispose();
    }

}
