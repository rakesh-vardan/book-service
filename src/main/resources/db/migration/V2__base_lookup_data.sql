insert into category values
('FC', 'Fiction', 'Fictional Novels'),
('NF', 'Non-Fiction', 'Non-Fictional Books'),
('HR', 'Horror', 'Horror stories'),
('CM', 'Comedy', 'Comedy Books'),
('SH', 'Self-Help', 'Self-Help and Motivational Books');

insert into author values 
('PC', 'Paolo Coelho', 'Brazil', '9885448921'),
('CB', 'Chetan Bhagat', 'India', '6303337379'),
('AT', 'Amish Tripathi', 'India', '123456');

insert into publisher values 
('HC', 'Harper Collins', 'Harper Collins Inc'),
('RC', 'Rupa and Co', 'Rupa and Co Ltd'),
('WP', 'Westland Press', 'Westland Press India Ltd');

insert into book (title, isbn, author_cd, publisher_cd, category_cd, published_date, price) values
('The Alchemist', '9886778999', 'PC', 'HC', 'FC', (select current_date), 55.70),
('Five Point Someone', '76786778999', 'CB', 'RC', 'HR', (select current_date), 55.70),
('Immortals of Meluha', '1234578999', 'AT', 'WP', 'NF', (select current_date), 100.75);
