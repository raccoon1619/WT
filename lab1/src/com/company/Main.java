
package com.company;

import com.company.Controller.Authenticator;
import com.company.Model.Enums.Gender;
import com.company.Model.Enums.ProcedureType;
import com.company.Model.TerminalClasses.Hospital;
import com.company.Model.TerminalClasses.MedicalProcedure;
import com.company.Model.TerminalClasses.MedicalSpecialist;
import com.company.Model.TerminalClasses.Patient;
import com.company.View.AppMenu;
import com.company.View.Greeter;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
                Greeter.welcome();

                Hospital hospital = new Hospital();
                Authenticator authenticator = new Authenticator();
                Patient patient = null;
                AppMenu menu = new AppMenu(hospital);
                Scanner scanner = new Scanner(System.in);
                tempIntialization(hospital);

                System.out.println("0: Создать учетную запись");
                System.out.println("1: Войти в учетную запись");
                System.out.print("Введите команду => ");
                int chosenCommand = scanner.nextInt();
                scanner.nextLine();
                switch (chosenCommand){
                        case 0:
                                System.out.print("Введите имя: ");
                                String regName = scanner.nextLine();
                                System.out.print("Введите фамилию: ");
                                String regSurname = scanner.nextLine();
                                System.out.print("Введите номер паспорта: ");

                                int regPassId;
                                try{
                                        regPassId = scanner.nextInt();
                                } catch (Exception e){
                                        System.out.println(e);
                                        return;
                                }

                                scanner.nextLine();
                                int age;
                                System.out.print("Введите возраст: ");
                                try{
                                        age = scanner.nextInt();
                                }catch(Exception e){
                                        System.out.println(e);
                                        return;
                                }
                                scanner.nextLine();
                                System.out.println(Gender.getGendersString());
                                Gender gender = null;
                                while(gender == null){
                                        System.out.print("Выберите пол => ");
                                        int genderId;
                                        try{
                                                genderId = scanner.nextInt();
                                        } catch(Exception e){
                                                System.out.println(e);
                                                return;
                                        }
                                        scanner.nextLine();
                                        gender = Gender.getGenderById(genderId);
                                }

                                patient = authenticator.Registrate(regName, regSurname, age, gender, regPassId);
                                menu.serveUser(patient);
                                break;
                        case 1:
                                System.out.print("Enter name: ");
                                String loginName = scanner.nextLine();
                                System.out.print("Enter surname: ");
                                String loginSurname = scanner.nextLine();
                                System.out.print("Enter passId: ");
                                int loginPassId;
                                try{
                                        loginPassId = scanner.nextInt();
                                }catch(Exception e){
                                        System.out.println(e);
                                        return;
                                }

                                patient = authenticator.LogIn(loginName, loginSurname, loginPassId);

                                if(patient != null){
                                        menu.serveUser(patient);
                                }else{
                                        System.out.println("Неправильные имя или фамилия, или идентификатор паспорта");
                                }

                                break;

                }
        }

        public static void tempIntialization(Hospital hospital){

                MedicalSpecialist specialist1 = new MedicalSpecialist("Sasha", "Alexandrov", ProcedureType.DERMATOLOGY);
                MedicalSpecialist specialist2 = new MedicalSpecialist("Pasha", "Popov", ProcedureType.NEUROLOGY);
                MedicalSpecialist specialist3 = new MedicalSpecialist("Karina", "Surkova", ProcedureType.DERMATOLOGY);
                MedicalProcedure p1 = new MedicalProcedure(1, LocalDateTime.now(), specialist1,
                        123f, 228, ProcedureType.DERMATOLOGY);
                MedicalProcedure p2 = new MedicalProcedure(2, LocalDateTime.now(), specialist2,
                        123f, 404, ProcedureType.DERMATOLOGY);
                MedicalProcedure p3 = new MedicalProcedure(3, LocalDateTime.now(), specialist3,
                        123f, 505, ProcedureType.DERMATOLOGY);



                hospital.addMedicalProcedure(p1);
                hospital.addMedicalProcedure(p2);
                hospital.addMedicalProcedure(p3);
                hospital.addMedicalSpecilist(specialist1);
                hospital.addMedicalSpecilist(specialist2);
                hospital.addMedicalSpecilist(specialist3);;
        }
}

// TODO: javadoc