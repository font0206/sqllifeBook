package com.example.sqlife_demo;

public class Book {
    private int id;
    private String title;
    private int idAuthor;

    public Book(int id, String title, int idAuthor) {
        this.id = id;
        this.title = title;
        this.idAuthor = idAuthor;
    }

    public Book() {
        this.id=0;
        this.title = null;
        this.idAuthor = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", idAuthor=" + idAuthor +
                '}';
    }
}
