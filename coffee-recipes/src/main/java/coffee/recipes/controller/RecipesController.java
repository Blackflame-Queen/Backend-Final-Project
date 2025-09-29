package coffee.recipes.controller;

import coffee.recipes.controller.model.CoffeeRecipesData;
import coffee.recipes.controller.model.IngredientDTO;
import coffee.recipes.controller.model.RatioDTO;
import coffee.recipes.controller.model.RecipeDTO;
import coffee.recipes.controller.model.SpecialEquipmentDTO;
import coffee.recipes.entity.Ingredients;
import coffee.recipes.entity.Ratios;
import coffee.recipes.entity.Recipe;
import coffee.recipes.entity.SpecialEquipment;
import coffee.recipes.service.CoffeeRecipesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//ANCHOR [7] here are the api endpoints for CRUD ops on recipes and related entities
@RestController
@RequestMapping("/coffee-recipes")
@Slf4j
public class RecipesController {

    @Autowired
    private CoffeeRecipesService coffeeRecipesService;
    
    @Autowired
    private CoffeeRecipesData coffeeRecipesData;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeDTO> getAllRecipes() {
        log.info("Retrieving all coffee recipes");
        List<Recipe> recipes = coffeeRecipesService.getAllRecipes();
        return coffeeRecipesData.entitiesToDtos(recipes);
    }
    
    @GetMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDTO getRecipeById(@PathVariable Long recipeId) {
        log.info("Retrieving coffee recipe with ID: {}", recipeId);
        Recipe recipe = coffeeRecipesService.getRecipeById(recipeId);
        return coffeeRecipesData.entityToDto(recipe);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeDTO createRecipe(@RequestBody RecipeDTO recipeDTO) {
        log.info("Creating new coffee recipe: {}", recipeDTO.getRecipeName());
        Recipe recipe = coffeeRecipesData.dtoToEntity(recipeDTO);
        Recipe savedRecipe = coffeeRecipesService.saveRecipe(recipe);
        return coffeeRecipesData.entityToDto(savedRecipe);
    }
    
    @DeleteMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long recipeId) {
        log.info("Deleting coffee recipe with ID: {}", recipeId);
        coffeeRecipesService.deleteRecipe(recipeId);
    }
    
    @PutMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeDTO updateRecipe(@PathVariable Long recipeId, @RequestBody RecipeDTO recipeDTO) {
        log.info("Updating coffee recipe with ID: {}", recipeId);
        recipeDTO.setRecipeId(recipeId);
        Recipe recipe = coffeeRecipesData.dtoToEntity(recipeDTO);
        Recipe updatedRecipe = coffeeRecipesService.saveRecipe(recipe);
        return coffeeRecipesData.entityToDto(updatedRecipe);
    }
    
    @GetMapping("/ingredients")
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientDTO> getAllIngredients() {
        log.info("Retrieving all ingredients");
        List<Ingredients> ingredients = coffeeRecipesService.getAllIngredients();
        return coffeeRecipesData.ingredientsToDtos(ingredients);
    }
    
    @GetMapping("/equipment")
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialEquipmentDTO> getAllSpecialEquipment() {
        log.info("Retrieving all special equipment");
        List<SpecialEquipment> equipment = coffeeRecipesService.getAllSpecialEquipment();
        return coffeeRecipesData.equipmentToDtos(equipment);
    }
    
    
    @GetMapping("/{recipeId}/ratios")
    @ResponseStatus(HttpStatus.OK)
    public RatioDTO getRatiosByRecipeId(@PathVariable Long recipeId) {
        log.info("Retrieving ratios for recipe with ID: {}", recipeId);
        Ratios ratios = coffeeRecipesService.getRatiosByRecipeId(recipeId);
        if (ratios == null) {
            return null;
        }
        return RatioDTO.builder()
            .coffee(ratios.getCoffee())
            .water(ratios.getWater())
            .milk(ratios.getMilk())
            .chocolate(ratios.getChocolate())
            .syrup(ratios.getSyrup())
            .cinnamon(ratios.getCinnamon())
            .whippedCream(ratios.getWhippedCream())
            .build();
    }
    
    @PutMapping("/{recipeId}/ratios")
    @ResponseStatus(HttpStatus.OK)
    public RatioDTO updateRatiosForRecipe(@PathVariable Long recipeId, @RequestBody RatioDTO ratioDTO) {
        log.info("Updating ratios for recipe with ID: {}", recipeId);
        
        Ratios ratios = new Ratios();
        ratios.setCoffee(ratioDTO.getCoffee());
        ratios.setWater(ratioDTO.getWater());
        ratios.setMilk(ratioDTO.getMilk());
        ratios.setChocolate(ratioDTO.getChocolate());
        ratios.setSyrup(ratioDTO.getSyrup());
        ratios.setCinnamon(ratioDTO.getCinnamon());
        ratios.setWhippedCream(ratioDTO.getWhippedCream());
        
        Ratios updatedRatios = coffeeRecipesService.updateRatiosForRecipe(recipeId, ratios);
        
        return RatioDTO.builder()
            .coffee(updatedRatios.getCoffee())
            .water(updatedRatios.getWater())
            .milk(updatedRatios.getMilk())
            .chocolate(updatedRatios.getChocolate())
            .syrup(updatedRatios.getSyrup())
            .cinnamon(updatedRatios.getCinnamon())
            .whippedCream(updatedRatios.getWhippedCream())
            .build();
    }
    
    @GetMapping("/by-ingredient/{ingredientName}")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeDTO> findRecipesByIngredient(@PathVariable String ingredientName) {
        log.info("Finding recipes with ingredient: {}", ingredientName);
        List<Recipe> recipes = coffeeRecipesService.findRecipesByIngredient(ingredientName);
        return coffeeRecipesData.entitiesToDtos(recipes);
    }
    
    @GetMapping("/by-equipment/{equipmentName}")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeDTO> findRecipesByEquipment(@PathVariable String equipmentName) {
        log.info("Finding recipes with equipment: {}", equipmentName);
        List<Recipe> recipes = coffeeRecipesService.findRecipesByEquipment(equipmentName);
        return coffeeRecipesData.entitiesToDtos(recipes);
    }
}
