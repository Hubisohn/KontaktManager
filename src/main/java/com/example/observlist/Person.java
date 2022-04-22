package com.example.observlist;

import javafx.collections.ObservableList;

public class Person {
    String Vorname;
    String Nachname;
    int Alter;

    public Person(String Vorname, String Nachname, int Alter) {
        this.Vorname = Vorname;
        this.Nachname = Nachname;
        this.Alter = Alter;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String Vorname) {
        this.Vorname = Vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String Nachname) {
        this.Nachname = Nachname;
    }

    public int getAlter() {
        return Alter;
    }

    public void setAlter(int Alter) {
        this.Alter = Alter;
    }
}
