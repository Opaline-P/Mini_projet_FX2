package ch.makery.address.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Student {

    private final IntegerProperty id; // TODO : on a vraiment besoin de garder l'Id ? :/
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty birthYear;
    private final StringProperty promotion;
    private final StringProperty option;

    /**
     * Default constructor.
     */
    public Student() {
        this(null, null, null, 1950, null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id Integer
     * @param firstName String
     * @param lastName String
     * @param birthYear int
     * @param promotion String
     * @param option String
     */
    public Student(Integer id, String firstName, String lastName, int birthYear, String promotion, String option) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthYear = new SimpleIntegerProperty(birthYear);
        this.promotion = new SimpleStringProperty(promotion);
        this.option = new SimpleStringProperty(option);
    }

    // Student Id ////////////////////
    public int getID() {
        return id.get();
    }

    public void setID(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // Student First Name ////////////////////
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    // Student Last Name ////////////////////
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    // Student Birth Year ////////////////////
    public Integer getBirthYear() {
        return birthYear.get();
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear.set(birthYear);
    }

    public IntegerProperty birthYearProperty() {
        return birthYear;
    }

    // Student's Promotion ////////////////////
    public String getPromotion() {
        return promotion.get();
    }

    public void setPromotion(String promotion) {
        this.promotion.set(promotion);
    }

    public StringProperty promotionProperty() {
        return promotion;
    }

    // Student's option
    public String getOption() {
        return option.get();
    }

    public void setOption(String option) {
        this.option.set(option);
    }

    public StringProperty optionProperty() {
        return option;
    }

}
