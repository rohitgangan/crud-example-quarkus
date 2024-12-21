INSERT INTO product(id, name, description, price, quantity)
Select 1,'Canon Full Frame 5D DSLR', 'Full Frame DSLR Camera from canon used by professional', 210000, 10
UNION
Select 2,'Samsung Notebook 4 pro', 'Samsung laptop intel evo edition i5 pro', 116000, 4
UNION
Select 3,'Apple iPhone 15 Pro 256 GB', 'iPhone 15 series', 45000, 10;
ALTER SEQUENCE product_seq RESTART WITH 4;
