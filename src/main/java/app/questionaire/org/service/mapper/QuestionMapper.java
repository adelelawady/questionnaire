package app.questionaire.org.service.mapper;


import app.questionaire.org.domain.*;
import app.questionaire.org.service.dto.QuestionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Question} and its DTO {@link QuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {SubjectMapper.class, ApplicationUserMapper.class})
public interface QuestionMapper extends EntityMapper<QuestionDTO, Question> {

    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "owner.id", target = "ownerId")
    QuestionDTO toDto(Question question);

    @Mapping(source = "subjectId", target = "subject")
    @Mapping(target = "answers", ignore = true)
    @Mapping(target = "removeAnswers", ignore = true)
    @Mapping(source = "ownerId", target = "owner")
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "removeComment", ignore = true)
    Question toEntity(QuestionDTO questionDTO);

    default Question fromId(String id) {
        if (id == null) {
            return null;
        }
        Question question = new Question();
        question.setId(id);
        return question;
    }
}
