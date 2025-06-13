CREATE TABLE product (
    id BIGINT PRIMARY KEY,
    code CHAR(10) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    price_eur NUMERIC(12, 2) NOT NULL,
    price_usd NUMERIC(12, 2) NOT NULL,
    is_available BOOLEAN NOT NULL
);

CREATE SEQUENCE product_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;