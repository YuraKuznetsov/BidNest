CREATE TABLE image
(
    image_id    BIGSERIAL    NOT NULL  PRIMARY KEY,
    product_id  BIGINT       NOT NULL  REFERENCES product,
    url         VARCHAR(50)  NOT NULL
);