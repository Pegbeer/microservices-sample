CREATE TABLE IF NOT EXISTS customer
(
    CUSTOMER_ID     INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME            VARCHAR(100) NOT NULL,
    EMAIL           VARCHAR(100) NOT NULL,
    MOBILE_NUMBER   VARCHAR(20) NOT NULL,
    CREATED_AT      DATE NOT NULL,
    CREATED_BY      VARCHAR(20) NOT NULL,
    UPDATED_AT      DATE DEFAULT NULL,
    UPDATED_BY      VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS accounts
(
    ACCOUNT_NUMBER      INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    CUSTOMER_ID         INT NOT NULL,
    ACCOUNT_TYPE        VARCHAR(100) NOT NULL,
    BRANCH_ADDRESS      VARCHAR(200) NOT NULL,
    CREATED_AT          DATE NOT NULL,
    CREATED_BY          VARCHAR(20) NOT NULL,
    UPDATED_AT          DATE DEFAULT NULL,
    UPDATED_BY          VARCHAR(20) DEFAULT NULL
);