package com.MainServer.Server_V2.modeB.model;


import jakarta.persistence.*;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Medicine")
@Table(name = "medicine",
        indexes = @Index(name = "med_name_index", columnList = "med_name", unique = true)
)
public class Medicine {
    @Id
    @Column(name = "med_id"
    ) @SequenceGenerator(name = "medicine_sequence",
            sequenceName = "medicine_sequence",
            allocationSize = 1
    ) @GeneratedValue(generator = "medicine_sequence",
            strategy = SEQUENCE
    ) Long id;

    @Column(name = "med_box_no",
            unique = true
    ) short medBoxNo;

    @Column(name = "med_name",
            nullable = false
    ) String medName;

    @Column(name = "med_amount",
            nullable = false
    ) short medAmount;

    @OneToMany(mappedBy = "medicine",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    ) private  List<ReminderB> times = new ArrayList<>();


    public Medicine(){}
    public Medicine(String med_name, short medBoxNo, short medAmount){
        this.medBoxNo = medBoxNo;
        this.medName = med_name.trim();
        this.medAmount = medAmount;
    }

    public void addTime(Time time, short dosage) {
        ReminderB reminder = new ReminderB(this, time, dosage);
        times.add(reminder);
        time.getMedicines().add(reminder);
    }

    public void removeTime(Time time) {
        for (Iterator<ReminderB> iterator = times.iterator();
             iterator.hasNext(); ) {
            ReminderB reminder = iterator.next();

            if (reminder.getMedicine().equals(this) &&
                    reminder.getTime().equals(time)) {
                iterator.remove();
                reminder.getTime().getMedicines().remove(reminder);
                reminder.setMedicine(null);
                reminder.setTime(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine)) return false;

        return id != null && id.equals(((Medicine) o).getId());
    }
    @Override
    public int hashCode() {return getClass().hashCode();}

    public List<ReminderB> getTimes(){return times;}
    public void setId(Long med_id) {this.id = med_id;}
    public Long getId() {return id;}
    public String getMedName() {return medName;}
    public void setMedName(String medName) {this.medName = medName.trim();}
    public short getMedAmount() {return medAmount;}
    public void setMedAmount(short medAmount) {this.medAmount = medAmount;}
    public  short getBox() {return medBoxNo;}
    public void setBox(short box) {this.medBoxNo = box;}



    @Override
    public String toString() {
        return "{"
                + "\"med_id\" : "  + id
                + ",\"med_box_no\" : "+ medBoxNo
                + ",\"med_name\" : " +  addQuotes(medName)
                + ", \"med_amount\" : " + medAmount
                +"}";
    }
    private String addQuotes(String a) {
        if(a.charAt(0)!='\"')
            return '\"'+a+'\"';
        return a;
    }
}