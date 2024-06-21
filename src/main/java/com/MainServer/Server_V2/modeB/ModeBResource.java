package com.MainServer.Server_V2.modeB;

import com.MainServer.Server_V2.modeB.model.Medicine;
import com.MainServer.Server_V2.modeB.model.ReminderB;
import com.MainServer.Server_V2.modeB.model.view.ReminderView;
import com.MainServer.Server_V2.modeB.service.ReminderBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modeB")
public class ModeBResource {
    private final ReminderBService reminderBService;
    public ModeBResource(ReminderBService reminderBService){
        this.reminderBService = reminderBService;
    }


    @GetMapping("reminders/all")
    public ResponseEntity<List<ReminderView>> getAllReminders(){
        return new ResponseEntity<>(reminderBService.getAllReminders(), HttpStatus.OK);
    }
    @PostMapping("reminder/add")
    public ResponseEntity<ReminderView> addReminder(@RequestBody ReminderView reminderView){
        return new ResponseEntity<>(reminderBService.addReminder(reminderView), HttpStatus.OK);
    }
    @PutMapping("reminder/update")
    public ResponseEntity<ReminderView> updateReminder(@RequestBody ReminderView reminderView){
        return new ResponseEntity<>(reminderBService.updateReminder(reminderView), HttpStatus.OK);
    }
    @DeleteMapping("reminder/delete/{medId},{timeId}")
    public ResponseEntity<?> deleteReminder(@PathVariable("medId") long medId, @PathVariable("timeId") long timeId){
        reminderBService.deleteReminder(medId,timeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("medicines/all")
    public ResponseEntity<List<Medicine>> getAllmedicines(){
        return new ResponseEntity<>(reminderBService.getAllMedicines(), HttpStatus.OK);
    }

    @PostMapping("medicine/add")
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine){
        Medicine newMedicine = reminderBService.addMedicine(medicine);
        return new ResponseEntity<>(newMedicine, HttpStatus.CREATED);
    }

    @PutMapping("medicine/update")
    public ResponseEntity<Medicine> updateMedicine(@RequestBody Medicine medicine){
        Medicine updateMedicine = reminderBService.updateMedicine(medicine);
        return new ResponseEntity<>(updateMedicine, HttpStatus.OK);
    }

    @DeleteMapping("medicine/delete/{id}")
    public ResponseEntity<?> deleteMedicine(@PathVariable("id") long id){
        reminderBService.deleteMedicine(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
