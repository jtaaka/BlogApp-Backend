package fi.tuni.blogapp.blogapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Class for user object.
 */
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {

    /**
     * Auto-generated user id.
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * Users username.
     */
    @Column(name="username", unique=true)
    @NotNull
    @NotEmpty
    private String username;

    /**
     * User password.
     */
    @Column(name="password")
    @NotNull
    @NotEmpty
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    /**
     * Default constructor for user object.
     */
    public User() {}

    /**
     * Constructor for user object.
     *
     * @param username Used to input username.
     * @param password Used to input password.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Getter for user id.
     *
     * @return User id.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for user id.
     *
     * @id User id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Setter for users username.
     *
     * @param username Used to input username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for users username.
     *
     * @return Users username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for users password.
     *
     * @param password Used to input password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for users password.
     *
     * @return Users password.
     */
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                username.equals(user.username) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
