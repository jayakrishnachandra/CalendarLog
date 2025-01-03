package com.jay.CalendarLog.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.jay.CalendarLog.Models.CommunicationLog;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface CommunicationLogRepo extends MongoRepository<CommunicationLog, String>{


        List<CommunicationLog> findByEmailAndCompletedFalse(String email);
        List<CommunicationLog> findByEmailAndCommunicationDateBeforeAndCompletedFalse(String email, LocalDate date);
        List<CommunicationLog> findByEmailAndCommunicationDateAndCompletedFalse(String email, LocalDate date);
        List<CommunicationLog> findByEmail(String email);
        Optional<CommunicationLog> findByLogId(String logId);
        List<CommunicationLog> findByEmailAndCompanyName(String email, String companyName);
            

}
