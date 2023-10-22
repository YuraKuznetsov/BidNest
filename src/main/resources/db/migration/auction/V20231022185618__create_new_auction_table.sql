DROP TABLE auction;

CREATE TABLE auction
(
    auction_id   BIGSERIAL     NOT NULL  PRIMARY KEY,
    seller_id    BIGINT        NOT NULL  REFERENCES users,
    title        VARCHAR(30)   NOT NULL,
    description  VARCHAR(255)  NOT NULL,
    category     VARCHAR(20),
    init_price   NUMERIC(7,2)  NOT NULL,
    currency     VARCHAR(3)    NOT NULL,
    start_at     TIMESTAMP     NOT NULL,
    ended        BOOLEAN       DEFAULT false
);





