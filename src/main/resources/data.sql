insert into flights (origin, destination, departure, arrival, price)
values ('London', 'New-York', current_timestamp() + 1, current_timestamp() + 2, 500.50),
       ('Berlin', 'Paris', current_timestamp() + 2, current_timestamp() + 3, 700),
       ('Barselona', 'Rome', current_timestamp() + 3, current_timestamp() + 4, 200.25);

insert into payments (price, date)
values (700, current_timestamp()),
       (500.50, current_timestamp()),
       (201, current_timestamp());

insert into users (name, username, email, password)
values ('Andrey1', 'Andreyyyy1', '1andrik@gmail.com', '12345'),
       ('Andrey2', 'Andreyyyy2', '2andrik@gmail.com', '14345'),
       ('Andrey3', 'Andreyyyy3', '3andrik@gmail.com', '15345');

insert into tickets (payment_id, user_id, flight_id)
values (2, 1, 1),
       (1, 1, 2),
       (3, 2, 3);