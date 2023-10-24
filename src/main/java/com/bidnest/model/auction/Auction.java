package com.bidnest.model.auction;

import com.bidnest.model.Bid;
import com.bidnest.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auction")
@Getter
@Setter
@ToString
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable=false)
    private User seller;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(name = "init_price", precision = 7, scale = 2, nullable = false)
    private BigDecimal initPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Column(name = "start_at", nullable = false)
    private Timestamp startAt;

    @Column(name = "end_at", nullable = false)
    private Timestamp endAt;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "auction", cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private Set<Bid> bids = new HashSet<>();

    public Set<Image> getImages() {
        return Collections.unmodifiableSet(images);
    }

    public void addImage(Image image) {
        if (image == null)
            throw new NullPointerException("Can't add null Image");
        if (image.getAuction() != null)
            throw new IllegalStateException("Image is already assigned to a Auction");

        images.add(image);
        image.setAuction(this);
    }

    public void removeImage(Image image) {
        images.remove(image);
        image.setAuction(null);
    }

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public void addBid(Bid bid) {
        if (bid == null)
            throw new NullPointerException("Can't add null Bid");
        if (bid.getAuction() != null)
            throw new IllegalStateException("Bid is already assigned to a Auction");

        bids.add(bid);
        bid.setAuction(this);
    }

    public void removeBid(Bid bid) {
        bids.remove(bid);
        bid.setAuction(null);
    }
}
