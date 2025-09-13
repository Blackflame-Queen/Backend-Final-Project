package coffee.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientsId;
    private String ingredients;
    
    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;
}
