/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.repository;

import data.models.Publisher;
import static data.repository.Repository.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Azhar
 */
public class PublisherRepository extends Repository<Publisher> {

    @Override
    public ArrayList<Publisher> populate(String searchText) {
        String query = "SELECT * FROM publishers WHERE name LIKE '%" + searchText + "%' ORDER BY name";
        ArrayList<Publisher> publishers = new ArrayList<Publisher>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Processing the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("publisher_id");
                String name = resultSet.getString("name");
                String imageURL = resultSet.getString("image_url");
                
                publishers.add(new Publisher(id, name, imageURL));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publishers;
    }

    @Override
    public void save(Publisher object) throws Exception {
        String query = String.format("""
                                     INSERT INTO publishers (name, image_url)
                                     VALUES ('%s', '%s')
                                     """, object.name, object.imageURL);
        executeUpdateQuery(query);
    }

    @Override
    public void update(Publisher object) throws Exception {
        String query = String.format("""
                                     UPDATE publishers SET name='%s', image_url='%s'
                                     WHERE publisher_id=%d
                                     """, object.name, object.imageURL, object.id);
        executeUpdateQuery(query);
    }

    @Override
    public void delete(String id) throws Exception {
        String query = "DELETE FROM publishers WHERE publisher_id = " +id+ "";
        executeUpdateQuery(query);
    }
    
}
