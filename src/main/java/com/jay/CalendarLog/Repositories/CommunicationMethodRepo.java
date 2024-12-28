package com.jay.CalendarLog.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jay.CalendarLog.Models.CommunicationMethod;

@Repository
public interface CommunicationMethodRepo extends MongoRepository<CommunicationMethod, String>{
     List<CommunicationMethod> findAllByOrderBySequenceAsc();
}
