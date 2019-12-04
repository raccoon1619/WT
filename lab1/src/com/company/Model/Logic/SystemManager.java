package com.company.Model.Logic;

import com.company.Model.TerminalClasses.MedicalProcedure;
import com.company.Model.TerminalClasses.Patient;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SystemManager {
    public static final String patientInfoDirPath = "./Patients/";
    public static final String passInfoFile = "/pass_info.csv";
    public static final String proceduresInfoFile = "/procedures.csv";

    private SystemManager(){
    }

    public static List<MedicalProcedure> getProcedures(Patient patient){
        CsvDeserializer deserializer = new CsvDeserializer();
        String dirPath = SystemManager.getPatientDirName(patient);
        String deserializedProcedures = null;
        try {
            deserializedProcedures = deserializer.getSerializedObject(dirPath + proceduresInfoFile, "\n");
        } catch(IOException e){
            return null;
        }

        return deserializer.deserializeProcedures(deserializedProcedures);
    }

    public static boolean createPatient(Patient patient){
        String fileName = getPatientDirName(patient);
        File patientDirectory = new File(fileName);

        if(patientDirectory.exists()){
            return false;
        }

        try {
            patientDirectory.mkdir();
        }catch(Exception exception){
            return false;
        }

        try {
            File file = new File(patientDirectory.getAbsolutePath() + "/procedures.csv");
            file.createNewFile();
            file = new File(patientDirectory.getAbsolutePath() + "/pass_info.csv");
            file.createNewFile();

            FileWriter writer = new FileWriter(file);
            CsvSerializer serializer = new CsvSerializer();
            String serializedPatient = serializer.SerilizePatient(patient);
            writer.write(serializedPatient);
            writer.flush();
            writer.close();

        }catch(IOException exception){
            return false;
        }
        return true;
    }

    private static String getPatientDirName(Patient patient) {
        return patientInfoDirPath +
                patient.getFirstName() + "." + patient.getSecondName() + " (" + patient.getPatientId() + ")";
    }

    public static boolean updatePatientInfo(Patient patient){
//        if(patient.isPassInfoChanged()){
        updatePatientPassFile(patient);
//        }

//        if(patient.isProceduresChanged()){
        updatePatientProcedures(patient);
//        }

        return true;
    }

    public static boolean deletePatient(Patient patient){
        File patientDir = new File(getPatientDirName(patient));

        if(patientDir.list().length == 0) {
            return patientDir.delete();
        }

        for(String fileName : patientDir.list()){
            if(!new File(patientDir, fileName).delete()){
                return false;
            }
        }

        return patientDir.delete();
    }

    public static boolean updatePatientPassFile(Patient patient){
        CsvSerializer serializer = new CsvSerializer();
        File passFile = new File(getPatientDirName(patient) + "/pass_info.csv");
        String passInfoStr = serializer.SerilizePatient(patient);

        try{
            passFile.createNewFile();
            FileWriter fileWriter = new FileWriter(passFile);
            fileWriter.write(passInfoStr);
            fileWriter.flush();
            fileWriter.close();

        } catch(Exception exception){
            return false;
        }
        return true;
    }

    private static boolean updatePatientProcedures(Patient patient){
        CsvSerializer serializer = new CsvSerializer();
        File proceduresFile = new File(getPatientDirName(patient) + proceduresInfoFile);
        List<MedicalProcedure> temp = patient.getMedicalProcedures();
        String proceduresListStr = serializer.SerializeProcedures(temp);

        try {
            proceduresFile.createNewFile();
            FileWriter fileWriter = new FileWriter(proceduresFile);
            fileWriter.write(proceduresListStr);
            fileWriter.flush();
            fileWriter.close();

        } catch(IOException exception){
            return false;
        }
        return true;
    }


    private static String getPatientDirName(String name, String surName, int passId){
        return patientInfoDirPath + name + "." + surName + " (" + passId + ")";
    }


    public static Patient getExistingPatient(String name, String surName, int passId) { // Get
        CsvDeserializer deserializer = new CsvDeserializer();
        try{
            return deserializer.deserializeObject(getPatientDirName(name, surName, passId));
        } catch (IOException e){
            return null;
        }
    }


}
