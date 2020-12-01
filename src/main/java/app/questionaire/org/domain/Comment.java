package app.questionaire.org.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import app.questionaire.org.domain.enumeration.CommentType;

/**
 * A Comment.
 */
@Document(collection = "comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("comment_type")
    private CommentType commentType;

    @Field("ref_question_id")
    private String refQuestionId;

    @DBRef
    @Field("answers")
    private Set<Answer> answers = new HashSet<>();

    @DBRef
    @Field("questions")
    private Set<Question> questions = new HashSet<>();

    @DBRef
    @Field("owner")
    @JsonIgnoreProperties(value = "comments", allowSetters = true)
    private ApplicationUser owner;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Comment name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public Comment commentType(CommentType commentType) {
        this.commentType = commentType;
        return this;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public String getRefQuestionId() {
        return refQuestionId;
    }

    public Comment refQuestionId(String refQuestionId) {
        this.refQuestionId = refQuestionId;
        return this;
    }

    public void setRefQuestionId(String refQuestionId) {
        this.refQuestionId = refQuestionId;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public Comment answers(Set<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public Comment addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.getComments().add(this);
        return this;
    }

    public Comment removeAnswer(Answer answer) {
        this.answers.remove(answer);
        answer.getComments().remove(this);
        return this;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Comment questions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Comment addQuestion(Question question) {
        this.questions.add(question);
        question.getComments().add(this);
        return this;
    }

    public Comment removeQuestion(Question question) {
        this.questions.remove(question);
        question.getComments().remove(this);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public Comment owner(ApplicationUser applicationUser) {
        this.owner = applicationUser;
        return this;
    }

    public void setOwner(ApplicationUser applicationUser) {
        this.owner = applicationUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        return id != null && id.equals(((Comment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comment{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", commentType='" + getCommentType() + "'" +
            ", refQuestionId='" + getRefQuestionId() + "'" +
            "}";
    }
}
