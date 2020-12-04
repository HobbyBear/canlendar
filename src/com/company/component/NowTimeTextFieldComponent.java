package com.company.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

/**
 * now time textFiled
 */
public class NowTimeTextFieldComponent extends JPanel implements ItemListener {

    private JComboBox<Integer> buttonYearComboBox = new JComboBox<>();

    private JComboBox<Integer> buttonMonthComboBox = new JComboBox<>();


    private int year, month;

    public void setCalendar(com.company.component.Calendar calendar) {
        this.calendar = calendar;
    }

    private com.company.component.Calendar calendar;


    public NowTimeTextFieldComponent(int column) {


        for (int i = 1; i <= 12; i++) {
            buttonMonthComboBox.addItem(i);
        }

        java.util.Calendar instance = java.util.Calendar.getInstance();

        year = instance.get(java.util.Calendar.YEAR);
        month = instance.get(Calendar.MONTH);

        for (int i = year - 5; i <= year + 5; i++) {
            buttonYearComboBox.addItem(i);
        }

        buttonYearComboBox.setSelectedItem(year);

        buttonMonthComboBox.setSelectedItem(month + 1);

        FlowLayout f = new FlowLayout();
        f.setHgap(20);
        f.setVgap(10);

        this.setLayout(f);

        this.add(buttonYearComboBox);
        this.add(new JLabel("year"));
        this.add(buttonMonthComboBox);
        this.add(new JLabel("month"));

        buttonYearComboBox.addItemListener(this);

        buttonMonthComboBox.addItemListener(this);

    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == buttonMonthComboBox) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                month = Integer.parseInt(e.getItem().toString()) - 1;
            }
        }

        if (e.getSource() == buttonYearComboBox) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                year = Integer.parseInt(e.getItem().toString());
            }
        }

        calendar.setYearAndMonth(year, month);
    }
}
