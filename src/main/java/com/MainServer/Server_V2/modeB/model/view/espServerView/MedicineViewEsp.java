package com.MainServer.Server_V2.modeB.model.view.espServerView;

public class MedicineViewEsp {
    private short medBox;
    private short dosage;
    private boolean success = false;

    public MedicineViewEsp(int medBox, int dosage) {
        this.medBox = (short) medBox;
        this.dosage = (short) dosage;
    }


    public int getMedBox() {return medBox;}
    public int getDosage() {return dosage;}
    public boolean getSuccess() {return success;}
    public void setMedBox(short medBox) {this.medBox = medBox;}
    public void setDosage(short dosage) {this.dosage = dosage;}
    public void setSuccess(boolean success) {this.success = success;}

    @Override
    public String toString() {
        return "{" +
                "  \"medBox\":" + medBox +
                ", \"dosage\":" + dosage +
                ", \"success\":" + success +
                '}';
    }
}
