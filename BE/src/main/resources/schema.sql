CREATE SEQUENCE IF NOT EXISTS account_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS mortgage_application_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS mortgage_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS transaction_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE account
(
    id                BIGINT           NOT NULL,
    iban              VARCHAR(255)     NOT NULL,
    balance           DOUBLE PRECISION NOT NULL,
    currency          VARCHAR(255)     NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE,
    updated_at        TIMESTAMP WITHOUT TIME ZONE,
    customer_username VARCHAR(255),
    CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE customer
(
    username    VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    birth_date  TIMESTAMP WITHOUT TIME ZONE,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    email       VARCHAR(255),
    phone       VARCHAR(255),
    fiscal_code VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_customer PRIMARY KEY (username)
);

CREATE TABLE mortgage
(
    id            BIGINT NOT NULL,
    name          VARCHAR(255),
    description   VARCHAR(255),
    max_amount    DOUBLE PRECISION,
    max_ltv_ratio DOUBLE PRECISION,
    interest_amount   DOUBLE PRECISION,
    valid_from    TIMESTAMP WITHOUT TIME ZONE,
    valid_to      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_mortgage PRIMARY KEY (id)
);

CREATE TABLE mortgage_application
(
    id                BIGINT NOT NULL,
    customer_username VARCHAR(255),
    mortgage_id       BIGINT,
    applied_amount    DOUBLE PRECISION,
    interest_type     VARCHAR(255),
    interest_amount   DOUBLE PRECISION,
    mortgage_status            VARCHAR(255),
    customer          VARCHAR(255),
    mortgage          BIGINT,
    CONSTRAINT pk_mortgage_application PRIMARY KEY (id)
);

CREATE TABLE transaction
(
    id          BIGINT NOT NULL,
    amount      DOUBLE PRECISION,
    type        VARCHAR(255),
    description VARCHAR(255),
    timestamp   TIMESTAMP WITHOUT TIME ZONE,
    account_id  BIGINT,
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);

ALTER TABLE account
    ADD CONSTRAINT uc_account_customer_username UNIQUE (customer_username);

ALTER TABLE customer
    ADD CONSTRAINT uc_customer_email UNIQUE (email);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_CUSTOMER_USERNAME FOREIGN KEY (customer_username) REFERENCES customer (username);

ALTER TABLE mortgage_application
    ADD CONSTRAINT FK_MORTGAGE_APPLICATION_ON_CUSTOMER FOREIGN KEY (customer) REFERENCES customer (username);

ALTER TABLE mortgage_application
    ADD CONSTRAINT FK_MORTGAGE_APPLICATION_ON_MORTGAGE FOREIGN KEY (mortgage_id) REFERENCES mortgage (id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);