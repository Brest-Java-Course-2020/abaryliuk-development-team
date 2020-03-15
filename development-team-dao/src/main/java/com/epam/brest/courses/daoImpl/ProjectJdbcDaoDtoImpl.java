package com.epam.brest.courses.daoImpl;

import com.epam.brest.courses.dao.ProjectJdbcDaoDto;
import com.epam.brest.courses.model.Projects;

import java.util.Date;
import java.util.List;

public class ProjectJdbcDaoDtoImpl implements ProjectJdbcDaoDto {



    @Override
    public List<Projects> findBetweenDates(Date dateStart, Date dateEnd) {

        return null;
    }

    @Override
    public Integer countOfDevelopers() {
        return null;
    }
}
