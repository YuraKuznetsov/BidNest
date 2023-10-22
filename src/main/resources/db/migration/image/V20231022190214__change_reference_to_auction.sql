ALTER TABLE image DROP CONSTRAINT image_product_id_fkey;

ALTER TABLE image RENAME COLUMN product_id TO auction_id;

ALTER TABLE image
ADD CONSTRAINT image_auction_id_fkey FOREIGN KEY (auction_id)
REFERENCES auction(auction_id);