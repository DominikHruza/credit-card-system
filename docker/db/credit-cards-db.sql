CREATE TABLE application_statuses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE applicants (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    oib VARCHAR(11) NOT NULL,
    status_id INTEGER NOT NULL,
    FOREIGN KEY (status_id) REFERENCES application_statuses (id)
);

CREATE TABLE application_file (
    id SERIAL PRIMARY KEY,
    location_url TEXT NOT NULL,
    is_active BOOLEAN,
    applicant_id INTEGER NOT NULL,
    FOREIGN KEY (applicant_id) REFERENCES applicants (id),
    UNIQUE(applicant_id)
);