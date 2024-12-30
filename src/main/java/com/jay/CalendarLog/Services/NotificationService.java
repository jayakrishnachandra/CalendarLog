package com.jay.CalendarLog.Services;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.CalendarLog.Models.CommunicationLog;
import com.jay.CalendarLog.Repositories.CommunicationLogRepo;

@Service
public class NotificationService {

    @Autowired
    private CommunicationLogRepo communicationLogRepo;

    public List<CommunicationLog> getOverdueCommunications(String email) {
        LocalDate today = LocalDate.now();
        return communicationLogRepo.findByEmailAndCommunicationDateBeforeAndCompletedFalse(email, today);
    }

    public List<CommunicationLog> getTodayCommunications(String email) {
        LocalDate today = LocalDate.now();
        return communicationLogRepo.findByEmailAndCommunicationDateAndCompletedFalse(email, today);
    }

    public CommunicationLog markAsCompleted(String logId) {
        Optional<CommunicationLog> logOptional = communicationLogRepo.findByLogId(logId);
        if (logOptional.isPresent()) {
            CommunicationLog log = logOptional.get();
            log.setCompleted(true);
            return communicationLogRepo.save(log);
        }
        return null; 
    }
}

