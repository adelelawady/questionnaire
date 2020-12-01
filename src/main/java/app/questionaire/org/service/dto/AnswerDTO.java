package app.questionaire.org.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link app.questionaire.org.domain.Answer} entity.
 */
public class AnswerDTO implements Serializable {
    
    private String id;

    private String content;

    private Integer votesUp;

    private Integer votesDown;


    private String ownerId;

    private String questionId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVotesUp() {
        return votesUp;
    }

    public void setVotesUp(Integer votesUp) {
        this.votesUp = votesUp;
    }

    public Integer getVotesDown() {
        return votesDown;
    }

    public void setVotesDown(Integer votesDown) {
        this.votesDown = votesDown;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String applicationUserId) {
        this.ownerId = applicationUserId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerDTO)) {
            return false;
        }

        return id != null && id.equals(((AnswerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerDTO{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", votesUp=" + getVotesUp() +
            ", votesDown=" + getVotesDown() +
            ", ownerId='" + getOwnerId() + "'" +
            ", questionId='" + getQuestionId() + "'" +
            "}";
    }
}
