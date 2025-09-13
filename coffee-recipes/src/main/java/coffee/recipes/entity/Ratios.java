package coffee.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ratios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratiosId;
    @Column(nullable = true)
    private Integer coffee;
    private Integer matcha;
    private Integer water;
    private Integer milk;
    private Integer chocolate;
    private Integer syrup;
    private Integer cinnamon;
    private Integer whippedCream;
    private Integer blendedIce;
    
    @OneToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
