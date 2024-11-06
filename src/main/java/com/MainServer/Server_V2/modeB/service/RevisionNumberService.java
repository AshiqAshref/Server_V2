package com.MainServer.Server_V2.modeB.service;

import com.MainServer.Server_V2.modeB.repository.RevisionNumberModeBRepository;
import com.MainServer.Server_V2.modeB.model.RevisionNumberModeB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RevisionNumberService {
    private final RevisionNumberModeBRepository repository;

    @Autowired
    public RevisionNumberService(RevisionNumberModeBRepository repository) {
        this.repository = repository;
    }

    public int getValue() {
        return repository.findById(1)
                .map(RevisionNumberModeB::getRevisionNo)
                .orElseThrow(() -> new IllegalStateException("Singleton value row not found!"));
    }

    @Transactional
    public void updateValue() {
        RevisionNumberModeB revisionNumber = repository.findById(1)
                .orElseThrow(() -> new IllegalStateException("Singleton value row not found!"));
        revisionNumber.updateRevisionValue();
        repository.save(revisionNumber);
    }

    public RevisionNumberModeB getRevisionNumber(){
        return repository.findById(1).get();
    }
}
