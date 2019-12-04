package com.company.Model.Logic.Comparators;

import com.company.Model.Enums.SortCriteria;
import com.company.Model.TerminalClasses.Patient;

import java.util.Comparator;

public class PatientComparator implements Comparator<Patient> {
    private SortCriteria sortCriteria;

    public PatientComparator(){
        this.sortCriteria = SortCriteria.NAME;
    }

    public PatientComparator(SortCriteria sortCriteria){
        this.sortCriteria = sortCriteria;
    }

    public SortCriteria getSortCriteria() {
        return sortCriteria;
    }

    public void setSortCriteria(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    @Override
    public int compare(Patient patient1, Patient patient2) {
        if(patient1 == patient2){
            return 0;
        }

        switch(sortCriteria){
            case SURNAME:
                return patient1.getSecondName().compareTo(patient2.getSecondName());
            case AGE:
                return (patient1.getAge() == patient2.getAge()) ? 0 : (patient1.getAge() > patient2.getAge() ? 1 : -1);
            case ID:
                return (patient1.getPatientId() == patient2.getPatientId()) ? 0 :
                            (patient1.getPatientId() > patient2.getPatientId() ? 1 : -1);
            default:
                return patient1.getFirstName().compareTo(patient2.getFirstName());
        }
    }

    @Override
    public Comparator<Patient> reversed() {
        PatientComparator parent = this;

        return new Comparator<Patient>() {
            @Override
            public int compare(Patient patient1, Patient patient2) {
                return -1 * parent.compare(patient1, patient2);
            }
        };
    }
}
