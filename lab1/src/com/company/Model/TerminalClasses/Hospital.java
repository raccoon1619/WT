package com.company.Model.TerminalClasses;

import com.company.Model.Enums.ProcedureType;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private List<Patient> patientsList;
    private List<MedicalSpecialist> medicalSpecialistsList;
    private List<MedicalProcedure> medicalProceduresList;

    public Hospital() {
        this.medicalProceduresList = new ArrayList<MedicalProcedure>();
        this.medicalSpecialistsList = new ArrayList<MedicalSpecialist>();
        this.patientsList = new ArrayList<Patient>();
    }

    public void removePatient(Patient patient) throws IllegalArgumentException {
        if(!patientsList.contains(patient)){
            throw new IllegalArgumentException("Can't find this patient!");
        }

        this.patientsList.remove(patient);
    }
    public void removePatient(int patientId) throws IllegalArgumentException {
        Patient patient = findPatient(patientId);
        if(patient == null){
            throw new IllegalArgumentException("There is no patient with " + patientId + " id!");
        }

        this.patientsList.remove(patient);
    }
    public Patient findPatient(int patientId) {
        for(Patient patient : patientsList){
            if(patient.getPatientId() == patientId){
                return patient;
            }
        }

        return null;
    }
    public void addPatient(Patient patient){
        this.patientsList.add(patient);
    }
    public List<Patient> findPatient(String name, String surname){
        List<Patient> result = new ArrayList<Patient>();
        for(Patient patient : patientsList){
            if((patient.getFirstName().compareTo(name) == 0) && (patient.getSecondName().compareTo(surname) == 0)) {
                result.add(patient);
            }
        }

        return result;
    }

    public List<Patient> getPatientsList() {
        return patientsList;
    }
    public void setPatientsList(List<Patient> patientsList) {
        this.patientsList = patientsList;
    }
    public List<MedicalSpecialist> getMedicalSpecialistsList() {
        return medicalSpecialistsList;
    }
    public void setMedicalSpecialistsList(List<MedicalSpecialist> medicalSpecialistsList) {
        this.medicalSpecialistsList = medicalSpecialistsList;
    }
    public List<MedicalProcedure> getMedicalProceduresList() {
        return medicalProceduresList;
    }
    public void setMedicalProceduresList(List<MedicalProcedure> medicalProceduresList) {
        this.medicalProceduresList = medicalProceduresList;
    }


    public void addMedicalProcedure(MedicalProcedure medicalProcedure) {
        if(medicalProcedure == null){
            throw new NullPointerException("medical procedure can't be null!");
        }

        this.medicalProceduresList.add(medicalProcedure);
    }

    public List<MedicalSpecialist> getMedicalSpecialistsByCriteria(ProcedureType procType){
        List<MedicalSpecialist> result = new ArrayList<MedicalSpecialist>();

        for(MedicalSpecialist medSpec : this.medicalSpecialistsList){
            if(medSpec.getProcedureType().compareTo(procType) == 0){
                result.add(medSpec);
            }
        }

        return result;
    }

    public void addMedicalSpecilist(MedicalSpecialist medicalSpecialist){
        if(medicalSpecialist == null){
            throw new NullPointerException("Medical specilist can't be null");
        }

        this.medicalSpecialistsList.add(medicalSpecialist);
    }

    public MedicalSpecialist findMedicalSpecialist(int id){
        if(id > this.medicalSpecialistsList.size() || (id < 0)){
            return null;
        }

        return this.medicalSpecialistsList.get(id - 1);
    }
}
