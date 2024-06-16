package com.MainServer.Server_V2.modeB.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Medicine")
@Table(name = "medicine",
        indexes = @Index(name = "med_name_index", columnList = "med_name", unique = true)
)
public class Medicine {
    @Id
    @Column(name = "med_id")
    @SequenceGenerator(
            name = "medicine_sequence",
            sequenceName = "medicine_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "medicine_sequence"
    )
    Long medId;
    @Column(name = "med_box_no")
    short medBoxNo;
    @Column(name = "med_name", nullable = false)
    String medName;
    @Column(name = "med_amount", nullable = false)
    short medAmount;
    @OneToMany(
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            fetch = FetchType.EAGER,

            fetch = FetchType.LAZY,
            mappedBy = "medicine"
    )
    private  List<ReminderB> reminders = new ArrayList<>();

    public List<ReminderB> getReminders() {return reminders;}
    public void removeReminder(ReminderB reminder){reminders.remove(reminder);}
    public void addReminder(ReminderB reminder){
        if (!reminders.contains(reminder)) {
            reminder.setReminderbId(new ReminderbId(
                    this.medId, reminder.getReminderbId().getTimebId())
            );
            reminders.add(reminder);
        }
    }


    public Medicine(String med_name, short medBoxNo, short medAmount){
        this.medBoxNo = medBoxNo;
        this.medName = med_name.trim();
        this.medAmount = medAmount;
    }
    public Medicine(){}




    public void setMedId(Long med_id) {this.medId =med_id;}
    public Long getMedId() {return medId;}
    public String getMedName() {return medName;}
    public void setMedName(String medName) {this.medName = medName.trim();}
    public short getMedAmount() {return medAmount;}
    public void setMedAmount(short medAmount) {this.medAmount = medAmount;}
    public  short getBox() {return medBoxNo;}
    public void setBox(short box) {this.medBoxNo = box;}

    public List<ReminderB> getReminderB(){
        return reminders;
    }


    @Override
    public String toString() {
        return "{"
                + "\"med_id\" : "  + medId
                + ",\"med_box_no\" : "+ medBoxNo
                + ",\"med_name\" : " +  addQuotes(medName)
                + ", \"med_amount\" : " + medAmount
//                +", \"timeb_times\" : "+ times.toString()
//                +", \"timeb_times\" : "+ reminders.toString()
                +"}";
    }


    private String addQuotes(String a) {
        if(a.charAt(0)!='\"')
            return '\"'+a+'\"';
        return a;
    }
}