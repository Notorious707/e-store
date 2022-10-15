package com.estore.accountservice.model;

import com.estore.accountservice.model.roles.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Embedded
    private Address shippingAddress;
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentMethod preferredPaymentMethod;
    @Temporal(value = TemporalType.TIMESTAMP)
    protected Date createDate = new Date();

    public Account(String firstName, String lastName, String email,String password, Address shippingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.shippingAddress = shippingAddress;
    }


}
