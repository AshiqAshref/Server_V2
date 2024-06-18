package com.MainServer.Server_V2.modeB.model.view;

import com.MainServer.Server_V2.modeB.model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class ReminderView {
    private Medicine medicine;
    private List<TimeView> times = new ArrayList<>();

    public ReminderView(Medicine medicine, List<TimeView> time){
        this.medicine = medicine;
        this.times = time;
    }

    public ReminderView(Medicine medicine){
        this.medicine = medicine;
//        this.times = time;
    }




    @Override
    public String toString() {
        return "{"
                + "\"med_id\" : "  + medicine.getId()
                + ",\"med_box_no\" : "+ medicine.getBox()
                + ",\"med_name\" : " +  addQuotes(medicine.getMedName())
                + ", \"med_amount\" : " + medicine.getMedAmount()
                + ", \"times\" : " + times
                +"}";
    }
    private String addQuotes(String a) {
        if(a.charAt(0)!='\"')
            return '\"'+a+'\"';
        return a;
    }
}
