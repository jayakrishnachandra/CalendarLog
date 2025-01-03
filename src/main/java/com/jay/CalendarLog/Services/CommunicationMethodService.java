package com.jay.CalendarLog.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.CalendarLog.Models.CommunicationMethod;

import com.jay.CalendarLog.Repositories.CommunicationMethodRepo;

@Service
public class CommunicationMethodService {
    @Autowired
    private CommunicationMethodRepo communicationMethodRepo;

    public CommunicationMethod save(CommunicationMethod communicationMethod)
    {
        return communicationMethodRepo.save(communicationMethod);
    }

    public List<CommunicationMethod> findAll() {
        return communicationMethodRepo.findAll();
    }

    public String delete(String name) {
        return communicationMethodRepo.deleteByName(name);
     }

     public Optional<CommunicationMethod> findMethodByname(String name)
    {
        return communicationMethodRepo.findByName(name);
    }

}
