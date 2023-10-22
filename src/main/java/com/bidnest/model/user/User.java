package com.bidnest.model.user;

import com.bidnest.model.auction.Auction;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private String country;

    private String city;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private Set<Auction> products = new HashSet<>();

    public Set<Auction> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void addProduct(Auction product) {
        if (product == null)
            throw new NullPointerException("Can't add null Product");
        if (product.getSeller() != null)
            throw new IllegalStateException("Product is already assigned to a User");

        products.add(product);
        product.setSeller(this);
    }

    public void removeProduct(Auction product) {
        products.remove(product);
        product.setSeller(null);
    }
}
