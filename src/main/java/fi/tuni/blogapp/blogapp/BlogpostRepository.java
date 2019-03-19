package fi.tuni.blogapp.blogapp;

import org.springframework.data.repository.CrudRepository;

public interface BlogpostRepository extends CrudRepository<Blogpost, Long> {

}
