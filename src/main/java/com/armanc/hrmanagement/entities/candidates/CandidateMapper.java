package com.armanc.hrmanagement.entities.candidates;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "appliedJobs", target = "appliedJobs")
    @Mapping(source = "imgURL", target = "imgURL")
    Candidate toCandidate(CandidateDTO candidateDTO);

    @Mapping(source = "imgURL", target = "imgURL")
    CandidateDTO toCandidateDTO(Candidate candidate);

    List<CandidateDTO> toCandidateDTOList(List<Candidate> candidateList);



}
