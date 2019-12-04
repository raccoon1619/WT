package com.company.Controller;

import com.company.Model.Logic.SystemManager;
import com.company.Model.TerminalClasses.Patient;

public class AccountInfoTracker {
    public AccountInfoTracker(){
    }

    public boolean deletePatient(Patient patient){
        return SystemManager.deletePatient(patient);
    }

    public boolean createPatient(Patient patient){
        return SystemManager.createPatient(patient);
    }

    public boolean updatePatient(Patient patient) {
        return SystemManager.updatePatientInfo(patient);
    }
}
