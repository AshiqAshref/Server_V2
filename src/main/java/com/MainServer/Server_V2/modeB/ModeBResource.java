package com.MainServer.Server_V2.modeB;


import com.MainServer.Server_V2.modeB.model.Medicine;
import com.MainServer.Server_V2.modeB.service.ReminderBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modeB")
public class ModeBResource {
    private final ReminderBService reminderBService;
    public ModeBResource(ReminderBService reminderBService){
        this.reminderBService = reminderBService;
    }

    @PostMapping("/add")
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine){
        Medicine newMedicine = reminderBService.addMedicine(medicine);
        return new ResponseEntity<>(medicine, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        reminderBService.addRandom(2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
