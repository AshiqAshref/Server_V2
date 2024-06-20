package com.MainServer.Server_V2.modeB.model.view;

import com.MainServer.Server_V2.modeB.model.Medicine;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ReminderView {
    private long medId;
    private String medName;
    private short medBox;
    private short medAmount;
    private List<TimeView> times = new ArrayList<>();

    public ReminderView(Medicine medicine, List<TimeView> time){
        this.medId = medicine.getMed_id();
        this.medName = medicine.getMed_Name();
        this.medBox = medicine.getMed_box_no();
        this.medAmount = medicine.getMed_amount();
        this.times = time;
    }

    public ReminderView(Medicine medicine){
        this.medId = medicine.getMed_id();
        this.medName = medicine.getMed_Name();
        this.medBox = medicine.getMed_box_no();
        this.medAmount = medicine.getMed_amount();
    }
    public ReminderView(){}

    @JsonIgnore
    public Medicine getMedicine() {return new Medicine(medId, medName, medBox, medAmount);}
    public void setMedicine(Medicine medicine) {
        this.medId = medicine.getMed_id();
        this.medName = medicine.getMed_Name();
        this.medBox = medicine.getMed_box_no();
        this.medAmount = medicine.getMed_amount();
    }
    public long getMed_id() {return medId;}
    public void setMed_id(long medId) {this.medId = medId;}
    public String getMed_name() {return medName;}
    public void setMed_name(String medName) {this.medName = medName;}
    public short getMed_box_no() {return medBox;}
    public void setMed_box_no(short medBox) {this.medBox = medBox;}
    public short getMed_amount() {return medAmount;}
    public void setMed_amount(short medAmount) {this.medAmount = medAmount;}

    public List<TimeView> getTimes() {return times;}
    public void setTimes(List<TimeView> times) {this.times = times;}
    public void removeTime(TimeView timeView){times.remove(timeView);}
    public void removeTime(int index){if(index < times.size()) times.remove(index);}
    public void addTime(TimeView time){if(!times.contains(time)) this.times.add(time);}

    @Override
    public String toString() {
        return "{"
                + "\"med_id\" : "  + medId
                + ",\"med_box_no\" : "+ medBox
                + ",\"med_name\" : " +  addQuotes(medName)
                + ", \"med_amount\" : " + medAmount
                + ", \"times\" : " + times
                +"}";
    }
    private String addQuotes(String a) {
        if(a.charAt(0)!='\"')
            return '\"'+a+'\"';
        return a;
    }
}
