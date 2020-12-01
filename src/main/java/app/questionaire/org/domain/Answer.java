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
 * A Answer.
 */
@Document(collection = "answer")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("content")
    private String content;

    @Field("votes_up")
    private Integer votesUp;

    @Field("votes_down")
    private Integer votesDown;

    @DBRef
    @Field("owner")
    @JsonIgnoreProperties(value = "answers", allowSetters = true)
    private ApplicationUser owner;

    @DBRef
    @Field("questions")
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

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

    public String getContent() {
        return content;
    }

    public Answer content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVotesUp() {
        return votesUp;
    }

    public Answer votesUp(Integer votesUp) {
        this.votesUp = votesUp;
        return this;
    }

    public void setVotesUp(Integer votesUp) {
        this.votesUp = votesUp;
    }

    public Integer getVotesDown() {
        return votesDown;
    }

    public Answer votesDown(Integer votesDown) {
        this.votesDown = votesDown;
        return this;
    }

    public void setVotesDown(Integer votesDown) {
        this.votesDown = votesDown;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public Answer owner(ApplicationUser applicationUser) {
        this.owner = applicationUser;
        return this;
    }

    public void setOwner(ApplicationUser applicationUser) {
        this.owner = applicationUser;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Answer questions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Answer addQuestion(Question question) {
        this.questions.add(question);
        question.getAnswers().add(this);
        return this;
    }

    public Answer removeQuestion(Question question) {
        this.questions.remove(question);
        question.getAnswers().remove(this);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Answer comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Answer addComment(Comment comment) {
        this.comments.add(comment);
        comment.getAnswers().add(this);
        return this;
    }

    public Answer removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.getAnswers().remove(this);
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
        if (!(o instanceof Answer)) {
            return false;
        }
        return id != null && id.equals(((Answer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Answer{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", votesUp=" + getVotesUp() +
            ", votesDown=" + getVotesDown() +
            "}";
    }
}
