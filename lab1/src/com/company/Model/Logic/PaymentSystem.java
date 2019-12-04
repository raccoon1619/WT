package com.company.Model.Logic;

import com.company.Model.TerminalClasses.MedicalProcedure;
import com.company.Model.TerminalClasses.Patient;

public class PaymentSystem {
    public boolean payFor(Patient patient, int procedureId){
        if((patient == null) || (procedureId < 0)){
            throw new RuntimeException("patient can't be null or procedure id lower than 0");
        }

        MedicalProcedure procedure = patient.getMedicalProcedures().get(procedureId);

        return substractSumm(patient, procedure);
    }

    private boolean substractSumm(Patient patient, MedicalProcedure procedure){
        double newSummOfMoney = (patient.getBalance() - procedure.getPrice());
        if(newSummOfMoney < 0){
            patient.setBalance(newSummOfMoney);
            return true;
        }

        return false;
    }
}
