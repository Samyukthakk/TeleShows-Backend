-- Genre
insert into genre(description, name) values('Family drama', 'Drama');
insert into genre(description, name) values('Action', 'Action');
insert into genre(description, name) values('Romance', 'Romance');
insert into genre(description, name) values('Horror', 'Horror');
insert into genre(description, name) values('Thriller', 'Thriller');
insert into genre(description, name) values('Whodunnit', 'Whodunnit');

-- TV Shows
insert into show(description, duration, name, rating, genre_id)
values('Netflix series - Season 1', 22, 'Breaking Bad', 5, 1);
insert into show(description, duration, name, rating, genre_id)
values('Amazon Originals', 30, 'Jack Ryan', 3, 2);
insert into show(description, duration, name, rating, genre_id)
values('Titanic', 3.5, 'Titanic', 4, 3);
insert into show(description, duration, name, rating, genre_id)
values('Conjuring Part 1', 2.5, 'The Conjuring', 3, 4);
insert into show(description, duration, name, rating, genre_id)
values('Netflix series -Season 3', 12, 'Money Heist', 2, 5);
insert into show(description, duration, name, rating, genre_id)
values('Amazon Originals', 2, 'Sherlock Holmes', 1, 6);