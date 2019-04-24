package fi.tuni.blogapp.blogapp.repository;

import fi.tuni.blogapp.blogapp.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAllByPostId(long postId);
}
