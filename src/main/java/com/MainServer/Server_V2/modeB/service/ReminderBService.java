package com.MainServer.Server_V2.modeB.service;

import com.MainServer.Server_V2.exception.DuplicateValueException;

import com.MainServer.Server_V2.modeB.model.Medicine;
import com.MainServer.Server_V2.modeB.model.ReminderB;
import com.MainServer.Server_V2.modeB.model.Time;
import com.MainServer.Server_V2.modeB.model.view.ReminderView;
import com.MainServer.Server_V2.modeB.model.view.TimeView;
import com.MainServer.Server_V2.modeB.repository.MedicineRepository;
import com.MainServer.Server_V2.modeB.repository.ReminderBRepository;
import com.MainServer.Server_V2.modeB.repository.TimeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service

public class ReminderBService  {
    public ReminderBRepository reminderBRepository;
    public MedicineRepository medicineRepository;
    public TimeRepository timeRepository;

    @Autowired
    public ReminderBService(ReminderBRepository reminderBRepository,
                            MedicineRepository medicineRepository,
                            TimeRepository timeRepository) {
        this.reminderBRepository = reminderBRepository;
        this.medicineRepository = medicineRepository;
        this.timeRepository = timeRepository;
    }

    @PersistenceContext
    private EntityManager em;





    public List<ReminderView> getAllReminders(){
        return reminderBtoReminderView(medicineRepository.findAll());
    }

    public ReminderView addReminder(ReminderView reminderReceived){
        Medicine medicine = medicineRepository.findById(reminderReceived.getMed_id()).orElseThrow(RuntimeException::new);
        Iterator<TimeView> remindersReceived = reminderReceived.getTimes().iterator();
        Iterator<ReminderB> remindersExisting = medicine.getTimes().iterator();
        while(remindersExisting.hasNext()){
            ReminderB reminderExisting = remindersExisting.next();
            boolean deleteReminder = true;
            while(remindersReceived.hasNext()){
                TimeView timeRecived = remindersReceived.next();
                if(reminderExisting.getTime().getTimeb_time().equals(timeRecived.getTimeb_time())){
                    reminderExisting.setDosage(timeRecived.getDosage());
                    deleteReminder = false;
                    remindersReceived.remove();
                }
            }
            if(deleteReminder)
                medicine.removeTime(reminderExisting.getTime());
        }
        while(remindersReceived.hasNext()){
            TimeView timeRecived = remindersReceived.next();
            medicine.addTime(getOrCreateTime(timeRecived.getTimeb_time()),timeRecived.getDosage());
        }
        return reminderBtoReminderView(medicineRepository.save(medicine));
    }


    private long checkIfTimeExists(Medicine medicine, String time){
        for(ReminderB rTime : medicine.getTimes())
            if(rTime.getTime().getTimeb_time().equals(time))
                return rTime.getTime().getTimeb_Id();
        return 0L;
    }

    private Time getOrCreateTime(String timeb_time){
        Time time = new Time(timeb_time);
        return timeRepository.findTimeByTimebTime(timeb_time)
                .orElse(timeRepository.save(time));
    }






    private ReminderView reminderBtoReminderView(Medicine medicine){
        ReminderView reminder = new ReminderView(medicine);
        for(ReminderB reminderB : medicine.getTimes())
            reminder.addTime(new TimeView(reminderB.getTime(),reminderB.getDosage()));
        return reminder;
    }
    private List<ReminderView> reminderBtoReminderView(List<Medicine> medicines){
        List<ReminderView> reminders = new ArrayList<>();
        for(Medicine medicine : medicines)
            reminders.add(reminderBtoReminderView(medicine));
        return reminders;
    }


    public ReminderView updateReminder(ReminderView reminder){
        return null;
    }
    public void deleteReminder(long medbId, long timebId){

    }





    public Medicine addMedicine(Medicine medicine)  {
        checkIfMedicineGood(medicine);
        medicineRepository.save(medicine);
        return medicine;
    }

    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    public Medicine updateMedicine(Medicine medicine)  {
        checkIfMedicineGood(medicine);
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getAllMedicines(){
        return medicineRepository.findAll();
    }

    private void checkIfMedicineGood(Medicine medicine) {
        if(medicineRepository.findMedicineByMedName(medicine.getMed_Name()).isPresent())
            throw new DuplicateValueException("Medicine with name " + medicine.getMed_Name() + " already exists");
        Optional<Medicine> medicineOptional =  medicineRepository.findMedicineByMedBoxNo(medicine.getMed_box_no());
        if(medicineOptional.isPresent())
            throw new DuplicateValueException(medicineOptional.get().getMed_Name()+" is in box no" + medicine.getMed_box_no() + " already exists");
    }

    private void getOrCreateMedicine(){

    }






    private String addZeroAstetic(int number){
        if(number<10) return "0"+number;
        return ""+number;
    }



}
