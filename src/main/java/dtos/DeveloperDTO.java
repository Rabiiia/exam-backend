package dtos;

import entities.Developer;
import entities.Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link entities.Developer} entity
 */
public class DeveloperDTO implements Serializable {
    private  Integer id;
    private  String name;

    private  String email;

    private Integer billingPrHour;


    public DeveloperDTO(Developer developer) {
        if (developer.getId() != null)
            this.id = developer.getId();
        this.name = developer.getName();
        this.email = developer.getEmail();
        this.billingPrHour = developer.getBillingPrHour();
    }

    public static List<DeveloperDTO> getDtos(List<Developer> developers) {
        List<DeveloperDTO> developerDTOS = new ArrayList();
        developers.forEach(developer -> developerDTOS.add(new DeveloperDTO(developer)));
        return developerDTOS;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getBillingPrHour() {
        return billingPrHour;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "email = " + email + ", " +
                "billingPrHour = " + billingPrHour + ")";
    }
}