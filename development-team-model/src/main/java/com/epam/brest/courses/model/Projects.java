package com.epam.brest.courses.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Projects.
 */
@SuppressFBWarnings(value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" }
                    , justification = "I prefer to suppress these FindBugs warnings")
public class Projects {

    /**
     * Constructor without arguments.
     */
    public Projects() {
        this.dateAdded=LocalDate.now();
    }

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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateAdded;


    /**
     * Returns <code>Integer</code> representation of this projectId.
     *
     * @return projectId Project Id.
     */
    public final Integer getProjectId() {
        return projectId;
    }

    /**
     * Sets the department's identifier.
     *
     * @param projectId Department Id.
     */
    public final Projects setProjectId(Integer projectId) {
        this.projectId = projectId;
        return this;
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
    public final Projects setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Returns <code>Date</code> representation of this add date.
     *
     * @return dateAdded Project add date.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public final LocalDate getDateAdded() {
        return dateAdded;
    }

    /**
     * Sets the dateAdded.
     *
     * @param dateAdded eAdded Project dateAdded.
     */
    public Projects setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return "Project{"
                + "progectId=" + projectId
                + ", description='" + description + '\''
                + ", dateAdded=" + dateAdded
                + '}';
    }

}
