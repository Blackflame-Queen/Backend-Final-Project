package coffee.recipes.dao;

import coffee.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRecipesDao extends JpaRepository<Recipe, Long> {
}
