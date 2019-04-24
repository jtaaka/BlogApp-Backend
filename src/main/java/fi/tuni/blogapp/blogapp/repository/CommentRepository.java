package fi.tuni.blogapp.blogapp.repository;

import fi.tuni.blogapp.blogapp.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {}
