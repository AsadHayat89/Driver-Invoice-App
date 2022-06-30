package com.example.newtravel.Recyle;

public class Items {
    public String date;
    public String time;
    public String name;
    public String Total;
    public String UId;

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    Items(String dat, String tim, String nam, String toa,String id){
        this.date=dat;
        this.time=tim;
        this.Total=toa;
        this.name=nam;
        this.UId=id;
    }
}
