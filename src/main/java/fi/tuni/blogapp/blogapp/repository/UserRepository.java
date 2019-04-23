package fi.tuni.blogapp.blogapp.repository;

import fi.tuni.blogapp.blogapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
