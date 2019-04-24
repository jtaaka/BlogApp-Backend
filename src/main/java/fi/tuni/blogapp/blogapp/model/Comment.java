package fi.tuni.blogapp.blogapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private Long postId;

    private String content;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date = LocalDate.now();

    public Comment() {}

    public Comment(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void LocalDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPostId(){
        return this.postId;
    }

    public void setPostId(long postId){
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

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
