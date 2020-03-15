package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Projects;

import java.util.Date;
import java.util.List;

/**
 * ProjectsJdbcDaoDto interface.
 */
public interface ProjectJdbcDaoDto {

    /**
     * Find all projects between two dates.
     *
     * @param dateStart
     * @param dateEnd
     * @return projects list.
     */
    List<Projects> findBetweenDates(Date dateStart, Date dateEnd);

    /**
     * Count of developers
     *
     * @return countOfDevelopers.
     */
    Integer countOfDevelopers();
}
