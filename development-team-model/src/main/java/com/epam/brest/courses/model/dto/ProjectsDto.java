package com.epam.brest.courses.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Date;

/**
 * POJO Projects for model.
 */
@SuppressFBWarnings(value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" }
        , justification = "I prefer to suppress these FindBugs warnings")
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
        this.dateAdded = new Date();
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public final Date getDateAdded() {
        return dateAdded;
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
