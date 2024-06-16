package com.MainServer.Server_V2.modeA;


import com.MainServer.Server_V2.modeA.model.ReminderA;
import com.MainServer.Server_V2.modeA.service.ReminderAService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modeA")
public class ModeAResource {
    private final ReminderAService reminderAService;
    public ModeAResource(ReminderAService reminderAService){
        this.reminderAService = reminderAService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<ReminderA>> getAllReminders(){
        Iterable<ReminderA> reminders = reminderAService.getAll();
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }



    @GetMapping("/find/{id}")
    public ResponseEntity<ReminderA> getReminderById(@PathVariable("id") long id){
        ReminderA reminder = reminderAService.getReminderById(id);
        return new ResponseEntity<>(reminder, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ReminderA> addReminder(@RequestBody ReminderA reminderA){
        ReminderA newReminder = reminderAService.addReminderA(reminderA);
        return new ResponseEntity<>(newReminder, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ReminderA> updateReminder(@RequestBody ReminderA reminderA){
        ReminderA updateReminder = reminderAService.updateReminderA(reminderA);
        return new ResponseEntity<>(updateReminder, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReminder(@PathVariable("id") long id){
        reminderAService.deleteReminder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}