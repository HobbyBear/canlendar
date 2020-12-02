package com.company.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * 当前时间的年份，月份框
 */
public class NowTimeTextFieldComponent extends JPanel implements ActionListener {

    public JTextField getYearField() {
        return yearField;
    }

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

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        year = calendar.get(java.util.Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;

        yearField.setHorizontalAlignment(JTextField.CENTER);
        yearField.setText(Integer.toString(year));

        monthField = new JTextField(column);
        buttonMonthLeft = new JButton(" < ");
        buttonMonthRight = new JButton(">");

        monthField.setHorizontalAlignment(JTextField.CENTER);
        monthField.setText(Integer.toString(month));

        FlowLayout f = new FlowLayout();
        f.setHgap(20);//水平间距
        f.setVgap(10);//组件垂直间距

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
        }
        if (e.getSource() == buttonMonthRight) {
            month++;
        }
        this.yearField.setText(Integer.toString(year));
        this.monthField.setText(Integer.toString(month));
        calendar.setYearAndMonth(year, month);
    }
}
