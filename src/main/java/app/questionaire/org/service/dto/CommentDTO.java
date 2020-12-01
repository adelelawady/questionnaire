package app.questionaire.org.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import app.questionaire.org.domain.enumeration.CommentType;

/**
 * A DTO for the {@link app.questionaire.org.domain.Comment} entity.
 */
public class CommentDTO implements Serializable {
    
    private String id;

    private String name;

    private CommentType commentType;

    private String refQuestionId;

    private Set<AnswerDTO> answers = new HashSet<>();
    private Set<QuestionDTO> questions = new HashSet<>();

    private String ownerId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public String getRefQuestionId() {
        return refQuestionId;
    }

    public void setRefQuestionId(String refQuestionId) {
        this.refQuestionId = refQuestionId;
    }

    public Set<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerDTO> answers) {
        this.answers = answers;
    }

    public Set<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionDTO> questions) {
        this.questions = questions;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String applicationUserId) {
        this.ownerId = applicationUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentDTO)) {
            return false;
        }

        return id != null && id.equals(((CommentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", commentType='" + getCommentType() + "'" +
            ", refQuestionId='" + getRefQuestionId() + "'" +
            ", answers='" + getAnswers() + "'" +
            ", questions='" + getQuestions() + "'" +
            ", ownerId='" + getOwnerId() + "'" +
            "}";
    }
}
