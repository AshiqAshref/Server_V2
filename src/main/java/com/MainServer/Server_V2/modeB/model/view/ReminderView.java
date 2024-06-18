package com.MainServer.Server_V2.modeB.model.view;

import com.MainServer.Server_V2.modeB.model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class ReminderView {
    private long medId;
    private String medName;
    private short medBox;
    private short medAmount;
    private List<TimeView> times = new ArrayList<>();

    public ReminderView(Medicine medicine, List<TimeView> time){
        this.medId = medicine.getId();
        this.medName = medicine.getMedName();
        this.medBox = medicine.getBox();
        this.medAmount = medicine.getMedAmount();
        this.times = time;
    }

    public ReminderView(Medicine medicine){
        this.medId = medicine.getId();
        this.medName = medicine.getMedName();
        this.medBox = medicine.getBox();
        this.medAmount = medicine.getMedAmount();
    }
    public ReminderView(){}

    public Medicine getMedicine() {return new Medicine(medId, medName, medBox, medAmount);}
    public void setMedicine(Medicine medicine) {
        this.medId = medicine.getId();
        this.medName = medicine.getMedName();
        this.medBox = medicine.getBox();
        this.medAmount = medicine.getMedAmount();
    }
    public long getMedId() {return medId;}
    public void setMedId(long medId) {this.medId = medId;}
    public String getMedName() {return medName;}
    public void setMedName(String medName) {this.medName = medName;}
    public short getMedBox() {return medBox;}
    public void setMedBox(short medBox) {this.medBox = medBox;}
    public short getMedAmount() {return medAmount;}
    public void setMedAmount(short medAmount) {this.medAmount = medAmount;}

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
