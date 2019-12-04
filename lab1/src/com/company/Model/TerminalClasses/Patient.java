package com.company.Model.TerminalClasses;

import com.company.Model.Enums.Gender;
import com.company.Model.Logic.SystemManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Patient implements Serializable, Comparable<Patient>{
    private String firstName;
    private String secondName;
    private int age;
    private double balance;
    private int patientId;
    private ArrayList<MedicalProcedure> medicalProcedures;
    private Gender gender;
    private boolean proceduresChanged;
    private boolean passInfoChanged;

    public Patient() {
        this.medicalProcedures = new ArrayList<MedicalProcedure>();
        this.balance = 0;
    }
    public Patient(String firstName, String secondName, int age, Gender gender, int passId){
        this();
        this.age = age;
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.patientId = passId;
    }

    public void removeProcedure(MedicalProcedure medicalProcedure){
        if(medicalProcedure == null){
            throw new NullPointerException("medical procedure can't be null!");
        }
        this.medicalProcedures.remove(medicalProcedure);
        SystemManager.updatePatientInfo(this);
    }

    public void setMedicalProcedures(List<MedicalProcedure> procedures){
        if(procedures == null) {
            throw new RuntimeException("Procedures can't be null!");
        }

        this.medicalProcedures = (ArrayList<MedicalProcedure>) procedures;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public boolean isProceduresChanged() {
        return proceduresChanged;
    }
    public void setProceduresChanged(boolean proceduresChanged) {
        this.proceduresChanged = proceduresChanged;
    }
    public boolean isPassInfoChanged() {
        return passInfoChanged;
    }
    public void setPassInfoChanged(boolean passInfoChanged) {
        this.passInfoChanged = passInfoChanged;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        if(firstName == null){
            return;
        }
        this.firstName = firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        if(secondName == null){
            return;
        }
        this.secondName = secondName;
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public List<MedicalProcedure> getMedicalProcedures() {
        return medicalProcedures;
    }

    public List<MedicalProcedure> getMedicalProcedures(int proceduresCount) {
        List<MedicalProcedure> result = new ArrayList<MedicalProcedure>();
        for(int i = 0; (i < proceduresCount) && (i < this.medicalProcedures.size()); i++){
            result.add(this.medicalProcedures.get(i));
        }

        return result;
    }

    public List<MedicalProcedure> getMedicalProcedures(int lastIndex, int proceduresCount) {
        if(lastIndex < 0){
            throw new IllegalArgumentException("lastIndex can't be lower than 0, current value: " + lastIndex);
        }

        List<MedicalProcedure> result = new ArrayList<MedicalProcedure>();
        for(int i = lastIndex; (i < proceduresCount) && (i < result.size()); i++){
            result.add(result.get(i));
        }

        return result;
    }

    public MedicalProcedure getMedicalProcedureById(int procedureId){
        try{
            return this.medicalProcedures.get(procedureId);
        } catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public void addMedicalProcedure(MedicalProcedure medicalProcedures) {
        this.medicalProcedures.add(medicalProcedures);
        SystemManager.updatePatientInfo(this);

    }
    public Gender getGender() {
        return gender;
    }
    public int getAge() {
        return age;
    }

    public static String getPassInfoStr(Patient patient){
        return patient.firstName + " " + patient.secondName + " " + patient.age + " " + patient.patientId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        return false;
    }

    @Override
    public int compareTo(Patient patient) {
        if(patient == this){
            return 0;
        }

        int compareRes = this.secondName.compareTo(patient.secondName);
        if (compareRes == 0) {

            compareRes = this.firstName.compareTo(patient.firstName);
            if (compareRes == 0) {

                if(this.patientId != patient.patientId){

                    if(this.patientId > patient.patientId){
                        return 1;
                    }

                    return 0;
                }

                return 0;
            }

            return compareRes;
        }

        return compareRes;
    }

    @Override
    public String toString() {
        return this.patientId + " => " + this.firstName + " " + this.secondName + " " + this.age +
                " " + this.gender.toString();
    }

}
