package com.example.passport;

public class PassportValidator {

    public boolean isDataComplete(String imie, String nazwisko) {
        if (imie == null || nazwisko == null) return false;
        return !imie.trim().isEmpty() && !nazwisko.trim().isEmpty();
    }

    public String buildMessage(String imie, String nazwisko, String kolorOczu) {
        if (!isDataComplete(imie, nazwisko)) return "Wprowadź dane";
        return imie.trim() + " " + nazwisko.trim() + " kolor oczu " + kolorOczu;
    }

    public String buildImageName(String numer, String typ) {
        return numer + "-" + typ + ".jpg";
    }
}
