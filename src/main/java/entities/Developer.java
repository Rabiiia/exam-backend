package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "developer")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @NotNull
    @Column(name = "billingPrHour", nullable = false)
    private Integer billingPrHour;

    @OneToMany(mappedBy = "developer")
    private Set<ProjectHour> projectHours = new LinkedHashSet<>();

    public Developer() {
    }

    public Developer(String name, String email, Integer billingPrHour) {
        this.name = name;
        this.email = email;
        this.billingPrHour = billingPrHour;
    }

    @ManyToMany(mappedBy = "developers")
//    @JoinTable(name = "developer_project",
//            joinColumns = @JoinColumn(name = "developer_id"),
//            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new LinkedHashSet<>();



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBillingPrHour() {
        return billingPrHour;
    }

    public void setBillingPrHour(Integer billingPrHour) {
        this.billingPrHour = billingPrHour;
    }

    public Set<ProjectHour> getProjectHours() {
        return projectHours;
    }

    public void setProjectHours(Set<ProjectHour> projectHours) {
        this.projectHours = projectHours;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

}