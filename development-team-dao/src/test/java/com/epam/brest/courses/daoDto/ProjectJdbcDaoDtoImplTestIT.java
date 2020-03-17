package com.epam.brest.courses.daoDto;

import com.epam.brest.courses.model.Projects;
import com.epam.brest.courses.model.dto.ProjectsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
class ProjectJdbcDaoDtoImplTestIT {

    @Autowired
    ProjectJdbcDaoDtoImpl projectJdbcDaoDto;

    @Autowired
    ProjectJdbcDaoImpl projectJdbcDao;


    @Test
    void shouldFindBetweenDates() throws ParseException {

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStart = myFormat.parse("2019-07-16");
        Date dateEnd = myFormat.parse("2020-01-16");

        Projects projectStart = new Projects();
        projectStart.setDateAdded(dateStart);
        projectStart.setDescription("Test");
        projectJdbcDao.create(projectStart);

        Projects projectEnd = new Projects();
        projectEnd.setDateAdded(dateEnd);
        projectEnd.setDescription("TestEnd");

        projectJdbcDao.create(projectEnd);

        List<ProjectsDto> projectsList = projectJdbcDaoDto.findBetweenDates(dateStart,dateEnd);
        assertEquals(3, projectsList.size());

    }

    @Test
    void countOfDevelopers() {
        List<ProjectsDto> projectsDtoList = projectJdbcDaoDto.countOfDevelopers();
        assertTrue(projectsDtoList.size() > 0);
        assertTrue(projectsDtoList.get(1).getCountOfDevelopers() > 0);
        assertNotNull(projectsDtoList);
    }
}