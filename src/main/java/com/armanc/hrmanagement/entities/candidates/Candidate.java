package com.armanc.hrmanagement.entities.candidates;


import com.armanc.hrmanagement.entities.jobs.Job;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity(name = "candidatelist")
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidateid")
    private int id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "phonenumber")
    private String phone;

    @Column(name = "img_address")
    private String imgURL;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany(mappedBy = "appliedCandidates", targetEntity = Job.class, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Job> appliedJobs = new HashSet<>();

    public Candidate() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
