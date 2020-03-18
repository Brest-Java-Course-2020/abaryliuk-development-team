package com.epam.brest.courses.daoImpl;

import com.epam.brest.courses.dao.ProjectsJdbcDaoDto;
import com.epam.brest.courses.model.dto.ProjectsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Date;
import java.util.List;

public class ProjectJdbcDaoDtoImpl implements ProjectsJdbcDaoDto {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectJdbcDaoDtoImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProjectJdbcDaoDtoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    MapSqlParameterSource parameterSource = new MapSqlParameterSource();

    @Value("${PRO.sqlFindBetweenDate}")
    private String findBetweenDate;

    @Value("${PRO.sqlCountOfDevelopers}")
    private String countOfDevelopers;

    @Override
    public List<ProjectsDto> findBetweenDates(Date dateStart, Date dateEnd) {

    LOGGER.debug("findBetweenDates. DateStart = {} DateEnd = {}", dateStart,dateEnd);

        parameterSource.addValue("dateStart",dateStart);
        parameterSource.addValue("dateEnd",dateEnd);

        return namedParameterJdbcTemplate.query(findBetweenDate,parameterSource
                , new BeanPropertyRowMapper<>(ProjectsDto.class));
    }

    @Override
    public List<ProjectsDto>  countOfDevelopers() {

        LOGGER.debug("CountOfDevelopers()");
        return namedParameterJdbcTemplate.query(countOfDevelopers
                ,new BeanPropertyRowMapper<>().newInstance(ProjectsDto.class));
    }
}
