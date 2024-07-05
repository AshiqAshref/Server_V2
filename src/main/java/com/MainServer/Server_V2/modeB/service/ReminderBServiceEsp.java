package com.MainServer.Server_V2.modeB.service;

import com.MainServer.Server_V2.modeB.model.ReminderbId;
import com.MainServer.Server_V2.modeB.model.view.espServerView.BoxViewEsp;
import com.MainServer.Server_V2.modeB.model.view.espServerView.MedicineViewEsp;
import com.MainServer.Server_V2.modeB.model.view.espServerView.ReminderViewEsp;
import com.MainServer.Server_V2.modeB.repository.MedicineRepository;
import com.MainServer.Server_V2.modeB.repository.ReminderBRepository;
import com.MainServer.Server_V2.modeB.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReminderBServiceEsp {
    public ReminderBRepository reminderBRepository;
    public MedicineRepository medicineRepository;
    public TimeRepository timeRepository;

    @Autowired
    public ReminderBServiceEsp(ReminderBRepository reminderBRepository,
                            MedicineRepository medicineRepository,
                            TimeRepository timeRepository) {
        this.reminderBRepository = reminderBRepository;
        this.medicineRepository = medicineRepository;
        this.timeRepository = timeRepository;
    }

    public List<ReminderViewEsp> getAllReminders(){
        List<ReminderViewEsp> remindersList = new ArrayList<>();
        timeRepository.findAllByOrderByTimebTimeAsc().forEach(time->{
            List<MedicineViewEsp> medicines = new ArrayList<>();
            time.getMedicines().forEach(reminder-> medicines.add(new MedicineViewEsp(
                    reminder.getMedicine().getMed_box_no(), reminder.getDosage())));
            remindersList.add(new ReminderViewEsp(time, medicines));
        });
        return remindersList;
    }
    public List<BoxViewEsp> getAllBoxes(){
        List<BoxViewEsp> boxes  = new ArrayList<>();
        medicineRepository.findAll().forEach(medicine -> boxes.add(new BoxViewEsp(medicine)));
        return boxes;
    }
    public void updateReminder(long medbId,long timebId){
        reminderBRepository.findById(new ReminderbId(medbId, timebId)).ifPresent(reminder ->{
            reminder.getTime();
        });
    }
}
