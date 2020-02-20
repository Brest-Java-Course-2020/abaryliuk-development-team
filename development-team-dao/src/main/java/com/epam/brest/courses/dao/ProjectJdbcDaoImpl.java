package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Projects;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProjectJdbcDaoImpl implements ProjectsDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProjectJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Projects> getAllProjects() {

        List<Projects> projectsList = namedParameterJdbcTemplate.
                query("SELECT projectId, description, dateAdded FROM projects p ORDER BY p.projectId",
                new ProjectRowMapper() );

        return projectsList;
    }

    @Override
    public void getProjectById(Integer id) {

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
    public Integer getNumberOfDevelopersBetweenDates(Date dateStart, Date dateEnd) {
        return null;
    }



  private class ProjectRowMapper implements RowMapper<Projects>{

        @Override
        public Projects mapRow(ResultSet rs, int rowNum) throws SQLException {
            Projects project = new Projects();
            project.setProjectId(rs.getInt("PROJECTID"));
            project.setDescription(rs.getString("DESCRIPTION"));
            project.setDateAdded(rs.getDate("DATEADDED"));
            return project;
        }
    }

}
