package com.epam.brest.courses.model;

import java.util.Date;

public class Projects {

    private Integer projectId;

    private String description;

    private Date dateAdded;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer progectId) {
        this.projectId = progectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Project{" +
                "progectId=" + projectId +
                ", description='" + description + '\'' +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
