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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;



import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    public ReminderView addReminder(ReminderView reminder){
        return null;
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
        if(medicineRepository.findMedicineByMedName(medicine.getMedName()).isPresent())
            throw new DuplicateValueException("Medicine with name " + medicine.getMedName() + " already exists");
        Optional<Medicine> medicineOptional =  medicineRepository.findMedicineByMedBoxNo(medicine.getBox());
        if(medicineOptional.isPresent())
            throw new DuplicateValueException(medicineOptional.get().getMedName()+" is in box no" + medicine.getBox() + " already exists");
    }






    private String addZeroAstetic(int number){
        if(number<10) return "0"+number;
        return ""+number;
    }



}
