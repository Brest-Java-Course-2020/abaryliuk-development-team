package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Projects;

import java.util.Date;
import java.util.List;

public interface ProjectsDao {
    List<Projects> getAllProjects();
    Projects getProjectById(Integer id);
    void updateProject(Projects project);
    void createProject();
    void deleteProjectById(Integer id);
    Integer getProjectsBetweenDates(Date dateStart, Date dateEnd);


}
