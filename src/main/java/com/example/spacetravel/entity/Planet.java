package com.example.spacetravel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planet")
public class Planet {

    @Id
    @Pattern(regexp = "^[A-Z0-9]+$")
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @NotBlank
    @Size(min = 1, max = 500)
    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @OneToMany(mappedBy = "fromPlanet")
    private List<Ticket> departureTickets = new ArrayList<>();

    @OneToMany(mappedBy = "toPlanet")
    private List<Ticket> arrivalTickets = new ArrayList<>();

    protected Planet() {
    }

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getDepartureTickets() {
        return departureTickets;
    }

    public List<Ticket> getArrivalTickets() {
        return arrivalTickets;
    }
}
