package com.company.View;

import com.company.Model.Enums.ProcedureType;
import com.company.Model.TerminalClasses.Hospital;
import com.company.Model.TerminalClasses.MedicalSpecialist;
import com.company.Model.TerminalClasses.Patient;

import java.util.*;

public final class HospitalViewer {
    private HospitalViewer(){

    }

    public static void printPatients(Hospital hospital){
        for(Patient patient : hospital.getPatientsList()){
            System.out.println(patient.toString());
        }
    }

    public static void printMedicalSpecialist(Hospital hospital, String separator){
        for(MedicalSpecialist medSpec : hospital.getMedicalSpecialistsList()){
            System.out.println(separator);
            System.out.println(medSpec.toString());
            System.out.println(separator);
        }
    }

    public static int printMedicalSpecialist(List<MedicalSpecialist> medSpecialists, String separator){
        int i = 0;
        for(MedicalSpecialist medSpec : medSpecialists){
            System.out.println(separator);
            System.out.println(medSpec.toString());
            System.out.println(separator);
            i++;
        }

        return i;
    }

    public static void printAllMedicalFields(){
        ProcedureType[] procedureTypes = ProcedureType.class.getEnumConstants();
        for(ProcedureType procedureType : procedureTypes){
            System.out.println(procedureType.ordinal() + ": " + procedureType.toString());
        }
    }
}
