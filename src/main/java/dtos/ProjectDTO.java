package dtos;

import entities.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link entities.Project} entity
 */
public class ProjectDTO  {
    private Integer id;
    private String name;
    private String description;



    public ProjectDTO(Project project) {

        if (project.getId() != null)
            this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
    }

    public static List<ProjectDTO> getDtos(List<Project> projects) {
        List<ProjectDTO> projectDTOS = new ArrayList();
        projects.forEach(project -> projectDTOS.add(new ProjectDTO(project)));
        return projectDTOS;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ")";
    }
}