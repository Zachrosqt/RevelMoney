INSERT INTO customer (username, password, birth_date, first_name, last_name, email, phone, fiscal_code, created_at,
                      updated_at)
VALUES ('alice',
        '$2a$10$4XmZQbGKVHCnplC4t6LzM.n7HoMS14.7e.0FkZwr/VLGOV.U.ww0G', -- Password123
        '1990-05-10 00:00:00',
        'Alice',
        'Rossi',
        'alice.rossi@example.com',
        '+390123456789',
        'RSSALC90E50H501X',
        NOW(),
        NOW());


INSERT INTO account (id, iban, balance, currency, created_at, updated_at, customer_username)
VALUES (nextval('account_seq'), 'IT60X0542811101000000123456', 3558.50, 'EUR', now(), now(), 'alice');


INSERT INTO transaction (id, amount, type, description, timestamp, account_id)
VALUES (nextval('transaction_seq'), 3500.00, 'IN', 'Initial deposit', dateadd('day', -120, current_timestamp), 1),
       (nextval('transaction_seq'), -200.00, 'OUT', 'Shopping online', dateadd('day', -100, current_timestamp), 1),
       (nextval('transaction_seq'), -500.00, 'OUT', 'House chores', dateadd('day', -70, current_timestamp), 1),
       (nextval('transaction_seq'), -12.00, 'OUT', 'Just Eat', dateadd('day', -65, current_timestamp), 1),
       (nextval('transaction_seq'), -30.00, 'OUT', 'Go Kart Rental', dateadd('day', -50, current_timestamp), 1),
       (nextval('transaction_seq'), -200.00, 'OUT', 'ATM withdrawal', dateadd('day', -10, current_timestamp), 1),
       (nextval('transaction_seq'), 1000.50, 'IN', 'Salary', dateadd('day', -2, current_timestamp), 1);

INSERT INTO mortgage (id, name, description, max_amount, max_ltv_ratio, valid_from, valid_to, interest_amount)
VALUES
    (nextval('mortgage_seq'), 'Mutuo Casa Giovani', 'Mutuo agevolato per under 36', 200000, 0.8, TIMESTAMP '2024-01-01 00:00:00', TIMESTAMP '2025-12-31 23:59:59', 2.5),
    (nextval('mortgage_seq'), 'Mutuo Green', 'Mutuo per acquisto abitazioni ecologiche', 250000, 0.85, TIMESTAMP '2024-01-01 00:00:00', TIMESTAMP '2025-12-31 23:59:59', 2.2),
    (nextval('mortgage_seq'), 'Mutuo Prima Casa', 'Mutuo standard per prima casa', 180000, 0.75, TIMESTAMP '2024-01-01 00:00:00', TIMESTAMP '2025-12-31 23:59:59', 2.1);

-- === Mortgage applications for alice ===
INSERT INTO mortgage_application (
    id, customer_username, mortgage_id, applied_amount, interest_type, interest_amount, mortgage_status, customer, mortgage
)
VALUES
    (nextval('mortgage_application_seq'), 'alice', 1, 150000, 'FIXED', 2.5, 'ACCEPTED','alice', 1),
    (nextval('mortgage_application_seq'), 'alice', 3, 120000, 'VARIABLE', 2.1, 'REJECTED', 'alice', 3);