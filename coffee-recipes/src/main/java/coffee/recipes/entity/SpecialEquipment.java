package coffee.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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
