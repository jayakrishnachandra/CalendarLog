package com.jay.CalendarLog.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jay.CalendarLog.Models.CommunicationLog;

import java.util.List;


@Repository
public interface CommunicationLogRepo extends MongoRepository<CommunicationLog, String>{

    List<CommunicationLog> findByEmail(String companyId);

}
