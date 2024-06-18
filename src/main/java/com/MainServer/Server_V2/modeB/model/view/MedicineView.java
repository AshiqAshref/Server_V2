package com.MainServer.Server_V2.modeB.model.view;

import java.util.ArrayList;
import java.util.List;

public class MedicineView {
    private long id;
    private String medName;
    private short medBoxNo;
    private short medAmount;

    public MedicineView(long id, String medName, short medAmount) {
        this.id = id;
        this.medName = medName;
        this.medAmount = medAmount;
    }
    public MedicineView(String medName, short medAmount) {
        this.medName = medName;
        this.medAmount = medAmount;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getMedName() {return medName;}
    public void setMedBoxNo(short medBoxNo) {this.medBoxNo = medBoxNo;}
    public short getMedBoxNo() {return medBoxNo;}
    public void setMedName(String medName) {this.medName = medName;}
    public short getMedAmount() {return medAmount;}
    public void setMedAmount(short medAmount) {this.medAmount = medAmount;}


}
