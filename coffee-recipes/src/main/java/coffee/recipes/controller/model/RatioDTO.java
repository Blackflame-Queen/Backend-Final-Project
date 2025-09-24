package coffee.recipes.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatioDTO {
    private Integer coffee;
    private Integer matcha;
    private Integer water;
    private Integer milk;
    private Integer chocolate;
    private Integer syrup;
    private Integer cinnamon;
    private Integer whippedCream;
    private Integer blendedIce;
}