--
-- Create table for shopping cart
--
CREATE TABLE T_SHOPPING_CART (
  ID BIGINT NOT NULL,
  SINCE TIMESTAMP,
  NAME VARCHAR(32),
  TENANT INTEGER,
  PRIMARY KEY (ID)
);

CREATE SEQUENCE SEQ_ID_SHOPPING_CART START WITH 1000000 INCREMENT BY 10;