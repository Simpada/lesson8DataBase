package no.kristiania.library;

import jakarta.persistence.*;

@Entity
@NamedQuery(
        name = "findByAuthor", query = "select b from Book b where b.author = :authorName"
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;
    private int year;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setYear(int year) {

        this.year = year;
    }

    public int getYear() {
        return year;
    }

}
