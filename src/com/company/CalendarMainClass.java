package com.company;

import com.company.component.CalendarFrame;
import com.company.dao.NotePadDao;
import com.company.model.NotePad;
import com.company.schedule.Scheduler;

import java.util.List;

public class CalendarMainClass {

    /**
     * 主函数
     *
     * @param args 传入的参数 args
     */
    public static void main(String[] args) {
        /**
         * 创建一个基于CalendarFrame的主容器fram
         */
        CalendarFrame frame = new CalendarFrame();
        /**
         * 设置主容器的显示位置，以及界面的高和宽数据
         */
        frame.setBounds(200, 150, 870, 490);
        /**
         * 设置主容器为可见
         */
        frame.setVisible(true);

        /**
         * 设置主容器的标题
         */
        frame.setTitle("万年历");

        Scheduler scheduler = new Scheduler();
        scheduler.setCalendarFrame(frame);

        NotePadDao notePadDao = new NotePadDao();

        List<NotePad> allTipList = notePadDao.findAll();

        for (NotePad notePad : allTipList) {
            scheduler.AddTask(notePad);
        }

    }

}
