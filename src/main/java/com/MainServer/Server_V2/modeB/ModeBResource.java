package com.MainServer.Server_V2.modeB;


import com.MainServer.Server_V2.modeA.model.ReminderA;
import com.MainServer.Server_V2.modeB.model.Medicine;
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


    @GetMapping("medicine/all")
    public ResponseEntity<List<Medicine>> getAllmedicines(){
        return new ResponseEntity<List<Medicine>>(reminderBService.getAllMedicines(), HttpStatus.OK);
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


    @GetMapping("/test/random/add/medicine/{numberOfRecords}")
    public ResponseEntity<List<Medicine>> addRandomMedicines(@PathVariable("numberOfRecords") int numberOfRecords){
        List<Medicine> addedRandomMedicines = reminderBService.addRandomMedicines(numberOfRecords);
        return new ResponseEntity<List<Medicine>>(addedRandomMedicines,HttpStatus.OK);
    }

    @GetMapping("/test/random/generate/medicine/{numberOfRecords}")
    public ResponseEntity<List<Medicine>> generateRandomMedicines(@PathVariable("numberOfRecords") int numberOfRecords){
        List<Medicine> generatedMedicineList = reminderBService.generateRandomMedicines(numberOfRecords);
        return new ResponseEntity<List<Medicine>>(generatedMedicineList,HttpStatus.OK);
    }

}
