package com.hrmanagement.entities.candidates;

import com.hrmanagement.entities.jobs.Job;
import com.hrmanagement.entities.jobs.JobDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class CandidateService {

    @Autowired
    private final CandidateMapper candidateMapper;
    @Autowired
    private final CandidateDAO candidateDAO;
    @Autowired
    private final JobDAO jobDAO;

    public List<CandidateDTO> getAllCandidates() {
        return candidateMapper.toCandidateDTOList(candidateDAO.findAll());
    }

    public CandidateDTO getCandidateById(int id) {
        Optional<Candidate> optionalCandidate = candidateDAO.findById(id);
        return optionalCandidate.map(candidateMapper::toCandidateDTO).orElse(null);
    }

    public Candidate createNewCandidate(@Valid CandidateDTO candidateDTO) {
        Candidate candidate = candidateMapper.toCandidate(candidateDTO);
        candidateDAO.save(candidate);
        return candidate;
    }

    public Candidate updateCandidate(CandidateDTO candidateDTO, int id) {
        Candidate revisedCandidate = candidateMapper.toCandidate(candidateDTO);
        revisedCandidate.setId(id);
        System.out.println(revisedCandidate.toString());
        candidateDAO.save(revisedCandidate);
        return candidateDAO.getOne(id);
    }

    public void deleteCandidateById(int id) {
        Candidate candidate = candidateDAO.getOne(id);
        for (Job job : candidate.getAppliedJobs()) {
            jobDAO.getOne(job.getId()).getAppliedCandidates().remove(candidate);
            jobDAO.save(job);
        }
        candidateDAO.deleteById(id);
    }

    public Set<Job> getAppliedJobs(int id) {
        return candidateDAO.getOne(id).getAppliedJobs();
    }

}
