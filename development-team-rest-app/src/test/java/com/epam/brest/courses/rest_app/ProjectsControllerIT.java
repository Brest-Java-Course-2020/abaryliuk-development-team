package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Projects;
import com.epam.brest.courses.rest_app.exception.projectsException.CustomExceptionHandler;
import com.epam.brest.courses.service.ProjectsServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static com.epam.brest.courses.model.constants.ProjectConstants.PROJECT_DESCRIPTION_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:app-cont.xml"})
class ProjectsControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsControllerIT.class);

    public static final String PROJECTS_ENDPOINT = "/projects";

    @Autowired
    private ProjectsController projectsController;

    @Autowired
    private CustomExceptionHandler customExceptionHandler;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    MockMvcProjectsService projectsService = new MockMvcProjectsService();

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(projectsController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void shouldFindAllProjects() throws Exception {

        List<Projects> projects = projectsService.findAll();
        assertNotNull(projects);
        assertTrue(projects.size() > 0);
    }

    @Test
    void shouldFindById() throws Exception {

        // given
        Projects project = new Projects()
                .setDescription(RandomStringUtils.randomAlphabetic(PROJECT_DESCRIPTION_SIZE));
        Integer id = projectsService.create(project);
        assertNotNull(id);

        Optional<Projects> projectOptional = projectsService.findById(id);
        assertTrue(projectOptional.isPresent());

        projectOptional.get().
                setDescription(RandomStringUtils.randomAlphabetic(PROJECT_DESCRIPTION_SIZE));

        // when
        int result = projectsService.update(projectOptional.get());

//        // then
        assertTrue(1 == result);

        Optional<Projects> updatedProjectOptional = projectsService.findById(id);
        assertTrue(updatedProjectOptional.isPresent());
        assertEquals(updatedProjectOptional.get().getProjectId(), id);
        assertEquals(updatedProjectOptional.get().getDescription(),projectOptional.get().getDescription());

    }

    @Test
    void add() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    class MockMvcProjectsService {

        public List<Projects> findAll() throws Exception {

            LOGGER.debug("MOCK_MVC findAll()");
            MockHttpServletResponse response = mockMvc.perform(get(PROJECTS_ENDPOINT)
                    .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
                    .andReturn().getResponse();
            assertNotNull(response);

            return objectMapper.readValue(response.getContentAsString(), new TypeReference<List<Projects>>() {
            });
        }


        public Integer create(Projects project) throws Exception {

            LOGGER.debug("MOCK_MVC create({})", project);
            String json = objectMapper.writeValueAsString(project);
            MockHttpServletResponse response =
                    mockMvc.perform(post(PROJECTS_ENDPOINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }

        public Optional<Projects> findById(Integer projectId) throws Exception {

            LOGGER.debug("MOCK_MVC findById({})", projectId);
            MockHttpServletResponse response = mockMvc.perform(get(PROJECTS_ENDPOINT + "/" + projectId)
                    .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
                    .andReturn().getResponse();
            return Optional.of(objectMapper.readValue(response.getContentAsString(), Projects.class));
        }

        private int update(Projects project) throws Exception {

            LOGGER.debug("MOCK_MVC update({})", project);
            MockHttpServletResponse response =
                    mockMvc.perform(put(PROJECTS_ENDPOINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(project))
                            .accept(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk())
                            .andReturn().getResponse();
            return objectMapper.readValue(response.getContentAsString(), Integer.class);
        }
    }
}