package coffee.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

//ANCHOR [1,5] one of the required entities w/ a table, one-to-many relationship w/ Recipe
@Entity
@Data
@NoArgsConstructor
public class SpecialEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialEquipmentId;
    private String specialEquipment;
    
    @OneToMany(mappedBy = "specialEquipment")
    private List<Recipe> recipes;
}
