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

    // init a thread pool
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(20);

    // the holder of tasks
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
            System.out.println("current_time:" + Calendar.getInstance().getTime() + " task_time:" + date + " can not add task");
            return;
        }
        System.out.println("add task! current_time:" + Calendar.getInstance().getTime() + "task_time:" + notePad.getTime() + ",content:" + notePad.getContent() + " after" + (date.getTime() - Calendar.getInstance().getTime().getTime()) / 1000 + "exec");
        Future f = this.scheduledExecutorService.schedule(() -> JOptionPane.showMessageDialog(calendarFrame, notePad.getContent(),
                "schedule", JOptionPane.INFORMATION_MESSAGE), date.getTime() - Calendar.getInstance().getTime().getTime(), TimeUnit.MILLISECONDS);

        taskMap.put(notePad.getTime(), f);

    }


    public void DelTask(NotePad notePad) {
        Future f = taskMap.get(notePad.getTime());
        f.cancel(true);
        taskMap.keySet().removeIf(key -> key.equals(notePad.getTime()));
    }



}
