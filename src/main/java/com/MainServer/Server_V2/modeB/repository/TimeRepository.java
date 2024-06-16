package com.MainServer.Server_V2.modeB.repository;

import com.MainServer.Server_V2.modeB.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeRepository extends JpaRepository<Time,Long> {
    Optional<Time> findTimeByTimebTime(String timebTime);
}
