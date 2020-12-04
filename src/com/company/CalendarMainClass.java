package com.company;

import com.company.component.CalendarFrame;
import com.company.dao.NotePadDao;
import com.company.model.NotePad;
import com.company.schedule.Scheduler;

import java.util.List;

public class CalendarMainClass {

   
    public static void main(String[] args) {

        // create frame
        CalendarFrame frame = new CalendarFrame();

        // set length,width,high
        frame.setBounds(200, 150, 870, 490);

        frame.setVisible(true);

       
        frame.setTitle("calendar");

        // init a scheduler
        Scheduler scheduler = new Scheduler();
        scheduler.setCalendarFrame(frame);

        // init a obj to save or delete scheduler task
        NotePadDao notePadDao = new NotePadDao();

        // read task from file
        List<NotePad> allTipList = notePadDao.findAll();

        for (NotePad notePad : allTipList) {
            // dispatch task to scheduler
            scheduler.AddTask(notePad);
        }
        // input text area hold the reference of  scheduler
        frame.getInputAreaComponent().setScheduler(scheduler);

    }

}
