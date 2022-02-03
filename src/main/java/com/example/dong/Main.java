package com.example.dong;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.ArrayList;

public class Main {

    static int[][] ints;

    public static void main(String[] args) throws IOException {



        Cost cost = new Cost();

        JFrame frame = new JFrame("Dong");
        frame.setSize(600, 800);
        frame.setVisible(true);

        Icon icon = new ImageIcon(new FileUtil().getFileFromResource("image.png"));
        JButton newPerson = new JButton(icon);
        newPerson.setBounds(270, 30, 30 , 30);



        JTextField nameField = new JTextField();
        nameField.setBounds(20, 30,130,30);

        nameField.setToolTipText("enter name");


        JTextField amountField = new JTextField();
        amountField.setBounds(160, 30,60,30);

        amountField.setToolTipText("enter amount");

        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(25, 80, 120 , 30);

        JCheckBox checkBox = new JCheckBox();
        checkBox.setBounds(230, 30, 30, 30);

        JPanel panel = new JPanel();

        frame.add(newPerson);
        frame.add(nameField);
        frame.add(amountField);
        frame.add(btnCalculate);
        frame.setLayout(null);
        frame.add(checkBox);
        frame.repaint();


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

                  return switch (columnIndex){
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


            table.setBounds(30,120, 500, 400);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(30, 120, 500, 400);
            frame.add(scrollPane);
            frame.repaint();

        });

        newPerson.addActionListener(e -> {
            if (checkBox.isSelected()) {

                Person person = new Person(nameField.getText(),Double.parseDouble(amountField.getText()));
                cost.addPayerAndSahim(person);

            }else {

                Person person = new Person(nameField.getText(), Double.parseDouble(amountField.getText()));
                cost.addJustPayer(person);
            }
            nameField.setText("");
            amountField.setText("");
        });

    }
}