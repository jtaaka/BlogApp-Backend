package fi.tuni.blogapp.blogapp.repository;

import fi.tuni.blogapp.blogapp.model.Blogpost;
import org.springframework.data.repository.CrudRepository;

/**
 * Crud repository for blogpost database.
 */
public interface BlogpostRepository extends CrudRepository<Blogpost, Long> {}
