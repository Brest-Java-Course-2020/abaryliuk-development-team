package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Projects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProjectJdbcDaoImpl implements ProjectsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectJdbcDaoImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProjectJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Value("${PRO.sqlGetAllProjects}")
    private String sqlGetAllProjects;

    @Value("${PRO.sqlAdd}")
    private String sqlAdd;

    @Value("${PRO.sqlUpdate}")
    private String sqlUpdate;

    @Value("${PRO.sqlCountById}")
    private String sqlCountById;

    @Value("${PRO.sqlCountByName}")
    private String sqlCountByName;

    @Value("${PRO.sqlGetProjectById}")
    private String sqlGetProjectById;

    @Value("${PRO.sqlDeleteById}")
    private String sqlDeleteById;

    MapSqlParameterSource parameterSource = new MapSqlParameterSource();


    @Override
    public List<Projects> getAllProjects() {

        List<Projects> projectsList = namedParameterJdbcTemplate.
                query(sqlGetAllProjects, new BeanPropertyRowMapper<>(Projects.class));

        return projectsList;
    }


    @Override
    public Projects getProjectById(Integer projectId) {

        parameterSource.addValue("projectId", projectId);

        Projects projects = (Projects) namedParameterJdbcTemplate
                            .query(sqlGetProjectById, parameterSource,
                            new BeanPropertyRowMapper<>(Projects.class)).stream().findAny().orElse(null);
        LOGGER.debug("Test ProjectId=:   " + projects.getProjectId()) ;

        return projects;
    }


    @Override
    public void updateProject(Projects project) {
        LOGGER.debug("Date = :      " + project.getDateAdded());
        parameterSource.addValue("description", project.getDescription());
        parameterSource.addValue("dateAdded", project.getDateAdded());
        namedParameterJdbcTemplate.update(sqlUpdate, parameterSource);

    }

    @Override
    public void createProject() {

    }

    @Override
    public void deleteProjectById(Integer id) {

    }

    @Override
    public Integer getProjectsBetweenDates(Date dateStart, Date dateEnd) {
        return null;
    }

}
