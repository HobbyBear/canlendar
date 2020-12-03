package com.company;

import com.company.component.CalendarFrame;
import com.company.dao.NotePadDao;
import com.company.model.NotePad;
import com.company.schedule.Scheduler;

import java.util.List;

public class CalendarMainClass {

   
    public static void main(String[] args) {
       
        CalendarFrame frame = new CalendarFrame();
      
        frame.setBounds(200, 150, 870, 490);
      
        frame.setVisible(true);

       
        frame.setTitle("calendar");

        Scheduler scheduler = new Scheduler();
        scheduler.setCalendarFrame(frame);

        NotePadDao notePadDao = new NotePadDao();

        List<NotePad> allTipList = notePadDao.findAll();

        for (NotePad notePad : allTipList) {
            scheduler.AddTask(notePad);
        }
        frame.getInputAreaComponent().setScheduler(scheduler);

    }

}
