package com.epam.brest.courses.web_app.validators;

import com.epam.brest.courses.model.Projects;
import com.epam.brest.courses.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.brest.courses.model.constants.ProjectConstants.PROJECT_DESCRIPTION_SIZE;

@Component
public class ProjectsValidator implements Validator {

    @Autowired
    ProjectsService projectsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Projects.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "description", "projectDescription.empty");
        Projects project = (Projects) target;

        if (StringUtils.hasLength(project.getDescription())
                && project.getDescription().length() > PROJECT_DESCRIPTION_SIZE) {
            errors.rejectValue("description", "projectDescription.maxSize");
        }
    }
}
