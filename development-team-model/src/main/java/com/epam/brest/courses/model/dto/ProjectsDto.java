package com.epam.brest.courses.model.dto;

import java.util.Date;

/**
 * POJO Projects for model.
 */
public class ProjectsDto {

    /**
     * Project id.
     */
    private Integer projectId;

    /**
     * Project description.
     */
    private String description;

    /**
     * Date adding of project.
     */
    private Date dateAdded;

    /**
     * Count of developers.
     */
    private Integer countOfDevelopers;

    /**
     * Constructor without arguments.
     */
    public ProjectsDto() {
    }

    /**
     * Returns <code>Integer</code> representation of this projectId.
     *      *
     *      * @return projectId Project Id.
     */
    public final Integer getProjectId() {
        return projectId;
    }

    /**
     * Sets the department's identifier.
     *
     * @param projectId Department Id.
     */

    public final void setProjectId(final Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * Returns <code>String</code> representation of this description.
     *
     * @return description Project description.
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description Project description.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns <code>Date</code> representation of this add date.
     *
     * @return dateAdded Project add date.
     */
    public final Date getDateAdded() {
        return dateAdded;
    }

    /**
     * Sets the dateAdded.
     *
     * @param dateAdded Project add date.
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * Returns <code>Integer</code> representation project's count of developers.
     *
     * @return countOfDevelopers Project's count of developers.
     */
    public final Integer getCountOfDevelopers() {
        return countOfDevelopers;
    }

    /**
     * Sets the project's count of developers.
     *
     * @param countOfDevelopers Count of developers.
     */
    public final void setCountOfDevelopers(final Integer countOfDevelopers) {
        this.countOfDevelopers = countOfDevelopers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return "ProjectsDto{"
                + "projectId=" + projectId
                + ", description='" + description + '\''
                + ", dateAdded=" + dateAdded
                + ", countOfDevelopers=" + countOfDevelopers
                + '}';
    }
}
