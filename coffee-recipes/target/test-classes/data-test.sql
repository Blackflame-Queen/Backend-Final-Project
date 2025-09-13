-- Insert test data

-- Insert ratios
INSERT INTO ratios (ratios_id, coffee, water, milk, other) VALUES (1, 1, 2, 0, 0);
INSERT INTO ratios (ratios_id, coffee, water, milk, other) VALUES (2, 1, 0, 2, 0);

-- Insert recipes
INSERT INTO recipe (recipe_id, recipe_name, directions, ratios_id) 
VALUES (1, 'Americano', 'Add hot water to espresso', 1);

INSERT INTO recipe (recipe_id, recipe_name, directions, ratios_id) 
VALUES (2, 'Latte', 'Add steamed milk to espresso', 2);

-- Insert ingredients
INSERT INTO ingredients (ingredients_id, ingredients) VALUES (1, 'Coffee');
INSERT INTO ingredients (ingredients_id, ingredients) VALUES (2, 'Water');
INSERT INTO ingredients (ingredients_id, ingredients) VALUES (3, 'Milk');

-- Insert special equipment
INSERT INTO special_equipment (special_equipment_id, special_equipment) VALUES (1, 'Espresso Machine');
INSERT INTO special_equipment (special_equipment_id, special_equipment) VALUES (2, 'Milk Steamer');

-- Link ingredients to recipes
INSERT INTO ingredients_recipes (ingredients_id, recipe_id) VALUES (1, 1); -- Coffee for Americano
INSERT INTO ingredients_recipes (ingredients_id, recipe_id) VALUES (2, 1); -- Water for Americano
INSERT INTO ingredients_recipes (ingredients_id, recipe_id) VALUES (1, 2); -- Coffee for Latte
INSERT INTO ingredients_recipes (ingredients_id, recipe_id) VALUES (3, 2); -- Milk for Latte

-- Link special equipment to recipes
INSERT INTO special_equipment_recipes (special_equipment_id, recipe_id) VALUES (1, 1); -- Espresso Machine for Americano
INSERT INTO special_equipment_recipes (special_equipment_id, recipe_id) VALUES (1, 2); -- Espresso Machine for Latte
INSERT INTO special_equipment_recipes (special_equipment_id, recipe_id) VALUES (2, 2); -- Milk Steamer for Latte