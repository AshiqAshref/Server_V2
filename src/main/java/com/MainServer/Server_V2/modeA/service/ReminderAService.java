package com.MainServer.Server_V2.modeA.service;

import com.MainServer.Server_V2.exception.DuplicateValueException;
import com.MainServer.Server_V2.exception.ReminderNotFoundException;
import com.MainServer.Server_V2.modeA.model.ReminderA;
import com.MainServer.Server_V2.modeA.repository.ReminderARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class ReminderAService {
    public  ReminderARepository reminderARepository;

    @Autowired
    public ReminderAService(ReminderARepository reminderARepository) {
        this.reminderARepository = reminderARepository;
    }

    public void addRandom(int numberOfValues){
        List<Short> boxes = new ArrayList<Short>();
        for (int i = 1; i <= 16; i++)
            boxes.add((short)i);

        Random random = new Random();
        for (int i = 0; i < numberOfValues; i++) {
            short boxNo = boxes.remove(random.nextInt(0, boxes.size()-1));
            String randomTime = getRandomTime();
            reminderARepository.save(new ReminderA(randomTime,boxNo));
        }
    }


    public Iterable<ReminderA> getAll(){
        return reminderARepository.findAllByOrderByTimeAsc();
    }


    public  ReminderA addReminderA(ReminderA reminderA){
        checkIfReminderAGood(reminderA);
        return reminderARepository.save(reminderA);
    }


    public ReminderA updateReminderA(ReminderA reminderA){
        checkIfReminderAGood(reminderA);
        return reminderARepository.save(reminderA);
    }


    public void deleteReminder(Long id){
        reminderARepository.deleteById(id);
    }


    public ReminderA getReminderById(long id){
        return reminderARepository.getReminderAById(id)
                .orElseThrow(()->
                        new ReminderNotFoundException("Reminder by id "+id+" not Found" )
                );
    }


    private void checkIfReminderAGood(ReminderA reminderA) throws DuplicateValueException {
        if(reminderARepository.findByTime(reminderA.getTime()).isPresent())
            throw new DuplicateValueException("No Time Fuck U");
        if(reminderARepository.findByBoxNo(reminderA.getBoxNo()).isPresent())
            throw new DuplicateValueException("No Box Fuck U");
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