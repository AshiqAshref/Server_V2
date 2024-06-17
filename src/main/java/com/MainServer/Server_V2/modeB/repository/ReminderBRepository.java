package com.MainServer.Server_V2.modeB.repository;

import com.MainServer.Server_V2.modeB.model.ReminderB;
import com.MainServer.Server_V2.modeB.model.ReminderbId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderBRepository extends JpaRepository<ReminderB, ReminderbId> {
//    Iterable<Medicine> find(Class<Medicine> medicineClass, ReminderbId reminderbId);
}
