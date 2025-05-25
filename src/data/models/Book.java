/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.models;

/**
 *
 * @author Azhar
 */
public class Book {
    public int id;
    public String title;
    public String isbn;
    public int publicationYear;
    public String genre;
    public String language;
    public int pages;
    public String coverImageURL;
    public Publisher publisher;
    public Author author;

    public Book(int id, String title, String isbn, int publicationYear, String genre, String language, int pages, String coverImageURL, Publisher publisher, Author author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.language = language;
        this.pages = pages;
        this.coverImageURL = coverImageURL;
        this.publisher = publisher;
        this.author = author;
    }
}
