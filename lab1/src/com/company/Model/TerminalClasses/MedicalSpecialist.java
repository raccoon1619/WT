package com.company.Model.TerminalClasses;

import com.company.Model.Enums.ProcedureType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MedicalSpecialist implements Comparable<MedicalSpecialist>, Serializable {
    private static int lastId;
    private int id;
    private List<MedicalProcedure> procedures;
    private String firstName;
    private String secondName;
    private ProcedureType procedureType;

    public void setProcedures(List<MedicalProcedure> procedures) {
        this.procedures = procedures;
    }

    public MedicalSpecialist() {
        this.procedures = new ArrayList<MedicalProcedure>();
        this.id = ++lastId;
    }

    public MedicalSpecialist(String firstName, String secondName, ProcedureType speciality) {
        this();
        this.firstName = firstName;
        this.secondName = secondName;
        this.procedureType = speciality;
    }

    public MedicalSpecialist(String firstName, String secondName) {
        this();
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public ProcedureType getProcedureType() {
        return procedureType;
    }
    public void setProcedureType(ProcedureType procedureType) {
        this.procedureType = procedureType;
    }
    public List<MedicalProcedure> getProcedures() {
        return procedures;
    }

    public void addProcedure(MedicalProcedure medicalProcedure){
        if(medicalProcedure == null){
            throw new NullPointerException("Medical procedure can't be null!");
        }

        this.procedures.add(medicalProcedure);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(MedicalSpecialist medicalSpecialist) {

        int compareRes = this.firstName.compareTo(medicalSpecialist.firstName);
        if(compareRes == 0){

            compareRes = this.secondName.compareTo(medicalSpecialist.secondName);
            if(compareRes == 0){
                return 0; // TODO: to add sorting critiries!
            }

            return compareRes;
        }

        return compareRes;
    }

    @Override
    public String toString() {
        return this.id + " => " + this.firstName + " " + this.secondName + " " + this.procedureType.toString();
    }
}
