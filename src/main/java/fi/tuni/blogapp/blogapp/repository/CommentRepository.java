package fi.tuni.blogapp.blogapp.repository;

import fi.tuni.blogapp.blogapp.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Crud repository for comment database.
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

    /**
     * Method for getting all comments by post id.
     *
     * @param postId Used to input post id.
     * @return List of comments.
     */
    List<Comment> findAllByPostId(long postId);
}
