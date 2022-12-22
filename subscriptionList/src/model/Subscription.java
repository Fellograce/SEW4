package model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Subscription {
    private final StringProperty firstname = new SimpleStringProperty();
    private final StringProperty lastname = new SimpleStringProperty();
    private final BooleanProperty javaFx = new SimpleBooleanProperty();
    private final BooleanProperty javaConcurency = new SimpleBooleanProperty();
    private final BooleanProperty javaMaster = new SimpleBooleanProperty();

    private final BooleanBinding valid;
    private static final ListProperty<Subscription> listSubscription = new SimpleListProperty<>();

    private static List<Subscription> subscriptionList = new ArrayList<>();

    public Subscription() {
        // Enable/Disable Submit-Button
        BooleanBinding nameEntered =
                firstname.length().greaterThanOrEqualTo(3)
                        .and(lastname.length().greaterThanOrEqualTo(3));

        BooleanBinding courseSelected = javaConcurency.or(javaFx.or(javaMaster));

        valid = nameEntered.and(courseSelected);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public ObservableList<Subscription> getListSubscription() {
        return listSubscription.get();
    }

    public ListProperty<Subscription> listSubscriptionProperty() {
        return listSubscription;
    }

    public void setListSubscription(ObservableList<Subscription> listSubscription) {
        this.listSubscription.set(listSubscription);
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public boolean isJavaFx() {
        return javaFx.get();
    }

    public BooleanProperty javaFxProperty() {
        return javaFx;
    }

    public void setJavaFx(boolean javaFx) {
        this.javaFx.set(javaFx);
    }

    public boolean isJavaConcurency() {
        return javaConcurency.get();
    }

    public BooleanProperty javaConcurencyProperty() {
        return javaConcurency;
    }

    public void setJavaConcurency(boolean javaConcurency) {
        this.javaConcurency.set(javaConcurency);
    }

    public boolean isJavaMaster() {
        return javaMaster.get();
    }

    public BooleanProperty javaMasterProperty() {
        return javaMaster;
    }

    public void setJavaMaster(boolean javaMaster) {
        this.javaMaster.set(javaMaster);
    }

    public Boolean getValid() {
        return valid.get();
    }

    public BooleanBinding validProperty() {
        return valid;
    }

    public void add() {
        if (subscriptionList.contains(this)) {
            subscriptionList.remove(this);
        }
        subscriptionList.add(this);

        listSubscription.set(FXCollections.observableArrayList(subscriptionList));
    }

    @Override
    public String toString() {
        String subs = "";

        if (isJavaFx()) {
            subs = "\"JavaFX - Properties and Bindings\"";
        }

        if (isJavaConcurency()) {
            if (subs.isEmpty()) {
                subs = "\"JavaFX - Concurrency\"";
            } else if (isJavaMaster()) {
                subs = subs + ", \"JavaFX - Concurrency\"";
            } else {
                subs = subs + " und \"JavaFX - Concurrency\"";
            }
        }

        if (isJavaMaster()) {
            if (subs.isEmpty()) {
                subs = "\"Java - Master of the Universe\"";
            } else {
                subs = subs + " und \"Java - Master of the Universe\"";
            }
        }

        return getFirstname() + " " + getLastname() + " " + subs;
    }

    public String getCoursesString() {
        String coursesString =
                (this.javaFx.get() ? "Java FX, " : "") +
                        (this.javaConcurency.get() ? "Java Concurrency, " : "") +
                        (this.javaMaster.get() ? "Java Master, " : "");
        if (coursesString.length() > 0) {
            coursesString = coursesString.substring(0, coursesString.length() - 2);
        }
        return coursesString;
    }

    public void save() {
        // nicht recht persistent
        System.out.println(this);
    }
}
