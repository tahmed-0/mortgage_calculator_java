import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;

public class mortgage {


    Random ran = new Random();
    double hp, ir, lt, dp;

    public DecimalFormat df = new DecimalFormat("#.##");


    calculations cc = new calculations();

    JFrame frame = new JFrame("Mortgage Calculator");

    //labels

    JLabel label_Title = new JLabel("Mortgage Calculator");
    JLabel label_result = new JLabel();
    JLabel label_optional = new JLabel("Optional");

    JLabel label_homePrice = new JLabel("Home price");
    JLabel label_downPayment = new JLabel("Down payment");
    JLabel label_loanTerm = new JLabel("Loan term");
    JLabel label_interestRate = new JLabel("Interest rate %");

    JTextField tf_homePrice = new JTextField();
    JTextField tf_downPayment = new JTextField();
    JTextField tf_loanTerm = new JTextField();
    JTextField tf_InterestRate = new JTextField();
    JTextField tf_pTax = new JTextField();
    JTextField tf_hInsurance = new JTextField();
    JTextField tf_PMI = new JTextField();
    JTextField tf_HFees = new JTextField();


    //Optional section

    JLabel label_pTax = new JLabel("Property tax");
    JLabel label_hInsurance = new JLabel("Insurance");
    JLabel label_pmi = new JLabel("PMI");
    JLabel label_hFees = new JLabel("HOA fees");

    //Buttons

    JButton btn_calculate = new JButton("Calculate");
    JButton btn_clear = new JButton("Clear");
    JButton btn_add = new JButton("Add +");


    JButton darkMode = new JButton("On/Off");
    //fonts

    Font timesRomanFont_1 = new Font("TimesRoman", Font.BOLD, 30);
    Font timesRomanFont_2 = new Font("TimesRoman", Font.BOLD, 12);

    Font timesRomanFont_3 = new Font("TimesRoman", Font.BOLD, 13);

    Font timesRomanFont_4 = new Font("TimesRoman", Font.BOLD, 25);


    public mortgage() {


        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        //UI
        createUI();
        optionalUI();

        //Events
        OptionalEvents();
        createEvents();


        frame.setVisible(true);

    }

    public void createUI() {

        label_Title.setBounds(56, 68, 322, 47);
        label_Title.setFont(timesRomanFont_1);

        label_homePrice.setBounds(54, 151, 72, 16);
        label_homePrice.setFont(timesRomanFont_2);
        tf_homePrice.setBounds(50, 175, 330, 30);

        label_downPayment.setBounds(54, 226, 92, 16);
        label_downPayment.setFont(timesRomanFont_2);
        tf_downPayment.setBounds(50, 250, 330, 30);

        label_loanTerm.setBounds(54, 300, 72, 16);
        label_loanTerm.setFont(timesRomanFont_2);
        tf_loanTerm.setBounds(50, 324, 330, 30);

        label_interestRate.setBounds(54, 379, 92, 13);
        label_interestRate.setFont(timesRomanFont_2);
        tf_InterestRate.setBounds(50, 403, 330, 30);

        btn_calculate.setBounds(84, 454, 111, 22);
        btn_clear.setBounds(239, 454, 111, 22);

        label_result.setBounds(307, 493, 185, 47);
        label_result.setFont(timesRomanFont_3);


        frame.add(label_result);
        frame.add(btn_clear);
        frame.add(tf_loanTerm);
        frame.add(btn_calculate);
        frame.add(label_loanTerm);
        frame.add(label_interestRate);
        frame.add(tf_InterestRate);
        frame.add(tf_homePrice);
        frame.add(tf_downPayment);
        frame.add(label_downPayment);
        frame.add(label_homePrice);
        frame.add(label_Title);

    }


    public void createEvents() {

        btn_calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hp = Double.parseDouble(tf_homePrice.getText());
                ir = Double.parseDouble(tf_InterestRate.getText());
                lt = Double.parseDouble(tf_loanTerm.getText());
                dp = Double.parseDouble(tf_downPayment.getText());


                System.out.println(cc.formula(330000, 5.450, 30, 66000, 0));

                label_result.setText("Monthly payment: $" + String.valueOf((df.format(cc.formula(hp, ir, lt, dp, cc.add)))));
                System.out.println(cc.add);

                int num = ran.nextInt(1000);

                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myMortgage?serverTimezone=America/New_York", "root", "");


                    String query = "INSERT INTO myTable values('" +num + "','" + hp + "','"+dp + "','"+lt+ "','"+ir+"')";

                    Statement sta = connection.createStatement();



                    int x = sta.executeUpdate(query);

                    PreparedStatement ps = connection.prepareStatement("SELECT * FROM myTable");


                    if(x != 0) {
                        System.out.println("It Worked");
                    }

                    connection.close();


                } catch (Exception var14) {
                    var14.printStackTrace();

                }

            }
        });

        btn_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_homePrice.setText("");
                tf_downPayment.setText("");
                tf_loanTerm.setText("");
                tf_InterestRate.setText("");
                label_result.setText("");
                tf_PMI.setText("");
                tf_HFees.setText("");
                tf_hInsurance.setText("");
                tf_pTax.setText("");
            }
        });

    }

    //optional section

    public void optionalUI() {
        label_optional.setBounds(588, 140, 105, 30);
        label_optional.setFont(timesRomanFont_4);

        label_pTax.setBounds(528, 214, 76, 16);
        label_pTax.setFont(timesRomanFont_2);
        tf_pTax.setBounds(629, 210, 87, 23);

        label_hInsurance.setBounds(528, 267, 76, 16);
        label_hInsurance.setFont(timesRomanFont_2);
        tf_hInsurance.setBounds(629, 253, 87, 23);

        label_pmi.setBounds(528, 300, 58, 16);
        label_pmi.setFont(timesRomanFont_2);
        tf_PMI.setBounds(629, 296, 87, 23);


        label_hFees.setBounds(528, 343, 58, 16);
        label_hFees.setFont(timesRomanFont_2);
        tf_HFees.setBounds(629, 339, 87, 23);

        btn_add.setBounds(584, 385, 55, 17);


        frame.add(btn_add);
        frame.add(label_hFees);
        frame.add(tf_HFees);
        frame.add(tf_PMI);
        frame.add(label_pmi);
        frame.add(tf_hInsurance);
        frame.add(label_hInsurance);
        frame.add(label_optional);
        frame.add(label_pTax);
        frame.add(tf_pTax);

    }

    public void OptionalEvents() {

        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double pTax = Double.parseDouble(tf_pTax.getText());
                double insurance = Double.parseDouble(tf_hInsurance.getText());
                double pmi = Double.parseDouble(tf_PMI.getText());
                double fees = Double.parseDouble(tf_HFees.getText());

                cc.addOn(pTax, insurance, pmi, fees);
                label_result.setText("Monthly payment: $" + String.valueOf((df.format(cc.formula(hp, ir, lt, dp, cc.add)))));


            }
        });


    }

}

