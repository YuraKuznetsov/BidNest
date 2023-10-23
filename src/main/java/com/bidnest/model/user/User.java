package com.bidnest.model.user;

import com.bidnest.model.Bid;
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
    private Set<Auction> auctions = new HashSet<>();

    @OneToMany(mappedBy = "bidder", cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private Set<Bid> bids = new HashSet<>();

    public Set<Auction> getAuctions() {
        return Collections.unmodifiableSet(auctions);
    }

    public void addAuction(Auction auction) {
        if (auction == null)
            throw new NullPointerException("Can't add null Auction");
        if (auction.getSeller() != null)
            throw new IllegalStateException("Auction is already assigned to a User");

        auctions.add(auction);
        auction.setSeller(this);
    }

    public void removeAuction(Auction auction) {
        auctions.remove(auction);
        auction.setSeller(null);
    }

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public void addBid(Bid bid) {
        if (bid == null)
            throw new NullPointerException("Can't add null Bid");
        if (bid.getAuction() != null)
            throw new IllegalStateException("Bid is already assigned to a User");

        bids.add(bid);
        bid.setBidder(this);
    }

    public void removeBid(Bid bid) {
        bids.remove(bid);
        bid.setBidder(null);
    }
}
