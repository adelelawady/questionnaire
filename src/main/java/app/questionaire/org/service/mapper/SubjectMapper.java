package app.questionaire.org.service.mapper;


import app.questionaire.org.domain.*;
import app.questionaire.org.service.dto.SubjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Subject} and its DTO {@link SubjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {ApplicationUserMapper.class})
public interface SubjectMapper extends EntityMapper<SubjectDTO, Subject> {

    @Mapping(source = "creator.id", target = "creatorId")
    SubjectDTO toDto(Subject subject);

    @Mapping(source = "creatorId", target = "creator")
    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "removeQuestion", ignore = true)
    Subject toEntity(SubjectDTO subjectDTO);

    default Subject fromId(String id) {
        if (id == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }
}
