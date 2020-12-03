package com.company.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class TimeSelectButtonComponent extends JPanel implements ItemListener {
    private JComboBox<Integer> buttonHourComboBox = new JComboBox<>();

    private JComboBox<Integer> buttonMinuteComboBox = new JComboBox<>();

    private JComboBox<Integer> buttonSecondComboBox = new JComboBox<>();

    private int hour, minute, second;


    public void setInputAreaComponent(InputAreaComponent inputAreaComponent) {
        this.inputAreaComponent = inputAreaComponent;
    }

    private InputAreaComponent inputAreaComponent;


    public TimeSelectButtonComponent() {
        super();

        FlowLayout f = new FlowLayout();
        f.setHgap(20);
        f.setVgap(10);

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
        this.add(new JLabel("hour"));

        buttonHourComboBox.addItemListener(this);
        buttonMinuteComboBox.addItemListener(this);
        buttonSecondComboBox.addItemListener(this);

        this.add(buttonMinuteComboBox);
        this.add(new JLabel("minute"));
        this.add(buttonSecondComboBox);
        this.add(new JLabel("second"));
    }


    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getSource() == buttonHourComboBox){
            if (e.getStateChange() == ItemEvent.SELECTED) {
                hour = Integer.parseInt(e.getItem().toString());
            }
        }

        if (e.getSource() == buttonMinuteComboBox){
            if (e.getStateChange() == ItemEvent.SELECTED) {
                minute = Integer.parseInt(e.getItem().toString());
            }
        }

        if (e.getSource() == buttonSecondComboBox){
            if (e.getStateChange() == ItemEvent.SELECTED) {
                second = Integer.parseInt(e.getItem().toString());
            }
        }

        if (this.inputAreaComponent != null) {
            // show tip content
            this.inputAreaComponent.ShowTipBySelectTimeComponent(this.hour, this.minute, this.second);
        }
    }
}
