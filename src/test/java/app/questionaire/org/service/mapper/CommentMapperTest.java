package app.questionaire.org.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommentMapperTest {

    private CommentMapper commentMapper;

    @BeforeEach
    public void setUp() {
        commentMapper = new CommentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(commentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(commentMapper.fromId(null)).isNull();
    }
}
