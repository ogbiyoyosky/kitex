
INSERT INTO cities (city_name, post_code)
values("Staffordshire","ST47FA");
INSERT INTO cities (city_name, post_code)
values("Hanley","ST5AFA");
INSERT INTO cities (city_name, post_code)
values("New Castle Under lyme","ST6AFA");


INSERT INTO restaurants (city_id, name, address)
values (1,"Ember Lounge", "1 north street staffordshire stoke on trent");
INSERT INTO restaurants (city_id, name, address)
values (2,"Mac Donald", "4 south street New Castle");
INSERT INTO restaurants (city_id, name, address)
values (3,"Tiger Bite", "4 south street, London UK");


INSERT INTO categories (name)
values ("Pasteries");
INSERT INTO categories (name)
values ("African dish");
INSERT INTO categories (name)
values ("Chiness Dish");


INSERT INTO menu_items (item_name, description, ingredients, recipe, restaurant_id, price)
values ("English Breakfast", "A Typical English Breakfast with fish and chips","Flour, Fish, Potatoes, Onions", null,1, 10);

INSERT INTO menu_items (item_name, description, ingredients, recipe, restaurant_id, price)
values ("Hamburger", "Cabbages beef breakfast","Cabbage, Beef, Onions,", null,1, 15);

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


