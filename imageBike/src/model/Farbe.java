package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public enum Farbe {
    rot, blau, grün, gelb, schwarz, braun;

    public static ObservableList<Farbe> valuesAsObservableList() {
        return FXCollections.observableList(Arrays.asList(values()));
    }
}
