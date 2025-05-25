/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.models;

/**
 *
 * @author fdn-azhar
 */
public class Author {
    public int id;
    public String firstName;
    public String lastName;
    public String dob;
    public String nationality;
    
    public Author(int id, String firstName, String lastName, String dob, String nationality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationality = nationality;
    }
}
