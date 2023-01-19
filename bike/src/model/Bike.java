package model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import serial.Catalog;

import java.io.*;

public class Bike implements Externalizable {
    private final StringProperty rahmennr = new SimpleStringProperty();
    private final StringProperty markeType = new SimpleStringProperty();
    private final StringProperty farbe = new SimpleStringProperty();

    private BooleanBinding validSave;

    public Bike() {
        validSave = rahmennr.isEmpty().not().and(markeType.isEmpty().not());
    }


    public String getRahmennr() {
        return rahmennr.get();
    }

    public StringProperty rahmennrProperty() {
        return rahmennr;
    }

    public void setRahmennr(String rahmennr) throws BikeExecption {
        if (rahmennr == null) {
            rahmennr = "";
        }
        if (rahmennr.length() < 5) {
            throw new BikeExecption("Rahmennummer muss zumindest 5 Stellen haben!");
        }

        this.rahmennr.set(rahmennr);
    }

    public String getMarkeType() {
        return markeType.get();
    }

    public StringProperty markeTypeProperty() {
        return markeType;
    }

    public void setMarkeType(String markeType) throws BikeExecption {
        if (markeType == null) {
            markeType = "";
        }
        if (markeType.length() < 3) {
            throw new BikeExecption("Marke und Type muss zumindest 3 Stellen haben!");
        }

        this.markeType.set(markeType);
    }

    public String getFarbe() {
        return farbe.get();
    }

    public StringProperty farbeProperty() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        if (farbe == null) {
            farbe = "";
        }

        this.farbe.set(farbe);
    }

    public Boolean getValidSave() {
        return validSave.get();
    }

    public BooleanBinding validSaveProperty() {
        return validSave;
    }

    public static Bike select(String rahmennr) {
        return Catalog.getInstance().selectBikeByRahmennr(rahmennr);
    }

    public void save() {
        Catalog.getInstance().save(this);
    }

    @Override
    public String toString() {
        return "Bike{" +
                "rahmennr='" + getRahmennr() + '\'' +
                ", markeType='" + getMarkeType() + '\'' +
                ", farbe='" + getFarbe() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Bike bike = (Bike) o;

        return rahmennr.equals(bike.rahmennr);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(rahmennr.get());
        out.writeObject(markeType.get());
        out.writeObject(farbe.get());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        try {
            setRahmennr((String) in.readObject());
            setMarkeType((String) in.readObject());
            setFarbe((String) in.readObject());
        } catch (BikeExecption e) {
            throw new RuntimeException(e);
        }
    }
}
