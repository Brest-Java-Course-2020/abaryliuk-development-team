package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Projects;

import java.util.Date;
import java.util.List;

public interface ProjectsDao {
    List<Projects> getAllProjects();
    void getProjectById(Integer id);
    void updateProjectById(Integer id);
    void createProject();
    void deleteProjectById(Integer id);
    Integer getNumberOfDevelopersBetweenDates(Date dateStart, Date dateEnd);


}
