package app.questionaire.org.service.mapper;


import app.questionaire.org.domain.*;
import app.questionaire.org.service.dto.CommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {AnswerMapper.class, QuestionMapper.class, ApplicationUserMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "owner.id", target = "ownerId")
    CommentDTO toDto(Comment comment);

    @Mapping(target = "removeAnswer", ignore = true)
    @Mapping(target = "removeQuestion", ignore = true)
    @Mapping(source = "ownerId", target = "owner")
    Comment toEntity(CommentDTO commentDTO);

    default Comment fromId(String id) {
        if (id == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}
