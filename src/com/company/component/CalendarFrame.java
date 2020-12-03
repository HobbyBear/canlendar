package com.company.component;

import com.company.component.Calendar;
import com.company.component.InputAreaComponent;

import javax.swing.*;
import java.awt.*;

public class CalendarFrame extends JFrame {


    private Calendar calendar;

    public InputAreaComponent getInputAreaComponent() {
        return inputAreaComponent;
    }

    private InputAreaComponent inputAreaComponent;


    public CalendarFrame() {


        calendar = new Calendar();
        inputAreaComponent = new InputAreaComponent();

        calendar.setInputAreaComponent(inputAreaComponent);

        add(calendar);
        add(inputAreaComponent, BorderLayout.EAST);
        inputAreaComponent.setCalendarFrame(this);


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
