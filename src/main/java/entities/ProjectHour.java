package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "projectHours")
public class ProjectHour {
    @Id
    @Column(name = "projectHours_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "hoursSpendt", nullable = false)
    private Integer hoursSpendt;

    @Size(max = 45)
    @NotNull
    @Column(name = "userStory", nullable = false, length = 45)
    private String userStory;

    @Size(max = 45)
    @NotNull
    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHoursSpendt() {
        return hoursSpendt;
    }

    public void setHoursSpendt(Integer hoursSpendt) {
        this.hoursSpendt = hoursSpendt;
    }

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}