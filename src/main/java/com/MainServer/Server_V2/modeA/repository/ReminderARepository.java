package com.MainServer.Server_V2.modeA.repository;

import com.MainServer.Server_V2.modeA.model.ReminderA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReminderARepository extends JpaRepository<ReminderA, Long> {
    Optional<ReminderA> findByTime(String remaTime);
    Optional<ReminderA> getReminderAById(long remaId);
    Iterable<ReminderA> findAllByOrderByTimeAsc();
    Optional<ReminderA> findByBoxNo(short boxNo);
}

