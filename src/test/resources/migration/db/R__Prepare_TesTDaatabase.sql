-- ${flyway:timestamp}
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS menu_items;
DROP TABLE IF EXISTS statuses;
DROP TABLE IF EXISTS order_statuses;
DROP TABLE IF EXISTS placed_orders;
DROP TABLE IF EXISTS placed_order_items;



CREATE table profiles (
                          id INTEGER PRIMARY KEY AUTO_INCREMENT,
                          contact_phone VARCHAR(256) NOT NULL,
                          address VARCHAR(256) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                          updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                          deleted_at TIMESTAMP
);

CREATE TABLE users
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(256),
    last_name VARCHAR(256),
    password VARCHAR(256),
    email VARCHAR(256),
    role VARCHAR(256),
    profile_id INTEGER DEFAULT NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP
);


CREATE table cities (
                        id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        city_name VARCHAR(256) NOT NULL,
                        post_code VARCHAR(256) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                        updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                        deleted_at TIMESTAMP
);



CREATE table restaurants(
                            id INTEGER PRIMARY KEY AUTO_INCREMENT,
                            city_id INTEGER NOT NULL,
                            FOREIGN KEY (city_id) REFERENCES cities(id),
                            name VARCHAR(256) NOT NULL,
                            user_id INTEGER NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            address VARCHAR(256) NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                            updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                            deleted_at TIMESTAMP
);

CREATE table categories(
                           id INTEGER PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(256) NOT NULL,
                           created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                           updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                           deleted_at TIMESTAMP
);

CREATE table menu_items(
                           id INTEGER PRIMARY KEY AUTO_INCREMENT,
                           item_name VARCHAR(256) NOT NULL,
                           description VARCHAR(256) NOT NULL,
                           ingredients VARCHAR(256) NOT NULL,
                           recipe VARCHAR(256),
                           restaurant_id INTEGER NOT NULL,
                           FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
                           price DECIMAL,
                           active BIT DEFAULT 1,
                           created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                           updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                           deleted_at TIMESTAMP
);

CREATE table statuses(
                         id INTEGER PRIMARY KEY AUTO_INCREMENT,
                         status_name VARCHAR(256) NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         deleted_at TIMESTAMP
);


CREATE table placed_orders(
                              id INTEGER PRIMARY KEY AUTO_INCREMENT,
                              restaurant_id INTEGER NOT NULL,
                              FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
                              order_time TIMESTAMP DEFAULT NULL,
                              estimated_delivery_time TIMESTAMP DEFAULT NULL,
                              food_ready_at TIMESTAMP DEFAULT NULL,
                              delivery_address VARCHAR(256),
                              price DECIMAL NOT NULL,
                              order_reference VARCHAR(256) NOT NULL,
                              vat DECIMAL NOT NULL DEFAULT 0.00,
                              status_id INTEGER NOT NULL DEFAULT 1,
                              FOREIGN KEY (status_id) REFERENCES statuses(id),
                              sub_total DECIMAL NOT NULL,
                              comment VARCHAR(2000),
                              user_id INTEGER NOT NULL,
                              FOREIGN KEY (user_id) REFERENCES users(id),
                              created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                              updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                              deleted_at TIMESTAMP
);

CREATE table placed_order_items(
                                   id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                   placed_order_id INTEGER NOT NULL,
                                   menu_item_id INTEGER NOT NULL,
                                   FOREIGN KEY (placed_order_id) REFERENCES placed_orders(id),
                                   FOREIGN KEY (menu_item_id) REFERENCES menu_items(id),
                                   quantity INTEGER NOT NULL,
                                   item_price DECIMAL NOT NULL,
                                   price DECIMAL NOT NULL,
                                   created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                                   updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                                   deleted_at TIMESTAMP
);



INSERT INTO cities (city_name, post_code)
values("Staffordshire","ST47FA");
INSERT INTO cities (city_name, post_code)
values("Hanley","ST5AFA");
INSERT INTO cities (city_name, post_code)
values("New Castle Under lyme","ST6AFA");


INSERT INTO categories (name)
values ("Pasteries");
INSERT INTO categories (name)
values ("African dish");
INSERT INTO categories (name)
values ("Chiness Dish");

INSERT INTO statuses (status_name)
values ("New_Order");
INSERT INTO statuses (status_name)
values ("Accepted");
INSERT INTO statuses (status_name)
values ("Declined");
INSERT INTO statuses (status_name)
values ("Is_Preparing");
INSERT INTO statuses (status_name)
values ("Delivering");
INSERT INTO statuses (status_name)
values ("Delivered");




