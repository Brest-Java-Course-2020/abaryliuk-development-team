package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Projects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public List<Projects> getAllProjects() {

        List<Projects> projectsList = namedParameterJdbcTemplate.
                query("SELECT projectId, description, dateAdded FROM projects p ORDER BY p.projectId",
                new BeanPropertyRowMapper<>(Projects.class));

        return projectsList;
    }

    @Override
    public Projects getProjectById(Integer projectId) {

        MapSqlParameterSource mapSqlParameterSource =
                            new MapSqlParameterSource("projectId", projectId);

        Projects projects = (Projects) namedParameterJdbcTemplate
                            .query("SELECT * FROM projects WHERE projectId = :projectId", mapSqlParameterSource,
                            new BeanPropertyRowMapper<>(Projects.class)).stream().findAny().orElse(null);

        LOGGER.debug("ProjectId=:   " + projects.getProjectId()) ;

        return projects;
    }


    @Override
    public void updateProjectById(Integer id) {

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
