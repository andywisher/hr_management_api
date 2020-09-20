package com.hrmanagement.security.auth;

import com.hrmanagement.entities.candidates.Candidate;
import com.hrmanagement.security.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


public class CandidateUser implements UserDetails {
    private final int candidateID;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final String password;
    private final String email;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;


    public CandidateUser(Candidate candidate) {
        this.candidateID = candidate.getId();
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
        this.grantedAuthorities = UserRoles.valueOf(candidate.getRole()).getGrantedAuthorities();
        this.password = candidate.getPassword();
        this.email = candidate.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public int getCandidateID() {
        return candidateID;
    }
}
