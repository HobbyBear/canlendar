package com.company.component;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

/**
 * now time textFiled
 */
public class NowTimeTextFieldComponent extends JPanel implements ActionListener {

    private JTextField yearField;

    private JTextField monthField;

    private JButton buttonYearLeft;

    private JButton buttonYearRight;

    private JButton buttonMonthLeft;

    private JButton buttonMonthRight;

    private int year, month;

    public void setCalendar(com.company.component.Calendar calendar) {
        this.calendar = calendar;
    }

    private com.company.component.Calendar calendar;


    public NowTimeTextFieldComponent(int column) {
        yearField = new JTextField(column);
        buttonYearLeft = new JButton(" < ");
        buttonYearRight = new JButton(">");

        java.util.Calendar instance = java.util.Calendar.getInstance();

        year = instance.get(java.util.Calendar.YEAR);
        month = instance.get(Calendar.MONTH);

        yearField.setHorizontalAlignment(JTextField.CENTER);
        yearField.setText(Integer.toString(year));

        monthField = new JTextField(column);
        buttonMonthLeft = new JButton(" < ");
        buttonMonthRight = new JButton(">");

        monthField.setHorizontalAlignment(JTextField.CENTER);
        monthField.setText(Integer.toString(month + 1));

        FlowLayout f = new FlowLayout();
        f.setHgap(20);
        f.setVgap(10);

        this.setLayout(f);

        this.add(buttonYearLeft);
        this.add(yearField);
        this.add(buttonYearRight);

        this.add(buttonMonthLeft);
        this.add(monthField);
        this.add(buttonMonthRight);

        buttonMonthRight.addActionListener(this);
        buttonMonthLeft.addActionListener(this);
        buttonYearRight.addActionListener(this);
        buttonYearLeft.addActionListener(this);

        yearField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((char) e.getKeyChar() == KeyEvent.VK_ENTER) {
                    year = Integer.parseInt(yearField.getText());
                    calendar.setYearAndMonth(year, month);
                }
            }
        });

        monthField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((char) e.getKeyChar() == KeyEvent.VK_ENTER) {
                    month = Integer.parseInt(monthField.getText()) - 1;
                    if (month < 1) {
                        month = 1;
                    }
                    if (month > 11) {
                        month = 11;
                    }
                    calendar.setYearAndMonth(year, month);
                }
            }
        });

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonYearLeft) {
            year--;
        }
        if (e.getSource() == buttonYearRight) {
            year++;
        }
        if (e.getSource() == buttonMonthLeft) {
            month--;
            if (month < 1) {
                month = 1;
            }
        }
        if (e.getSource() == buttonMonthRight) {
            month++;
            if (month > 11) {
                month = 11;
            }
        }
        this.yearField.setText(Integer.toString(year));
        this.monthField.setText(Integer.toString(month + 1));
        calendar.setYearAndMonth(year, month);
    }

}
