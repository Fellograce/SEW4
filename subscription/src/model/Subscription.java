package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Subscription {
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleBooleanProperty propertyBinding = new SimpleBooleanProperty();
    private SimpleBooleanProperty concurrency = new SimpleBooleanProperty();
    private SimpleBooleanProperty master = new SimpleBooleanProperty();

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public boolean isPropertyBinding() {
        return propertyBinding.get();
    }

    public SimpleBooleanProperty propertyBindingProperty() {
        return propertyBinding;
    }

    public void setPropertyBinding(boolean propertyBinding) {
        this.propertyBinding.set(propertyBinding);
    }

    public boolean isConcurrency() {
        return concurrency.get();
    }

    public SimpleBooleanProperty concurrencyProperty() {
        return concurrency;
    }

    public void setConcurrency(boolean concurrency) {
        this.concurrency.set(concurrency);
    }

    public boolean isMaster() {
        return master.get();
    }

    public SimpleBooleanProperty masterProperty() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master.set(master);
    }

    @Override
    public String toString() {
        String subs = "";

        if (isPropertyBinding()) {
            subs = "\"JavaFX - Properties and Bindings\"";
        }

        if (isConcurrency()) {
            if (subs.isEmpty()) {
                subs = "\"JavaFX - Concurrency\"";
            } else if (isMaster()) {
                subs = subs + ", \"JavaFX - Concurrency\"";
            } else {
                subs = subs + " und \"JavaFX - Concurrency\"";
            }
        }

        if (isMaster()) {
            if (subs.isEmpty()) {
                subs = "\"Java - Master of the Universe\"";
            } else {
                subs = subs + " und \"Java - Master of the Universe\"";
            }
        }

        return getFirstName() + " " + getLastName() + " wir freuen uns, sie bei " + subs + " begrüßen zu dürfen";
    }
}
