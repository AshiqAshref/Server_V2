package com.MainServer.Server_V2.modeB.service;

import com.MainServer.Server_V2.modeB.model.view.ReminderView;
import com.MainServer.Server_V2.modeB.model.view.TimeView;
import com.MainServer.Server_V2.modeB.model.ReminderB;
import com.MainServer.Server_V2.modeB.model.Medicine;
import com.MainServer.Server_V2.modeB.model.Time;
import com.MainServer.Server_V2.modeB.repository.MedicineRepository;
import com.MainServer.Server_V2.modeB.repository.ReminderBRepository;
import com.MainServer.Server_V2.modeB.repository.TimeRepository;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ReminderBServiceTest {
    public ReminderBRepository reminderBRepository;
    public MedicineRepository medicineRepository;
    public TimeRepository timeRepository;

    @Autowired
    public ReminderBServiceTest(ReminderBRepository reminderBRepository,
                            MedicineRepository medicineRepository,
                            TimeRepository timeRepository) {
        this.reminderBRepository = reminderBRepository;
        this.medicineRepository = medicineRepository;
        this.timeRepository = timeRepository;
    }
    @PersistenceContext
    private EntityManager em;


    @Transactional
    public void test(int a){
        List<Medicine> meds = medicineRepository.findAll();
        try {
            System.out.println(reminderBtoReminderView(meds));
        }catch (Exception e){
            e.printStackTrace();
        }
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


    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<Medicine> addRandomRemindersToDatabase(int numberOfMedicineRecords, int numberOfTimeRecords){
        Random random = new Random();
        List<Medicine> medicines =  generateRandomMedicines(numberOfMedicineRecords);
        for (Medicine medicine : medicines) {
            medicine = saveMedicine(medicine);
            List<Time> times = generateRandomTimes(numberOfTimeRecords, true);
            for (Time time : times) {
                time = saveTime(time);
                medicine.addTime(time, (short) (random.nextInt(4) + 1));
            }
            em.flush();
        }
        return medicines;
    }
    private Time saveTime(Time time){
        return timeRepository.findTimeByTimebTime(time.getTimeb_time()).orElse(timeRepository.save(time));
    }
    private List<Time> generateRandomTimes(int numberOfTimes, boolean byFives){
        List<Time> times = new ArrayList<>();
        for (int i = 0; i < numberOfTimes; i++) {
            Time time;
            if (byFives) time = new Time(getRandomTimeof5());
            else time = new Time(getRandomTime());

            Optional<Time> timeOptional = timeRepository.findTimeByTimebTime(time.getTimeb_time());
            if (timeOptional.isPresent()){
                if (!checkTimesforTime(timeOptional.get().getTimeb_time(), times))
                    times.add(timeOptional.get());
            }else{
                if(!checkTimesforTime(time.getTimeb_time(), times))
                    times.add(time);
            }
        }
        return times;
    }
    private String getRandomTime(){
        Random rand = new Random();
        String hour =   addZeroAstetic(rand.nextInt(0,24));
        String minute = addZeroAstetic(rand.nextInt(0,59));
        return hour+':'+minute;
    }
    private String getRandomTimeof5(){
        Random rand = new Random();
        int hour =   rand.nextInt(0,24);
        int minute = rand.nextInt(0,59);
        while((hour%5)!=0)
            hour = rand.nextInt(0,24);
        while((minute%5)!=0)
            minute = rand.nextInt(0,59);
        return addZeroAstetic(hour)+':'+ addZeroAstetic(minute);
    }


    private boolean checkTimesforTime(String time, List<Time> times){
        for(Time t : times)
            if(t.getTimeb_time().equals(time))
                return true;
        return false;
    }
    private String addZeroAstetic(int number){
        if(number<10) return "0"+number;
        return ""+number;
    }



    public List<Medicine> addRandomMedicines(int numberOfRecords){
        List<Medicine> medicines = generateRandomMedicines(numberOfRecords);
        List<Medicine> returnMeds = new ArrayList<>();
        for(Medicine medicine : medicines){
            saveMedicine(medicine);
            returnMeds.add(medicineRepository.save(medicine));
        }
        return returnMeds;
    }
    public List<Medicine> generateRandomMedicines(int size){
        List<Medicine>medicinesInDatabase = medicineRepository.findAll();
        int freeSpace = (16- medicinesInDatabase.size());
        if (size>freeSpace) size = freeSpace;
        List<Medicine> medicines = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Medicine medicine = getRandomMedicine(medicinesInDatabase);
            medicines.add(medicine);
        }return medicines;
    }

    private Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.findMedicineByMedName(medicine.getMed_name()).orElse(
                medicineRepository.findMedicineByMedBoxNo(medicine.getMed_box_no()).orElse(
                        medicineRepository.save(medicine)
                )
        );
    }

    private Medicine getRandomMedicine(List<Medicine> medicines){
        Faker faker = new Faker();
        List<Short> boxes = new ArrayList<Short>();
        for (int i = 1; i <= 16; i++){
            boxes.add((short)i);
            for(Medicine med : medicines)
                if(i==(int)med.getMed_box_no())
                    boxes.removeLast();
        }
        short boxNo = boxes.remove((int) faker.random().nextInt(0, boxes.size() - 1));

        String medicineName = faker.medical().medicineName().toLowerCase();
        while(medicineRepository.findMedicineByMedName(medicineName).isPresent())
            medicineName = faker.medical().medicineName().toLowerCase();

        short amount = 0;
        boolean endsWith0=true;
        while(endsWith0){
            amount = (short)(int)(faker.random().nextInt(20, 50));
            if(amount%10 == 0) endsWith0=false;
        }
        return new Medicine(medicineName, boxNo, amount);
    }



}
