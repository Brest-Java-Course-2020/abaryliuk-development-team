package com.epam.brest.courses.rest_app.exception.developersException;

/**
 * DevelopersNotFoundException.
 */
public class DevelopersNotFoundException extends RuntimeException {

    /**
     * Constructor with argument Integer id.
     */
    public DevelopersNotFoundException(Integer id) {
        super("Developer id not found : " + id);
    }
}
