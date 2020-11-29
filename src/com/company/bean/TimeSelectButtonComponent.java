package com.company.bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 时分秒的按钮
 */
public class TimeSelectButtonComponent extends JComponent implements ActionListener{
    private JComboBox<Integer> buttonUp = new JComboBox<>();

    public TimeSelectButtonComponent(int[] select, int curIndex) {
        super();


        this.setLayout(new BorderLayout());
        for (int i = 0; i < select.length; i++){
            buttonUp.addItem(select[i]);
        }


        this.setVisible(true);
        this.add(buttonUp);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // todo 数字的变化


    }
}
