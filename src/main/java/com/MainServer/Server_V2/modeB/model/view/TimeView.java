package com.MainServer.Server_V2.modeB.model.view;

import com.MainServer.Server_V2.modeB.model.Time;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TimeView {
    private long timeId;
    private String timebTime;
    private short dosage;



    public TimeView(Time timebTime, short dosage) {
        this.timeId = timebTime.getTimeb_Id();
        this.timebTime = timebTime.getTimeb_time();
        this.dosage = dosage;
    }

    public TimeView(){}

    public long getTimeb_id() {return timeId;}
    public void setTimeb_id(long timeId) {this.timeId = timeId;}

    public String getTimeb_time() {return timebTime;}
    public void setTimeb_time(String timebTime) {this.timebTime = timebTime;}

    public void setTime(Time time){
        this.timeId = time.getTimeb_Id();
        this.timebTime = time.getTimeb_time();
    }
    @JsonIgnore
    public Time getAsTime(){ return new Time(timeId, timebTime);}


    public short getDosage() {return dosage;}
    public void setDosage(short dosage) {this.dosage = dosage;}

    @Override
    public String toString() {
        return "{"
                + "\"time_id\" : " + timeId
                + ",\"time_time\" : " + addQuotes(timebTime)
                + ",\"dosage\" : " + dosage
                + "}";
    }
    private String addQuotes(String a) {
        if(a.charAt(0)!='\"') return '\"'+a+'\"';
        return a;
    }
}
