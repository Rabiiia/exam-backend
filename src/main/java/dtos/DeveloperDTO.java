package dtos;

import entities.Developer;

import java.io.Serializable;

/**
 * A DTO for the {@link entities.Developer} entity
 */
public class DeveloperDTO implements Serializable {
    private  Integer id;
    private  String name;

    private  String email;

    private Integer billingPrHour;

    public DeveloperDTO(Integer id, String name, String email, Integer billingPrHour) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.billingPrHour = billingPrHour;
    }

    public DeveloperDTO(Developer developer) {
        if (developer.getId() != null)
            this.id = developer.getId();
        this.name = developer.getName();
        this.email = developer.getEmail();
        this.billingPrHour = developer.getBillingPrHour();
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