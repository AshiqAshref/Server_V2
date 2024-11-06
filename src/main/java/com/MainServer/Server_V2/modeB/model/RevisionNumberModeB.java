package com.MainServer.Server_V2.modeB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "RevisionNumberModeB")
@Table(name = "revision_number_mode_B")
public class RevisionNumberModeB {

    @Id
    private Integer id = 1; // Fixed ID to prevent additional rows

    @Column(name = "revision_no", columnDefinition = "integer default 1")
    private int revisionNo;

    @JsonIgnore
    public Integer getId() {
        return id;
    }
    public int getRevisionNo() {
        return revisionNo;
    }
    public RevisionNumberModeB(){
        this.revisionNo =1;
    }

    public void updateRevisionValue() {
        if (this.revisionNo >2147483637)
            this.revisionNo =1;
        else
            this.revisionNo += 1;
    }
    public void setRevisionNo(int revisionValue){
        this.revisionNo = revisionValue;
    }
}
