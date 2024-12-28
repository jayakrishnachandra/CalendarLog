package com.jay.CalendarLog.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jay.CalendarLog.Models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByEmail(String email);
}
