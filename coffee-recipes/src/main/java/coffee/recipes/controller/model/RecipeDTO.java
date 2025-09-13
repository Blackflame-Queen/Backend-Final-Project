package coffee.recipes.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private Long recipeId;
    private String recipeName;
    private String directions;
    private List<String> specialEquipment;
    private RatioDTO ratios;
    private List<String> ingredients;
}