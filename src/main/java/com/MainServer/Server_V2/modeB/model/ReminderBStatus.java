package com.MainServer.Server_V2.modeB.model;

import jakarta.persistence.*;

import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "ReminderBStatus")
@Table(name ="reminderB_status")
public class ReminderBStatus {
    @Id
    @Column(name = "statb_id"
    ) @SequenceGenerator(name = "statb_sequence",
            sequenceName = "statb_sequence",
            allocationSize = 1
    ) @GeneratedValue(generator = "statb_sequence",
            strategy = SEQUENCE
    ) Long id;
    @Column(name = "stat_time"
    )String statTime;
    @Column(name = "stat_med_box_no"
    )int statMedBoxNo;
    @Column(name = "stat_med_name"
    ) String statMedName;
    @Column(name = "stat_med_amount"
    ) short statMedAmount;
    @Column(name = "stat_med_dosage"
    ) short statMedDosage;
    @Column(name = "stat_date_created"
    ) Date dateCreated;

    public ReminderBStatus() {}
    public ReminderBStatus( short statMedDosage, short statMedAmount, String statMedName, int statMedBoxNo, String statTime) {
        this.statMedDosage = statMedDosage;
        this.statMedAmount = statMedAmount;
        this.statMedName = statMedName;
        this.statMedBoxNo = statMedBoxNo;
        this.statTime = statTime;
    }

    public void setDateCreated(Date dateCreated) {this.dateCreated = dateCreated;}

    public Long getId()                 {return id;}
    public String getStat_time()        {return statTime;}
    public int getStat_med_box_no()     {return statMedBoxNo;}
    public String getStat_med_name()    {return statMedName;}
    public short getStat_med_amount()   {return statMedAmount;}
    public short getStat_med_dosage()   {return statMedDosage;}
    public Date getStat_date_created()  {return dateCreated;}
}
