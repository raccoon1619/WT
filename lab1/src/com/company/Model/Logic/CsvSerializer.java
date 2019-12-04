package com.company.Model.Logic;

import com.company.Model.TerminalClasses.MedicalProcedure;
import com.company.Model.TerminalClasses.Patient;

import java.util.List;

public class CsvSerializer {

    public String SerilizePatient(Object o) {
        String resultString = "";
        resultString += ((Patient)o).getFirstName() + ",";
        resultString += ((Patient)o).getSecondName() + ",";
        resultString += ((Patient)o).getAge() + ",";
        resultString += ((Patient)o).getGender().toString() + ",";
        resultString += ((Patient)o).getBalance() + ",";
        resultString += ((Patient)o).getPatientId() + ",";

        return resultString;
    }

    public String SerializeProcedures(List<MedicalProcedure> procedures){
        String resultString = "";
        for(Object procedure : procedures){
            resultString += ((MedicalProcedure)procedure).getProcedureId() + ",";                   //1
            resultString += ((MedicalProcedure)procedure).getDate().toString() + ",";               //2
            resultString += ((MedicalProcedure)procedure).getPrice() + ",";                         //3
            resultString += ((MedicalProcedure)procedure).getCabinetId() + ",";                     //4
            resultString += ((MedicalProcedure)procedure).getDoctor().getFirstName() + "." +        //5
                            ((MedicalProcedure)procedure).getDoctor().getSecondName()  + ",";       //6
            resultString += ((MedicalProcedure)procedure).isPaid() + ",";                           //7
            resultString += ((MedicalProcedure)procedure).getType();                                //8
            resultString += "\n";
        }

        return resultString;
    }

}
