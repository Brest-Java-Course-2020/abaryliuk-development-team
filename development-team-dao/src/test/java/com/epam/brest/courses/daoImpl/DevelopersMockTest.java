package com.epam.brest.courses.daoImpl;

import com.epam.brest.courses.dao.DevelopersJdbcDao;
import com.epam.brest.courses.model.Developers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DevelopersMockTest {

    @InjectMocks
    private DevelopersJdbcDaoImpl developersJdbcDao;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @AfterEach
    void after(){
        verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void shouldGetAll(){

        String sql = "something";
        List<Developers> developersList = new LinkedList<>();
        Developers developer = new Developers();
        developersList.add(developer);

        ReflectionTestUtils.setField(developersJdbcDao,"sqlGetAllDevelopers", sql);

        when(namedParameterJdbcTemplate
                .query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(developersList);

        List<Developers> testList = developersJdbcDao.findAll();
        Assert.assertNotNull(testList);
        assertEquals(1,testList.size());
        Developers developerTest = developersList.get(0);
        assertNotNull(developerTest);
        assertSame(developerTest, developer);
    }
}
