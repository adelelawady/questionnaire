package app.questionaire.org.web.rest;

import app.questionaire.org.QuestionnaireApp;
import app.questionaire.org.domain.Comment;
import app.questionaire.org.repository.CommentRepository;
import app.questionaire.org.service.CommentService;
import app.questionaire.org.service.dto.CommentDTO;
import app.questionaire.org.service.mapper.CommentMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import app.questionaire.org.domain.enumeration.CommentType;
/**
 * Integration tests for the {@link CommentResource} REST controller.
 */
@SpringBootTest(classes = QuestionnaireApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommentResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final CommentType DEFAULT_COMMENT_TYPE = CommentType.Answer;
    private static final CommentType UPDATED_COMMENT_TYPE = CommentType.Question;

    private static final String DEFAULT_REF_QUESTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_REF_QUESTION_ID = "BBBBBBBBBB";

    @Autowired
    private CommentRepository commentRepository;

    @Mock
    private CommentRepository commentRepositoryMock;

    @Autowired
    private CommentMapper commentMapper;

    @Mock
    private CommentService commentServiceMock;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MockMvc restCommentMockMvc;

    private Comment comment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comment createEntity() {
        Comment comment = new Comment()
            .name(DEFAULT_NAME)
            .commentType(DEFAULT_COMMENT_TYPE)
            .refQuestionId(DEFAULT_REF_QUESTION_ID);
        return comment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comment createUpdatedEntity() {
        Comment comment = new Comment()
            .name(UPDATED_NAME)
            .commentType(UPDATED_COMMENT_TYPE)
            .refQuestionId(UPDATED_REF_QUESTION_ID);
        return comment;
    }

    @BeforeEach
    public void initTest() {
        commentRepository.deleteAll();
        comment = createEntity();
    }

    @Test
    public void createComment() throws Exception {
        int databaseSizeBeforeCreate = commentRepository.findAll().size();
        // Create the Comment
        CommentDTO commentDTO = commentMapper.toDto(comment);
        restCommentMockMvc.perform(post("/api/comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentDTO)))
            .andExpect(status().isCreated());

        // Validate the Comment in the database
        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList).hasSize(databaseSizeBeforeCreate + 1);
        Comment testComment = commentList.get(commentList.size() - 1);
        assertThat(testComment.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testComment.getCommentType()).isEqualTo(DEFAULT_COMMENT_TYPE);
        assertThat(testComment.getRefQuestionId()).isEqualTo(DEFAULT_REF_QUESTION_ID);
    }

    @Test
    public void createCommentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commentRepository.findAll().size();

        // Create the Comment with an existing ID
        comment.setId("existing_id");
        CommentDTO commentDTO = commentMapper.toDto(comment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommentMockMvc.perform(post("/api/comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comment in the database
        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllComments() throws Exception {
        // Initialize the database
        commentRepository.save(comment);

        // Get all the commentList
        restCommentMockMvc.perform(get("/api/comments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comment.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].commentType").value(hasItem(DEFAULT_COMMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].refQuestionId").value(hasItem(DEFAULT_REF_QUESTION_ID)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCommentsWithEagerRelationshipsIsEnabled() throws Exception {
        when(commentServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCommentMockMvc.perform(get("/api/comments?eagerload=true"))
            .andExpect(status().isOk());

        verify(commentServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCommentsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(commentServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCommentMockMvc.perform(get("/api/comments?eagerload=true"))
            .andExpect(status().isOk());

        verify(commentServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getComment() throws Exception {
        // Initialize the database
        commentRepository.save(comment);

        // Get the comment
        restCommentMockMvc.perform(get("/api/comments/{id}", comment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(comment.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.commentType").value(DEFAULT_COMMENT_TYPE.toString()))
            .andExpect(jsonPath("$.refQuestionId").value(DEFAULT_REF_QUESTION_ID));
    }
    @Test
    public void getNonExistingComment() throws Exception {
        // Get the comment
        restCommentMockMvc.perform(get("/api/comments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateComment() throws Exception {
        // Initialize the database
        commentRepository.save(comment);

        int databaseSizeBeforeUpdate = commentRepository.findAll().size();

        // Update the comment
        Comment updatedComment = commentRepository.findById(comment.getId()).get();
        updatedComment
            .name(UPDATED_NAME)
            .commentType(UPDATED_COMMENT_TYPE)
            .refQuestionId(UPDATED_REF_QUESTION_ID);
        CommentDTO commentDTO = commentMapper.toDto(updatedComment);

        restCommentMockMvc.perform(put("/api/comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentDTO)))
            .andExpect(status().isOk());

        // Validate the Comment in the database
        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList).hasSize(databaseSizeBeforeUpdate);
        Comment testComment = commentList.get(commentList.size() - 1);
        assertThat(testComment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testComment.getCommentType()).isEqualTo(UPDATED_COMMENT_TYPE);
        assertThat(testComment.getRefQuestionId()).isEqualTo(UPDATED_REF_QUESTION_ID);
    }

    @Test
    public void updateNonExistingComment() throws Exception {
        int databaseSizeBeforeUpdate = commentRepository.findAll().size();

        // Create the Comment
        CommentDTO commentDTO = commentMapper.toDto(comment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommentMockMvc.perform(put("/api/comments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comment in the database
        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteComment() throws Exception {
        // Initialize the database
        commentRepository.save(comment);

        int databaseSizeBeforeDelete = commentRepository.findAll().size();

        // Delete the comment
        restCommentMockMvc.perform(delete("/api/comments/{id}", comment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
