INSERT INTO client (id, name) VALUES
    (1, 'Oleksii Panpukha'),
    (2, 'Anna Kowalska'),
    (3, 'John Smith'),
    (4, 'Maria Garcia'),
    (5, 'Ivan Petrenko'),
    (6, 'Olivia Brown'),
    (7, 'Noah Wilson'),
    (8, 'Emma Davis'),
    (9, 'Liam Miller'),
    (10, 'Sophia Taylor');

INSERT INTO planet (id, name) VALUES
    ('EARTH', 'Earth'),
    ('MARS', 'Mars'),
    ('VEN', 'Venus'),
    ('JUP', 'Jupiter'),
    ('SAT', 'Saturn');

INSERT INTO ticket (id, created_at, client_id, from_planet_id, to_planet_id) VALUES
    (1, TIMESTAMP WITH TIME ZONE '2026-01-01 10:00:00+00:00', 1, 'EARTH', 'MARS'),
    (2, TIMESTAMP WITH TIME ZONE '2026-01-02 11:00:00+00:00', 2, 'MARS', 'EARTH'),
    (3, TIMESTAMP WITH TIME ZONE '2026-01-03 12:00:00+00:00', 3, 'EARTH', 'VEN'),
    (4, TIMESTAMP WITH TIME ZONE '2026-01-04 13:00:00+00:00', 4, 'VEN', 'MARS'),
    (5, TIMESTAMP WITH TIME ZONE '2026-01-05 14:00:00+00:00', 5, 'MARS', 'JUP'),
    (6, TIMESTAMP WITH TIME ZONE '2026-01-06 15:00:00+00:00', 6, 'JUP', 'SAT'),
    (7, TIMESTAMP WITH TIME ZONE '2026-01-07 16:00:00+00:00', 7, 'SAT', 'EARTH'),
    (8, TIMESTAMP WITH TIME ZONE '2026-01-08 17:00:00+00:00', 8, 'VEN', 'JUP'),
    (9, TIMESTAMP WITH TIME ZONE '2026-01-09 18:00:00+00:00', 9, 'JUP', 'MARS'),
    (10, TIMESTAMP WITH TIME ZONE '2026-01-10 19:00:00+00:00', 10, 'EARTH', 'SAT');

ALTER TABLE client ALTER COLUMN id RESTART WITH 11;
ALTER TABLE ticket ALTER COLUMN id RESTART WITH 11;
