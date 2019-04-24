package fi.tuni.blogapp.blogapp.restcontroller;

import fi.tuni.blogapp.blogapp.model.User;
import fi.tuni.blogapp.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

/**
 * Rest controller for user database.
 */
@RestController
public class UserRestController {

    /**
     * User database.
     */
    @Autowired
    private UserRepository database;

    /**
     * Method for login.
     *
     * @param user Used to input user.
     * @return Logged in user or null.
     * @throws UserAuthenticationException Indicates error in user authentication.
     */
    @RequestMapping(value = "users/login", method = RequestMethod.POST)
    public User login(@RequestBody User user) throws UserAuthenticationException {
        if (user != null) {
            User userFromDatabase = database.findByUsername(user.getUsername());

            if (userFromDatabase == null)
                throw new UserAuthenticationException("User " + user.getUsername() + " not found.");

            if (user.getPassword().contentEquals(userFromDatabase.getPassword()))
                return userFromDatabase;
            else
                throw new UserAuthenticationException("Invalid password!");
        }

        return null;
    }

    /**
     * Exception class for failure cases.
     */
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    private class UserAuthenticationException extends AuthenticationException {

        /**
         * Method for exception message.
         *
         * @param msg Used to input message.
         */
        private UserAuthenticationException(String msg) {
            super(msg);
        }
    }
}
