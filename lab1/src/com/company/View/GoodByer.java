package com.company.View;

import com.company.Model.TerminalClasses.Patient;

public class GoodByer {
    private GoodByer(){

    }

    public static String goodBye(Patient patient){
        return "Прощайте, " + patient.getFirstName() + " " + patient.getSecondName() + ", желаем вам здоровья!";
    }
}
