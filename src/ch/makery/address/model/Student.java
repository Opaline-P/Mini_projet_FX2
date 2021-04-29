package ch.makery.address.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Student.
 *
 *  @author Group 35
 * IHM Project - Java FX programmming
 */
public class Student {

    private final IntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty promo;
    private final StringProperty speciality;
    private final IntegerProperty birthyear;

    /**
     * Default constructor.
     */
    public Student() {
        this(0, null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id the student id
     * @param firstName the student firstName
     * @param lastName the student lastName
     */
    public Student(Integer id, String firstName, String lastName) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.promo = new SimpleStringProperty("L3");
        this.speciality = new SimpleStringProperty("");
        this.birthyear = new SimpleIntegerProperty(1999);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id the student id
     * @param firstName the student firstName
     * @param lastName the student lastName
     * @param birthyear the student birthday
     * @param promo the student promo
     * @param speciality the student speciality
     */
    public Student(Integer id, String firstName, String lastName, Integer birthyear, String promo, String speciality) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.promo = new SimpleStringProperty(promo);
        this.speciality = new SimpleStringProperty(speciality);
        this.birthyear = new SimpleIntegerProperty(birthyear);
    }

    /**
     * getFirstName
     * @return  the firstName
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * setFirstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * firstNameProperty
     * @return  the  stringProperty of the firstName
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * getLastName
     * @return  the lastName
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Set the last name of the student
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * lastNameProperty
     * @return  the  stringProperty of the lastName
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * getPromo
     * @return  the student Promo
     */
    public String getPromo() {
        return promo.get();
    }

    /**
     * Set the promo of the student
     * @param promo
     */
    public void setPromo(String promo) {
        this.promo.set(promo);
    }

    /**
     * promoProperty
     * @return  the  stringProperty of the promo
     */
    public StringProperty promoProperty() {
        return promo;
    }

    /**
     * getID
     * @return the ID
     */
    public int getID() {
        return id.get();
    }

    /**
     *Set the id of the student
     * @param id
     */
    public void setID(int id) {
        this.id.set(id);
    }

    /**
     * idProperty
     * @return   the  stringProperty of the id
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     * getSpeciality
     * @return  the Speciality
     */
    public String getSpeciality() {
        return speciality.get();
    }

    /**
     *Set the speciality  of the student
     * @param speciality
     */
    public void setSpeciality(String speciality) {
        this.speciality.set(speciality);
    }

    /**
     * specialityProperty
     * @return  the  stringProperty of the speciality
     */
    public StringProperty specialityProperty() {
        return speciality;
    }

    /**
     * getBirthyear
     * @return  the birthyear
     */
    public Integer getBirthyear() {
        return birthyear.get();
    }

    /**
     *Set the birthyear  of the student
     * @param birthyear
     */
    public void setBirthyear(Integer birthyear) {
        this.birthyear.set(birthyear);
    }

    /**
     * birthyearProperty
     * @return  the  stringProperty of the birthyear
     */

    public IntegerProperty birthyearProperty() {
        return birthyear;
    }
}
