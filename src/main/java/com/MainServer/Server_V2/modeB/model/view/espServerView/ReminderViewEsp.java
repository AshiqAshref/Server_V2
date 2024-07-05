package com.MainServer.Server_V2.modeB.model.view.espServerView;

import com.MainServer.Server_V2.modeB.model.Time;

import java.util.ArrayList;
import java.util.List;

public class ReminderViewEsp {
    private long timeId;
    private String time;
    private List<MedicineViewEsp> medicines = new ArrayList<>();

    public ReminderViewEsp(Time time, List<MedicineViewEsp> medicines){
        this.timeId = time.getTimeb_Id();
        this.time = time.getTimeb_time();
        this.medicines = medicines;
    }
    public ReminderViewEsp(Time time){
        this.timeId = time.getTimeb_Id();
        this.time = time.getTimeb_time();
    }

    public long getTimeId() {return timeId;}
    public String getTime() {return time;}
    public List<MedicineViewEsp> getMedicines() {return medicines;}

    public void setTimeId(long timeId) {this.timeId = timeId;}
    public void setTime(String time) {this.time = time;}
    public void setMedicines(List<MedicineViewEsp> medicines) {this.medicines = medicines;}
    public void removeMedicine(MedicineViewEsp medicine){medicines.remove(medicine);}
    public void addMedicine(MedicineViewEsp medicine){if(!medicines.contains(medicine)) medicines.add(medicine);}

    @Override
    public String toString() {
        return "{" +
                "  \"timeId\":" + timeId +
                ", \"time\":'" +  time + '\'' +
                ", \"medicines\":" + medicines +
                '}';
    }
}
