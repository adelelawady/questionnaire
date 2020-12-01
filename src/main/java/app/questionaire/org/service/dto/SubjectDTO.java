package app.questionaire.org.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link app.questionaire.org.domain.Subject} entity.
 */
public class SubjectDTO implements Serializable {
    
    private String id;

    private String name;


    private String creatorId;
    
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String applicationUserId) {
        this.creatorId = applicationUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubjectDTO)) {
            return false;
        }

        return id != null && id.equals(((SubjectDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubjectDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", creatorId='" + getCreatorId() + "'" +
            "}";
    }
}
