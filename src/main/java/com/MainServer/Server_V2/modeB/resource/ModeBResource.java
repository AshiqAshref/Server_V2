package com.MainServer.Server_V2.modeB.resource;

import com.MainServer.Server_V2.modeB.model.Medicine;
import com.MainServer.Server_V2.modeB.model.RevisionNumberModeB;
import com.MainServer.Server_V2.modeB.model.view.websiteView.ReminderView;
import com.MainServer.Server_V2.modeB.service.ReminderBService;
import com.MainServer.Server_V2.modeB.service.RevisionNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modeB")
public class ModeBResource {
    private final ReminderBService reminderBService;
    private final RevisionNumberService revisionNumberService;
    public ModeBResource(ReminderBService reminderBService, RevisionNumberService revisionNumberService){
        this.reminderBService = reminderBService;
        this.revisionNumberService = revisionNumberService;
    }


    @GetMapping("reminders/all")
    public ResponseEntity<List<ReminderView>> getAllReminders(){
        return new ResponseEntity<>(reminderBService.getAllReminders(), HttpStatus.OK);
    }
    @PostMapping("reminder/add")
    public ResponseEntity<ReminderView> addReminder(@RequestBody ReminderView reminderView){
        System.out.println(reminderView);
        ReminderView reminder = reminderBService.addReminder(reminderView);
        System.out.println(reminder);
        return new ResponseEntity<>(reminder, HttpStatus.OK);
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

    @GetMapping("revision_no")
    public ResponseEntity<RevisionNumberModeB> getRevisionNo(){
        return new ResponseEntity<>(revisionNumberService.getRevisionNumber(), HttpStatus.OK);
    }


}