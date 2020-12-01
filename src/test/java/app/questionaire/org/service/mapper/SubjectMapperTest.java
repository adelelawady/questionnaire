package app.questionaire.org.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SubjectMapperTest {

    private SubjectMapper subjectMapper;

    @BeforeEach
    public void setUp() {
        subjectMapper = new SubjectMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(subjectMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(subjectMapper.fromId(null)).isNull();
    }
}
