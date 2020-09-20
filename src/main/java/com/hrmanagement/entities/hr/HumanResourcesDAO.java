package com.hrmanagement.entities.hr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanResourcesDAO extends JpaRepository<HumanResources, Integer> {

    HumanResources findAllByEmailEquals(String input);

}
