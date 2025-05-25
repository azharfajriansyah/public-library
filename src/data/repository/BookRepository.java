/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.repository;

import data.models.Author;
import data.models.Book;
import data.models.Publisher;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Azhar
 */
public class BookRepository extends Repository<Book> {

    @Override
    public ArrayList<Book> populate(String searchText) {
        String query = "SELECT b.book_id, b.title, b.isbn, b.publisher_id, b.publication_year, b.genre, b.language, b.pages, b.cover_image_url, p.name publisher_name, p.image_url publisher_image_url, a.author_id, a.first_name, a.last_name FROM books b INNER JOIN publishers p ON (p.publisher_id = b.publisher_id) INNER JOIN authors a ON (a.author_id = (SELECT author_id FROM book_authors ba WHERE ba.book_id = b.book_id)) WHERE b.title LIKE '%" + searchText + "%' ORDER BY title;";
        ArrayList<Book> books = new ArrayList<Book>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Processing the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String isbn = resultSet.getString("isbn");
                int publisherId = resultSet.getInt("publisher_id");
                int publicationYear = resultSet.getInt("publication_year");
                String genre = resultSet.getString("genre");
                String language = resultSet.getString("language");
                int pages = resultSet.getInt("pages");
                String coverImageURL = resultSet.getString("cover_image_url");
                String publisherName = resultSet.getString("publisher_name");
                String publisherImageURL = resultSet.getString("publisher_image_url");
                int authorId = resultSet.getInt("author_id");
                String authorFirstName = resultSet.getString("first_name");
                String authorLastName = resultSet.getString("last_name");
                
                books.add(new Book(id, title, isbn, publicationYear, genre, language, pages, coverImageURL, new Publisher(publisherId, publisherName, publisherImageURL), new Author(authorId, authorFirstName, authorLastName, "", "")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void save(Book object) throws Exception {
        String query = String.format("""
                                     INSERT INTO books (title, isbn, publisher_id, publication_year, genre, language, pages, cover_image_url)
                                     VALUES ('%s', '%s', %d, '%s', '%s', '%s', %d, '%s')
                                     """, object.title, object.isbn, object.publisher.id, object.publicationYear, object.genre, object.language, object.pages, object.coverImageURL);
        int bookId = executeUpdateQuery(query);
        
        String query2 = String.format("""
                                     INSERT INTO book_authors (book_id, author_id)
                                     VALUES (%d, %d)
                                     """, bookId, object.author.id);
        executeUpdateQuery(query2);
    }

    @Override
    public void update(Book object) throws Exception {
        String query = String.format("""
                                     UPDATE books SET
                                     title = '%s',
                                     isbn = '%s',
                                     publisher_id = %d,
                                     publication_year = %d,
                                     genre = '%s',
                                     language = '%s',
                                     pages = %d,
                                     cover_image_url = '%s'
                                     WHERE book_id = %d
                                     """, object.title, object.isbn, object.publisher.id, object.publicationYear, object.genre, object.language, object.pages, object.coverImageURL, object.id);
        executeUpdateQuery(query);
    }

    @Override
    public void delete(String id) throws Exception {
        String query = "DELETE FROM books WHERE book_id = " +id+ "";
        executeUpdateQuery(query);
    }
    
}
