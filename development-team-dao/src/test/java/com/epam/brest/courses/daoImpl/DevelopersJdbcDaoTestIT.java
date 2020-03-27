package com.epam.brest.courses.daoImpl;

import com.epam.brest.courses.dao.DevelopersJdbcDao;
import com.epam.brest.courses.model.Developers;
import com.epam.brest.courses.model.Projects;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.brest.courses.model.constants.DeveloperConstants.FIRSTNAME_SIZE;
import static com.epam.brest.courses.model.constants.DeveloperConstants.LASTNAME_SIZE;
import static com.epam.brest.courses.model.constants.ProjectConstants.PROJECT_DESCRIPTION_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
class DevelopersJdbcDaoTestIT {

    @Autowired
    DevelopersJdbcDaoImpl developersJdbcDao;

    @Autowired
    ProjectJdbcDaoImpl projectJdbcDao;

    @Autowired
    Projects project;

    @Test
    void shouldFindAllDevelopers() {

        List<Developers> developersList = developersJdbcDao.findAll();
        assertTrue(developersList.size() > 0);
    }

    @Test
    void shouldFindDeveloperById() {

       Developers developer =newDeveloper();

        Integer developerId = developersJdbcDao.create(developer);
        Optional<Developers> optionalDeveloper = developersJdbcDao.findById(developerId);

        assertTrue(optionalDeveloper.isPresent());
        assertEquals(optionalDeveloper.get().getDeveloperId(), developerId);
        assertEquals(developer.getFirstName(),optionalDeveloper.get().getFirstName());
        assertEquals(developer.getLastName(),optionalDeveloper.get().getLastName());
    }

    @Test
    void shouldCreateDeveloper() {

        Developers developer = newDeveloper();
        Integer id = developersJdbcDao.create(developer);
        assertTrue(id > 0);

        Optional<Developers> optionalDevelopers = developersJdbcDao.findById(id);
        assertEquals(developer.getLastName(),optionalDevelopers.get().getLastName());
        assertEquals(developer.getFirstName(), optionalDevelopers.get().getFirstName());
    }

    @Test
    void shouldUpdateDeveloper() {

        Developers developer = newDeveloper();
        String beforeUpdateLastName = developer.getLastName();
        String beforeUpdateFirstName = developer.getFirstName();

        Integer id = developersJdbcDao.create(developer);
        Optional<Developers> optionalDevelopers = developersJdbcDao.findById(id);

            String afterUpdateLastName = "TestLastName";
            optionalDevelopers.get().setLastName(afterUpdateLastName);
                String afterUpdateFirstName = "TestFirstName";
                optionalDevelopers.get().setFirstName(afterUpdateFirstName);

        Integer result = developersJdbcDao.update(optionalDevelopers.get());

        assertTrue(result == 1);
        assertNotEquals(beforeUpdateLastName, afterUpdateLastName);
        assertNotEquals(beforeUpdateFirstName, afterUpdateFirstName);
    }

    @Test
    void shouldDeleteDeveloper() {

        Developers developer = newDeveloper();
        Integer id = developersJdbcDao.create(developer);
        Integer result = developersJdbcDao.delete(id);

        assertEquals(1, result.intValue());
        Optional<Developers> optionalDevelopers = developersJdbcDao.findById(id);
        assertFalse(optionalDevelopers.isPresent());

    }

    @Test
    void shouldSelectDevelopersFromProjects_Developers() {

        Developers developer = newDeveloper();
        Integer firstDeveloperId = developersJdbcDao.create(developer);
        Integer secondDeveloperId = developersJdbcDao.create(developer);
        Projects newProject = project;
        newProject.setDescription(RandomStringUtils.randomAlphabetic(PROJECT_DESCRIPTION_SIZE));
        Integer projectId = projectJdbcDao.create(newProject);
        Integer resultFirst = developersJdbcDao.addDeveloperToProjects_Developers(projectId, firstDeveloperId);
        Integer resultSecond = developersJdbcDao.addDeveloperToProjects_Developers(projectId, secondDeveloperId);

        List<Developers> developersList = developersJdbcDao.selectDevelopersFromProjects_Developers(projectId);
        assertEquals(2, developersList.size());
        assertEquals(0, resultFirst.intValue());
        assertEquals(0, resultSecond.intValue());

    }

    @Test
    void shoulAddDeveloperToProjects_Developers() {

        Developers developer = newDeveloper();
        Integer developerId = developersJdbcDao.create(developer);
            Projects newProject = project;
            newProject.setDescription(RandomStringUtils.randomAlphabetic(PROJECT_DESCRIPTION_SIZE));
            Integer projectId = projectJdbcDao.create(newProject);
        Integer result = developersJdbcDao.addDeveloperToProjects_Developers(projectId, developerId);

        List<Developers> developersList = developersJdbcDao.selectDevelopersFromProjects_Developers(projectId);
        assertEquals(1, developersList.size());
        assertEquals(0, result.intValue());
    }

    @Test
    void shoulDeleteDeveloperFromProject_Developers() {

        Developers developer = newDeveloper();
        Integer developerId = developersJdbcDao.create(developer);
        Projects newProject = project;
        newProject.setDescription(RandomStringUtils.randomAlphabetic(PROJECT_DESCRIPTION_SIZE));
        Integer projectId = projectJdbcDao.create(newProject);
        Integer resultFirst = developersJdbcDao.addDeveloperToProjects_Developers(projectId, developerId);

        List<Developers> developersList = developersJdbcDao.selectDevelopersFromProjects_Developers(projectId);
        assertEquals(1, developersList.size());
        assertEquals(0, resultFirst.intValue());

        Integer resultAfterDelete = developersJdbcDao.deleteDeveloperFromProject_Developers(projectId, developerId);
        List<Developers> developersListAfterDelete = developersJdbcDao.selectDevelopersFromProjects_Developers(projectId);
        assertEquals(0, developersListAfterDelete.size());
        assertEquals(1, resultAfterDelete.intValue());
    }

    private static Developers newDeveloper(){
        Developers developer = new Developers();
        String firstName = RandomStringUtils.randomAlphabetic(LASTNAME_SIZE);
        developer.setFirstName(firstName);
        String lastName = RandomStringUtils.randomAlphabetic(FIRSTNAME_SIZE);
        developer.setLastName(lastName);
        return developer;
    }

}