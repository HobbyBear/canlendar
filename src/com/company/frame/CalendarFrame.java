package com.company.frame;

import com.company.bean.Calendar;
import com.company.bean.InputAreaComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalendarFrame extends JFrame {


    private Calendar calendar;

    private InputAreaComponent inputAreaComponent;


    public CalendarFrame() {

        this.setLayout(new BorderLayout());

        JPanel pCenter = new JPanel();
        JPanel pEast = new JPanel();

        calendar = new Calendar();
        inputAreaComponent = new InputAreaComponent();

        pCenter.add(calendar);
        pEast.add(inputAreaComponent);

        add(pCenter, BorderLayout.CENTER);
        add(pEast,BorderLayout.EAST);

        /**
         * 设置，当点击窗体右上角的关闭图标时，程序会做出直接结束程序的处理
         */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
