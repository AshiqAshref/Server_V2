package com.MainServer.Server_V2.modeB.service;

import com.MainServer.Server_V2.exception.DuplicateValueException;

import com.MainServer.Server_V2.modeB.model.Medicine;
import com.MainServer.Server_V2.modeB.model.ReminderB;
import com.MainServer.Server_V2.modeB.model.Time;
import com.MainServer.Server_V2.modeB.model.view.websiteView.ReminderView;
import com.MainServer.Server_V2.modeB.model.view.websiteView.TimeView;
import com.MainServer.Server_V2.modeB.repository.MedicineRepository;
import com.MainServer.Server_V2.modeB.repository.ReminderBRepository;
import com.MainServer.Server_V2.modeB.repository.TimeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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

    @Transactional
    public ReminderView addReminder(ReminderView reminderReceived){
        Medicine medicine = medicineRepository.findById(reminderReceived.getMed_id()).orElseThrow(RuntimeException::new);
        List<TimeView> recivedTimeeees = reminderReceived.getTimes();
        Iterator<ReminderB> iterator = medicine.getTimes().iterator();
        while(iterator.hasNext()){
            ReminderB reminderExisting = iterator.next();
            boolean deleteReminder = true;
            Iterator<TimeView> receivedTimes = recivedTimeeees.iterator();
            while (receivedTimes.hasNext()) {
                TimeView receivedTime = receivedTimes.next();
                if (reminderExisting.getTime().getTimeb_time().equals(receivedTime.getTime())) {
                    reminderExisting.setDosage(receivedTime.getDosage());
                    deleteReminder = false;
                    receivedTimes.remove();
                }
            }
            if (deleteReminder) {
                iterator.remove();
                medicine.removeTime(reminderExisting.getTime());
            }
        }

        medicineRepository.flush();
        for (TimeView timeRecived : recivedTimeeees) {
            Time time = getOrCreateTime(timeRecived.getTime());
            medicine.addTime(time, timeRecived.getDosage());
            medicineRepository.flush();
        }
        cleanTimesTable();
        return reminderBtoReminderView(medicineRepository.save(medicine));
    }

    @Transactional
    public void cleanTimesTable(){
        List<Time> times = timeRepository.findAll();
        long count = times.size();
        for(Time time : times){
            if(time.getMedicines().isEmpty())
                timeRepository.delete(time);
        }
        System.out.println("Cleaned Time Records : " + (count - timeRepository.count()));
    }




    private long checkIfTimeExists(Medicine medicine, String time){
        for(ReminderB rTime : medicine.getTimes())
            if(rTime.getTime().getTimeb_time().equals(time))
                return rTime.getTime().getTimeb_Id();
        return 0L;
    }

    @Transactional
    public Time getOrCreateTime(String timeb_time){
        Optional<Time> timeOPtional = timeRepository.findTimeByTimebTime(timeb_time.trim());
        if(timeOPtional.isPresent())
            return timeOPtional.get();
        else
            return timeRepository.save(new Time(timeb_time.trim()));
//                .orElse(timeRepository.save(new Time(timeb_time.trim())));
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


    public ReminderView updateReminder(ReminderView remindersReceived){
        Medicine medicine = medicineRepository.findById(remindersReceived.getMed_id()).orElseThrow();
        Iterator<TimeView> reminderReceived = remindersReceived.getTimes().iterator();
        Iterator<ReminderB> remindersExisting = medicine.getTimes().iterator();
        while(remindersExisting.hasNext()){
            ReminderB reminder = remindersExisting.next();
            boolean deleteReminder = true;
        }
        return null;
    }
    public void deleteReminder(long medbId, long timebId){}



    @Transactional
    public Medicine addMedicine(Medicine medicine)  {
        checkIfMedicineGood(medicine);
        return medicineRepository.save(medicine);
    }

    @Transactional
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    @Transactional
    public Medicine updateMedicine(Medicine medicineToUpdate)  {
        Optional<Medicine> medicineOptional = medicineRepository.findMedicineByMedName(medicineToUpdate.getMed_name());
        if(medicineOptional.isPresent()){
            if(medicineOptional.get().getMed_id()!= medicineToUpdate.getMed_id())
                throw new DuplicateValueException("Medicine with name " + medicineToUpdate.getMed_name() + " already exists");
        }
        Medicine medicine = medicineRepository.findById(medicineToUpdate.getMed_id()).orElseThrow(()-> new DuplicateValueException("Couldnt find med_id to Update"));
        medicine.setMed_amount(medicineToUpdate.getMed_amount());
        medicine.setMed_box_no(medicineToUpdate.getMed_box_no());
        medicine.setMed_name(medicineToUpdate.getMed_name());
        return medicineRepository.save(medicine);
    }

    @Transactional
    public List<Medicine> getAllMedicines(){
        return medicineRepository.findAll();
    }

    private void checkIfMedicineGood(Medicine medicine) {
        if(medicineRepository.findMedicineByMedName(medicine.getMed_name()).isPresent())
            throw new DuplicateValueException("Medicine with name " + medicine.getMed_name() + " already exists");
        Optional<Medicine> medicineByBoxOptional =  medicineRepository.findMedicineByMedBoxNo(medicine.getMed_box_no());
        if(medicineByBoxOptional.isPresent())
            if(!medicineByBoxOptional.get().getMed_id().equals(medicine.getMed_id()))
                throw new DuplicateValueException(medicineByBoxOptional.get().getMed_name()+" is in box no " + medicine.getMed_box_no() + " already exists");
    }

    private Medicine getOrCreateMedicine(Medicine medicine){
        return medicineRepository.findMedicineByMedName(medicine.getMed_name()).orElse(
                medicineRepository.findMedicineByMedBoxNo(medicine.getMed_box_no()).orElse(
                        medicineRepository.save(medicine)
                )
        );
    }

    private String addZeroAstetic(int number){
        if(number<10) return "0"+number;
        return ""+number;
    }



}
