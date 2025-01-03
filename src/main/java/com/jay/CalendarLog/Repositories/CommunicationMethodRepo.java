package com.jay.CalendarLog.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jay.CalendarLog.Models.CommunicationMethod;
import com.jay.CalendarLog.Models.Company;

@Repository
public interface CommunicationMethodRepo extends MongoRepository<CommunicationMethod, String>{
     List<CommunicationMethod> findAllByOrderBySequenceAsc();
     String deleteByName(String name);
     Optional<CommunicationMethod> findByName(String name);
}
