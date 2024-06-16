package com.MainServer.Server_V2.modeA.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "ReminderA")
@Table(name = "reminderA",
        uniqueConstraints = {
                @UniqueConstraint(name = "rema_time_unique", columnNames = "rema_time"),
                @UniqueConstraint(name = "rema_box_no_unique", columnNames = "rema_box_no"),
        }
//        ,indexes = @Index(name = "med_name_index", columnList = "med_name", unique = true)
)
public class ReminderA {
    @Id
    @Column(name = "rema_id") @SequenceGenerator(
            name = "rema_id_sequence",
            sequenceName = "rema_id_sequence",
            allocationSize = 1
    ) @GeneratedValue(
            strategy = SEQUENCE,
            generator = "rema_id_sequence"
    ) Long id;

    @Column(name = "rema_time",
            nullable = false
    ) String time;

    @Column(name = "rema_box_no",
            nullable = false
    ) short boxNo;

    public ReminderA(String time, short boxNo) {
        this.time = time;
        this.boxNo = boxNo;
    }

    public ReminderA() {
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}
    public short getBoxNo() {return boxNo;}
    public void setBoxNo(short boxNo) {this.boxNo = boxNo;}
}
