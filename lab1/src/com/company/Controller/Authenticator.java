package com.company.Controller;

import com.company.Model.Logic.SystemManager;
import com.company.Model.Enums.Gender;
import com.company.Model.TerminalClasses.Patient;

public class Authenticator {
    public Patient Registrate(String name, String surname, int age, Gender gender, int passId){
        Patient patient = new Patient(name, surname, age, gender, passId);
        if(SystemManager.createPatient(patient)){
            return patient;
        }

        return null;
    }

    public Patient LogIn(String name, String surName, int passId){
        return SystemManager.getExistingPatient(name, surName, passId);
    }
}
