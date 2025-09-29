package coffee.recipes.controller.model;

import coffee.recipes.entity.Ingredients;
import coffee.recipes.entity.Ratios;
import coffee.recipes.entity.Recipe;
import coffee.recipes.entity.SpecialEquipment;
import coffee.recipes.dao.IngredientsDao;
import coffee.recipes.dao.SpecialEquipmentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//ANCHOR [7] handles DTO conversions for the api implementation
@Component
@Slf4j
public class CoffeeRecipesData {
    public IngredientDTO ingredientToDto(Ingredients ingredient) {
        if (ingredient == null) {
            return null;
        }
        return IngredientDTO.builder()
            .ingredientsId(ingredient.getIngredientsId())
            .ingredients(ingredient.getIngredients())
            .build();
    }

    public List<IngredientDTO> ingredientsToDtos(List<Ingredients> ingredients) {
        return ingredients.stream()
            .map(this::ingredientToDto)
            .collect(Collectors.toList());
    }

    public SpecialEquipmentDTO equipmentToDto(SpecialEquipment equipment) {
        if (equipment == null) {
            return null;
        }
        return SpecialEquipmentDTO.builder()
            .specialEquipmentId(equipment.getSpecialEquipmentId())
            .specialEquipment(equipment.getSpecialEquipment())
            .build();
    }

    public List<SpecialEquipmentDTO> equipmentToDtos(List<SpecialEquipment> equipment) {
        return equipment.stream()
            .map(this::equipmentToDto)
            .collect(Collectors.toList());
    }
    
    @Autowired
    private IngredientsDao ingredientsDao;
    
    @Autowired
    private SpecialEquipmentDao specialEquipmentDao;

    public RecipeDTO entityToDto(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        
        List<String> ingredientNames = null;
        if (recipe.getIngredients() != null) {
            ingredientNames = recipe.getIngredients().stream()
                .map(Ingredients::getIngredients)
                .collect(Collectors.toList());
        }
        
        RatioDTO ratioDTO = null;
        if (recipe.getRatios() != null) {
            ratioDTO = RatioDTO.builder()
                .coffee(recipe.getRatios().getCoffee())
                .matcha(recipe.getRatios().getMatcha())
                .water(recipe.getRatios().getWater())
                .milk(recipe.getRatios().getMilk())
                .chocolate(recipe.getRatios().getChocolate())
                .syrup(recipe.getRatios().getSyrup())
                .cinnamon(recipe.getRatios().getCinnamon())
                .whippedCream(recipe.getRatios().getWhippedCream())
                .blendedIce(recipe.getRatios().getBlendedIce())
                .build();
        }
        
        List<String> equipmentNames = null;
        if (recipe.getSpecialEquipment() != null) {
            equipmentNames = new ArrayList<>();
            equipmentNames.add(recipe.getSpecialEquipment().getSpecialEquipment());
        } else {
            equipmentNames = new ArrayList<>();
        }
        
        return RecipeDTO.builder()
            .recipeId(recipe.getRecipeId())
            .recipeName(recipe.getRecipeName())
            .directions(recipe.getDirections())
            .specialEquipment(equipmentNames)
            .ratios(ratioDTO)
            .ingredients(ingredientNames)
            .build();
    }
    
    public List<RecipeDTO> entitiesToDtos(List<Recipe> recipes) {
        return recipes.stream()
            .map(this::entityToDto)
            .collect(Collectors.toList());
    }
    
    public Recipe dtoToEntity(RecipeDTO recipeDTO) {
        if (recipeDTO == null) {
            return null;
        }
        
        Recipe recipe = new Recipe();
        
        if (recipeDTO.getRecipeId() != null) {
            recipe.setRecipeId(recipeDTO.getRecipeId());
        }
        recipe.setRecipeName(recipeDTO.getRecipeName());
        recipe.setDirections(recipeDTO.getDirections());
        
        if (recipeDTO.getSpecialEquipment() != null && !recipeDTO.getSpecialEquipment().isEmpty()) {
            String equipmentName = recipeDTO.getSpecialEquipment().get(0);
            SpecialEquipment equipment = specialEquipmentDao.findBySpecialEquipment(equipmentName);
            if (equipment == null) {
                equipment = new SpecialEquipment();
                equipment.setSpecialEquipment(equipmentName);
                equipment = specialEquipmentDao.save(equipment);
            }
            recipe.setSpecialEquipment(equipment);
        }
        
        if (recipeDTO.getRatios() != null) {
            Ratios ratios = new Ratios();
            ratios.setCoffee(recipeDTO.getRatios().getCoffee());
            ratios.setMatcha(recipeDTO.getRatios().getMatcha());
            ratios.setWater(recipeDTO.getRatios().getWater());
            ratios.setMilk(recipeDTO.getRatios().getMilk());
            ratios.setChocolate(recipeDTO.getRatios().getChocolate());
            ratios.setSyrup(recipeDTO.getRatios().getSyrup());
            ratios.setCinnamon(recipeDTO.getRatios().getCinnamon());
            ratios.setWhippedCream(recipeDTO.getRatios().getWhippedCream());
            ratios.setBlendedIce(recipeDTO.getRatios().getBlendedIce());
            ratios.setRecipe(recipe);
            recipe.setRatios(ratios);
        }
        
        if (recipeDTO.getIngredients() != null && !recipeDTO.getIngredients().isEmpty()) {
            List<Ingredients> ingredientsList = new ArrayList<>();
            for (String ingredientName : recipeDTO.getIngredients()) {
                Ingredients ingredient = ingredientsDao.findAll().stream()
                    .filter(ing -> ing.getIngredients().equals(ingredientName))
                    .findFirst()
                    .orElse(null);
                if (ingredient != null) {
                    ingredientsList.add(ingredient);
                }
            }
            recipe.setIngredients(ingredientsList);
        }
        
        return recipe;
    }
    
    public List<Recipe> dtosToEntities(List<RecipeDTO> recipeDTOs) {
        return recipeDTOs.stream()
            .map(this::dtoToEntity)
            .collect(Collectors.toList());
    }
}
