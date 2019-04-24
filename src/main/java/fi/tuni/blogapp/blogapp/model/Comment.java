package fi.tuni.blogapp.blogapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class for blogpost comment object.
 */
@Entity
public class Comment {

    /**
     * Auto-generated comment id.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Comments attached post id.
     */
    private Long postId;

    /**
     * Comment content.
     */
    private String content;

    /**
     * Date of comment creation.
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date = LocalDate.now();

    /**
     * Default constructor for comment object.
     */
    public Comment() {}

    /**
     * Constructor for comment object.
     *
     * @param content Used to input content.
     */
    public Comment(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }

    /**
     * Getter for comments date of creation.
     *
     * @return Comments date of creation.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setter for comments date of creation.
     *
     * @param date Used to input comments date of creation.
     */
    public void LocalDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Getter for comment id.
     *
     * @return Comment id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for comment id.
     *
     * @param id Used to input id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for comments attached post id.
     *
     * @return Attached post id.
     */
    public long getPostId(){
        return this.postId;
    }

    /**
     * Setter for comments attached post id.
     *
     * @param postId Used to input post id.
     */
    public void setPostId(long postId){
        this.postId = postId;
    }

    /**
     * Getter for comment content.
     *
     * @return Comment content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter for comment content.
     *
     * @param content Used to input content.
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id) &&
                postId.equals(comment.postId) &&
                content.equals(comment.content) &&
                date.equals(comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, content, date);
    }
}
