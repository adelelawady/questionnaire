package app.questionaire.org.service.mapper;


import app.questionaire.org.domain.*;
import app.questionaire.org.service.dto.AnswerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Answer} and its DTO {@link AnswerDTO}.
 */
@Mapper(componentModel = "spring", uses = {ApplicationUserMapper.class, QuestionMapper.class})
public interface AnswerMapper extends EntityMapper<AnswerDTO, Answer> {

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "question.id", target = "questionId")
    AnswerDTO toDto(Answer answer);

    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "questionId", target = "question")
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "removeComment", ignore = true)
    Answer toEntity(AnswerDTO answerDTO);

    default Answer fromId(String id) {
        if (id == null) {
            return null;
        }
        Answer answer = new Answer();
        answer.setId(id);
        return answer;
    }
}
