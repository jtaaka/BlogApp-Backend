package fi.tuni.blogapp.blogapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDate;

/**
 * Class for Blogpost object.
 */
@Entity
public class Blogpost {

    /**
     * Auto-generated id for blogpost.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Blogpost title.
     */
    private String title;

    /**
     * Blogpost content.
     */
    @Lob
    private String content;

    /**
     * Date of blogpost creation.
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date = LocalDate.now();

    /**
     * Default constructor for blogpost.
     */
    public Blogpost() {}

    /**
     * Constructor for blogpost object.
     *
     * @param title Used to input title.
     * @param content Used to input content.
     */
    public Blogpost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Getter for blogpost id.
     *
     * @return Blogpost id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for blogpost id.
     *
     * @param id Used to input id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for blogpost title.
     *
     * @return Blogpost title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for blogpost title.
     *
     * @param title Used to input title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for blogpost content.
     *
     * @return Blogpost content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter for blogpost content.
     *
     * @param content Used to input content.
     */
    public void setContent(String content) {
        this.content = content;
    }
}
