package com.company.bean;


import javax.swing.*;

// todo 输入框
public class InputAreaComponent extends JComponent {

    private  JTextArea inputText;

    public InputAreaComponent(){
        inputText = new JTextArea();

        inputText.setColumns(25);
        inputText.setRows(23);
        this.add(inputText);
    }

}
