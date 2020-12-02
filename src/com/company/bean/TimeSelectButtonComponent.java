package com.company.bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 时分秒的按钮
 */
public class TimeSelectButtonComponent extends JComponent implements ActionListener {
    private JComboBox<Integer> buttonHourComboBox = new JComboBox<>();

    private JComboBox<Integer> buttonMinuteComboBox = new JComboBox<>();

    private JComboBox<Integer> buttonSecondComboBox = new JComboBox<>();


    private int currentHour, currentMinute, currentSecond;


    public TimeSelectButtonComponent() {
        super();

        FlowLayout f = new FlowLayout();
        f.setHgap(20);//水平间距
        f.setVgap(10);//组件垂直间距

        this.setLayout(f);

        for (int i = 1; i <= 24; i++) {
            buttonHourComboBox.addItem(i);
        }

        for (int i = 1; i <= 60; i++) {
            buttonMinuteComboBox.addItem(i);
        }

        for (int i = 1; i <= 60; i++) {
            buttonSecondComboBox.addItem(i);
        }

        this.add(buttonHourComboBox);
        this.add(new JLabel("时"));
        this.add(buttonMinuteComboBox);
        this.add(new JLabel("分"));
        this.add(buttonSecondComboBox);
        this.add(new JLabel("秒"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // todo 数字的变化


    }
}
