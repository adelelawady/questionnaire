package app.questionaire.org.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link app.questionaire.org.domain.Question} entity.
 */
public class QuestionDTO implements Serializable {
    
    private String id;

    private String title;

    private String content;

    private Integer views;

    private Integer likes;

    private Boolean answerd;

    private String bestAnswerId;


    private String subjectId;

    private String ownerId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Boolean isAnswerd() {
        return answerd;
    }

    public void setAnswerd(Boolean answerd) {
        this.answerd = answerd;
    }

    public String getBestAnswerId() {
        return bestAnswerId;
    }

    public void setBestAnswerId(String bestAnswerId) {
        this.bestAnswerId = bestAnswerId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
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
        if (!(o instanceof QuestionDTO)) {
            return false;
        }

        return id != null && id.equals(((QuestionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", views=" + getViews() +
            ", likes=" + getLikes() +
            ", answerd='" + isAnswerd() + "'" +
            ", bestAnswerId='" + getBestAnswerId() + "'" +
            ", subjectId='" + getSubjectId() + "'" +
            ", ownerId='" + getOwnerId() + "'" +
            "}";
    }
}
