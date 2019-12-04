package com.company.Model.Logic;

import com.company.Model.TerminalClasses.Hospital;
import com.company.Model.TerminalClasses.MedicalProcedure;
import com.company.Model.TerminalClasses.MedicalSpecialist;
import com.company.Model.TerminalClasses.Patient;

public class AppointmentManager {
    private Hospital hospital;

    public AppointmentManager(Hospital hospital){
        this.hospital = hospital;
    }
    
    public void makeAnAppointment(Patient patient, MedicalProcedure medicalProcedure) throws Exception {
        MedicalSpecialist medSpec = medicalProcedure.getDoctor();
        try{
            medSpec.addProcedure(medicalProcedure);
        } catch(Exception e){
            throw e;
        }

        patient.addMedicalProcedure(medicalProcedure);
        this.hospital.addMedicalProcedure(medicalProcedure);
    }

}
