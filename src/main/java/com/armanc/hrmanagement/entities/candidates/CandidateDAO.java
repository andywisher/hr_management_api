package com.armanc.hrmanagement.entities.candidates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateDAO extends JpaRepository<Candidate, Integer> {

    List<Candidate> findByFirstNameContains(String name);
    List<Candidate> findByLastNameContains(String name);

}
