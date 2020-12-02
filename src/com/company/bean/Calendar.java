package com.company.bean;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * 窗体的具体构造及执行代码
 *
 */
public class Calendar extends JComponent implements ActionListener, ItemListener {
    private static final long serialVersionUID = 1L;
    /**
     * labelDay[] 存放每一天的标签数组|
     * titleName 显示标题栏名称的按钮|
     * text 输入年份的文本框|
     * button,button1 按钮组件|
     * label 标签|
     * comBox 下拉列表组件|
     * showMessage 显示日历的标签项
     */

    // todo 改下布局，改成垂直布局


    JLabel labelDay[] = new JLabel[42];
    JButton titleName[] = new JButton[7];
    NowTimeTextFieldComponent nowTimeTextFieldComponent;
    JTextArea inputText;

    JButton button, button1;
    JLabel nowTimeLabel;
    JComboBox<String> comBox;
    String name[] = {"日", "一", "二", "三", "四", "五", "六"};
    CalendarBean calendar;

    TimeSelectButtonComponent timeSelectButtonComponent = new TimeSelectButtonComponent();


    /**
     * JFrame都是Windows类的子类，因此主窗口为Windows型容器，其默认布局为BorderLayout布局
     */
    public Calendar() {

        this.setLayout(new BorderLayout());
        /**
         * 创建面板（可向面板中添加组件，再将面板添加到其他容器中）
         */
        JPanel pCenter = new JPanel();
        /**
         * 将pCenter的布局设置为7行7列的GridLayout布局
         */
        pCenter.setLayout(new GridLayout(7, 7));
        pCenter.setBackground(Color.WHITE);
        /**
         * 向主容器中添加星期栏
         */
        for (int i = 0; i < 7; i++) {
            titleName[i] = new JButton(name[i]);
            titleName[i].setBorder(new SoftBevelBorder(BevelBorder.RAISED));
            pCenter.add(titleName[i]);
        }
        /**
         * 向主容器添加每个小日期标签
         */
        for (int i = 0; i < 42; i++) {
            labelDay[i] = new JLabel("", JLabel.CENTER);
            labelDay[i].setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
            pCenter.add(labelDay[i]);
        }
        /**
         * 声明calendar为calendarBean类的子类
         */
        calendar = new CalendarBean();
        /**
         * 设置个组件的具体内容
         *
         */
        nowTimeTextFieldComponent = new NowTimeTextFieldComponent(3);
        button = new JButton("转到");
        button1 = new JButton("当前日期");
        /**
         * 创建下拉列表，并添加下拉列表选项
         */
        nowTimeLabel = new JLabel();

        /**
         * 向组件添加响应事件
         */
        button.addActionListener(this);
        button1.addActionListener(this);
        /**
         * 创建pNorth，pSouth面板,pEast面板
         */
        JPanel pNorth = new JPanel();
        pNorth.setBackground(Color.cyan);
        JPanel pSouth = new JPanel();
        JPanel pEast = new JPanel();
        /**
         * 向面板中添加各个组件
         */
        FlowLayout f = (FlowLayout) pNorth.getLayout();
        f.setHgap(20);//水平间距
        f.setVgap(10);//组件垂直间距
        pNorth.add(nowTimeTextFieldComponent);
        pNorth.add(nowTimeLabel);
        pSouth.add(timeSelectButtonComponent);

        inputText = new JTextArea();


        /**
         * 将三个面板添加到以BorderLayout为布局的主Windows型容器中
         */
        add(pCenter, BorderLayout.CENTER);
        add(pNorth, BorderLayout.NORTH);
        add(pSouth, BorderLayout.SOUTH);
        add(pEast, BorderLayout.EAST);



        setYearAndMonth(2016,1);

    }

