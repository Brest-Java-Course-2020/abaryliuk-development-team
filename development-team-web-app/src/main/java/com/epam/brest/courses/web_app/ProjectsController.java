package com.epam.brest.courses.web_app;

import com.epam.brest.courses.model.Developers;
import com.epam.brest.courses.model.Projects;
import com.epam.brest.courses.model.dto.ProjectsDto;
import com.epam.brest.courses.service.DevelopersService;
import com.epam.brest.courses.service.ProjectsDtoService;
import com.epam.brest.courses.service.ProjectsService;
import com.epam.brest.courses.web_app.validators.ProjectsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Projects controller.
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsController.class);

    private final ProjectsDtoService projectsDtoService;

    private final ProjectsService projectsService;

    private final DevelopersService developersService;

    public ProjectsController(ProjectsDtoService projectsDtoService, ProjectsService projectsService, DevelopersService developersService) {
        this.projectsDtoService = projectsDtoService;
        this.projectsService = projectsService;
        this.developersService = developersService;
    }


        @Autowired
        ProjectsValidator projectsValidator;

        @Autowired
        Projects newProject;

    /**
     * Goto projects list page.
     *
     * @return view name
     */
    @GetMapping
    public final String projects(
                                 @RequestParam (value = "dateStart", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                                 @RequestParam (value = "dateEnd", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
                                 Model model){

     if (dateStart!= null && dateEnd != null){

         LOGGER.debug("Find projects between dates. Date start = {}, Date End = {}", dateStart, dateEnd);
         model.addAttribute("projects", projectsDtoService.findBetweenDates(dateStart, dateEnd));
    }
    else{

         LOGGER.debug("Find all projects");
         List<ProjectsDto> projectsDtoList =projectsDtoService.countOfDevelopers();
         LOGGER.debug("Date = {}",projectsDtoList.get(0).getDateAdded() );

         model.addAttribute("projects", projectsDtoList );
    }

  return "projects";
}

    /**
     * Goto edit project's page.
     *
     * @return view name
     */
    @GetMapping(value = "/{projectId}")
    public final String gotoEditProjectsPage(@PathVariable Integer projectId, Model model) {

        LOGGER.debug("gotoEditProjectsPage({},{})", projectId, model);
        Optional<Projects> optionalProjects = projectsService.findById(projectId);
        List<Developers> developersList = developersService.selectDevelopersFromProjects_Developers(projectId);
        List<Developers> allDevelopers = developersService.findAll();
        LOGGER.debug("Count of developers = {}", developersList.size());
        if (optionalProjects.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("project", optionalProjects.get());
            model.addAttribute("developers", developersList);
            model.addAttribute("developersList", allDevelopers);
            return "project";
        } else {
            // TODO department not found - pass error message as parameter or handle not found error
            return "redirect:projects";
        }
    }

    /**
     * Update department.
     *
     * @param project project with filled data.
     * @param result     binding result
     * @return view name
     */
    @PostMapping(value = "/{id}")
    public String updateProject(@Valid Projects project,
                                BindingResult result){

        LOGGER.debug("updateProject({}, {})", project, result);
        projectsValidator.validate(project, result);
        if (result.hasErrors()) {
            return "project";
        } else {
            this.projectsService.update(project);
            return "redirect:/projects";
        }
    }

    /**
     * Goto add project page.
     *
     * @return view name
     */
    @GetMapping(value = "/add")
    public final String gotoAddProjectPage(Model model) {

        LOGGER.debug("gotoAddProjectPage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("project", newProject);
        return "project";
    }

    /**
     * Persist new project into persistence storage.
     *
     * @param project new project with filled data.
     * @param result     binding result.
     * @return view name
     */
    @PostMapping(value = "/add")
    public String addProject(@Valid Projects project,
                                BindingResult result) {

        LOGGER.debug("addProject{}, {})", project, result);
        projectsValidator.validate(project, result);
        if (result.hasErrors()) {
            return "project";
        } else {
            this.projectsService.create(project);
            return "redirect:/projects";
        }
    }


    /**
     * Delete project.
     *
     * @return view name
     */
    @GetMapping(value = "/{id}/delete")
    public final String deleteProjectById(@PathVariable Integer id, Model model) {

        LOGGER.debug("delete({},{})", id, model);
        projectsService.delete(id);
        return "redirect:/projects";
    }

    /**
     * Delete project.
     *
     * @return view name
     */
    @GetMapping(value = "/{projectId}/{developerId}/delete")
    public final String deleteDeveloperFromProjects_developers( @PathVariable Integer projectId
                                                               ,@PathVariable Integer developerId
                                                               ,Model model) {

        LOGGER.debug("delete({},{}{})", projectId, developerId, model);
        developersService.deleteDeveloperFromProject_Developers(projectId,developerId);
        return "redirect:/projects";
    }

}
