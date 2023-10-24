package com.bidnest.model;

import com.bidnest.model.auction.Auction;
import com.bidnest.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "bid")
@Getter
@Setter
@ToString
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidder_id", nullable=false)
    private User bidder;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id", nullable=false)
    private Auction auction;

    @Column(precision = 7, scale = 2, nullable = false)
    private BigDecimal price;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp placedAt;
}
