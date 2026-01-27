package com.example.Mini.Amazon.Clone.entity;

import com.example.Mini.Amazon.Clone.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled = true;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)   // One User has exactly One Cart,The Cart table owns the relationship, Any change to User should also affect Cart
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)  // One User can have multiple Orders, The Order table owns the relationship, Any change to User should also affect Orders
    @JsonIgnore
    private List<Order> orders;

    //@JoinColumn → “I OWN this relationship. The FK is in my table.”

    //mappedBy → “I DO NOT own this relationship. FK is in the other table.”
}
