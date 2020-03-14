package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Projects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
class ProjectJdbcDaoImplTest {

    @Autowired
    private ProjectsDao projectsDao;

    @Test
    void getAllProjects() {
        List<Projects> projects = projectsDao.findAll();
       assertNotNull(projects);
    }

    @Test
    void getProjectById() {
        Projects project = new Projects();
        project.setDescription("Test");
        Integer id = projectsDao.create(project);
        Optional<Projects> projectOptional = projectsDao.findById(id);

        assertTrue(projectOptional.isPresent());
        assertNotNull(projectOptional.get().getDescription());
        assertEquals(projectOptional.get().getProjectId(), id);
        assertEquals(projectOptional.get().getDescription(), "Test");
    }

    @Test
    void updateProject() {
        Projects project = new Projects();
        project.setDescription("Test");
        Integer id =projectsDao.create(project);

        Date checkDate = new Date();
        project.setDescription("New");
        project.setDateAdded(checkDate);

        projectsDao.update(project);
        assertTrue(project.getDescription().equals("New"));
        assertTrue(project.getDateAdded().equals(checkDate));
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