package com.MainServer.Server_V2.modeB.service;

import com.MainServer.Server_V2.exception.DuplicateValueException;
import com.MainServer.Server_V2.modeB.model.Medicine;
import com.MainServer.Server_V2.modeB.model.ReminderB;
import com.MainServer.Server_V2.modeB.model.ReminderbId;
import com.MainServer.Server_V2.modeB.model.Time;
import com.MainServer.Server_V2.modeB.repository.MedicineRepository;
import com.MainServer.Server_V2.modeB.repository.ReminderBRepository;
import com.MainServer.Server_V2.modeB.repository.TimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
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

//    private ReminderBService(){}


    public void addRandom(int numberOfValues) {

        Random random = new Random();
        for (int i = 0; i < numberOfValues; i++) {
            Medicine medicine = getRandomMedicine();

            medicineRepository.save(medicine);

            String randTime = getRandomTime();
            Time time = addTime(new Time(randTime));

            short dosage = (short) (random.nextInt(4) + 1);

            ReminderB reminder = new ReminderB(
                    new ReminderbId(medicine.getMedId(), time.getId()),
                    medicine, time, dosage);
            medicine = addReminderToMedicine(medicine, reminder);
            reminderBRepository.save(reminder);
            medicine.addReminder(reminder);
            medicineRepository.save(medicine);
        }
    }


    private Time addTime( Time timeToAdd){
        Optional<Time> getByTimeOptional = timeRepository
                .findTimeByTimebTime(timeToAdd.getTime());
        if(getByTimeOptional.isPresent())
            return getByTimeOptional.get();
        timeRepository.save(timeToAdd);
        return timeToAdd;
    }


    private Medicine addReminderToMedicine(Medicine medicine, ReminderB reminderB){
//		if(reminderBRepository.findById(reminderB.getReminderbId()).isPresent())
//			throw new RuntimeException("this Reminder Already Exists");

        medicine.addReminder(reminderB);
        return medicine;
    }

    public Medicine addMedicine(Medicine medicine) throws DuplicateValueException {
        checkIfMedicineGood(medicine);
        medicineRepository.save(medicine);
        return medicine;
    }

    private void checkIfMedicineGood(Medicine medicine) throws DuplicateValueException {
        if(medicineRepository.findMedicineByMedName(medicine.getMedName()).isPresent())
            throw new DuplicateValueException("Medicine Already Exists");
        Optional<Medicine> getByBoxOptional = medicineRepository.findMedicineByMedBoxNo(medicine.getBox());
        getByBoxOptional.ifPresent( a-> {throw new DuplicateValueException(a.getMedName()+" is in this box");});

    }


    private Medicine getRandomMedicine(){
        Faker faker = new Faker();
        List<Short> boxes = new ArrayList<Short>();
        for (int i = 1; i <= 16; i++)
            boxes.add((short)i);

        short boxNo = boxes.remove((int) faker.random().nextInt(0, boxes.size() - 1));
        String medicineName = faker.medical().medicineName().toLowerCase();
        short amount = 0;
        boolean endsWith0=true;
        while(endsWith0){
            amount = (short)(int)(faker.random().nextInt(20, 50));
            if(amount%10 == 0) endsWith0=false;
        }

        return new Medicine(medicineName, boxNo, amount);
    }
    private String getRandomTime(){
        Random rand = new Random();
        String hour =   addZeroAstetic(rand.nextInt(0,24));
        String minute = addZeroAstetic(rand.nextInt(0,59));
        return hour+':'+minute;
    }
    private String addZeroAstetic(int number){
        if(number<10) return "0"+number;
        return ""+number;
    }
}
