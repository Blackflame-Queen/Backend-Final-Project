package coffee.recipes.dao;

import coffee.recipes.entity.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsDao extends JpaRepository<Ingredients, Long> {
}
