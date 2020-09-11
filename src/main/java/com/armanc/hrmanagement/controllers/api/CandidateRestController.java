package com.armanc.hrmanagement.controllers.api;

import com.armanc.hrmanagement.entities.candidates.Candidate;
import com.armanc.hrmanagement.entities.candidates.CandidateDTO;
import com.armanc.hrmanagement.entities.candidates.CandidateService;
import com.armanc.hrmanagement.entities.jobs.Job;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/candidates")
@AllArgsConstructor
public class CandidateRestController {

    private final CandidateService candidateService;


    @GetMapping
    public List<CandidateDTO> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public CandidateDTO getCandidateById(@PathVariable int id) {
        return candidateService.getCandidateById(id);
    }

    @PostMapping
    public Candidate createCandidate(@RequestBody @Valid CandidateDTO candidateDTO) {

        return candidateService.createNewCandidate(candidateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable int id) {
        candidateService.deleteCandidateById(id);
    }

    @PutMapping("/{id}")
    public Candidate updateCandidate(@RequestBody CandidateDTO candidateDTO, @PathVariable int id) {
        return candidateService.updateCandidate(candidateDTO, id);
    }

    @GetMapping("/{id}/jobs")
    public Set<Job> getAppliedJobs(@PathVariable int id) {
        return candidateService.getAppliedJobs(id);
    }

}
