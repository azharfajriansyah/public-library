/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.models;

/**
 *
 * @author Azhar
 */
public class Loan {
    public int loanId;
    public int copyId;
    public int memberId;
    public String loanDate;
    public String dueDate;
    public String returnDate;
    public String status; //On Loan, Returned, Overdue
    public String bookTitle;

    public Loan(int loanId, int copyId, int memberId, String loanDate, String dueDate, String returnDate, String status) {
        this.loanId = loanId;
        this.copyId = copyId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }
}
