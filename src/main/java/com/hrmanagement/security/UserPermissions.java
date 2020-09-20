package com.hrmanagement.security;

public enum UserPermissions {
    CANDIDATE_READ("candidate:read"),
    CANDIDATE_WRITE("candidate:write"),
    JOB_READ("job:read"),
    JOB_WRITE("job:write"),
    JOB_APPLY("job:apply");

    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
