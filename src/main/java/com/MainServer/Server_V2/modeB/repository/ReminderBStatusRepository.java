package com.MainServer.Server_V2.modeB.repository;

import com.MainServer.Server_V2.modeB.model.ReminderBStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderBStatusRepository extends JpaRepository<ReminderBStatus,Long> {

}
