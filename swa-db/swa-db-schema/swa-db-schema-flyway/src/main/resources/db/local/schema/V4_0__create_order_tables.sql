--
-- Create table for order
--

CREATE TABLE T_ORDER (
  ID BIGINT NOT NULL,
  CREATED TIMESTAMP NOT NULL,
  COMPLETED TIMESTAMP,
  CONFIRMED TIMESTAMP,
  APPROVED TIMESTAMP,
  PRIMARY KEY (ID)
);

CREATE SEQUENCE SEQ_ID_ORDER START WITH 1000000 INCREMENT BY 10;