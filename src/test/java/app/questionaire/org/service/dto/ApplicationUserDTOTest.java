package app.questionaire.org.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import app.questionaire.org.web.rest.TestUtil;

public class ApplicationUserDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApplicationUserDTO.class);
        ApplicationUserDTO applicationUserDTO1 = new ApplicationUserDTO();
        applicationUserDTO1.setId("id1");
        ApplicationUserDTO applicationUserDTO2 = new ApplicationUserDTO();
        assertThat(applicationUserDTO1).isNotEqualTo(applicationUserDTO2);
        applicationUserDTO2.setId(applicationUserDTO1.getId());
        assertThat(applicationUserDTO1).isEqualTo(applicationUserDTO2);
        applicationUserDTO2.setId("id2");
        assertThat(applicationUserDTO1).isNotEqualTo(applicationUserDTO2);
        applicationUserDTO1.setId(null);
        assertThat(applicationUserDTO1).isNotEqualTo(applicationUserDTO2);
    }
}
