package com.MainServer.Server_V2.modeB.model.view;

public class TimeView {
    private long timebId;
    private String timebTime;
    private short amount;

    public TimeView(long timebId, String timebTime, short amount) {
        this.timebId = timebId;
        this.timebTime = timebTime;
        this.amount = amount;
    }
    public TimeView(String timebTime, short amount) {
        this.timebTime = timebTime;
        this.amount = amount;
    }

    public long getTimebId() {return timebId;}
    public void setTimebId(long timebId) {this.timebId = timebId;}
    public String getTimebTime() {return timebTime;}
    public void setTimebTime(String timebTime) {this.timebTime = timebTime;}
    public short getAmount() {return amount;}
    public void setAmount(short amount) {this.amount = amount;}

    @Override
    public String toString() {
        return "{"
                + "\"timeb_id\" : " + timebId
                + ",\"timeb_time\" : " + addQuotes(timebTime)
                + ",\"amount\" : " + amount
                + "}";
    }
    private String addQuotes(String a) {
        if(a.charAt(0)!='\"') return '\"'+a+'\"';
        return a;
    }
}
