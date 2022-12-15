package model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Cylinder {
    private final DoubleProperty radius = new SimpleDoubleProperty();
    private final DoubleProperty hoehe = new SimpleDoubleProperty();
    private NumberBinding volumen;
    private NumberBinding umfang;
    private NumberBinding oberflaeche;

    Integer i;

    public Cylinder() {
        // Var. 1: Fluent-API
        volumen = hoehe.multiply(radius.multiply(radius.multiply(Math.PI)));

        // Var. 2: mit Bindings-Klasse
        umfang = Bindings.multiply(2, Bindings.multiply(radius, Math.PI));

        // Variante 3: Low Level API
        oberflaeche = new DoubleBinding() {
            {
                super.bind(hoehe, radius);
            }

            @Override
            protected double computeValue() {
                return (2 * Math.PI * radius.get() * radius.get()) +
                        (2 * Math.PI * radius.get() * hoehe.get());
            }
        };
    }

    public double getRadius() {
        return radius.get();
    }

    public DoubleProperty radiusProperty() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius.set(radius);
    }

    public double getHoehe() {
        return hoehe.get();
    }

    public DoubleProperty hoeheProperty() {
        return hoehe;
    }

    public void setHoehe(double hoehe) {
        this.hoehe.set(hoehe);
    }

    public NumberBinding volumenProperty() {
        return volumen;
    }

    public NumberBinding umfangProperty() {
        return umfang;
    }

    public NumberBinding oberflaecheProperty() {
        return oberflaeche;
    }
}
