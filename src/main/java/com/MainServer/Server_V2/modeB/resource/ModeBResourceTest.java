package com.MainServer.Server_V2.modeB.resource;

import com.MainServer.Server_V2.modeB.model.Medicine;
import com.MainServer.Server_V2.modeB.service.ReminderBServiceTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/modeB")
public class ModeBResourceTest {
    private final ReminderBServiceTest reminderBServiceTest;
    public ModeBResourceTest(ReminderBServiceTest reminderBService){
        this.reminderBServiceTest = reminderBService;
    }


    @GetMapping("/test/random/add/medicine/{numberOfRecords}")
    public ResponseEntity<List<Medicine>> addRandomMedicines(@PathVariable("numberOfRecords") int numberOfRecords){
        List<Medicine> addedRandomMedicines = reminderBServiceTest.addRandomMedicines(numberOfRecords);
        return new ResponseEntity<List<Medicine>>(addedRandomMedicines, HttpStatus.OK);
    }

    @GetMapping("/test/random/add/reminders/{numberOfMedicineRecords},{numberOfTimeRecords}")
    public ResponseEntity<List<Medicine>> addRandomRemidners(
            @PathVariable("numberOfMedicineRecords") int numberOfMedicineRecords,
            @PathVariable("numberOfTimeRecords") int numberOfTimeRecords){
        List<Medicine> addedRandomReminders = reminderBServiceTest.addRandomRemindersToDatabase(numberOfMedicineRecords, numberOfTimeRecords);
        return new ResponseEntity<List<Medicine>>(addedRandomReminders,HttpStatus.OK);
    }

    @GetMapping("/test/random/generate/medicine/{numberOfRecords}")
    public ResponseEntity<List<Medicine>> generateRandomMedicines(@PathVariable("numberOfRecords") int numberOfRecords){
        List<Medicine> generatedMedicineList = reminderBServiceTest.generateRandomMedicines(numberOfRecords);
        return new ResponseEntity<List<Medicine>>(generatedMedicineList,HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        System.out.println("GOT FROM TEST");
        reminderBServiceTest.test(2);
        return new ResponseEntity<>("fuckuBitch", HttpStatus.OK);
    }
}