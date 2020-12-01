package app.questionaire.org.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Question.
 */
@Document(collection = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("content")
    private String content;

    @Field("views")
    private Integer views;

    @Field("likes")
    private Integer likes;

    @Field("answerd")
    private Boolean answerd;

    @Field("best_answer_id")
    private String bestAnswerId;

    @DBRef
    @Field("subject")
    private Subject subject;

    @DBRef
    @Field("answers")
    private Set<Answer> answers = new HashSet<>();

    @DBRef
    @Field("owner")
    @JsonIgnoreProperties(value = "questions", allowSetters = true)
    private ApplicationUser owner;

    @DBRef
    @Field("comments")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Question title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public Question content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViews() {
        return views;
    }

    public Question views(Integer views) {
        this.views = views;
        return this;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getLikes() {
        return likes;
    }

    public Question likes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Boolean isAnswerd() {
        return answerd;
    }

    public Question answerd(Boolean answerd) {
        this.answerd = answerd;
        return this;
    }

    public void setAnswerd(Boolean answerd) {
        this.answerd = answerd;
    }

    public String getBestAnswerId() {
        return bestAnswerId;
    }

    public Question bestAnswerId(String bestAnswerId) {
        this.bestAnswerId = bestAnswerId;
        return this;
    }

    public void setBestAnswerId(String bestAnswerId) {
        this.bestAnswerId = bestAnswerId;
    }

    public Subject getSubject() {
        return subject;
    }

    public Question subject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public Question answers(Set<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public Question addAnswers(Answer answer) {
        this.answers.add(answer);
        answer.setQuestion(this);
        return this;
    }

    public Question removeAnswers(Answer answer) {
        this.answers.remove(answer);
        answer.setQuestion(null);
        return this;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public Question owner(ApplicationUser applicationUser) {
        this.owner = applicationUser;
        return this;
    }

    public void setOwner(ApplicationUser applicationUser) {
        this.owner = applicationUser;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Question comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Question addComment(Comment comment) {
        this.comments.add(comment);
        comment.getQuestions().add(this);
        return this;
    }

    public Question removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.getQuestions().remove(this);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        return id != null && id.equals(((Question) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Question{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", views=" + getViews() +
            ", likes=" + getLikes() +
            ", answerd='" + isAnswerd() + "'" +
            ", bestAnswerId='" + getBestAnswerId() + "'" +
            "}";
    }
}
