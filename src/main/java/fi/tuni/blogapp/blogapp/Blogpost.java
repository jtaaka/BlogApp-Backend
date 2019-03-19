package fi.tuni.blogapp.blogapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Blogpost {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private Date date = new Date();

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blogpost blogpost = (Blogpost) o;
        return id.equals(blogpost.id) &&
                title.equals(blogpost.title) &&
                content.equals(blogpost.content) &&
                date.equals(blogpost.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, date);
    }

    @Override
    public String toString() {
        return "Blogpost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
