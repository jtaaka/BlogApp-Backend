package fi.tuni.blogapp.blogapp;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDate;

@Entity
public class Blogpost {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date = LocalDate.now();

    public Blogpost() {}

    public Blogpost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
