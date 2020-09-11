package com.armanc.hrmanagement.entities.jobs;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {

    @Mapping(source = "appliedCandidates", target = "appliedCandidates")
    Job toJob(JobDTO JobDTO);

    @Mapping(source = "appliedCandidates", target = "appliedCandidates")
    JobDTO toJobDTO(Job job);

    List<JobDTO> toJobDTOList(List<Job> jobList);

}
