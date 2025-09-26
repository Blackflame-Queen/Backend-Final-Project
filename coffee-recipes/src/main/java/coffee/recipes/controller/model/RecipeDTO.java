package coffee.recipes.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeDTO {
    private Long recipeId;
    private String recipeName;
    private String directions;
    private List<String> specialEquipment;
    private List<String> ingredients;
    private RatioDTO ratios;
}