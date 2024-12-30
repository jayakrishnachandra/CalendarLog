package com.jay.CalendarLog.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.CalendarLog.Models.CommunicationLog;
import com.jay.CalendarLog.Repositories.CommunicationLogRepo;

@Service
public class CommunicationLogService {
    @Autowired
    private CommunicationLogRepo communicationLogRepo;

    public CommunicationLog addCommunicationLog(CommunicationLog log)
    {
       return communicationLogRepo.save(log);
    }

    public List<CommunicationLog> findAllLogs(String email)
    {
       return communicationLogRepo.findByEmail(email);
    }

    public List<CommunicationLog> findByEmail(String email) {
      return communicationLogRepo.findByEmail(email);
    }

}
