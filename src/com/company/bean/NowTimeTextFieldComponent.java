package com.company.bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 当前时间的年份，月份框
 */
public class NowTimeTextFieldComponent extends JComponent implements ActionListener {

    public JTextField getYearField() {
        return yearField;
    }

    private JTextField yearField;

    private JTextField monthField;

    private JButton buttonYearLeft;

    private JButton buttonYearRight;

    private JButton buttonMonthLeft;

    private JButton buttonMonthRight;


    public NowTimeTextFieldComponent(int column) {
        yearField = new JTextField(column);
        buttonYearLeft = new JButton(" < ");
        buttonYearRight = new JButton(">");

        yearField.setHorizontalAlignment(JTextField.CENTER);
        yearField.setText("2016");

        monthField = new JTextField(column);
        buttonMonthLeft = new JButton(" < ");
        buttonMonthRight = new JButton(">");

        yearField.setHorizontalAlignment(JTextField.CENTER);
        yearField.setText("2016");

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

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
