package com.hrmanagement.controllers;

import com.hrmanagement.entities.jobs.Job;
import com.hrmanagement.entities.jobs.JobDTO;
import com.hrmanagement.entities.jobs.JobService;
import com.hrmanagement.security.auth.CandidateUser;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor
public class JobRestController {

    private final JobService jobService;


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_CANDIDATE','ROLE_HR')")
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_CANDIDATE','ROLE_HR')")
    public JobDTO getJobById(@PathVariable int id) {
        return jobService.getJobById(id);
    }

    // Post a new job, only accessible by human resources
    @PostMapping
    @PreAuthorize("hasAuthority('job:write')")
    public Job createNewJob(@RequestBody JobDTO jobDTO) {
        return jobService.createNewJob(jobDTO);
    }

    // Apply for an existing job, only accessible by candidates
    @GetMapping("/apply/{job_id}")
    @PreAuthorize("hasAuthority('job:apply')")
    public void applyToJob(@PathVariable(value = "job_id") int job_id) {
        CandidateUser principal = (CandidateUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        jobService.applyToJob(job_id, principal.getCandidateID());
    }

    // Delete an existing job, only accessible by human resources
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('job:write')")
    public void deleteJob(@PathVariable int id) {
        jobService.deleteJobById(id);
    }

    // Updating an existing job, only accessible by human resources
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('job:write')")
    public void updateJob(@PathVariable int id, JobDTO jobDTO) {
        jobService.updateJob(jobDTO, id);
    }

}
