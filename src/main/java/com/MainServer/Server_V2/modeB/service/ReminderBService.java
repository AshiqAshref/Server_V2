package com.MainServer.Server_V2.modeB.service;

import com.MainServer.Server_V2.exception.DuplicateValueException;
import com.MainServer.Server_V2.modeB.model.Medicine;
import com.MainServer.Server_V2.modeB.model.ReminderB;
import com.MainServer.Server_V2.modeB.model.ReminderbId;
import com.MainServer.Server_V2.modeB.model.Time;
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


//    private ReminderBService(){}

    public List<Medicine> addRandomMedicines(int numberOfRecords){
        List<Medicine> medicines = generateRandomMedicines(numberOfRecords);
        return medicineRepository.saveAll(medicines);
    }

    public List<Medicine> generateRandomMedicines(int numberOfRecords){
        int freeSpace = (int)(16-medicineRepository.count());
        if (numberOfRecords>freeSpace)
            numberOfRecords = freeSpace;
        List<Medicine> medicines = new ArrayList<>();
        for (int i = 0; i <= numberOfRecords; i++) {
            Medicine medicine = getRandomMedicine();
            try {
                checkIfMedicineGood(medicine);
                medicines.add(medicine);
            }catch (Exception e){
                i=i-1;
            }
        }return medicines;
    }

    public List<Medicine> getReminders(){
        List<Medicine> reminders = new ArrayList<>();
        return reminders;
    }

    public ReminderB updateReminder(ReminderB reminder){
        return null;
    }

    public void deleteReminder(){
        
    }


    @Transactional
    public void addRandom(int numberOfValues){
        Random random = new Random();
        for (int i = 0; i < numberOfValues; i++) {
//            Medicine medicine = getRandomMedicine();
//            medicineRepository.save(medicine);
            Medicine medicine = new Medicine();
            System.out.println();
            medicine = getMedicineById(1L);
            String randTime = getRandomTime();
            Time time = addTime(new Time(randTime));

            short dosage = (short) (random.nextInt(4) + 1);



//            ReminderB reminder = new ReminderB(medicine, time, dosage);
//            medicine = addReminderToMedicine(medicine, reminder);
//            reminderBRepository.save(reminder);
//            medicine.addReminder(reminder);
//            medicineRepository.save(medicine);
        }
    }


    @Transactional
    public Time getTimeById(Long id){
        return timeRepository.findById(id).get();
    }
    @Transactional
    public Medicine getMedicineById(Long id){
        return medicineRepository.findById(id).get();
    }
    @Transactional
    public void addTimeToMed(Medicine medicine, Time time, short dosage){
        medicine.addTime(time, dosage);
    }
    @Transactional
    public Medicine saveMedicine(Medicine medicine){
        return medicineRepository.save(medicine);
    }



    private Time addTime( Time timeToAdd){
        Optional<Time> getByTimeOptional = timeRepository
                .findTimeByTimebTime(timeToAdd.getTime());
        if(getByTimeOptional.isPresent())
            return getByTimeOptional.get();
        timeRepository.save(timeToAdd);
        return timeToAdd;
    }


    public Medicine addMedicine(Medicine medicine) throws DuplicateValueException {
        checkIfMedicineGood(medicine);
        medicineRepository.save(medicine);
        return medicine;
    }

    public void deleteMedicine(Long id) throws DuplicateValueException {
        medicineRepository.deleteById(id);
    }

    public Medicine updateMedicine(Medicine medicine) throws DuplicateValueException {
        checkIfMedicineGood(medicine);
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getAllMedicines(){
        return medicineRepository.findAll();
    }

    private void checkIfMedicineGood(Medicine medicine) throws DuplicateValueException {
        if(medicineRepository.findMedicineByMedName(medicine.getMedName()).isPresent())
            throw new DuplicateValueException("Medicine Mame Already Exists");
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
