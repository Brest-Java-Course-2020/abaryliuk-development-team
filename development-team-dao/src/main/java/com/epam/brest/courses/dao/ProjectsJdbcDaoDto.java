package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.dto.ProjectsDto;

import java.util.Date;
import java.util.List;

/**
 * ProjectsJdbcDaoDto interface.
 */
public interface ProjectsJdbcDaoDto {

    /**
     * Find all projects between two dates.
     *
     * @param dateStart
     * @param dateEnd
     * @return project's list.
     */
    List<ProjectsDto> findBetweenDates(Date dateStart, Date dateEnd);

    /**
     * Count of developers
     *
     * @return project's list.
     */
    List<ProjectsDto> countOfDevelopers();
}
