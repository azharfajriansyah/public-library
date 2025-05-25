/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.repository;

import data.models.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Azhar
 */
public class AuthorRepository extends Repository<Author> {

    @Override
    public ArrayList<Author> populate(String searchText) {
        String query = "SELECT * FROM authors WHERE first_name LIKE '%" + searchText + "%' OR last_name LIKE '%" + searchText + "%' ORDER BY first_name";
        ArrayList<Author> authors = new ArrayList<Author>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Processing the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("author_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String dob = resultSet.getString("birth_date");
                String nationality = resultSet.getString("nationality");
                
                authors.add(new Author(id, firstName, lastName, dob, nationality));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public void save(Author object) throws Exception {
        String query = "INSERT INTO authors (first_name, last_name, birth_date, nationality) VALUES('"+ object.firstName +"','"+ object.lastName +"','"+ object.dob +"','"+ object.nationality +"')";
        executeUpdateQuery(query);
    }

    @Override
    public void update(Author object) throws Exception {
        String query = "UPDATE authors SET first_name = '" + object.firstName + "', last_name = '" + object.lastName + "', birth_date = '" + object.dob + "', nationality = '" + object.nationality + "' WHERE author_id = " + object.id + "";
        executeUpdateQuery(query);
    }

    @Override
    public void delete(String id) throws Exception {
        String query = "DELETE FROM authors WHERE author_id = " +id+ "";
        executeUpdateQuery(query);
    }
    
}
