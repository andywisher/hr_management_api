package com.hrmanagement.security;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRoles {
    CANDIDATE(Sets.newHashSet(UserPermissions.CANDIDATE_READ, UserPermissions.CANDIDATE_WRITE, UserPermissions.JOB_READ, UserPermissions.JOB_APPLY)),
    HR(Sets.newHashSet(UserPermissions.CANDIDATE_READ, UserPermissions.JOB_READ, UserPermissions.JOB_WRITE));

    private final Set<UserPermissions> permissions;

    UserRoles(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(a -> new SimpleGrantedAuthority(a.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
