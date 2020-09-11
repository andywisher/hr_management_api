package com.armanc.hrmanagement.entities.jobs;

import com.armanc.hrmanagement.entities.candidates.Candidate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "joblist")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobID")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "hrname")
    private String hrName;

    @Column(name = "creationdate")
    private LocalDate creationDate;

    @Column(name = "duedate")
    private LocalDate dueDate;

    @Column(name = "jobdescription", length = 10000)
    private String jobDescription;


    @ManyToMany(targetEntity = Candidate.class,fetch = FetchType.EAGER)
    @JoinTable(
            name = "job_cand_table",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id")
    )
    @JsonBackReference
    private Set<Candidate> appliedCandidates = new HashSet<>();


    public Job() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
