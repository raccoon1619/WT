package com.company.Model.TerminalClasses;

import com.company.Model.Enums.ProcedureType;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MedicalProcedure  implements Comparable<MedicalProcedure>, Serializable {
    private int procedureId; // TODO:  <===== fix this static field!
    private static int currentAvelilableIndex;
    private LocalDateTime date;
    private MedicalSpecialist doctor;
    private boolean isPaid;
    private double price;
    private Patient patient;
    private int cabinetId;
    private ProcedureType type;

    public MedicalProcedure(){ procedureId = currentAvelilableIndex++; }
    public MedicalProcedure(int procedureId, LocalDateTime date, MedicalSpecialist doctor, double price,
                            int cabinetId, ProcedureType type) {
        this.procedureId = procedureId;
        this.date = date;
        this.doctor = doctor;
        this.price = price;
        this.cabinetId = cabinetId;
        this.type = type;
    }

    public MedicalProcedure(MedicalSpecialist specialist, Patient patient, LocalDateTime date, int cabinetId,
                            ProcedureType procedureType){
        this();
        this.patient = patient;
        this.doctor = specialist;
        this.date = date;
        this.cabinetId = cabinetId;
        this.type = procedureType;
    }

    public ProcedureType getType() {
        return type;
    }
    public void setType(ProcedureType type) {
        this.type = type;
    }
    public boolean isPaid() {
        return isPaid;
    }
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        price = price;
    }
    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }
    public MedicalSpecialist getDoctor() {
        return doctor;
    }
    public void setDoctor(MedicalSpecialist doctor) {
        this.doctor = doctor;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public int getCabinetId() {
        return cabinetId;
    }
    public void setCabinetId(int cabinetId) {
        this.cabinetId = cabinetId;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime time) throws IllegalArgumentException {
        if(time == null) {
            throw new IllegalArgumentException("date can't be null");
        }

        this.date = time;
    }
    public int getProcedureId() {
        return procedureId;
    }

    @Override
    public int compareTo(MedicalProcedure medicalProcedure) {
        int compareRes;
        if(this.procedureId == medicalProcedure.procedureId) {

            compareRes = this.type.compareTo(medicalProcedure.type);
            if(compareRes == 0){
                return this.date.compareTo(medicalProcedure.date);
            }

            return compareRes;
        }
        return (this.procedureId > medicalProcedure.procedureId) ? 1 : 0;
    }

    @Override
    public String toString() {
        return this.procedureId + " => " + this.getDate().toString() + " " + this.getCabinetId() + " " + this.price;
    }
}
