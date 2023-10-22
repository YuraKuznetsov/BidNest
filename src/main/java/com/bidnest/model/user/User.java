package com.bidnest.model.user;

import com.bidnest.model.product.Product;
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
    private Integer id;

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
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void addProduct(Product product) {
        if (product == null)
            throw new NullPointerException("Can't add null Product");
        if (product.getSeller() != null)
            throw new IllegalStateException("Product is already assigned to a User");

        products.add(product);
        product.setSeller(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setSeller(null);
    }
}
