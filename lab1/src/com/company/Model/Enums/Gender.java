package com.company.Model.Enums;

public enum Gender{
    MALE,
    FEEMALE;

    public static String getGendersString() {
        String result = "";
        Gender[] genders = Gender.class.getEnumConstants();
        for(Gender gender : genders){
            result += gender.ordinal() + ": " + gender.toString() + "\n";
        }

        return result;
    }

    public static Gender getGenderById(int genderId) {
        try{
            return Gender.values()[genderId];
        }
        catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
}
