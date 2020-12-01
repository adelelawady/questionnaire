package app.questionaire.org.service;

import app.questionaire.org.service.dto.CommentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link app.questionaire.org.domain.Comment}.
 */
public interface CommentService {

    /**
     * Save a comment.
     *
     * @param commentDTO the entity to save.
     * @return the persisted entity.
     */
    CommentDTO save(CommentDTO commentDTO);

    /**
     * Get all the comments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CommentDTO> findAll(Pageable pageable);

    /**
     * Get all the comments with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<CommentDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" comment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommentDTO> findOne(String id);

    /**
     * Delete the "id" comment.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
