/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.repository;

import data.models.Loan;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Azhar
 */
public class LoanRepository extends Repository<Loan> {
    @Override
    public ArrayList<Loan> populate(String searchText) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ArrayList<Loan> populateByMemberId(int memberId) {
        String query = String.format("""
                                     SELECT l.loan_id, l.copy_id, l.loan_date, l.due_date, b.title FROM loans l INNER JOIN copies c ON c.copy_id = l.copy_id INNER JOIN books b ON b.book_id = c.book_id WHERE l.member_id = %d  AND l.status = 'On Loan'
                                     """, memberId);
        ArrayList<Loan> loans = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Processing the result set
            while (resultSet.next()) {
                int loanId = resultSet.getInt("loan_id");
                int copyId = resultSet.getInt("copy_id");
                String loanDate = resultSet.getString("loan_date");
                String dueDate = resultSet.getString("due_date");
                String bookTitle = resultSet.getString("title");
                
                Loan loan = new Loan(loanId, copyId, memberId, loanDate, dueDate, "", "On Loan");
                loan.bookTitle = bookTitle;
                
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }
    
    public void save(Loan object) throws Exception {
        String query = String.format("""
                                     INSERT INTO loans (copy_id, member_id, loan_date, due_date, status)
                                     VALUES (%d, %d, '%s', '%s', '%s')
                                     """, object.copyId, object.memberId, object.loanDate, object.dueDate, object.status);
        executeUpdateQuery(query);
        
        String query2 = String.format("""
                                     UPDATE copies SET
                                     status = 'On Loan'
                                     WHERE copy_id = %d
                                     """, object.copyId);
        executeUpdateQuery(query2);
    }

    @Override
    public void update(Loan object) throws Exception {
        String query = String.format("""
                                     UPDATE loans SET
                                     status = 'Returned', return_date = NOW()
                                     WHERE loan_id = %d
                                     """, object.loanId);
        executeUpdateQuery(query);
        
        String query2 = String.format("""
                                     UPDATE copies SET
                                     status = 'Available'
                                     WHERE copy_id = %d
                                     """, object.copyId);
        executeUpdateQuery(query2);
    }

    @Override
    public void delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
