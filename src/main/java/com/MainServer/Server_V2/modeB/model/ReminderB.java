package com.MainServer.Server_V2.modeB.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "ReminderB"
)@Table(name = "reminderb"
) public class ReminderB {

    @EmbeddedId(
    ) private ReminderbId reminderbId;

    @ManyToOne(fetch = FetchType.LAZY
    ) @MapsId("medbId"
    ) private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY
    ) @MapsId("timebId"
    ) private Time time;

    @Column(name = "timeb_dosage",
            nullable = false
    ) private short rembDosage;


    public ReminderB(){}
    public ReminderB(Medicine medicine, Time time, short rembDosage){
        this.medicine = medicine;
        this.time = time;
        this.reminderbId = new ReminderbId(medicine.getMed_id(), time.getTimeb_Id());
        this.rembDosage = rembDosage;
    }

    public ReminderbId getReminderbId() {
        return reminderbId;
    }
    public void setReminderbId(ReminderbId reminderbId) {this.reminderbId = reminderbId;}

    public Medicine getMedicine() {return medicine;}
    public void setMedicine(Medicine medicine) {this.medicine = medicine;}

    public Time getTime() {return time;}
    public void setTime(Time time) {this.time = time;}
    public short getDosage(){return rembDosage;}
    public void setDosage(short dosage){this.rembDosage = dosage;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReminderB that = (ReminderB) o;
        return Objects.equals(medicine, that.medicine) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {return Objects.hash(medicine, time);}
}
