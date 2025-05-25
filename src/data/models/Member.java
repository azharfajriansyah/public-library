/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.models;

/**
 *
 * @author Azhar
 */
public class Member {
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String address;
    public String membershipDate;
    public String membershipStatus; // Active, Inactive
    
    public Member(int id, String firstName, String lastName, String email, String phoneNumber, String address, String membershipDate, String membershipStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.membershipDate = membershipDate;
        this.membershipStatus = membershipStatus;
    }
}
