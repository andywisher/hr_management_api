package com.hrmanagement.entities.candidates;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateMapper {

    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "appliedJobs", target = "appliedJobs")
    @Mapping(source = "imgURL", target = "imgURL")
    Candidate toCandidate(CandidateDTO candidateDTO);

    @Mapping(source = "imgURL", target = "imgURL")
    CandidateDTO toCandidateDTO(Candidate candidate);

    List<CandidateDTO> toCandidateDTOList(List<Candidate> candidateList);


}
