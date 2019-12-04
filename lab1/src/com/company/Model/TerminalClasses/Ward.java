package com.company.Model.TerminalClasses;

import com.company.Model.Enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class Ward {
    private int id;
    private int capacity;
    private Gender gender;
    private List<Patient> patientList;

    public Ward() {
        this.patientList = new ArrayList<Patient>();
    }

    public Ward(int id, int capacity, Gender gender) throws IllegalArgumentException{
        this();
        this.setId(id);
        this.setCapacity(capacity);
        this.gender = gender;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void addPatient(Patient patient) throws Exception {
        if(patient == null){
            throw new IllegalArgumentException("NullPointerException");
        }

        if(capacity == 0){
            throw new Exception("Ward is full!");
        }

        this.patientList.add(patient);
        this.capacity--;

    }

    public boolean removePatient(Patient patient) throws IllegalArgumentException {
        if(!patientList.remove(patient)){
            throw new IllegalArgumentException("Cannot remove this patient! Cannot find him in this Ward");
        }

        this.capacity++;
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws IllegalArgumentException {
        if(id < 0){
            throw new IllegalArgumentException("Id of ward cant be lower than 0");
        }

        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if(capacity < 0){
            throw new IllegalArgumentException("Capacity of ward cant be lower than 0");
        }

        this.capacity = capacity;
    }
}
