CREATE TABLE bid
(
    bid_id      BIGSERIAL     NOT NULL  PRIMARY KEY,
    bidder_id   BIGINT        NOT NULL  REFERENCES users,
    auction_id  BIGINT        NOT NULL  REFERENCES auction,
    price       NUMERIC(7,2)  NOT NULL,
    placed_at   TIMESTAMP     NOT NULL
);