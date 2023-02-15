package com.example.madrasaregister;

import androidx.annotation.NonNull;

public class Student {
    private Integer id;
    private String name;
    private String rollNo;
    private String joining;

    public Student(Integer id, String name, String rollNo, String joining) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.joining = joining;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getJoining() {
        return joining;
    }

    public void setJoining(String joining) {
        this.joining = joining;
    }

    @NonNull
    public String toString()
    {
        return getId()+": "+getName();
    }
}
