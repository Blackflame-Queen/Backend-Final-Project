INSERT INTO ingredients (ingredients) VALUES ('Coffee');
INSERT INTO ingredients (ingredients) VALUES ('Matcha');
INSERT INTO ingredients (ingredients) VALUES ('Water');
INSERT INTO ingredients (ingredients) VALUES ('Milk');
INSERT INTO ingredients (ingredients) VALUES ('Chocolate');
INSERT INTO ingredients (ingredients) VALUES ('Syrup');
INSERT INTO ingredients (ingredients) VALUES ('Cinnamon');
INSERT INTO ingredients (ingredients) VALUES ('Whipped Cream');
INSERT INTO ingredients (ingredients) VALUES ('Blended Ice');

INSERT INTO special_equipment (special_equipment) VALUES ('Espresso Machine w/ Steam Wand');
INSERT INTO special_equipment (special_equipment) VALUES ('Blender');

INSERT INTO recipe (recipe_name, directions, special_equipment_id) 
VALUES ('Americano', 'Pull a shot of espresso. Add hot water to dilute.', 1);

INSERT INTO recipe (recipe_name, directions, special_equipment_id) 
VALUES ('Cappuccino', 'Pull a shot of espresso. Steam milk to create microfoam. Add equal parts espresso, steamed milk, and milk foam.', 1);

INSERT INTO recipe (recipe_name, directions, special_equipment_id) 
VALUES ('Latte', 'Pull a shot of espresso. Steam milk until smooth. Pour steamed milk into espresso with a small layer of foam on top.', 1);

INSERT INTO recipe (recipe_name, directions, special_equipment_id) 
VALUES ('Mocha', 'Pull a shot of espresso. Mix with chocolate. Add steamed milk and top with whipped cream.', 1);

-- Americano ratio
INSERT INTO ratios (coffee, water, milk, chocolate, syrup, cinnamon, whipped_cream, recipe_id) 
VALUES (1, 3, NULL, NULL, NULL, NULL, NULL, 1);

-- Cappuccino ratio
INSERT INTO ratios (coffee, water, milk, chocolate, syrup, cinnamon, whipped_cream, recipe_id) 
VALUES (1, 2, 2, NULL, NULL, NULL, NULL, 2);

-- Latte ratio
INSERT INTO ratios (coffee, water, milk, chocolate, syrup, cinnamon, whipped_cream, recipe_id) 
VALUES (1, 2, 3, NULL, NULL, NULL, NULL, 3);

-- Mocha ratio
INSERT INTO ratios (coffee, water, milk, chocolate, syrup, cinnamon, whipped_cream, recipe_id) 
VALUES (1, 2, 2, 1, NULL, NULL, 1, 4);

-- Americano:
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (1, 1);
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (1, 2);

-- Cappuccino:
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (2, 1);
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (2, 2);
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (2, 3);

-- Latte:
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (3, 1);
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (3, 2);
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (3, 3);

-- Mocha:
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (4, 1);
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (4, 2);
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (4, 3);
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (4, 4);
INSERT INTO recipe_ingredients (recipe_id, ingredients_id) VALUES (4, 7);