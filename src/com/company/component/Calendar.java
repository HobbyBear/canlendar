package com.company.component;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;


public class Calendar extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    JLabel labelDay[] = new JLabel[42];
    JButton titleName[] = new JButton[7];
    NowTimeTextFieldComponent nowTimeTextFieldComponent;
    JTextArea inputText;

    JLabel nowTimeLabel;
    String name[] = {"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sar"};
    CalendarBean calendarBean;

    TimeSelectButtonComponent timeSelectButtonComponent = new TimeSelectButtonComponent();

    public void setInputAreaComponent(InputAreaComponent inputAreaComponent) {
        this.inputAreaComponent = inputAreaComponent;
        this.timeSelectButtonComponent.setInputAreaComponent(inputAreaComponent);
    }

    private InputAreaComponent inputAreaComponent;


    public Calendar() {

        this.setLayout(new BorderLayout());

        JPanel pCenter = new JPanel();

        pCenter.setLayout(new GridLayout(7, 7));
        pCenter.setBackground(Color.WHITE);

        for (int i = 0; i < 7; i++) {
            titleName[i] = new JButton(name[i]);
            titleName[i].setBorder(new SoftBevelBorder(BevelBorder.RAISED));
            pCenter.add(titleName[i]);
        }

        for (int i = 0; i < 42; i++) {
            labelDay[i] = new JLabel("", JLabel.CENTER);
            labelDay[i].setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
            pCenter.add(labelDay[i]);
        }

        calendarBean = new CalendarBean();

        nowTimeTextFieldComponent = new NowTimeTextFieldComponent(3);
        nowTimeTextFieldComponent.setCalendar(this);

        nowTimeLabel = new JLabel();



        JPanel pNorth = new JPanel();
        JPanel pSouth = new JPanel();
        JPanel pEast = new JPanel();

        FlowLayout f = (FlowLayout) pNorth.getLayout();
        f.setHgap(20);//水平间距
        f.setVgap(10);//组件垂直间距
        pNorth.add(nowTimeTextFieldComponent);
        pNorth.add(nowTimeLabel);
        pSouth.add(timeSelectButtonComponent);

        inputText = new JTextArea();



        add(pCenter, BorderLayout.CENTER);
        add(pNorth, BorderLayout.NORTH);
        add(pSouth, BorderLayout.SOUTH);
        add(pEast, BorderLayout.EAST);


        setYearAndMonth(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR), java.util.Calendar.getInstance().get(java.util.Calendar.MONTH));

    }


    public void setYearAndMonth(int y, int m) {

        calendarBean.setAndGetCalendar(y, m);
        for (int i = 0; i < 42; i++) {
            labelDay[i].setText(calendarBean.getDays()[i]);
            JLabel label = labelDay[i];
            label.setOpaque(true);
            label.setBackground(Color.WHITE);
            labelDay[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                       setDay(label.getText());
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        this.labelDay[calendarBean.getFirstDayIndex()].setBackground(Color.CYAN);

        if (this.inputAreaComponent != null) {
            this.inputAreaComponent.ShowTipByCalendar(this.calendarBean.year, this.calendarBean.month, 1);
        }
    }


    private void setDay(String day) {

        for (JLabel jLabel : this.labelDay) {
            jLabel.setBackground(Color.WHITE);
            if (day.equals(jLabel.getText())) {
                jLabel.setBackground(Color.CYAN);
            }
        }

        if (this.inputAreaComponent != null) {
            this.inputAreaComponent.ShowTipByCalendar(this.calendarBean.year, this.calendarBean.month, Integer.parseInt(day));
        }
    }


    public void actionPerformed(ActionEvent e) {

        for (JLabel jLabel : this.labelDay) {
            if (e.getSource() == jLabel) {
                setDay(jLabel.getText());
            }
        }

    }

}