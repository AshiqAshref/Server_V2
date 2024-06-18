package com.MainServer.Server_V2.modeB.model.view;

import com.MainServer.Server_V2.modeB.model.Time;

public class TimeView {
    private long timeId;
    private String timebTime;
    private short dosage;


    public TimeView(Time timebTime, short dosage) {
        this.timeId = timebTime.getId();
        this.timebTime = timebTime.getTime();
        this.dosage = dosage;
    }

    public TimeView(){}

    public long getTimeId() {return timeId;}
    public void setTimeId(long timeId) {this.timeId = timeId;}

    public String getTimebTime() {return timebTime;}
    public void setTimebTime(String timebTime) {this.timebTime = timebTime;}

    public void setTime(Time time){
        this.timeId = time.getId();
        this.timebTime = time.getTime();
    }
    public Time getAsTime(){ return new Time(timeId, timebTime);}

    public short getDosage() {return dosage;}
    public void setDosage(short dosage) {this.dosage = dosage;}

    @Override
    public String toString() {
        return "{"
                + "\"timeb_id\" : " + timeId
                + ",\"timeb_time\" : " + addQuotes(timebTime)
                + ",\"dosage\" : " + dosage
                + "}";
    }
    private String addQuotes(String a) {
        if(a.charAt(0)!='\"') return '\"'+a+'\"';
        return a;
    }
}
