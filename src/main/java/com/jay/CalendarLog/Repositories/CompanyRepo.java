package com.jay.CalendarLog.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jay.CalendarLog.Models.Company;

@Repository
public interface CompanyRepo extends MongoRepository<Company, String>{
    List<Company> findAll();
    Optional<Company> findByName(String name);
    String deleteByName(String name);
}