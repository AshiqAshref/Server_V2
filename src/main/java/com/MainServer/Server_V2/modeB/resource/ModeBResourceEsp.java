package com.MainServer.Server_V2.modeB.resource;

import com.MainServer.Server_V2.modeB.model.view.espServerView.BoxViewEsp;
import com.MainServer.Server_V2.modeB.model.view.espServerView.ReminderViewEsp;
import com.MainServer.Server_V2.modeB.service.ReminderBServiceEsp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/esp/modeb")
public class ModeBResourceEsp {
    private final ReminderBServiceEsp reminderBServiceEsp;

    public ModeBResourceEsp(ReminderBServiceEsp reminderBServiceEsp) {
        this.reminderBServiceEsp = reminderBServiceEsp;
    }
    @GetMapping("reminders/all")
    public ResponseEntity<List<ReminderViewEsp>> getAllReminders(){
        return new ResponseEntity<>(reminderBServiceEsp.getAllReminders(), HttpStatus.OK);
    }
    @GetMapping("boxes/all")
    public ResponseEntity<List<BoxViewEsp>> getAllBoxes(){
        return new ResponseEntity<>(reminderBServiceEsp.getAllBoxes(), HttpStatus.OK);
    }

    @PutMapping("reminder/update")
    public ResponseEntity<?> setSuccess(){
        reminderBServiceEsp.updateReminder(1L,2L);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
