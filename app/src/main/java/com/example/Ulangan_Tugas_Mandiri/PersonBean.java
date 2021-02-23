package com.example.Ulangan_Tugas_Mandiri;

public class PersonBean {
    int id;
    long date;
    String title;
    String desc;
    public PersonBean() {
    }
    public long getDate(){
        return date;
    }
    public void setDate(long time){
        this.date = time;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

}