package com.MainServer.Server_V2.modeB.model;

import jakarta.persistence.*;

@Entity(name = "ReminderB")
@Table(name = "reminderb")
public class ReminderB {

    @EmbeddedId()
    private ReminderbId reminderbId;
    @ManyToOne(
//            cascade = {CascadeType.PERSIST}
            fetch = FetchType.EAGER
//            fetch =FetchType.LAZY
    )
    @MapsId("medbId")
    @JoinColumn(name = "medb_id",
            foreignKey = @ForeignKey(name = "remb_medb_id")
    )
    private Medicine medicine;


    @ManyToOne(
            fetch = FetchType.LAZY
//            cascade = {CascadeType.PERSIST}
    )
    @MapsId("timebId")
    @JoinColumn(name = "timeb_id",
            foreignKey = @ForeignKey(name = "remb_timeb_id")
    )
    private Time time;



    @Column(name = "timeb_dosage",
            nullable = false
    )
    private short rembDosage;


    public ReminderB(){}
    public ReminderB(ReminderbId reminderbId,Medicine medicine, Time time, short rembDosage){
        this.reminderbId = reminderbId;
        this.medicine = medicine;
        this.time = time;
        this.rembDosage = rembDosage;
    }
    public ReminderB(Medicine medicine, Time time, short rembDosage){
        this.reminderbId = new ReminderbId(medicine.getMedId(), time.getId());
        this.medicine = medicine;
        this.time = time;
        this.rembDosage = rembDosage;
    }

//    public ReminderB(Medicine medicine, Time time, short rembDosage){
//        this.medicine = medicine;
//        this.time = time;
//        this.rembDosage = rembDosage;
//    }


    public ReminderbId getReminderbId() {
        return reminderbId;
    }
    public void setReminderbId(ReminderbId reminderbId) {this.reminderbId = reminderbId;}
    public Medicine getMedicine() {return medicine;}
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
        this.reminderbId.setMedbId(medicine.getMedId());
    }
    public Time getTime() {return time;}
    public void setTime(Time time) {
        this.time = time;
        this.reminderbId.setTimebId(time.getId());
    }
    public short getRembDosage() {return rembDosage;}
    public void setRembDosage(short rembDosage) {this.rembDosage = rembDosage;}
}
