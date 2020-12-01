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
 * A Subject.
 */
@Document(collection = "subject")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @DBRef
    @Field("creator")
    @JsonIgnoreProperties(value = "subjects", allowSetters = true)
    private ApplicationUser creator;

    @DBRef
    @Field("questions")
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

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

    public Subject name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicationUser getCreator() {
        return creator;
    }

    public Subject creator(ApplicationUser applicationUser) {
        this.creator = applicationUser;
        return this;
    }

    public void setCreator(ApplicationUser applicationUser) {
        this.creator = applicationUser;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Subject questions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Subject addQuestion(Question question) {
        this.questions.add(question);
        question.getSubjects().add(this);
        return this;
    }

    public Subject removeQuestion(Question question) {
        this.questions.remove(question);
        question.getSubjects().remove(this);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subject)) {
            return false;
        }
        return id != null && id.equals(((Subject) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Subject{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
