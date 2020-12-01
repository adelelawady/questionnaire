package app.questionaire.org.repository;

import app.questionaire.org.domain.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Comment entity.
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    @Query("{}")
    Page<Comment> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Comment> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Comment> findOneWithEagerRelationships(String id);
}
