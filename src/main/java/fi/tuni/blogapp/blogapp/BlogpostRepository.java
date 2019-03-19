package fi.tuni.blogapp.blogapp;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogpostRepository extends CrudRepository<Blogpost, Long> {
    List<Blogpost> findByTitleOrderByTitleAsc(String title);
}
