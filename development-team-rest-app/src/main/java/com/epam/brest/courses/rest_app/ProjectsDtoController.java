package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.dto.ProjectsDto;
import com.epam.brest.courses.service.ProjectsDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;

/**
 * ProjectsController
 */
@RestController
@EnableSwagger2
public class ProjectsDtoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsDtoController.class);

    private final ProjectsDtoService projectsDtoService;

    public ProjectsDtoController(ProjectsDtoService projectsDtoService) {
        this.projectsDtoService = projectsDtoService;
    }

    /**
     * Goto projects list page.
     *
     * @return view name.
     */
    @GetMapping("/project_dtos")
    public final Collection<ProjectsDto> projects(){

        LOGGER.debug("project_dtos");
        return projectsDtoService.countOfDevelopers();
    }
}
