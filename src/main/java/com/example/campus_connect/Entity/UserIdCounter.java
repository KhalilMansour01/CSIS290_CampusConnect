package com.example.campus_connect.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_id_counters")
public class UserIdCounter {
    @Id
    @Column(name = "year_suffix")
    private String yearSuffix;

    @Column(name = "last_number")
    private int lastNumber;


    public String getYearSuffix() {
        return yearSuffix;
    }

    public void setYearSuffix(String yearSuffix) {
        this.yearSuffix = yearSuffix;
    }

    public int getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }
}
