package com.company.component;

import com.company.component.Calendar;
import com.company.component.InputAreaComponent;

import javax.swing.*;
import java.awt.*;

public class CalendarFrame extends JFrame {


    private Calendar calendar;

    private InputAreaComponent inputAreaComponent;


    public CalendarFrame() {


        calendar = new Calendar();
        inputAreaComponent = new InputAreaComponent();

        calendar.setInputAreaComponent(inputAreaComponent);

        add(calendar);
        JTextArea inputText;
        inputText = new JTextArea();

        inputText.setColumns(25);
        inputText.setRows(23);
        add(inputAreaComponent, BorderLayout.EAST);

        /**
         * 设置，当点击窗体右上角的关闭图标时，程序会做出直接结束程序的处理
         */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
