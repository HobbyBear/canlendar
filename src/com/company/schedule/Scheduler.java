package com.company.schedule;


import com.company.component.CalendarFrame;
import com.company.model.NotePad;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(20);

    private Map<String, Future> taskMap = new HashMap<>();

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public void setCalendarFrame(CalendarFrame calendarFrame) {
        this.calendarFrame = calendarFrame;
    }

    private CalendarFrame calendarFrame;

    public void AddTask(NotePad notePad) {
        Date date = null;
        try {
            date = formatter.parse(notePad.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (Calendar.getInstance().getTime().compareTo(date) > 0) {
            System.out.println("当前时间:" + Calendar.getInstance().getTime() + " 任务时间:" + date + " 不添加定时任务");
            return;
        }
        System.out.println("添加了备忘录定时任务, 当前时间：" + Calendar.getInstance().getTime() + "时间:" + notePad.getTime() + ",内容:" + notePad.getContent() + " 在" + (date.getTime() - Calendar.getInstance().getTime().getTime()) / 1000 + "秒后执行");
        Future f = this.scheduledExecutorService.schedule(() -> JOptionPane.showMessageDialog(calendarFrame, notePad.getContent(),
                "日程", JOptionPane.INFORMATION_MESSAGE), date.getTime() - Calendar.getInstance().getTime().getTime(), TimeUnit.MILLISECONDS);

        taskMap.put(notePad.getTime(), f);

    }


    public void DelTask(NotePad notePad) {
        Future f = taskMap.get(notePad.getTime());
        f.cancel(true);
        taskMap.keySet().removeIf(key -> key.equals(notePad.getTime()));
    }

    public void UpdateTask(NotePad notePad) {
        DelTask(notePad);
        AddTask(notePad);
    }


}
