package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Projects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
class ProjectJdbcDaoImplTest {

    @Autowired
    private ProjectsDao projectsDao;

    @Test
    void getAllProjects() {
        List<Projects> projects = projectsDao.getAllProjects();
       assertNotNull(projects);
    }

    @Test
    void getProjectById() {
        Projects projects = projectsDao.getProjectById(1);
        assertNotNull(projects);
    }

    @Test
    void updateProjectById() {
    }

    @Test
    void createProject() {
    }

    @Test
    void deleteProjectById() {
    }

    @Test
    void getNumberOfDevelopersBetweenDates() {
    }
}