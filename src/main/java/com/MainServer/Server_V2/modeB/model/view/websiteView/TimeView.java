package com.MainServer.Server_V2.modeB.model.view.websiteView;

import com.MainServer.Server_V2.modeB.model.Time;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TimeView {
    private long timeId;
    private String time;
    private short dosage;



    public TimeView(Time time, int dosage) {
        this.timeId = time.getTimeb_Id();
        this.time = time.getTimeb_time();
        this.dosage = (short)dosage;
    }

    public TimeView(){}

//    @JsonIgnore
    public long getTime_id() {return timeId;}
    public void setTimeb_id(long timeId) {this.timeId = timeId;}

    public String getTime() {return time;}
    public void setTimeb_time(String timebTime) {this.time = timebTime;}

    public void setTimeFromObject(Time time){
        this.timeId = time.getTimeb_Id();
        this.time = time.getTimeb_time();
    }
    @JsonIgnore
    public Time getAsTimeObject(){ return new Time(timeId, time);}


    public int getDosage() {return dosage;}
    public void setDosage(int dosage) {this.dosage = (short)dosage;}

    @Override
    public String toString() {
        return "{"
                + "\"time_id\" : " + timeId
                + ",\"time_time\" : " + addQuotes(time)
                + ",\"dosage\" : " + dosage
                + "}";
    }
    private String addQuotes(String a) {
        if(a.charAt(0)!='\"') return '\"'+a+'\"';
        return a;
    }
}