    /**
     * ------编写传入年月的方法setYearAndMonth------
     *
     * @param y 年份
     * @param m 月份
     */
    public void setYearAndMonth(int y, int m) {
        calendar.setYear(y);
        /**
         * 将setYearAndMonth方法得到的year，month的值传给calendar类
         */
        calendar.setMonth(m);
        /**
         * 获取calendar类中getCalendar方法中的日期字符串数组，将获取的字符串数组的值传给每个labelDay小日期标签
         */
        String day[] = calendar.getCalendar();
        for (int i = 0; i < 42; i++)
            labelDay[i].setText(day[i]);

    }

    /**
     * ---------重写actionPerformed方法---------
     */
    public void actionPerformed(ActionEvent e) {
        /**
         * reg为正则表达式，表示纯数字的字符串（不含其他任何与数字无关的字符）
         */
        String reg = "^[\\d]*$";
        /**
         * 判断响应事件触发的是button组件
         */
        if (e.getSource() == button) {
            /**
             * 这个地方如何让程序识别并弹出对话框，而不是在未输入时直接判别程序出错
             */
            if (nowTimeTextFieldComponent.getYearField().getText().isEmpty()) {

                JOptionPane.showMessageDialog(this, "您未输入年份", "消息对话框", JOptionPane.WARNING_MESSAGE);
                /**
                 * 将屏幕焦点聚在text组件上
                 */
                nowTimeTextFieldComponent.requestFocus(true);
            }
            /**
             * 判断text文本框中是否为纯数字，若不是则弹出对话框 "您输入了非法字符"
             */
            else if (!(nowTimeTextFieldComponent.getYearField().getText()).matches(reg)) {
                JOptionPane.showMessageDialog(this, "您输入了非法字符", "消息对话框", JOptionPane.WARNING_MESSAGE);
            }
            /**
             * 判断text文本框中是否为纯数字，若是，则输出相应年份
             */
            else {

                /**
                 * 将文本框中得到的字符串强制转为整型值，并赋值给整型变量i
                 */
                Integer i = Integer.parseInt(nowTimeTextFieldComponent.getYearField().getText());
                /**
                 * 将整型i变量中的值传递给calendar类中，并设置为year值
                 */
                calendar.setYear(i);
            }


            /**
             * 判断用户是否使用了下拉列表框，若未使用，则弹出消息对话框 "您未选择月份"
             */
            if (((String) comBox.getSelectedItem()).equals("选择月份")) {
                JOptionPane.showMessageDialog(this, "您未选择月份", "消息对话框", JOptionPane.WARNING_MESSAGE);
                /**
                 * 将屏幕焦点聚在comBox组件上
                 */
                comBox.requestFocus(true);
            } else {
                /**
                 * 将下拉列表框中选中的字符串值强制转换为整型，并赋值给整型变量j
                 */
                Integer j = Integer.parseInt(comBox.getSelectedItem().toString());
                /**
                 * 将整型i变量中的值传递给calendar类中，并设置为month值
                 */
                calendar.setMonth(j);
                /**
                 * 获取calendar类中getCalendar方法中的日期字符串数组
                 */
                String day[] = calendar.getCalendar();
                /**
                 * 将获取的字符串数组的值传给每个labelDay小日期标签
                 */
                for (int n = 0; n < 42; n++) {
                    labelDay[n].setText(day[n]);
                }
            }

        }
        /**
         * 判断响应时间触发的是button1组件
         */
        else if (e.getSource() == button1) {
            /**
             * --------------获取系统当前日期--------------
             */
            java.util.Calendar c = java.util.Calendar.getInstance();
            int y = c.get(java.util.Calendar.YEAR);
            int m = c.get(java.util.Calendar.MONTH) + 1;
            calendar.setMonth(m);
            String day[] = calendar.getCalendar();
            for (int n = 0; n < 42; n++) {
                labelDay[n].setText(day[n]);
            }
            nowTimeTextFieldComponent.getYearField().setText("");
        }

    }

    /**
     * ---------重写itemStateChanged方法--------
     * 下拉列表的响应事件具体已经在actionPerformed方法内实现
     */
    public void itemStateChanged(ItemEvent arg0) {
        // TODO Auto-generated method stub                                  
    }
}