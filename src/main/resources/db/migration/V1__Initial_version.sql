
CREATE TABLE IF NOT EXISTS parts(
    id 	BIGINT NOT NULL,
    part_id VARCHAR (50) NOT NULL,
    part_name VARCHAR (100) NOT NULL ,
    part_number VARCHAR (100) NOT NULL,
    manufacturer VARCHAR (100),
    price_RON NUMERIC (19, 2) NOT NULL,
    price_EURO NUMERIC (19, 2) NOT NULL,
    price_USD NUMERIC (19, 2) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS users(
    id BIGINT NOT NULL,
    username VARCHAR (255) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS parts_users(
  parts_id BIGINT UNIQUE NOT NULL,
  users_id BIGINT UNIQUE NOT NULL,
  CONSTRAINT fk_parts
      FOREIGN KEY (parts_id) REFERENCES parts (id),
  CONSTRAINT fk_users
      FOREIGN KEY (users_id) REFERENCES users (id)
);

CREATE SEQUENCE wish_part
INCREMENT 50
START 1;

CREATE SEQUENCE wish_user
INCREMENT 50
START 1;