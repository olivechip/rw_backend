DROP TABLE IF EXISTS restaurants;

DROP TABLE IF EXISTS staff;

DROP TABLE IF EXISTS guests;

DROP TABLE IF EXISTS waitlist_entries;

CREATE TABLE restaurants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    cuisine_type VARCHAR(255),
    website VARCHAR(255) UNIQUE,
    description TEXT,
    hours_of_operation TEXT,
    CONSTRAINT unique_name_address UNIQUE (name, address)
);

CREATE TABLE staff (
    id SERIAL PRIMARY KEY,
    restaurant_id INT NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
    first_name VARCHAR(15) NOT NULL,
    last_name VARCHAR(15) NOT NULL,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('ADMIN', 'MANAGER', 'WAITSTAFF'))
);

CREATE TABLE guests (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    party_size INT NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE waitlist_entries (
    id SERIAL PRIMARY KEY,
    restaurant_id INT NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
    guest_id INT NOT NULL REFERENCES guests(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL CHECK (
        status IN ('WAITING', 'NOTIFIED', 'COMPLETED', 'CANCELED')
    ),
    join_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    notified_time TIMESTAMP DEFAULT NULL,
    completed_time TIMESTAMP DEFAULT NULL,
    canceled_time TIMESTAMP DEFAULT NULL
);