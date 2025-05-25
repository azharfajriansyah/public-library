/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.repository;

import data.models.Member;
import static data.repository.Repository.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Azhar
 */
public class MemberRepository extends Repository<Member> {

    @Override
    public ArrayList<Member> populate(String searchText) {
        String query = "SELECT * FROM members WHERE first_name LIKE '%" + searchText + "%' OR last_name LIKE '%" + searchText + "%' ORDER BY first_name";
        ArrayList<Member> members = new ArrayList<Member>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Processing the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("member_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String membershipDate = resultSet.getString("membership_date");
                String membershipStatus = resultSet.getString("membership_status");
                
                members.add(new Member(id, firstName, lastName, email, phoneNumber, address, membershipDate, membershipStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public void save(Member object) throws Exception {
        String query = String.format("""
                                     INSERT INTO members (first_name, last_name, email, phone_number, address, membership_date, membership_status)
                                     VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')
                                     """, object.firstName, object.lastName, object.email, object.phoneNumber, object.address, object.membershipDate, object.membershipStatus);
        executeUpdateQuery(query);
    }

    @Override
    public void update(Member object) throws Exception {
        String query = String.format("""
                                     UPDATE members SET first_name='%s', last_name='%s', email='%s', phone_number='%s', address='%s', membership_date='%s', membership_status='%s'
                                     WHERE member_id=%d
                                     """, object.firstName, object.lastName, object.email, object.phoneNumber, object.address, object.membershipDate, object.membershipStatus, object.id);                
        executeUpdateQuery(query);
    }

    @Override
    public void delete(String id) throws Exception {
        String query = "DELETE FROM members WHERE member_id = " +id+ "";
        executeUpdateQuery(query);
    }
    
}
