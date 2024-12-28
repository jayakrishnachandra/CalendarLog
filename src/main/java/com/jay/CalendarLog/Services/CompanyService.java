package com.jay.CalendarLog.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.CalendarLog.Models.Company;
import com.jay.CalendarLog.Repositories.CompanyRepo;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepo companyRepo;

    public Company save(Company company)
    {
        return companyRepo.save(company);
    }

    public List<Company> findAll() {
       return companyRepo.findAll();
    }

    public Optional<Company> findCompanyByname(String name)
    {
        return companyRepo.findByName(name);
    }

    public String delete(String name) {
       return companyRepo.deleteByName(name);
    }
}
