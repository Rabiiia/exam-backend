package dtos;


import entities.Project;
import entities.ProjectHour;


import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link entities.ProjectHour} entity
 */
public class ProjectHourDTO  {
    private  Integer id;
    private  Integer hoursSpendt;

    private  String userStory;
    private  String description;

    private ProjectDTO project;



    public ProjectHourDTO(ProjectHour projecthour) {
        this.id = projecthour.getId();
        this.hoursSpendt = projecthour.getHoursSpendt();
        this.userStory = projecthour.getUserStory();
        this.description = projecthour.getDescription();
        this.project = new ProjectDTO(projecthour.getProject());
    }

    public static List<ProjectHourDTO> getDtos(List<ProjectHour> projectHours) {
        List<ProjectHourDTO> projectHourDTOS = new ArrayList();
        projectHours.forEach(projectHour -> projectHourDTOS.add(new ProjectHourDTO(projectHour)));
        return projectHourDTOS;
    }


    public Integer getId() {
        return id;
    }

    public Integer getHoursSpendt() {
        return hoursSpendt;
    }

    public String getUserStory() {
        return userStory;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "hoursSpendt = " + hoursSpendt + ", " +
                "userStory = " + userStory + ", " +
                "description = " + description + ")";
    }
}