package com.company.View;

import com.company.Model.TerminalClasses.MedicalProcedure;
import com.company.Model.TerminalClasses.Patient;

import java.util.List;

public class AccountViewer {
    public static void printPassInfo(Patient patient){
        System.out.println("name    - " + patient.getFirstName());
        System.out.println("surname - " + patient.getSecondName());
        System.out.println("id      - " + patient.getPatientId());
    }

    public static void printProceduresList(Patient patient){
        for(MedicalProcedure procedure : patient.getMedicalProcedures()){
            System.out.println(procedure.toString());
        }
    }

    public static void printProceduresList(List<MedicalProcedure> procedures, String separator){
        for(MedicalProcedure procedure : procedures){
            System.out.println(separator);
            System.out.println("id: " + procedure.getProcedureId());
            System.out.println("cabinet id " + procedure.getCabinetId());
            System.out.println("date: " + procedure.getDate()); // TODO: to add DateTime format!
            System.out.println("doctor: " + procedure.getDoctor().getFirstName() + " " +
                    procedure.getDoctor().getSecondName());
            System.out.println("price: " + procedure.getPrice());
            System.out.println(separator);
        }
    }

    public static void printPatients(List<Patient> patients){
        for(Patient patient : patients){
            System.out.println(Patient.getPassInfoStr(patient));
        }
    }

}
