package com.MainServer.Server_V2.modeB.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReminderbId implements Serializable {
    @Column(name = "medb_id"
    ) private long medbId;

    @Column(name = "timeb_id"
    ) private long timebId;

    public ReminderbId(){}
    public ReminderbId(Long medbId, Long timebId){
        this.medbId = medbId;
        this.timebId = timebId;
    }

    public long getMedbId() {return medbId;}
    public void setMedbId(long medbId) {this.medbId = medbId;}

    public long getTimebId() {return timebId;}
    public void setTimebId(long timebId) {this.timebId = timebId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReminderbId that = (ReminderbId) o;
        return Objects.equals(medbId, that.medbId) &&
                Objects.equals(timebId, that.timebId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medbId, timebId);
    }
}