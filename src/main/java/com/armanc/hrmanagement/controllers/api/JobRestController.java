package com.armanc.hrmanagement.controllers.api;

import com.armanc.hrmanagement.entities.jobs.Job;
import com.armanc.hrmanagement.entities.jobs.JobService;
import com.armanc.hrmanagement.entities.jobs.JobDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor
public class JobRestController {

    private final JobService jobService;

    @GetMapping
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobDTO getJobById(@PathVariable int id) {
        return jobService.getJobById(id);
    }

    @PostMapping
    public Job createNewJob(@RequestBody JobDTO jobDTO) {
        return jobService.createNewJob(jobDTO);
    }

    @RequestMapping(value = "/initialize", method = RequestMethod.GET)
    public void initialize() {
        jobService.initialize();
    }

    @GetMapping("/apply/{job_id}/{candidate_id}")
    public void applyToJob(@PathVariable(value = "job_id") int job_id,
                           @PathVariable(value = "candidate_id") int candidate_id) {
        jobService.applyToJob(job_id, candidate_id);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable int id){
        jobService.deleteJobById(id);
    }

}
