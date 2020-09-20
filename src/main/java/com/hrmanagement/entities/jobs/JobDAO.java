package com.hrmanagement.entities.jobs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDAO extends JpaRepository<Job, Integer> {
}
