package com.MainServer.Server_V2.modeB.model.view.espServerView;

import com.MainServer.Server_V2.modeB.model.Medicine;

public class BoxViewEsp {
    long medId;
    String medName;
    short medBox;
    short medAmount;

    public BoxViewEsp(Medicine medicine){
        medId = medicine.getMed_id();
        medName = medicine.getMed_name();
        medBox = (short) medicine.getMed_box_no();
        medAmount = (short) medicine.getMed_amount();
    }
    public long getMed_id() {return medId;}
    public String getMed_name() {return medName;}
    public short getMed_box_no() {return medBox;}
    public short getMed_amount() {return medAmount;}

    public void setMed_id(long medId) {this.medId = medId;}
    public void setMed_name(String medName) {this.medName = medName;}
    public void setMed_box_no(short medBox) {this.medBox = medBox;}
    public void setMed_amount(short medAmount) {this.medAmount = medAmount;}
    public String toString(){
        return "{"
                + "  \"med_id\":" + medId
                + ", \"med_name\":\"" + medName + "\""
                + ", \"med_box_no\":" + medBox
                + ", \"med_amount\":" + medAmount
                + "}";
    }
}
