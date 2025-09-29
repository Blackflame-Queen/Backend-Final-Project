package coffee.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

//ANCHOR [1,6] one of the required entities w/ a table, many-to-many relationship w/ Ingredients
@Entity
@Data
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;
    private String recipeName;
    private String directions;
    
    @ManyToOne
    @JoinColumn(name = "special_equipment_id")
    private SpecialEquipment specialEquipment;
    
    @OneToOne(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Ratios ratios;
    
    @ManyToMany
    @JoinTable(
        name = "recipe_ingredients",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    private List<Ingredients> ingredients;
}
