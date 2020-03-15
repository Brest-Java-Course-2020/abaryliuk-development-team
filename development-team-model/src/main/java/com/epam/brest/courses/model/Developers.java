package com.epam.brest.courses.model;

/**
 * Developers.
 */
public class Developers {

    /**
     * Developer's id.
     */
    private Integer developerId;

    /**
     * Developer's firstName.
     */
    private String firstName;

    /**
     * Developer's lastName.
     */
    private String lastName;

    /**
     * Returns <code>Integer</code> representation of this developer's id.
     *
     * @return developerId Developer's id.
     */
    public final Integer getDeveloperId() {
        return developerId;
    }

    /**
     * Sets the developer's identifier.
     *
     * @param developerId Developer's id.
     */
    public final void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }


    /**
     * Returns <code>String</code> representation of this developer's firstName.
     *
     * @return developerId Developer's firstName.
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * Sets the developer's firstName.
     *
     * @param firstName Developer's firstName.
     */
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * Returns <code>String</code> representation of this developer's lastName.
     *
     * @return lastName Developer's lastName.
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * Sets the developer's lastName.
     *
     * @param lastName Developer's lastName.
     */
    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns <code>String</code> representation of this description.
     *
     * @return description Project description.
     */
    @Override
    public final String toString() {
        return "Developers{"
                + "developerId=" + developerId
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + '}';
    }
}
