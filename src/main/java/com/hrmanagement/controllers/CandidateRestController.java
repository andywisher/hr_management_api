package com.hrmanagement.controllers;

import com.hrmanagement.entities.candidates.Candidate;
import com.hrmanagement.entities.candidates.CandidateDTO;
import com.hrmanagement.entities.candidates.CandidateService;
import com.hrmanagement.entities.jobs.Job;
import com.hrmanagement.security.auth.CandidateUser;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/candidates")
@AllArgsConstructor
public class CandidateRestController {

    private final CandidateService candidateService;


    // Gets all candidates, only accessible by Human Resources
    @GetMapping
    @PreAuthorize("hasRole('ROLE_HR')")
    public List<CandidateDTO> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    // Gets a candidate, only accessible by that candidate and all human resources
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('candidate:read')")
    public CandidateDTO getCandidateById(@PathVariable int id) {

        Object test = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (test instanceof CandidateUser) {
            if (((CandidateUser) test).getCandidateID() == id) {
                return candidateService.getCandidateById(id);
            } else {
                throw new AccessDeniedException("Not Authorized");
            }
        }

        return candidateService.getCandidateById(id);
    }

    // Creating a new user
    @PostMapping
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public Candidate createCandidate(@RequestBody @Valid CandidateDTO candidateDTO) {
        return candidateService.createNewCandidate(candidateDTO);
    }

    // Deleting an existing candidate
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    public void deleteCandidate(@PathVariable int id) {
        candidateService.deleteCandidateById(id);
    }

    // Update profile, only accessible by that candidate
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('candidate:write')")
    public Candidate updateCandidate(@RequestBody CandidateDTO candidateDTO, @PathVariable int id) {
        Object test = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (test instanceof CandidateUser) {
            if (((CandidateUser) test).getCandidateID() == id) {
                return candidateService.updateCandidate(candidateDTO, id);
            } else {
                throw new AccessDeniedException("Not Authorized");
            }
        }
        return candidateService.updateCandidate(candidateDTO, id);
    }

    // Get applied job list for logged candidate
    @GetMapping("/{id}/jobs")
    @PreAuthorize("hasAuthority('candidate:read')")
    public Set<Job> getAppliedJobs(@PathVariable int id) {
        Object test = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (test instanceof CandidateUser) {
            if (((CandidateUser) test).getCandidateID() == id) {
                return candidateService.getAppliedJobs(id);
            } else {
                throw new AccessDeniedException("Not Authorized");
            }
        }
        return candidateService.getAppliedJobs(id);
    }
}
