package coffee.recipes.service;

import coffee.recipes.dao.CoffeeRecipesDao;
import coffee.recipes.dao.IngredientsDao;
import coffee.recipes.dao.RatiosDao;
import coffee.recipes.dao.SpecialEquipmentDao;
import coffee.recipes.entity.Ingredients;
import coffee.recipes.entity.Ratios;
import coffee.recipes.entity.Recipe;
import coffee.recipes.entity.SpecialEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

//ANCHOR [2,3,4] the service layer handles CRUD ops for entities
@Service
public class CoffeeRecipesService {
    
    @Autowired
    private CoffeeRecipesDao coffeeRecipesDao;
    
    @Autowired
    private IngredientsDao ingredientsDao;
    
    @Autowired
    private SpecialEquipmentDao specialEquipmentDao;
    
    @Autowired
    private RatiosDao ratiosDao;
    
    public List<Recipe> getAllRecipes() {
        return coffeeRecipesDao.findAll();
    }
    
    public Recipe getRecipeById(Long recipeId) {
        return coffeeRecipesDao.findById(recipeId)
                .orElseThrow(() -> new NoSuchElementException("Recipe not found with ID: " + recipeId));
    }
    
    @Transactional
    public Recipe saveRecipe(Recipe recipe) {
        return coffeeRecipesDao.save(recipe);
    }
    
    @Transactional
    public void deleteRecipe(Long recipeId) {
        coffeeRecipesDao.deleteById(recipeId);
    }
    
    public List<Ingredients> getAllIngredients() {
        return ingredientsDao.findAll();
    }
    
    public List<SpecialEquipment> getAllSpecialEquipment() {
        return specialEquipmentDao.findAll();
    }
    
    
    public Ratios getRatiosByRecipeId(Long recipeId) {
        Recipe recipe = getRecipeById(recipeId);
        return recipe.getRatios();
    }
    
    @Transactional
    public Ratios saveRatios(Ratios ratios) {
        return ratiosDao.save(ratios);
    }
    
    @Transactional
    public Ratios updateRatiosForRecipe(Long recipeId, Ratios ratios) {
        Recipe recipe = getRecipeById(recipeId);
        
        if (recipe.getRatios() != null) {
            Ratios existingRatios = recipe.getRatios();
            existingRatios.setCoffee(ratios.getCoffee());
            existingRatios.setWater(ratios.getWater());
            existingRatios.setMilk(ratios.getMilk());
            existingRatios.setChocolate(ratios.getChocolate());
            existingRatios.setSyrup(ratios.getSyrup());
            existingRatios.setCinnamon(ratios.getCinnamon());
            existingRatios.setWhippedCream(ratios.getWhippedCream());
            return ratiosDao.save(existingRatios);
        } else {
            ratios.setRecipe(recipe);
            Ratios savedRatios = ratiosDao.save(ratios);
            recipe.setRatios(savedRatios);
            coffeeRecipesDao.save(recipe);
            return savedRatios;
        }
    }
    
    
    public List<Recipe> findRecipesByIngredient(String ingredientName) {
        Ingredients ingredient = ingredientsDao.findAll().stream()
            .filter(ing -> ing.getIngredients().equals(ingredientName))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Ingredient not found: " + ingredientName));
        
        return ingredient.getRecipes();
    }
    
    public List<Recipe> findRecipesByEquipment(String equipmentName) {
        SpecialEquipment equipment = specialEquipmentDao.findAll().stream()
            .filter(eq -> eq.getSpecialEquipment().equals(equipmentName))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Equipment not found: " + equipmentName));
        
        return equipment.getRecipes();
    }
}
