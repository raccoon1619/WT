package com.company.View;

import com.company.Controller.AccountInfoTracker;
import com.company.Controller.AppointmentTracker;
import com.company.Model.Enums.ProcedureType;
import com.company.Model.Enums.SortCriteria;
import com.company.Model.Logic.Comparators.ProcedureComparator;
import com.company.Model.TerminalClasses.Hospital;
import com.company.Model.TerminalClasses.MedicalProcedure;
import com.company.Model.TerminalClasses.MedicalSpecialist;
import com.company.Model.TerminalClasses.Patient;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;

public class AppMenu {

//    private Hashtable<ProcedureType, String> medicalProceduresNames;
    private AppointmentTracker appointmentTracker;
    private Hospital hospital;
    private AccountInfoTracker accountInfoTracker;

    public AppMenu(Hospital hospital){
        this.hospital = hospital;
        this.accountInfoTracker = new AccountInfoTracker();
        this.appointmentTracker =  new AppointmentTracker(hospital);
    }

    private Hashtable<ProcedureType, String> getMedicalProceduresNamesMap() {
        Hashtable<ProcedureType, String> result = new Hashtable<ProcedureType, String>();
        ProcedureType[] procedureTypeValues = ProcedureType.class.getEnumConstants();
        for(ProcedureType procType : procedureTypeValues){
            result.put(procType, procType.name().toLowerCase());
        }

        return result;
    }

    public static String separatorStr = "---------------------------";

    public void printUserMenu(){
        System.out.println(separatorStr);
        System.out.println("0. Выйти");
        System.out.println("1. Получить список процедур");
        System.out.println("2. Записаться на процедуру");
        System.out.println("3. Осортировать список процедур по индектификатору процедуры");
        System.out.println("4. Осортировать список процедур по времени");
        System.out.println("5. Осортировать список процедур по цене");
        System.out.println("6. Осортировать список процедур по типу");
        System.out.println("7. Удалить аккаунт");
        System.out.println("8. Отказаться от процедуры");
        System.out.println(separatorStr);
    }

    public void printMedicalProceduresNames(){

    }

    public void serveUser(Patient patient){
        Scanner scanner = new Scanner(System.in);

        int decision = 1;
        while(decision != 0) {
            printUserMenu();
            System.out.print("Введите команду => ");
            decision = scanner.nextInt();

            switch (decision) {
                case 0:
                    System.out.println("До свидания!");
                    break;
                case 1:
                    AccountViewer.printProceduresList(patient.getMedicalProcedures(), separatorStr);
                    break;
                case 2:
                    System.out.println(AppMenu.separatorStr);
                    HospitalViewer.printAllMedicalFields();
                    boolean fieldWasChosen = false;
                    ProcedureType procedureType = null;
                    while(!fieldWasChosen) {
                        System.out.print("Выберите поле => ");
                        try {
                            int index = scanner.nextInt();
                            procedureType = ProcedureType.values()[index];
                            fieldWasChosen = true;
                        } catch(ArrayIndexOutOfBoundsException e){
                            System.out.println("Неправильный номер процедуры!");
                        }
                    }

                    System.out.println(AppMenu.separatorStr);

                    if(HospitalViewer.printMedicalSpecialist(this.hospital.getMedicalSpecialistsByCriteria(procedureType),
                            AppMenu.separatorStr) == 0){
                        System.out.println("Извените, но мы не можем найти специалистов!");
                        break;
                    }
                    MedicalSpecialist medicalSpecialist = null;
                    while(true) {
                        System.out.println(AppMenu.separatorStr);

                        System.out.print("Введите номер мед.специалиста => ");
                        int specialistId = scanner.nextInt();
                        medicalSpecialist = hospital.findMedicalSpecialist(specialistId);
                        if(medicalSpecialist != null){
                            break;
                        }

                        System.out.println("Неправильный номер мед. специалиста!");
                    }

                    System.out.println(AppMenu.separatorStr);

                    System.out.println("Выберите дату: " + LocalDateTime.now());
                    // TODO: to add choosing of available data
                    try{
                        this.appointmentTracker.makeAnAppointment(patient,
                                new MedicalProcedure(medicalSpecialist, patient,
                                LocalDateTime.now(), 228, procedureType)); // TODO: to add automatically choosen cabinet id!
                        this.accountInfoTracker.updatePatient(patient);
                        System.out.println("Успех");
                    } catch(Exception e){
                        System.out.println(e);
                        break;
                    }

                    // TODO: print medical spelist's available dates
                    // TODO: if there are no dates, break!

                    break;
                case 3:
                    Collections.sort(patient.getMedicalProcedures(), new ProcedureComparator(SortCriteria.ID));
                    AccountViewer.printProceduresList(patient.getMedicalProcedures(), separatorStr);
                    break;
                case 4:
                    Collections.sort(patient.getMedicalProcedures(), new ProcedureComparator(SortCriteria.DATE));
                    AccountViewer.printProceduresList(patient.getMedicalProcedures(), separatorStr);
                    break;
                case 5:
                    Collections.sort(patient.getMedicalProcedures(), new ProcedureComparator(SortCriteria.PRICE));
                    AccountViewer.printProceduresList(patient.getMedicalProcedures(), separatorStr);
                    break;
                case 6:
                    Collections.sort(patient.getMedicalProcedures(), new ProcedureComparator(SortCriteria.PROC_TYPE));
                    AccountViewer.printProceduresList(patient.getMedicalProcedures(), separatorStr);
                    break;
                case 7:
                    System.out.println("Вы уверены?");
                    System.out.println("1: да");
                    System.out.println("2: не");
                    System.out.print("Ваш выбор => ");
                    decision = scanner.nextInt();
                    switch (decision){
                        case 1:
                            System.out.println(GoodByer.goodBye(patient));
                            this.accountInfoTracker.deletePatient(patient);
                            return;
                        default:
                            break;
                    }
                    break;
                case 8:
                    AccountViewer.printProceduresList(patient);

                    boolean procedureWasChoosed = false;
                    MedicalProcedure choosedProcedure = null;

                    while(!procedureWasChoosed){
                        System.out.print("Введите индекс процедуры, которую хотите удалить => ");
                        int procedureId = scanner.nextInt();
                        choosedProcedure = patient.getMedicalProcedureById(procedureId);
                        if(choosedProcedure != null){
                            procedureWasChoosed = true;
                        }else{
                            System.out.println("Неверный индекс процедуры!");
                        }
                    }

                    patient.removeProcedure(choosedProcedure);
                    System.out.println("Процедура была удалена!");
                    break;
                default:
                    System.out.println("Неизвестная команда!");
                    break;
            }
        }
    }


    public void serveUser(MedicalSpecialist specialist){
        AccountInfoTracker accountInfoTracker = new AccountInfoTracker();
        int decision = -1;
        switch (decision){
            case 0:
                System.out.println("До свидания!");
                break;
            case 1:

                break;
            default:
                System.out.println("Неверная команда!");
                break;
        }
    }
}
