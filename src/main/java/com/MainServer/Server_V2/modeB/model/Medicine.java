package com.MainServer.Server_V2.modeB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    public Medicine(String med_name, int medBoxNo, int medAmount){
        this.medBoxNo = (short)medBoxNo;
        this.medName = med_name.trim().toUpperCase();
        this.medAmount = (short)medAmount;
    }
    public Medicine(long id, String med_name, int medBoxNo, int medAmount){
        this.id = id;
        this.medBoxNo = (short)medBoxNo;
        this.medName = med_name.trim().toUpperCase();
        this.medAmount = (short)medAmount;
    }


    public void addTime(Time time, int dosage) {
        ReminderB reminder = new ReminderB(this, time, (short)dosage);
        times.add(reminder);
        time.getMedicines().add(reminder);
    }
    @JsonIgnore
    public List<ReminderB> getTimes(){return times;}

    public void removeTime(Time time) {
        for (Iterator<ReminderB> iterator = times.iterator();
             iterator.hasNext(); ) {
            ReminderB reminder = iterator.next();

            if (reminder.getMedicine().equals(this) &&
                    reminder.getTime().getTimeb_time().equals(time.getTimeb_time())) {
                iterator.remove();
                reminder.getTime().getMedicines().remove(reminder);
                reminder.setMedicine(null);
                reminder.setTime(null);
            }
        }
    }

    public Long getMed_id() {return id;}
    public void setMed_id(Long med_id) {this.id = med_id;}

    public String getMed_name() {return medName.toUpperCase();}
    public void setMed_name(String medName) {this.medName = medName.trim().toUpperCase();}

    public  int getMed_box_no() {return medBoxNo;}
    public void setMed_box_no(int box) {this.medBoxNo = (short)box;}

    public int getMed_amount() {return medAmount;}
    public void setMed_amount(int medAmount) {this.medAmount = (short)medAmount;}



    //    @Override
//    public String toString() {
//        return "{"
//                + "\"med_id\" : "  + id
//                + ", \"med_box_no\" : "+ medBoxNo
//                + ", \"med_name\" : " +  addQuotes(medName)
//                + ", \"med_amount\" : " + medAmount
//                + ", \"times\" : " + getTimes()
//                +"}";
//    }
    private String addQuotes(String a) {
        if(a.charAt(0)!='\"')
            return '\"'+a+'\"';
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine)) return false;

        return id != null && id.equals(((Medicine) o).getMed_id());
    }
    @Override
    public int hashCode() {return getClass().hashCode();}
}