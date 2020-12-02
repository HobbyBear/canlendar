package com.company.component;
import java.util.Calendar;
/**
 * 生成日历字符串数组
 */
public class CalendarBean {
    String day[];
    int year=2005,month=0;
    /**
     * 设置year的set和get方法
     * @param year 年份
     * 
     */
    public void setYear(int year){                                  
        this.year=year;                                             
    }                                                               
    public int getYear(){                                           
        return year;                                                
    }                   
    /**
     * 设置month的set和get方法
     * @param month 月份
     */
    public void setMonth(int month){                                
        this.month=month;                                           
    }                                                               
    public int getMonth(){                                          
        return month;
    }                                                               
 
    /**
     * 创建可以得到具体日期的字符串数组
     * @return 返回具体日期的字符串数组
     */
    public String[] getCalendar(){                                  
        String a[]=new String[42];
        Calendar instance=Calendar.getInstance();
        instance.set(year, month-1, 1);
        int weekday=instance.get(Calendar.DAY_OF_WEEK)-1;
        int day=0;
        if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
            day=31;
        if(month==4||month==6||month==9||month==11)
            day=30;
        if(month==2){
            if(((year%4==0)&&(year%100!=0))||(year%400==0))
                day=29;
            else
                day=28;
        }
        for(int i=weekday,n=1;i<weekday+day;i++){
            a[i]=String.valueOf(n);
            n++;
        }
        return a;
    }
}