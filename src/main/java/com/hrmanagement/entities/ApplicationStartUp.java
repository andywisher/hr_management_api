package com.hrmanagement.entities;

import com.hrmanagement.entities.candidates.Address;
import com.hrmanagement.entities.candidates.Candidate;
import com.hrmanagement.entities.candidates.CandidateDAO;
import com.hrmanagement.entities.hr.HumanResources;
import com.hrmanagement.entities.hr.HumanResourcesDAO;
import com.hrmanagement.entities.jobs.Job;
import com.hrmanagement.entities.jobs.JobDAO;
import com.hrmanagement.security.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

    /** Class for database initialization
     *  Persist 1 HumanResources, 2 Jobs and 2 Candidate entities
    */

@Component
public class ApplicationStartUp implements ApplicationListener<ApplicationReadyEvent> {

    private final JobDAO jobDAO;
    private final HumanResourcesDAO humanResourcesDAO;
    private final CandidateDAO candidateDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationStartUp(JobDAO jobDAO, CandidateDAO candidateDAO
            , PasswordEncoder passwordEncoder, HumanResourcesDAO humanResourcesDAO) {
        this.jobDAO = jobDAO;
        this.candidateDAO = candidateDAO;
        this.passwordEncoder = passwordEncoder;
        this.humanResourcesDAO = humanResourcesDAO;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        HumanResources humanResources = new HumanResources();
        humanResources.setName("hr");
        humanResources.setEmail("hr@hr.com");
        humanResources.setPassword(passwordEncoder.encode("hr"));
        humanResources.setRole(UserRoles.HR.name());
        humanResourcesDAO.save(humanResources);


        Job job1 = new Job();
        job1.setTitle("Sales Manager");
        job1.setHrName("hr");
        job1.setJobDescription("Looking for a Sales Manager");
        job1.setCreationDate(LocalDate.now());
        job1.setDueDate(LocalDate.of(2020, 9, 30));
        Job job2 = new Job();
        job2.setTitle("Internship");
        job2.setHrName("hr");
        job2.setJobDescription("Looking for a Intern");
        job2.setCreationDate(LocalDate.now());
        job2.setDueDate(LocalDate.of(2020, 9, 15));
        jobDAO.save(job1);
        jobDAO.save(job2);

        Address address1 = new Address();
        address1.setCountry("Turkey");
        address1.setCity("İstanbul");
        address1.setPo("34340");
        address1.setStreet("Test sk.");
        address1.setApt("Test Apt. 2.");

        Address address2 = new Address();
        address2.setCountry("Turkey");
        address2.setCity("İstanbul");
        address2.setPo("34230");
        address2.setStreet("Acıbadem");
        address2.setApt("4");


        Candidate candidate1 = new Candidate();
        candidate1.setFirstName("candname1");
        candidate1.setLastName("candlastname2");
        candidate1.setPhone("5555");
        candidate1.setEmail("candname1@hotmail.com");
        candidate1.setBirthday(LocalDate.of(1988, 11, 23));
        candidate1.setImgURL("test//URLS");
        candidate1.setAddress(address1);
        candidate1.setPassword(passwordEncoder.encode("pass"));
        candidate1.setRole(UserRoles.CANDIDATE.name());
        candidateDAO.save(candidate1);

        Candidate candidate2 = new Candidate();
        candidate2.setFirstName("candname2");
        candidate2.setLastName("candlastname2");
        candidate2.setPhone("1111");
        candidate2.setEmail("candname2@hotmail.com");
        candidate2.setBirthday(LocalDate.of(1990, 8, 13));
        candidate2.setImgURL("test//URLS");
        candidate2.setAddress(address2);
        candidate2.setRole(UserRoles.CANDIDATE.name());
        candidate2.setPassword(passwordEncoder.encode("pass"));
        candidateDAO.save(candidate2);

    }
}
