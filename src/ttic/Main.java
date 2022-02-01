package ttic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        Cost2 cost2 = new Cost2();

        JFrame frame = new JFrame("Dong");
        frame.setSize(600, 700);
        frame.setVisible(true);

        Icon icon = new ImageIcon("/home/iman/IdeaProjects/ProjectFinal/src/ttic/add.png");
        JButton newPerson = new JButton(icon);
        newPerson.setBounds(200, 30, 30 , 30);


        JTextField nameField = new JTextField();
        nameField.setBounds(20, 30,100,30);

        JTextField amountField = new JTextField();
        amountField.setBounds(130, 30,60,30);

        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(25, 80, 120 , 30);

        frame.add(newPerson);
        frame.add(nameField);
        frame.add(amountField);
        frame.add(btnCalculate);
        frame.setLayout(null);
        frame.repaint();

        btnCalculate.addActionListener(e -> {
            cost2.calculate();
        });

        newPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person = new Person(nameField.getText(),Double.parseDouble(amountField.getText()));
                cost2.addPayerAndSahim(person);
            }
        });
    }
}