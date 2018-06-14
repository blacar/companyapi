CREATE TABLE COMPANY (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  country varchar(255) NOT NULL,
  mail varchar(255),
  phone varchar(255),
  PRIMARY KEY (id)
);

CREATE TABLE OWNER (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  company BIGINT,
  PRIMARY KEY (id)
);