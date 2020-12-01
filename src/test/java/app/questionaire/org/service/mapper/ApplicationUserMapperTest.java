package app.questionaire.org.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationUserMapperTest {

    private ApplicationUserMapper applicationUserMapper;

    @BeforeEach
    public void setUp() {
        applicationUserMapper = new ApplicationUserMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(applicationUserMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(applicationUserMapper.fromId(null)).isNull();
    }
}
