package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Projects;
import com.epam.brest.courses.model.dto.ProjectsDto;
import com.epam.brest.courses.rest_app.exception.projectsException.ProjectsNotFoundException;
import com.epam.brest.courses.service.ProjectsDtoService;

import com.epam.brest.courses.service.ProjectsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Optional;


/**
 * ProjectsController
 */
@RestController
@EnableSwagger2
@RequestMapping("projects")
public class ProjectsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsController.class);

    @Autowired
    Projects projectNew;

    private final ProjectsService projectsService;

    private final ProjectsDtoService projectsDtoService;

    public ProjectsController(ProjectsService projectsService, ProjectsDtoService projectsDtoService) {
        this.projectsService = projectsService;
        this.projectsDtoService = projectsDtoService;
    }

    /**
     * Goto projects list page.
     *
     * @return view name.
     */
    @GetMapping(value = "/")
    public final Collection<ProjectsDto> projects(){

        LOGGER.debug("projects");
        return projectsDtoService.countOfDevelopers();
    }

    /**
     *Find project by id.
     *
     * @param projectId
     * @return Project.
     */
    @GetMapping(value = "/{projectId}")
    public Projects findById(@PathVariable Integer projectId) {

        LOGGER.debug("findById");
        return projectsService.findById(projectId).orElseThrow(() -> new ProjectsNotFoundException(projectId));
    }

    /**
     * Add project by description.
     * @param description
     * @return Project id.
     */
    @PostMapping(value = "/addByDescription", consumes = "application/json", produces = "application/json" )
    public Integer add(@RequestBody String description){

        LOGGER.debug("Add project {}", description);
        projectNew.setDescription(description);
        return projectsService.create(projectNew);
    }

    /**
     * Add project with requestBody = Projects project.
     * @param project
     * @returnnew Project().
     */
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json" )
    public Integer add(@RequestBody Projects project){

        LOGGER.debug("Add project {}", project);

        return projectsService.create(project);
    }

    /**
     * Update project with request body = Projects project.
     *
     * @param description
     * @return Project after update.
     */
    @PutMapping(value = "/update/{projectId}", consumes = "application/json", produces = "application/json")
    public Integer update(@PathVariable Integer projectId, @RequestBody String description){

        LOGGER.debug("Update project's description {} with projectId = {}", description, projectId);
        Optional<Projects> projectsOptional = projectsService.findById(projectId);
        Projects project = projectsOptional.get().setDescription(description);
        return projectsService.update(project);
    }

    /**
     *Delete project by id.
     *
     * @param projectId
     * @return Affected rows.
     */
    @DeleteMapping(value = "/delete/{projectId}" )
    public Integer delete(@PathVariable Integer projectId){

        LOGGER.debug("delete()");
        return projectsService.delete(projectId);
    }

}
