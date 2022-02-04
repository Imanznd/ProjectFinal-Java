package com.example.dong;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Ui extends JFrame {

    JButton btnCalculate, newPerson,clear;
    JCheckBox checkBox;
    JTextField nameField,amountField;
    JScrollPane scrollPane;
    Cost cost;

    public Ui() throws HeadlessException, IOException {
        super("Dong");
        this.cost = new Cost();

        this.setSize(600, 800);
        this.setVisible(true);

        init();
        addActionListeners();
    }

    private void addActionListeners() {
        newPerson.addActionListener(e -> {
            Person person = new Person(nameField.getText(), Double.parseDouble(amountField.getText()));
            if (checkBox.isSelected())
                cost.addPayerAndSahim(person);
            else
                cost.addJustPayer(person);
            amountField.setText("");
            nameField.setText("");
        });
        btnCalculate.addActionListener(e -> {
            ArrayList<Transaction> transactions = cost.finalCalculate();

            JTable table = new JTable(new TableModel() {
                @Override
                public int getRowCount() {
                    return transactions.size();
                }

                @Override
                public int getColumnCount() {
                    return 3;
                }

                @Override
                public String getColumnName(int columnIndex) {

                    return switch (columnIndex) {
                        case 0 -> "amount";
                        case 1 -> "payer";
                        case 2 -> "getter";
                        default -> "";
                    };

                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    Class<?> aClass;

                    if (columnIndex == 0)
                        aClass = Double.class;
                    else
                        aClass = String.class;

                    return aClass;

                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {

                    Transaction transaction = transactions.get(rowIndex);

                    return switch (columnIndex) {
                        case 0 -> transaction.getAmount();
                        case 1 -> transaction.getPayer().getName();
                        case 2 -> transaction.getGetter().getName();
                        default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
                    };
                }

                @Override
                public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

                }

                @Override
                public void addTableModelListener(TableModelListener l) {

                }

                @Override
                public void removeTableModelListener(TableModelListener l) {

                }
            });


            table.setBounds(30, 120, 500, 400);

            if(scrollPane != null)
                this.remove(scrollPane);

            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(30, 120, 500, 400);

            this.add(scrollPane);
            this.repaint();

        });

        clear.addActionListener(e -> {
            cost.clear();
            if (scrollPane != null)
                Ui.this.remove(scrollPane);

            repaint();

        });
    }

    private void init() throws IOException {

        Icon icon = new ImageIcon(FileUtil.getFileFromResource("image.png"));
        newPerson = new JButton(icon);
        newPerson.setBounds(270, 30, 30 , 30);

        nameField = new JTextField();
        nameField.setBounds(20, 30,130,30);

        nameField.setToolTipText("enter name");


        amountField = new JTextField();
        amountField.setBounds(160, 30,60,30);

        amountField.setToolTipText("enter amount");

        btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(25, 80, 120 , 30);

        checkBox = new JCheckBox();
        checkBox.setBounds(230, 30, 30, 30);

        JPanel panel = new JPanel();

        clear = new JButton("Clear");
        clear.setBounds(50 + 130, 80, 120 , 30);

        this.add(newPerson);
        this.add(nameField);
        this.add(amountField);
        this.add(btnCalculate);
        this.setLayout(null);
        this.add(checkBox);
        this.add(clear);
        this.repaint();
    }
}
