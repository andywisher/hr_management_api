package com.armanc.hrmanagement.entities.jobs;

import com.armanc.hrmanagement.entities.candidates.Address;
import com.armanc.hrmanagement.entities.candidates.Candidate;
import com.armanc.hrmanagement.entities.candidates.CandidateDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
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

    public void initialize() {

        Job job1 = new Job();
        job1.setTitle("Sales Manager");
        job1.setHrName("Seda Gürsel");
        job1.setJobDescription("Satış Müdürü arıyoruz");
        job1.setCreationDate(LocalDate.now());
        job1.setDueDate(LocalDate.of(2020, 9, 30));
        Job job2 = new Job();
        job2.setTitle("Internship");
        job2.setHrName("Seda Gürsel");
        job2.setJobDescription("Köle arıyoruz");
        job2.setCreationDate(LocalDate.now());
        job2.setDueDate(LocalDate.of(2020, 9, 15));
        jobDAO.save(job1);
        jobDAO.save(job2);

        Address address1 = new Address();
        address1.setCountry("Turkey");
        address1.setCity("İstanbul");
        address1.setPo("34340");
        address1.setStreet("Zambak sk.");
        address1.setApt("A2");

        Address address2 = new Address();
        address2.setCountry("Turkey");
        address2.setCity("İstanbul");
        address2.setPo("34230");
        address2.setStreet("Acıbadem");
        address2.setApt("4");


        Candidate candidate1 = new Candidate();
        candidate1.setFirstName("Taner");
        candidate1.setLastName("Tataroglu");
        candidate1.setPhone("5555");
        candidate1.setEmail("arm_anc@hotmail.com");
        candidate1.setBirthday(LocalDate.of(1988, 11, 23));
        candidate1.setImgURL("test//URLS");
        candidate1.setAddress(address1);
        candidateDAO.save(candidate1);

        Candidate candidate2 = new Candidate();
        candidate2.setFirstName("Deniz Can");
        candidate2.setLastName("Arıkan");
        candidate2.setPhone("1111");
        candidate2.setEmail("denizc@hotmail.com");
        candidate2.setBirthday(LocalDate.of(1990, 8, 13));
        candidate2.setImgURL("test//URLS");
        candidate2.setAddress(address2);
        candidateDAO.save(candidate2);


//        Job getJob1 = jobDAO.getOne(1);
//        Job getJob2 = jobDAO.getOne(2);
//
//        Candidate candidate = candidateDAO.getOne(1);
//
//        candidate.getAppliedJobs().add(getJob1);
//        candidate.getAppliedJobs().add(getJob2);
//
//        getJob1.getAppliedCandidates().add(candidate);
//        getJob2.getAppliedCandidates().add(candidate);
//
//        jobDAO.save(job1);
//        jobDAO.save(job2);
//        candidateDAO.save(candidate);
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
