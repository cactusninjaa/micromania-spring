package com.cour.micromania.model;

import java.util.Date;

public class Employee {
    private static int CPT_ID = 1;
    private final int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private Role role;

    public Employee(String firstName, String lastName, Date birthDate, String email, Role role) {
        this.id = CPT_ID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
