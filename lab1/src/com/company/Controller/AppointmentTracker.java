package com.company.Controller;

import com.company.Model.Logic.AppointmentManager;
import com.company.Model.TerminalClasses.Hospital;
import com.company.Model.TerminalClasses.MedicalProcedure;
import com.company.Model.TerminalClasses.Patient;

public class AppointmentTracker {
    private Hospital hospital;

    public AppointmentTracker(Hospital hospital){
        this.hospital = hospital;
    }

    public void makeAnAppointment(Patient patient, MedicalProcedure medicalProcedure) throws Exception {
        AppointmentManager appMaker = new AppointmentManager(this.hospital);
        try {
            appMaker.makeAnAppointment(patient, medicalProcedure);
        } catch (Exception e) {
            throw e;
        }

    }

}
