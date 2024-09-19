INSERT INTO booking (reservation_date, number_of_tables, customer_code, restaurante_code)
VALUES
    ('2024-11-10 12:00:00', 5, 1, 3),
    ('2024-12-01 19:30:00', 8, 2, 4),
    ('2024-10-15 17:00:00', 3, 3, 1),
    ('2024-10-25 20:15:00', 10, 2, 2);


INSERT INTO customer (name, email, phone)
VALUES
    ('João', 'joao123@gmail.com', '21987654321'),
    ('Mariana', 'mariana_santos@hotmail.com', '11324567890'),
    ('Carlos', 'carlos_ferreira@yahoo.com', '11983456789');


INSERT INTO restaurant (name, cep, number, complement, cuisine_type, total_tables, opening_hours, closing_time)
VALUES
    ('Rei do bacalhal', '3550000', 100, 'Estrada Velha', 'Brasileira', 250, '07:00:00', '20:00:00'),
    ('Tempero das Gerais', '3017000', 240, 'Avenida das Montanhas', 'Mineira', 180, '06:30:00', '19:00:00'),
    ('Cantinho Mineiro', '3127500', 320, 'Rua dos Bandeirantes', 'Mineira', 120, '08:00:00', '17:00:00');