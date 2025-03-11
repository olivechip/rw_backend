DROP TABLE IF EXISTS restaurants;

DROP TABLE IF EXISTS staff;

DROP TABLE IF EXISTS guests;

DROP TABLE IF EXISTS waitlist_entries;

CREATE TABLE restaurants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    cuisine_type VARCHAR(255),
    website VARCHAR(255),
    description TEXT,
    hours_of_operation TEXT
);

CREATE TABLE staff (
    id SERIAL PRIMARY KEY,
    restaurant_id INT NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL CHECK (role IN ('ADMIN', 'MANAGER', 'WAITSTAFF'))
);

CREATE TABLE guests (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    party_size INT NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE waitlist_entries (
    id SERIAL PRIMARY KEY,
    guest_id INT NOT NULL REFERENCES guests(id) ON DELETE CASCADE,
    status VARCHAR(50) NOT NULL CHECK (
        status IN ('WAITING', 'NOTIFIED', 'COMPLETED', 'CANCELED')
    ),
    join_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    notified_time TIMESTAMP DEFAULT NULL,
    completed_time TIMESTAMP DEFAULT NULL,
    canceled_time TIMESTAMP DEFAULT NULL
);

-- seeding database
INSERT INTO
    staff (username, pin, role)
VALUES
    ('admin1', '1234', 'ADMIN'),
    ('manager1', '4321', 'MANAGER'),
    ('waitstaff1', '1111', 'WAITSTAFF');

INSERT INTO
    guests (name, party_size, phone_number)
VALUES
    (
        'Terra',
        (random() * 7) :: int + 1,
        (random() * 10000000000) :: bigint
    ),
    (
        'Locke',
        (random() * 7) :: int + 1,
        (random() * 10000000000) :: bigint
    ),
    (
        'Celes',
        (random() * 7) :: int + 1,
        (random() * 10000000000) :: bigint
    ),
    (
        'Edgar',
        (random() * 7) :: int + 1,
        (random() * 10000000000) :: bigint
    );

INSERT INTO
    waitlist_entries (guest_id, status)
VALUES
    (1, 'WAITING'),
    (2, 'WAITING'),
    (3, 'NOTIFIED'),
    (4, 'NOTIFIED');