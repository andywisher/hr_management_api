package com.hrmanagement.entities.jobs;

import com.hrmanagement.entities.candidates.Candidate;
import com.hrmanagement.entities.candidates.CandidateDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class JobService {

    private final JobDAO jobDAO;
    private final JobMapper mapper;
    private final CandidateDAO candidateDAO;


    public List<JobDTO> getAllJobs() {
        return mapper.toJobDTOList(jobDAO.findAll());
    }

    public JobDTO getJobById(int id) {
        Optional<Job> jobDTOOptional = jobDAO.findById(id);
        return jobDTOOptional.map(mapper::toJobDTO).orElse(null);
    }

    public Job createNewJob(JobDTO jobDTO) {
        Job job = mapper.toJob(jobDTO);
        jobDAO.save(job);
        return job;
    }

    public Job updateJob(JobDTO jobDTO, int id) {
        Job job = mapper.toJob(jobDTO);
        job.setId(id);
        jobDAO.save(job);
        return job;
    }

    public void deleteJobById(int id) {
        jobDAO.deleteById(id);
    }

    public void applyToJob(int jobID, int candidateID) {
        Job job = jobDAO.getOne(jobID);
        Candidate candidate = candidateDAO.getOne(candidateID);

        job.getAppliedCandidates().add(candidate);
        jobDAO.save(job);

        candidate.getAppliedJobs().add(job);
        candidateDAO.save(candidate);
    }

}
