package com.epam.brest.courses.daoImpl;

import com.epam.brest.courses.model.Projects;
import com.epam.brest.courses.model.dto.ProjectsDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.epam.brest.courses.model.constants.ProjectConstants.PROJECT_DESCRIPTION_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
class ProjectJdbcDaoDtoTestIT {

    @Autowired
    ProjectJdbcDaoDtoImpl projectJdbcDaoDto;

    @Autowired
    ProjectJdbcDaoImpl projectJdbcDao;

    @Autowired
    Projects project;

    @Test
    void shouldFindBetweenDates() throws ParseException {

        Calendar c = Calendar.getInstance();
            Date dateStart = new Date();
            c.setTime(dateStart);
            c.add(Calendar.DATE, -2);
            dateStart = c.getTime();
                Date dateEnd = new Date();
                c.setTime(dateEnd);
                c.add(Calendar.DATE, 2);
                dateEnd = c.getTime();


        Projects projectStart = project;
        projectStart.setDescription(RandomStringUtils.randomAlphabetic(PROJECT_DESCRIPTION_SIZE));
        Integer idStart = projectJdbcDao.create(projectStart);
            assertTrue(idStart > 0);

        Projects projectEnd = project;
        projectEnd.setDescription(RandomStringUtils.randomAlphabetic(PROJECT_DESCRIPTION_SIZE));
        Integer idEnd = projectJdbcDao.create(projectEnd);
            assertTrue(idEnd > 0);

        List<ProjectsDto> projectsList = projectJdbcDaoDto.findBetweenDates(dateStart,dateEnd);
            assertTrue(projectsList.size() > 0);

    }

    @Test
    void countOfDevelopers() {
        List<ProjectsDto> projectsDtoList = projectJdbcDaoDto.countOfDevelopers();
        assertTrue(projectsDtoList.size() > 0);
        assertTrue(projectsDtoList.get(1).getCountOfDevelopers() > 0);
        assertNotNull(projectsDtoList);
    }
}