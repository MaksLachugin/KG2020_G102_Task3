package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.panel;

import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.utils.nameF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {
    Object[] types = new nameF[] {nameF.Hyperbole, nameF.IvertExponent,nameF.PolinomPow3,nameF.Sin,nameF.SinCos, nameF.SinExp};

    private JPanel JPanelMain;
    private JButton buttonSave;
    private JButton buttonCreate;
    private JComboBox JComboBoxRead;
    private JComboBox<Object> JComboBoxGenerator;
    private JTextField textField1A;
    private JTextField textField1B;
    private JTextField textField1C;
    private JTextField textField1D;
    private JPanel Card1;
    private JLabel JLabel1Name;
    private JLabel JLabel1B;
    private JLabel JLabel1C;
    private JLabel JLabel1D;
    private JLabel JLabel1A;
    private JPanel JPanelCards;
    private JPanel Card2;
    private JTextField textField2A1;
    private JTextField textField2W1;
    private JTextField textField2F1;
    private JLabel JLabel2A;
    private JLabel JLabel2W;
    private JLabel JLabel2F;
    private JTextField textField2A2;
    private JLabel JLabel2Name;
    private JTextField textField2W2;
    private JTextField textField2F2;
    private JTextField textField2C2;
    private JTextField textField2C1;
    private JPanel Card3;
    private JTextField textField1;
    public boolean isRun = false;

    public Settings() {

        buttonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Card1.setVisible(false);
                Card3.setVisible(true);
            }
        });
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        for (Object t: types){
            JComboBoxGenerator.addItem(t);
        }

    }

    public void run() {
        JFrame jFrame = new JFrame("тест");
        jFrame.setContentPane(new Settings().JPanelMain);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(800, 800);
        jFrame.setVisible(true);
        isRun = true;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
