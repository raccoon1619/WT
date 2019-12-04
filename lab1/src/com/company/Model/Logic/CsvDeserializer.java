package com.company.Model.Logic;

import com.company.Model.Enums.Gender;
import com.company.Model.Enums.ProcedureType;
import com.company.Model.TerminalClasses.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Create
//
public class CsvDeserializer {
    public final static String passFilePath = "/pass_info.csv";
    public final static String proceduresFilePath = "/procedures.csv";

    public Patient deserializeObject(String dirPath) throws IOException {
        String serializedProcedures = null;
        String serializedPassInfo = null;

        try {
            serializedProcedures = getSerializedObject(dirPath + proceduresFilePath, "\n");
            serializedPassInfo = getSerializedObject(dirPath + passFilePath, "");
        } catch(IOException e){
            throw new IOException("Can't deserialize an object");
        }

        Patient patient = deserializePatient(serializedPassInfo);
        List<MedicalProcedure> procedures = deserializeProcedures(serializedProcedures);
        patient.setMedicalProcedures(procedures);

        return patient;
    }

    public String getSerializedObject(String filePath, String splitter) throws IOException {
        String result = "";
        File file = new File(filePath);
        if(!file.exists()){
            throw new IOException("There is no such file!");
        }

        Scanner scanner;
        scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            result += scanner.nextLine() + splitter;
        }

        return result;
    }

    public Patient deserializePatient(String serializedPatient){
        Object[] data = split(serializedPatient,',');
        String name = (String)data[0];
        String surName = (String)data[1];
        int age = Integer.parseInt((String) data[2]);
        Gender gender = Gender.valueOf((String) data[3]);
        int passId = Integer.parseInt((String)data[5]);
        Patient patient = new Patient(name, surName, age, gender, passId);
        return patient;
    }

    public List<MedicalProcedure> deserializeProcedures(String proceduresStr){
        ArrayList<MedicalProcedure> list =  new ArrayList<MedicalProcedure>();

        if(proceduresStr == "" || proceduresStr == null){
            return list;
        }

        String[] procedures = proceduresStr.split("\n");
        for(String procedure : procedures){
            Object[] data = split((String)procedure, ',');
            String temp = String.valueOf(data[4]);
            Object[] ns = split(temp, '.');
            MedicalSpecialist specialist = new MedicalSpecialist(String.valueOf(ns[0]), String.valueOf(ns[1]));

            MedicalProcedure medicalProcedure = new MedicalProcedure();

            medicalProcedure.setProcedureId(Integer.parseInt(data[0].toString()));
            medicalProcedure.setDate((LocalDateTime.parse(data[1].toString())));
            medicalProcedure.setPrice(Double.parseDouble(data[2].toString()));
            medicalProcedure.setCabinetId(Integer.parseInt(data[3].toString()));
            medicalProcedure.setDoctor(specialist);
            medicalProcedure.setPaid(Boolean.parseBoolean(data[5].toString()));
            medicalProcedure.setType((ProcedureType.valueOf(data[6].toString())));

            list.add(medicalProcedure);
        }

        return list;
    }

    private Object[] split(String serializedPatient, char spliter){
        List<Object> result = new ArrayList<Object>();
        String word = "";
        for(int i = 0; i < serializedPatient.length(); i++){
            if(serializedPatient.charAt(i) == spliter){
                result.add(word);
                word = "";
            }
            else{
                word += serializedPatient.charAt(i);
            }
        }

        if(word != ""){
            result.add(word);
        }

        return ListToObjectArray(result);
    }

    private Object[] ListToObjectArray(List<Object> list) {
        Object[] result = new Object[list.size()];
        int i = 0;
        for(Object o : list){
            result[i++] = o;
        }

        return result;
    }
}
