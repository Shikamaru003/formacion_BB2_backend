
insert into users(username, password, role) values('admin', '$2a$10$G.RPpRyxzo2DCW7N0T7u8.PVtgGPu9tSxp8D1r7cdfOnUEzQzTv.C', 0);
insert into users(username, password, role) values('user', '$2a$10$UGxXcvvzMT91.YzcRbo23uSkSsKNNqbZ8qfCIbe2PQar01YC9vl0G', 1);

insert into products(item_code, description, price, state, creation_date, creator) values(123, 'Product 1', 99.5, 1, '2020-12-15', 'admin');
insert into products(item_code, description, price, state, creation_date, creator) values(456, 'Product 2', 79.5, 1, '2020-12-15', 'admin');
insert into products(item_code, description, price, state, creation_date, creator) values(789, 'Product 3', 19.5, 1, '2020-12-15', 'user');
insert into products(item_code, description, price, state, creation_date, creator) values(321, 'Product 4', 39.5, 1, '2020-12-15', 'user');
insert into products(item_code, description, price, state, creation_date, creator) values(753, 'Product 5', 99.5, 0, '2020-12-15', 'admin');
insert into products(item_code, description, price, state, creation_date, creator) values(159, 'Product 6', 79.5, 1, '2020-12-15', 'admin');
insert into products(item_code, description, price, state, creation_date, creator) values(852, 'Product 7', 19.5, 0, '2020-12-15', 'user');
insert into products(item_code, description, price, state, creation_date, creator) values(457, 'Product 8', 39.5, 1, '2020-12-15', 'user');
insert into products(item_code, description, price, state, creation_date, creator) values(873, 'Product 9', 99.5, 0, '2020-12-15', 'admin');
insert into products(item_code, description, price, state, creation_date, creator) values(328, 'Product 10', 79.5, 1, '2020-12-15', 'admin');
insert into products(item_code, description, price, state, creation_date, creator) values(459, 'Product 11', 19.5, 0, '2020-12-15', 'user');
insert into products(item_code, description, price, state, creation_date, creator) values(651, 'Product 12', 39.5, 1, '2020-12-15', 'user');


insert into suppliers(country, name) values('Spain', 'Productos Martínez');
insert into suppliers(country, name) values('France', 'France_Supplies');
insert into suppliers(country, name) values('Spain', 'Productos Varios');
insert into suppliers(country, name) values('Germany', 'Germany_Supplies');
insert into suppliers(country, name) values('Spain', 'Productos Reposteria');
insert into suppliers(country, name) values('Italy', 'Italian_Supplies');

insert into product_supplier(product_id, supplier_id) values(1,1);
insert into product_supplier(product_id, supplier_id) values(1,2);
insert into product_supplier(product_id, supplier_id) values(2,1);
insert into product_supplier(product_id, supplier_id) values(3,1);
insert into product_supplier(product_id, supplier_id) values(4,3);

insert into price_reductions(reduced_price, start_date, end_date) values('-15%', '2020-12-15', '2020-12-15');
insert into price_reductions(reduced_price, start_date, end_date) values('-25%', '2020-12-15', '2021-12-15');
insert into price_reductions(reduced_price, start_date, end_date) values('-35%', '2020-12-15', '2021-12-15');
insert into price_reductions(reduced_price, start_date, end_date) values('-45%', '2020-12-15', '2021-12-15');

insert into product_price_reduction(product_id, price_reduction_id) values(1,1);
insert into product_price_reduction(product_id, price_reduction_id) values(4,2);
