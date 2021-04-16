package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person {

    private final IntegerProperty id;
    //private final StringProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty promo;
    private final StringProperty speciality;
    //private final ObjectProperty<LocalDate> birthday;
    private final IntegerProperty birthyear;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id
     * @param firstName
     * @param lastName
     */
    public Person(Integer id, String firstName, String lastName) {
        this.id = new SimpleIntegerProperty(id);
        //this.id = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        this.promo = new SimpleStringProperty("L3");
        this.speciality = new SimpleStringProperty("some city");
        //this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        this.birthyear = new SimpleIntegerProperty(1999);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getPromo() {
        return promo.get();
    }

    public void setPromo(String promo) {
        this.promo.set(promo);
    }

    public StringProperty promoProperty() {
        return promo;
    }

    public int getID() {
        return id.get();
    }

    /*public String getID() {
        return id.get();
    }*/

    public void setID(int id) {
        this.id.set(id);
    }

    /*public void setID(String id) {
        this.id.set(id);
    }*/

    public IntegerProperty idProperty() {
        return id;
    }

    /*public StringProperty idProperty() {
        return id;
    }*/

    public String getSpeciality() {
        return speciality.get();
    }

    public void setSpeciality(String speciality) {
        this.speciality.set(speciality);
    }

    public StringProperty specialityProperty() {
        return speciality;
    }

    /*public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }*/


    public Integer getBirthyear() {
        return birthyear.get();
    }

    public void setBirthyear(Integer birthyear) {
        this.birthyear.set(birthyear);
    }

    public IntegerProperty birthyearProperty() {
        return birthyear;
    }
}
