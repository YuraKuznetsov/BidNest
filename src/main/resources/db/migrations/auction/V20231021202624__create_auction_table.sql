CREATE TABLE auction
(
    auction_id  BIGSERIAL  NOT NULL  PRIMARY KEY,
    product_id  BIGINT     NOT NULL  REFERENCES product,
    start_at    TIMESTAMP  NOT NULL
);