package com.example.newtravel;

import java.io.Serializable;

public class Invoice implements Serializable
{
    public static int NID=0;
    public String Name;
    public String NIF;
    public String StartLocation;
    public String EndLocation;
    public String price;
    public String TollsAndOthers;
    public String supplements;
    public String notes;
    public String Total;
    public String Day;
    public String Mounth;
    public String Year;
    public String time;
    public int InvoiceID;

    public static int getNID() {
        return NID;
    }

    public static void setNID(int NID) {
        Invoice.NID = NID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getStartLocation() {
        return StartLocation;
    }

    public void setStartLocation(String startLocation) {
        StartLocation = startLocation;
    }

    public String getEndLocation() {
        return EndLocation;
    }

    public void setEndLocation(String endLocation) {
        EndLocation = endLocation;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTollsAndOthers() {
        return TollsAndOthers;
    }

    public void setTollsAndOthers(String tollsAndOthers) {
        TollsAndOthers = tollsAndOthers;
    }

    public String getSupplements() {
        return supplements;
    }

    public void setSupplements(String supplements) {
        this.supplements = supplements;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getMounth() {
        return Mounth;
    }

    public void setMounth(String mounth) {
        Mounth = mounth;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        InvoiceID = invoiceID;
    }

    Invoice(String Nam, String Ni, String SL, String ED, String Pric, String TO, String Supp, String Not, String To, String da, String mon, String ye, String tiim){

        this.Day=da;
        InvoiceID=++NID;
        this.Mounth=mon;
        this.Year=ye;
        this.Name=Nam;
        this.NIF=Ni;
        this.Total=To;
        this.StartLocation=SL;
        this.EndLocation=ED;
        this.price=Pric;
        this.TollsAndOthers=TO;
        this.supplements=Supp;
        this.notes=Not;
this.time=tiim;
    }

}
