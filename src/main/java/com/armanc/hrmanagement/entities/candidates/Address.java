package com.armanc.hrmanagement.entities.candidates;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ReadOnlyProperty
    private int id;

    private String country, city, street, apt, po;

    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private Candidate candidate;

}
