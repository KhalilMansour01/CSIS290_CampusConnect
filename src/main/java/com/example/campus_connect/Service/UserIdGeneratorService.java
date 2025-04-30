package com.example.campus_connect.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.campus_connect.Entity.UserIdCounter;
import com.example.campus_connect.Repository.UserIdCounterRepository;

@Service
public class UserIdGeneratorService {
    @Autowired
    private UserIdCounterRepository counterRepository;

    @Transactional
    public String generateUserId() {
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy")); // e.g., "25"

        UserIdCounter counter = counterRepository.findById(year).orElseGet(() -> {
            UserIdCounter newCounter = new UserIdCounter();
            newCounter.setYearSuffix(year);
            newCounter.setLastNumber(0);
            return newCounter;
        });

        int nextNumber = counter.getLastNumber() + 1;
        counter.setLastNumber(nextNumber);
        counterRepository.save(counter);

        String paddedNumber = String.format("%04d", nextNumber); // 0001, 0002, etc.
        return "USR" + year + paddedNumber;
    }
}
