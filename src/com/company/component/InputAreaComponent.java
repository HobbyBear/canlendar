package com.company.component;


import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// 输入框
public class InputAreaComponent extends JPanel {

    private JTextArea inputText;

    private JLabel nowTime;

    private JButton saveButton;

    private JButton delButton;


    private int year, month, day, hour, minute, second;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取String类型的时间
        String createdate = sdf.format(date);
        nowTime.setText(createdate);
        nowTime.setFont(new Font("微软雅黑", Font.BOLD, 18));
        this.add(nowTime, BorderLayout.NORTH);

        JPanel sPanel = new JPanel();
        this.add(sPanel, BorderLayout.SOUTH);

        FlowLayout f = new FlowLayout();
        f.setHgap(20);//水平间距
        f.setVgap(10);//组件垂直间距
        sPanel.setLayout(f);

        saveButton = new JButton("保存");
        sPanel.add(saveButton);
        delButton = new JButton("删除");
        sPanel.add(delButton);


        java.util.Calendar calendar = java.util.Calendar.getInstance();

        year = calendar.get(java.util.Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        ShowTip();
    }


    public void ShowTipByCalendar(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        ShowTip();
    }

    public void ShowTipBySelectTimeComponent(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        ShowTip();
    }

    private void ShowTip() {

    }
}
