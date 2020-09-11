package com.armanc.hrmanagement.entities.jobs;

import com.armanc.hrmanagement.entities.candidates.Candidate;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
public class JobDTO {

    @ReadOnlyProperty
    private int id;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String hrName;

    @NotNull
    @NotEmpty
    private LocalDate creationDate;

    @NotNull
    @NotEmpty
    private LocalDate dueDate;

    @NotNull
    @NotEmpty
    private String jobDescription;

    private Set<Candidate> appliedCandidates;
}
