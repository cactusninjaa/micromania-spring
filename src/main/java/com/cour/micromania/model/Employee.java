package com.cour.micromania.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
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
}
