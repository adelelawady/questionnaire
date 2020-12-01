package app.questionaire.org.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link app.questionaire.org.domain.ApplicationUser} entity.
 */
public class ApplicationUserDTO implements Serializable {
    
    private String id;


    private String internalUserId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInternalUserId() {
        return internalUserId;
    }

    public void setInternalUserId(String userId) {
        this.internalUserId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUserDTO)) {
            return false;
        }

        return id != null && id.equals(((ApplicationUserDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUserDTO{" +
            "id=" + getId() +
            ", internalUserId='" + getInternalUserId() + "'" +
            "}";
    }
}
