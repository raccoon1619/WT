
package com.company.Model.Logic.Comparators;

import com.company.Model.Enums.SortCriteria;
import com.company.Model.TerminalClasses.MedicalProcedure;

import java.util.Comparator;

public class ProcedureComparator implements Comparator<MedicalProcedure> {

    private SortCriteria sortCriteria;

    public ProcedureComparator(){
        this.sortCriteria = SortCriteria.ID;
    }

    public ProcedureComparator(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    @Override
    public int compare(MedicalProcedure m1, MedicalProcedure m2) {
        switch(this.sortCriteria){
            case ROOM_ID:
                return Integer.compare(m1.getCabinetId(), m2.getCabinetId());
            case PRICE:
                return Double.compare(m1.getPrice(), m2.getPrice());
            case PROC_TYPE:
                return m1.getType().compareTo(m2.getType());
            case DATE:
                return m1.getDate().compareTo(m2.getDate());
            default:
                return Integer.compare(m1.getProcedureId(), m2.getProcedureId());
        }
    }

    @Override
    public Comparator<MedicalProcedure> reversed() {
        ProcedureComparator parentComparator = this;

        return new Comparator<MedicalProcedure>() {
            @Override
            public int compare(MedicalProcedure m1, MedicalProcedure m2) {
                return -1 * parentComparator.compare(m1, m2);
            }
        };
    }
}
