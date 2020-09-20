package com.hrmanagement.security.auth;

import com.hrmanagement.entities.candidates.Candidate;
import com.hrmanagement.entities.candidates.CandidateDAO;
import com.hrmanagement.entities.hr.HumanResources;
import com.hrmanagement.entities.hr.HumanResourcesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CandidateDAO candidateDAO;
    private final HumanResourcesDAO humanResourcesDAO;

    @Autowired
    public CustomUserDetailsService(CandidateDAO candidateDAO, HumanResourcesDAO humanResourcesDAO) {
        this.candidateDAO = candidateDAO;
        this.humanResourcesDAO = humanResourcesDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        HumanResources humanResources = humanResourcesDAO.findAllByEmailEquals(username);
        if (humanResources != null) {
            return new HrUser(humanResources);
        }

        Candidate candidate = candidateDAO.findByEmailEquals(username);
        if (candidate == null) {
            throw new UsernameNotFoundException(String.format("User with this email: %s not found", username));
        }

        return new CandidateUser(candidate);
    }
}
