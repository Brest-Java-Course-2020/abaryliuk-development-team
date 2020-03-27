package com.epam.brest.courses.daoImpl;

import com.epam.brest.courses.dao.DevelopersJdbcDao;
import com.epam.brest.courses.model.Developers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Optional;

import static com.epam.brest.courses.model.constants.DeveloperConstants.*;

public class DevelopersJdbcDaoImpl implements DevelopersJdbcDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DevelopersJdbcDaoImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DevelopersJdbcDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    GeneratedKeyHolderFactory keyHolderFactory = new GeneratedKeyHolderFactory();

    @Value("${DEV.sqlGetAllDevelopers}")
    private String sqlGetAllDevelopers;

    @Value("${DEV.sqlGetDeveloperById}")
    private String sqlGetDeveloperById;

    @Value("${DEV.sqlAdd}")
    private String  sqlAdd;

    @Value("${DEV.sqlUpdate}")
    private String sqlUpdate;

    @Value("${DEV.sqlDeleteById}")
    private String sqlDeleteById;

    @Value("${DEV.sqlSelectDeveloperFromProjectsDevelopers}")
    private String sqlSelectDeveloperFromProjectsDevelopers;

    @Value("${DEV.sqlAddDeveloperToProjectsDevelopers}")
    private String sqlAddDeveloperToProjectsDevelopers;

    @Value("${DEV.sqlDeleteFromProjectDevelopers}")
    private String sqlDeleteFromProjectDevelopers;

    @Override
    public List<Developers> findAll() {

        LOGGER.debug("FindAll");
        List<Developers> developersList = namedParameterJdbcTemplate
                .query(sqlGetAllDevelopers, new BeanPropertyRowMapper<>(Developers.class));
        return developersList;
    }

    @Override
    public Optional<Developers> findById(Integer developerId) {

        LOGGER.debug("FindById developerId = {}", developerId);
        parameterSource.addValue(DEVELOPER_ID, developerId);
        List<Developers> developersList = namedParameterJdbcTemplate
                .query(sqlGetDeveloperById,parameterSource,new BeanPropertyRowMapper<>(Developers.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(developersList));
    }

    @Override
    public Integer create(Developers developer) {

        LOGGER.debug("Create developer = {} ", developer);
        parameterSource.addValue(LASTNAME, developer.getLastName());
        parameterSource.addValue(FIRSTNAME, developer.getFirstName());

        KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

        namedParameterJdbcTemplate.update(sqlAdd, parameterSource, keyHolder);
        return  keyHolder.getKey().intValue();
    }

    @Override
    public Integer update(Developers developer) {

        LOGGER.debug("Update developer = {}", developer);
        parameterSource.addValue(DEVELOPER_ID, developer.getDeveloperId());
        parameterSource.addValue(FIRSTNAME, developer.getFirstName());
        parameterSource.addValue(LASTNAME, developer.getLastName());
        int result = namedParameterJdbcTemplate.update(sqlUpdate, parameterSource);

        LOGGER.debug("Result = {}", result);
        return result;
    }

    @Override
    public Integer delete(Integer developerId) {

        LOGGER.debug("Delete developerId = {}", developerId);
        parameterSource.addValue(DEVELOPER_ID, developerId);
       Integer result =  namedParameterJdbcTemplate.update(sqlDeleteById,parameterSource);

        return result;
    }

    @Override
    public List<Developers> selectDevelopersFromProjects_Developers(Integer projectId) {

        LOGGER.debug("DAO SelectDeveloperFromProjects_Developers(). Project id = {}", projectId);
        parameterSource.addValue("projectId", projectId);
        List<Developers> developersList = namedParameterJdbcTemplate.query(sqlSelectDeveloperFromProjectsDevelopers
                ,parameterSource, new BeanPropertyRowMapper<>(Developers.class));
        return developersList;
    }

    @Override
    public Integer addDeveloperToProjects_Developers(Integer projectId, Integer developerId) {

        LOGGER.debug("DAO addDeveloperToProjects_Developers() Project id = {}. Developer id = {}",projectId,  developerId);
        parameterSource.addValue("projectId", projectId);
        parameterSource.addValue("developerId", developerId);
        Integer result = namedParameterJdbcTemplate.update(sqlAddDeveloperToProjectsDevelopers,parameterSource);
        return result;
    }

    @Override
    public Integer deleteDeveloperFromProject_Developers(Integer progectId, Integer developerId) {

        LOGGER.debug("DAO deleteDeveloperFromProject_Developers(). Developer id = {}, Project id = {}", developerId, progectId);
        parameterSource.addValue("developerId", developerId);
        parameterSource.addValue("projectId", progectId);
        Integer result = namedParameterJdbcTemplate.update(sqlDeleteFromProjectDevelopers,parameterSource);
        return result;
    }
}
