package com.company;

import com.company.frame.CalendarFrame;

public class CalendarMainClass {

    /**
     * 主函数
     * @param args 传入的参数 args
     */
    public static void main(String[] args) {
        /**
         * 创建一个基于CalendarFrame的主容器fram
         */
        CalendarFrame frame= new CalendarFrame();
        /**
         * 设置主容器的显示位置，以及界面的高和宽数据
         */
        frame.setBounds(200,150,870,490);
        /**
         * 设置主容器为可见
         */
        frame.setVisible(true);

        /**
         * 设置主容器的标题
         */
        frame.setTitle("万年历");

    }

}
