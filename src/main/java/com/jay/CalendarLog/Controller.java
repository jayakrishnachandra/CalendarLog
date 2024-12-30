package com.jay.CalendarLog;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jay.CalendarLog.Models.CommunicationLog;
import com.jay.CalendarLog.Models.CommunicationMethod;
import com.jay.CalendarLog.Models.Company;
import com.jay.CalendarLog.Models.Token;
import com.jay.CalendarLog.Models.User;
import com.jay.CalendarLog.Services.CommunicationLogService;
import com.jay.CalendarLog.Services.CommunicationMethodService;
import com.jay.CalendarLog.Services.UserService;

import jakarta.servlet.http.HttpServletRequest;

import com.jay.CalendarLog.Services.CompanyService;
import com.jay.CalendarLog.Services.NotificationService;
import com.jay.CalendarLog.Services.TokenService;


@CrossOrigin(origins = "*") 
@RestController
public class Controller {
    @Autowired
    private CommunicationLogService communicationLogService;
    @Autowired
    private CommunicationMethodService communicationMethodService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private NotificationService notificationService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Optional<Token> validateToken(HttpServletRequest req)
    {
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer "))
        {
            String token = authHeader.substring(7);
            return tokenService.findByToken(token);
        }
        return Optional.empty();
    }
    
    @RequestMapping(path = "/login")
    public String user(@RequestBody User us) {
        List<User> users = userService.findByEmail(us.email);
        if (users.isEmpty()) {
            return "Authentication Failed: User not found";
        }
        User u = users.get(0);
        if (u != null && passwordEncoder.matches(us.password, u.password)) { // Use BCrypt's matches method
            Optional<Token> existingToken = tokenService.findByEmail(us.email);
            if (existingToken.isPresent() && !tokenService.isTokenExpired(existingToken.get())) {
                return "Authentication : " + existingToken.get().getToken();
            } else {
                Token t = new Token(us.email);
                tokenService.save(t);
                return "Authentication : " + t.token;
            }
        }
        return "Authentication Failed";
    }

    @RequestMapping(path = "/register")
    public String registerUser(@RequestBody User us) {
        for (User u : userService.findall()) {
            if (u.email.equals(us.email)) {
                return "User already exists";
            }
        }
        // Hash the password before saving the user
        us.password = passwordEncoder.encode(us.password);
        userService.save(us);

        Token t = new Token(us.email);
        tokenService.save(t);
        return "Authorization : " + t.token;
    }

    
    @RequestMapping(path = "/userData")
    public ResponseEntity<User> getUserData(HttpServletRequest req)
    {
        Optional<Token> dbToken = validateToken(req);
        if (dbToken.isPresent() && !tokenService.isTokenExpired(dbToken.get()))
        {
            String email = dbToken.get().getEmail();
            List<User> users = userService.findByEmail(email);
            return ResponseEntity.ok(users.get(0));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @PostMapping(path = "/addCommunicationLog")
    public ResponseEntity<Object> addCommunicationLog(HttpServletRequest req, @RequestBody CommunicationLog log) {
    // Validate the token
    Optional<Token> dbToken = validateToken(req);
    
    if (dbToken.isPresent() && !tokenService.isTokenExpired(dbToken.get())) {
        String email = dbToken.get().getEmail();
        log.setEmail(email); // Setting the email of the user to the log (optional)
        CommunicationLog savedLog = communicationLogService.addCommunicationLog(log);
        return ResponseEntity.ok(savedLog);
    }

    // If the token is invalid or expired, return an Unauthorized response
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
}
@GetMapping("/allLogs")
public ResponseEntity<List<CommunicationLog>> getAllCommunicationLogs(HttpServletRequest req) {
    Optional<Token> dbToken = validateToken(req);
    
    if (dbToken.isPresent()) {
        Token token = dbToken.get();
        if (!tokenService.isTokenExpired(token)) {
            String email = token.getEmail();
            List<CommunicationLog> logs = communicationLogService.findByEmail(email);
            return ResponseEntity.ok(logs);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
    } 
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.emptyList());
    }


    @PostMapping(path = "/addCompany")
    public  ResponseEntity<Object> addCompany(HttpServletRequest req, @RequestBody Company company)
    {
        Optional<Token> dbToken = validateToken(req);
        if (dbToken.isPresent() && !tokenService.isTokenExpired(dbToken.get()))
        {
            return ResponseEntity.ok(companyService.save(company));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @DeleteMapping(path = "/deleteCompany/{name}")
    public ResponseEntity<Object> deleteCompany(HttpServletRequest req, @PathVariable String name) {
    Optional<Token> dbToken = validateToken(req);
    
    if (dbToken.isPresent() && !tokenService.isTokenExpired(dbToken.get())) {
        Optional<Company> existingCompany = companyService.findCompanyByname(name);
        if (existingCompany.isPresent()) {
            companyService.delete(name);
            
            return ResponseEntity.status(HttpStatus.OK).body("Company deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
}


    @PostMapping(path = "/addCommunicationMethod")
    public ResponseEntity<Object> addCommunicationMethod(HttpServletRequest req, @RequestBody CommunicationMethod method)
    {
        Optional<Token> dbToken = validateToken(req);
        if (dbToken.isPresent() && !tokenService.isTokenExpired(dbToken.get()))
        {
            return ResponseEntity.ok(communicationMethodService.save(method));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    
    

    @GetMapping("/company-method-names")
    public Map<String, List<String>> getCompanyAndMethodNames() {
        List<CommunicationMethod> methods = communicationMethodService.findAll();
        List<Company> companies = companyService.findAll();
        return Map.of(
            "companyNames", companies.stream().map(Company::getName).collect(Collectors.toList()),
            "methodNames", methods.stream().map(CommunicationMethod::getName).collect(Collectors.toList())
        );
    }

   @GetMapping("/overdue")
public ResponseEntity<List<CommunicationLog>> getOverdueCommunications(HttpServletRequest req) {
    Optional<Token> dbToken = validateToken(req);
    
    if (dbToken.isPresent()) {
        Token token = dbToken.get();
        if (!tokenService.isTokenExpired(token)) {
            String email = token.getEmail();
            List<CommunicationLog> overdue = notificationService.getOverdueCommunications(email);
            return ResponseEntity.ok(overdue);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
    } 
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.emptyList());
    }


    @GetMapping("/today")
    public ResponseEntity<List<CommunicationLog>> getTodayCommunications(@RequestParam String email) {
        List<CommunicationLog> today = notificationService.getTodayCommunications(email);
        return ResponseEntity.ok(today);
    }

    @PutMapping("/markCompleted/{LogId}")
    public ResponseEntity<Object>  markAsCompleted(@PathVariable String LogId, HttpServletRequest req) {
    Optional<Token> dbToken = validateToken(req);
    if (dbToken.isPresent() && !tokenService.isTokenExpired(dbToken.get())) {
        return ResponseEntity.ok(notificationService.markAsCompleted(LogId));

    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
   
}
    @GetMapping("/badge-count")
    public ResponseEntity<Map<String, Integer>> getBadgeCount(HttpServletRequest req) {
        Optional<Token> dbToken = validateToken(req);
        if (dbToken.isPresent()) {
            Token token = dbToken.get();
            if (!tokenService.isTokenExpired(token)) {
                String email = token.getEmail();
            int overdueCount = notificationService.getOverdueCommunications(email).size();
            int todayCount = notificationService.getTodayCommunications(email).size();
            Map<String, Integer> counts = new HashMap<>();
            counts.put("overdue", overdueCount);
            counts.put("today", todayCount);
            return ResponseEntity.ok(counts);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}