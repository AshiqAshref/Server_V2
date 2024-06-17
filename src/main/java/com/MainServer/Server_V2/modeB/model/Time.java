package com.MainServer.Server_V2.modeB.model;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;


@Entity(name = "Time")
@Table(name = "timeb",
//        uniqueConstraints = {
//                @UniqueConstraint(name = "timeb_time_unique", columnNames = "timeb_time")
//        },
        indexes = @Index(name = "timeb_time_index", columnList = "timeb_time", unique = true)
)
public class Time {
    @Id
    @SequenceGenerator(name = "time_sequence",
            sequenceName = "time_sequence",
            allocationSize = 1
    ) @GeneratedValue(generator = "time_sequence",
            strategy = SEQUENCE
    ) @Column(name = "timeb_id"
    ) private Long timebId;

    @Column(name = "timeb_time",
            length = 5,
            nullable = false
    ) private String timebTime;

    @OneToMany(mappedBy = "time",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    ) private List<ReminderB> reminders = new ArrayList<>();


    public Time(){}
    public Time(String time) {
        this.timebTime = time.trim();
    }
    public Time(Long id, String time) {
        this.timebId = id;
        this.timebTime = time.trim();
    }

    public Long getId() {return timebId;}
    public void setId(Long id) {this.timebId = id;}

    public String getTime() {return timebTime; }
    public void setTime(String time) {this.timebTime = time.trim();}




//    public List<ReminderB> getReminders() {return reminders;}
//    public void removeReminder(ReminderB reminder){reminders.remove(reminder);}
//    public void addReminder(ReminderB reminder){
//        if (!reminders.contains(reminder)){
//            reminder.setReminderbId(new ReminderbId(
//                    reminder.getReminderbId().getMedbId(),this.timebId)
//            );
//            reminders.add(reminder);
//        }
//    }


    @Override
    public String toString() {
        return "{"
                + "\"timeb_id\" : " + timebId
                + ",\"timeb_time\" : " + addQuotes(timebTime)
//                + ", \"timeb_dosage\" : " + timebDosage
//                +", \"medb_medicines\" : "+ reminders.toString()
                + "}";
    }


    private String addQuotes(String a) {
        if(a.charAt(0)!='\"')
            return '\"'+a+'\"';
        return a;
    }
}
