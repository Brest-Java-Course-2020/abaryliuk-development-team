package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Projects;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * ProjectsDao interface.
 */
public interface ProjectsDao {

    /**
     * Find all projects.
     *
     * @return projects list.
     */
    List<Projects> findAll();

    /**
     * Find department by Id.
     *
     * @param projectId project's id.
     * @return project.
     */
    Optional<Projects> findById(Integer projectId);

    /**
     * Update project.
     *
     * @param project project.
     * @return number of updated records in the database.
     */
    Integer update(Projects project);

    /**
     * Persist new project.
     *
     * @param project project.
     * @return persisted project id.
     */
    Integer create(Projects project);

    /**
     * Delete project.
     *
     * @param projectId project id.
     * @return number of updated records in the database.
     */
    Integer delete(Integer projectId);

    /**
     * Find all projects between two dates.
     *
     * @param dateStart
     * @param dateEnd
     * @return projects list.
     */
    List<Projects> findBetweenDates(Date dateStart, Date dateEnd);

}
