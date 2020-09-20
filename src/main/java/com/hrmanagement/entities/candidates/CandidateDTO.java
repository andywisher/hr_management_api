package com.hrmanagement.entities.candidates;

import com.hrmanagement.entities.customvalidation.CheckAgeValidation;
import com.hrmanagement.entities.jobs.Job;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CandidateDTO {

    @ReadOnlyProperty
    private int id;

    @NotNull
    @NotEmpty
    @Size(max = 15, message = "Your name cannot be longer than 15 characters")
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(max = 15, message = "Your last name cannot be longer than 15 characters")
    private String lastName;

    @Email(message = "Please enter an valid e-email address")
    @NotEmpty
    private String email;

    @Size(max = 15, message = "Please enter an valid phone number")
    @NotEmpty
    private String phone;

    @CheckAgeValidation
    @NotEmpty
    private LocalDate birthday;

    @NotNull
    @NotEmpty
    private Address address;

    private String imgURL;

    private Set<Job> appliedJobs;


}
