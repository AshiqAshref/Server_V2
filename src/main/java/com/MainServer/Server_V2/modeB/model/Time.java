package com.MainServer.Server_V2.modeB.model;


import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;


@Entity(name = "Time")
@Table(name = "timeb",
        indexes = @Index(name = "timeb_time_index", columnList = "timeb_time", unique = true)
)
@NaturalIdCache
public class Time {
    @Id
    @SequenceGenerator(name = "time_sequence",
            sequenceName = "time_sequence",
            allocationSize = 1
    ) @GeneratedValue(generator = "time_sequence",
            strategy = SEQUENCE
    ) @Column(name = "timeb_id"
    ) private Long timebId;

    @NaturalId
    @Column(name = "timeb_time",
            length = 5,
            nullable = false
    ) private String timebTime;

    @OneToMany(mappedBy = "time",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    ) private List<ReminderB> medicines = new ArrayList<>();


    public Time(){}
    public Time(String time) {
        this.timebTime = time.trim();
    }
    public Time(long timebId, String time) {
        this.timebId = timebId;
        this.timebTime = time.trim();
    }

    public List<ReminderB> getMedicines(){
        return this.medicines;
    }

    public Long getTimeb_Id() {return timebId;}
    public void setTimeb_Id(Long id) {this.timebId = id;}

    public String getTimeb_time() {return timebTime; }
    public void setTimeb_time(String time) {this.timebTime = time.trim();}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(timebTime, time.timebTime);
    }
    @Override
    public int hashCode() {
        return Objects.hash(timebTime);
    }


    @Override
    public String toString() {
        return "{"
                + "\"timeb_id\" : " + timebId
                + ",\"timeb_time\" : " + addQuotes(timebTime)
                + "}";
    }

    private String addQuotes(String a) {
        if(a.charAt(0)!='\"') return '\"'+a+'\"';
        return a;
    }
}
