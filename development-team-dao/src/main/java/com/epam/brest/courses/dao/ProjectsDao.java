package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Projects;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProjectsDao {
    List<Projects> findAll();
    Optional<Projects> findById(Integer id);
    Integer update(Projects project);
    Integer create(Projects project);
    Integer delete(Integer id);
    List<Projects> findBetweenDates(Date dateStart, Date dateEnd);


}
