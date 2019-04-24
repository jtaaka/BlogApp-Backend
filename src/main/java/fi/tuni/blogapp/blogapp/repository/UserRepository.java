package fi.tuni.blogapp.blogapp.repository;

import fi.tuni.blogapp.blogapp.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Crud repository for user database.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Method for finding user by username.
     *
     * @param username Used to input username.
     * @return User object.
     */
    User findByUsername(String username);
}
