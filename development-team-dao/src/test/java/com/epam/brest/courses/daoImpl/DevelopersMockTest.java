package com.epam.brest.courses.daoImpl;

import com.epam.brest.courses.dao.DevelopersJdbcDao;
import com.epam.brest.courses.dao.KeyHolderFactory;
import com.epam.brest.courses.model.Developers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DevelopersMockTest {

    @InjectMocks
    private DevelopersJdbcDaoImpl developersJdbcDao;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Mock
    KeyHolderFactory keyHolderFactory;


    @AfterEach
    void after(){
        verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void shouldGetAll(){

        Developers developer = newDeveloper();
        String sql = "something";
        ReflectionTestUtils.setField(developersJdbcDao,"sqlGetAllDevelopers", sql);

        when(namedParameterJdbcTemplate
                .query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(Collections.singletonList(developer));


        List<Developers> testList = developersJdbcDao.findAll();
        Assert.assertNotNull(testList);
        assertEquals(1,testList.size());

        Developers developerTest = testList.get(0);
        assertNotNull(developerTest);
        assertSame(developerTest, developer);
    }

    @Test
    public void shouldFindDeveloperById(){

        Developers developer = newDeveloper();
        String sql = "something";
        List<Developers> developersList = new LinkedList<>();
        developersList.add(developer);
        ReflectionTestUtils.setField(developersJdbcDao,"sqlGetDeveloperById", sql);

        when(namedParameterJdbcTemplate.query(anyString(), any(MapSqlParameterSource.class)
                ,any(BeanPropertyRowMapper.class))).thenReturn(developersList);

        Optional<Developers> optionalDeveloper = developersJdbcDao.findById(1);

        assertEquals("Jon", optionalDeveloper.get().getFirstName());
        assertEquals("Connor", optionalDeveloper.get().getLastName());
    }


    @Test
    public void shouldCreateNewDeveloper(){

//        Developers developer = newDeveloper();
//        developer.setDeveloperId(null);
//        String sql = "something";
//        ReflectionTestUtils.setField(developersJdbcDao,"sqlAdd", sql);
//
//        KeyHolder keyHolder = mock(GeneratedKeyHolder.class);
//        when(keyHolderFactory.newKeyHolder()).thenReturn(keyHolder);
//
//        when(keyHolder.getKey()).thenReturn(1);
//
//        when(namedParameterJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class)
//                ,any(KeyHolder.class))).thenReturn(1);
//
//        Integer id = developersJdbcDao.create(developer);
//        assertNotNull(id );

        }

    @Test
    public void shouldDeleteDeveloper(){

        String sql = "something";

        ReflectionTestUtils.setField(developersJdbcDao,"sqlDeleteById", sql);
        when(namedParameterJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(1);

        Integer result = developersJdbcDao.delete(1);
        assertTrue(result > 0);


    }

    private static Developers newDeveloper(){

        Developers developer = new Developers();
        developer.setDeveloperId(1);
        developer.setFirstName("Jon");
        developer.setLastName("Connor");
        return developer;
    }
}
