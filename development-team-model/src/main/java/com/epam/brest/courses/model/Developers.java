package com.epam.brest.courses.model;

/**
 * Developers
 */
public class Developers {

    private Integer developerId;
    private String firstName;
    private String lastName;


    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Developers{"
                + "developerId=" + developerId
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + '}';
    }
}
