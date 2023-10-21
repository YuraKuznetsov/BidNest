CREATE TABLE product
(
    product_id   BIGSERIAL     NOT NULL  PRIMARY KEY,
    seller_id    BIGINT        NOT NULL  REFERENCES users,
    name         VARCHAR(30)   NOT NULL,
    description  VARCHAR(255)  NOT NULL,
    category     VARCHAR(20)   NOT NULL,
    price        NUMERIC(7,2)  NOT NULL,
    currency     VARCHAR(3),
    sold         BOOLEAN
);