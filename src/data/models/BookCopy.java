/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.models;

/**
 *
 * @author Azhar
 */
public class BookCopy {
    public int id;
    public String status; // Available, On Loan, Lost
    public Book book;

    public BookCopy(int id, String status, Book book) {
        this.id = id;
        this.status = status;
        this.book = book;
    }
}
