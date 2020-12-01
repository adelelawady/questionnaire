package app.questionaire.org.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import app.questionaire.org.web.rest.TestUtil;

public class QuestionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionDTO.class);
        QuestionDTO questionDTO1 = new QuestionDTO();
        questionDTO1.setId("id1");
        QuestionDTO questionDTO2 = new QuestionDTO();
        assertThat(questionDTO1).isNotEqualTo(questionDTO2);
        questionDTO2.setId(questionDTO1.getId());
        assertThat(questionDTO1).isEqualTo(questionDTO2);
        questionDTO2.setId("id2");
        assertThat(questionDTO1).isNotEqualTo(questionDTO2);
        questionDTO1.setId(null);
        assertThat(questionDTO1).isNotEqualTo(questionDTO2);
    }
}
