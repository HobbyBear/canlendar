package com.company.dao;

import com.alibaba.fastjson.JSON;
import com.company.model.NotePad;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void saveOrUpdateNotePad(NotePad notePad) {
        NotePad oldNotePad = findNotePadeByTime(notePad.getTime());
        if (oldNotePad == null) {
            saveNotePad(notePad);
        } else {
            updateNotePad(oldNotePad, notePad);
        }
    }

    public void saveNotePad(NotePad notePad) {
        // 存文件
        try {
            FileOutputStream out = new FileOutputStream(db, true);
            try {
                out.write(new StringBuffer(objToStr(notePad)).append("\n").toString().getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public NotePad findNotePadeByTime(String time) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String targetStr = "", lineTxt = "";
        try {
            while (scanner.hasNextLine()) {
                lineTxt = scanner.nextLine();
                if (lineTxt != null && !lineTxt.equals("")) {
                    if (strToObj(lineTxt).getTime().equals(time)) {
                        targetStr = lineTxt;
                        break;
                    }
                }
            }
        }finally {
            scanner.close();
        }

        if (!targetStr.equals("")) {
            return strToObj(targetStr);
        }

        return null;
    }

    public void delNotePad(NotePad notePad) {
        List<NotePad> notePadList = findAll();
        List<NotePad> newNotePadList = new ArrayList<>();
        for (int i = 0; i < notePadList.size(); i++) {
            if (!notePadList.get(i).getTime().equals(notePad.getTime())) {
                newNotePadList.add(notePadList.get(i));
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(db, false);
            try {
                out.write("".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream out = new FileOutputStream(db, true);
            try {
                for (int i = 0; i < newNotePadList.size(); i++) {
                    out.write(new StringBuffer(objToStr(newNotePadList.get(i))).append("\n").toString().getBytes("utf-8"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<NotePad> findAll() {
        List<NotePad> notePadList = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String lineTxt = "";
        try {
            while (scanner.hasNextLine()) {
                lineTxt = scanner.nextLine();
                if (lineTxt != null && !lineTxt.equals("")) {
                    notePadList.add(strToObj(lineTxt));
                }
            }
        } finally {
            scanner.close();
        }
        return notePadList;
    }


    public void updateNotePad(NotePad oldNotedPad, NotePad notePad) {
        delNotePad(oldNotedPad);
        saveNotePad(notePad);
    }


    private String objToStr(NotePad notePad) {
        return JSON.toJSONString(notePad);
    }

    private NotePad strToObj(String objText) {
        return JSON.parseObject(objText, NotePad.class);
    }
}

