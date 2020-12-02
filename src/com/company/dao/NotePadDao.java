package com.company.dao;

import com.company.model.NotePad;
import com.sun.deploy.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NotePadDao {

    private final String path = "database.txt";

    private File db;

    public NotePadDao() {
        db = new File(path);
        if (db.exists()) {
            return;
        }

        try {
            db.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNotePad(NotePad notePad) {
        // 存文件
        try {
            FileOutputStream out = new FileOutputStream(db,true);
            try {
                out.write(new StringBuffer(objToStr(notePad)).append("\n").toString().getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void delNotePad(NotePad notePad){

    }


    public void UpdateNotePad(NotePad notePad){

    }


    private String objToStr(NotePad notePad) {
        return "时间:" + notePad.getTime() + ":内容:" + notePad.getContent();
    }

    private NotePad strToObj(String objText) {
        NotePad notePad = new NotePad();
        notePad.setTime(StringUtils.splitString(objText, ":")[1]);
        notePad.setTime(StringUtils.splitString(objText, ":")[3]);
        return notePad;
    }
}

