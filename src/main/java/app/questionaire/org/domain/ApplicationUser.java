package app.questionaire.org.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ApplicationUser.
 */
@Document(collection = "application_user")
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @DBRef
    @Field("internalUser")
    private User internalUser;

    @DBRef
    @Field("comments")
    private Set<Comment> comments = new HashSet<>();

    @DBRef
    @Field("subjects")
    private Set<Subject> subjects = new HashSet<>();

    @DBRef
    @Field("questions")
    private Set<Question> questions = new HashSet<>();

    @DBRef
    @Field("answers")
    private Set<Answer> answers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getInternalUser() {
        return internalUser;
    }

    public ApplicationUser internalUser(User user) {
        this.internalUser = user;
        return this;
    }

    public void setInternalUser(User user) {
        this.internalUser = user;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public ApplicationUser comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public ApplicationUser addComments(Comment comment) {
        this.comments.add(comment);
        comment.setOwner(this);
        return this;
    }

    public ApplicationUser removeComments(Comment comment) {
        this.comments.remove(comment);
        comment.setOwner(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public ApplicationUser subjects(Set<Subject> subjects) {
        this.subjects = subjects;
        return this;
    }

    public ApplicationUser addSubjects(Subject subject) {
        this.subjects.add(subject);
        subject.setCreator(this);
        return this;
    }

    public ApplicationUser removeSubjects(Subject subject) {
        this.subjects.remove(subject);
        subject.setCreator(null);
        return this;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public ApplicationUser questions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public ApplicationUser addQuestions(Question question) {
        this.questions.add(question);
        question.setOwner(this);
        return this;
    }

    public ApplicationUser removeQuestions(Question question) {
        this.questions.remove(question);
        question.setOwner(null);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public ApplicationUser answers(Set<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public ApplicationUser addAnswers(Answer answer) {
        this.answers.add(answer);
        answer.setOwner(this);
        return this;
    }

    public ApplicationUser removeAnswers(Answer answer) {
        this.answers.remove(answer);
        answer.setOwner(null);
        return this;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUser)) {
            return false;
        }
        return id != null && id.equals(((ApplicationUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUser{" +
            "id=" + getId() +
            "}";
    }
}
