/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.repository;

import data.models.Book;
import data.models.BookCopy;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Azhar
 */
public class BookCopyRepository extends Repository<BookCopy> {

    @Override
    public ArrayList<BookCopy> populate(String searchText) {
        String query = "SELECT b.title, c.copy_id, c.status, b.book_id, b.cover_image_url FROM copies c INNER JOIN books b ON (b.book_id = c.book_id) WHERE b.title LIKE '%" + searchText + "%' ORDER BY b.title, c.copy_id";
        ArrayList<BookCopy> bookCopies = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Processing the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("copy_id");
                String status = resultSet.getString("status");
                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String coverImageURL = resultSet.getString("cover_image_url");
                
                bookCopies.add(new BookCopy(id, status, new Book(bookId, title, "", 0, "", "", 0, coverImageURL, null, null)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookCopies;
    }
    
    public ArrayList<BookCopy> populateAvailable(String searchText) {
        String query = "SELECT b.title, c.copy_id, c.status, b.book_id, b.cover_image_url FROM copies c INNER JOIN books b ON (b.book_id = c.book_id) WHERE b.title LIKE '%" + searchText + "%' AND c.status = 'Available' ORDER BY b.title, c.copy_id";
        ArrayList<BookCopy> bookCopies = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Processing the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("copy_id");
                String status = resultSet.getString("status");
                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String coverImageURL = resultSet.getString("cover_image_url");
                
                bookCopies.add(new BookCopy(id, status, new Book(bookId, title, "", 0, "", "", 0, coverImageURL, null, null)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookCopies;
    }

    @Override
    public void save(BookCopy object) throws Exception {
        String query = String.format("""
                                     INSERT INTO copies (book_id, status)
                                     VALUES (%d, '%s')
                                     """, object.book.id, object.status);
        executeUpdateQuery(query);
    }

    @Override
    public void update(BookCopy object) throws Exception {
        String query = String.format("""
                                     UPDATE copies SET
                                     status = '%s'
                                     WHERE copy_id = %d
                                     """, object.status, object.id);
        executeUpdateQuery(query);
    }

    @Override
    public void delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
