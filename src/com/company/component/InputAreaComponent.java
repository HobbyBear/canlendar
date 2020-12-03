package com.company.component;


import com.company.dao.NotePadDao;
import com.company.model.NotePad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InputAreaComponent extends JPanel implements ActionListener {

    private JTextArea inputText;

    private JLabel nowTime;

    private JButton saveButton;

    private JButton delButton;

    public void setCalendarFrame(CalendarFrame calendarFrame) {
        this.calendarFrame = calendarFrame;
    }

    private CalendarFrame calendarFrame;

    private NotePadDao notePadDao = new NotePadDao();

    private int year, month, day, hour, minute, second;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public InputAreaComponent() {
        this.setLayout(new BorderLayout());
        inputText = new JTextArea();
        inputText.setColumns(25);
        inputText.setRows(24);
        this.add(inputText, BorderLayout.CENTER);

        nowTime = new JLabel();

        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间

        //获取String类型的时间
        String createdate = sdf.format(date);
        nowTime.setText(createdate);
        nowTime.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(nowTime, BorderLayout.NORTH);

        JPanel sPanel = new JPanel();
        this.add(sPanel, BorderLayout.SOUTH);

        FlowLayout f = new FlowLayout();
        f.setHgap(20);
        f.setVgap(10);
        sPanel.setLayout(f);

        saveButton = new JButton("save");
        sPanel.add(saveButton);
        saveButton.addActionListener(this);

        delButton = new JButton("delete");
        sPanel.add(delButton);
        delButton.addActionListener(this);


        java.util.Calendar calendar = java.util.Calendar.getInstance();

        year = calendar.get(java.util.Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        ShowTip();
    }


    public void ShowTipByCalendar(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.nowTime.setText(getTime());
        ShowTip();
    }

    public void ShowTipBySelectTimeComponent(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.nowTime.setText(getTime());
        ShowTip();
    }

    private void ShowTip() {
        NotePad notePad = notePadDao.findNotePadeByTime(getTime());
        if (notePad != null) {
            this.inputText.setText(notePad.getContent());
        }
    }

    private String getTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day,hour,minute,second);
        return sdf.format(calendar.getTime());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            NotePad notePad = new NotePad();
            notePad.setContent(this.inputText.getText());
            notePad.setTime(getTime());
            notePadDao.saveOrUpdateNotePad(notePad);
            JOptionPane.showMessageDialog(calendarFrame, "success!!!", "save", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == delButton) {
            NotePad notePad = new NotePad();
            notePad.setContent(this.inputText.getText());
            notePad.setTime(getTime());
            notePadDao.delNotePad(notePad);
            JOptionPane.showMessageDialog(calendarFrame, "success!!!", "delete", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
