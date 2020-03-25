package com.epam.brest.courses.web_app;

import com.epam.brest.courses.model.Projects;
import com.epam.brest.courses.service.ProjectsDtoService;
import com.epam.brest.courses.service.ProjectsService;
import com.epam.brest.courses.web_app.validators.ProjectsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public ProjectsController(ProjectsDtoService projectsDtoService, ProjectsService projectsService) {
        this.projectsDtoService = projectsDtoService;
        this.projectsService = projectsService;
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
    public final String projects(@RequestParam (value = "dateStart", required = false) String dateStart,
                                 @RequestParam (value = "dateEnd", required = false) String dateEnd,
                                 Model model) throws ParseException {

    System.out.println(dateStart);
    System.out.println(dateEnd);

     if (dateStart!= null && dateEnd != null){
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         Date dateStartDate = formatter.parse(dateStart);
         Date dateEndDate = formatter.parse(dateEnd);
         LOGGER.debug("IFprojects()");
         model.addAttribute("projects", projectsDtoService.findBetweenDates(dateStartDate, dateEndDate));
    }
    else{
         LOGGER.debug("ELSEprojects()");
         model.addAttribute("projects", projectsDtoService.countOfDevelopers());
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

        if (optionalProjects.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("project", optionalProjects.get());
            System.out.println("Date : " + optionalProjects.get().getDateAdded());
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
    public final String gotoAddDepartmentPage(Model model) {

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
    public String addDepartment(@Valid Projects project,
                                BindingResult result) {

        LOGGER.debug("addDepartment({}, {})", project, result);
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


}
