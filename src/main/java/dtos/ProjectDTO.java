package dtos;

import entities.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectDTO)) return false;
        ProjectDTO that = (ProjectDTO) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}