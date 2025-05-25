/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.models;

/**
 *
 * @author Azhar
 */
public class AdminUser {
    public int id;
    public String username;
    public String password;
    public String fullName;
    public String email;
    public String role; // "admin" or "superadmin"
    public String createdAt;
    public String updatedAt;

    // Constructor
    public AdminUser() {
    }

    public AdminUser(int id, String username, String password, String fullName,
                     String email, String role,
                     String createdAt, String updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
