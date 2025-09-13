package coffee.recipes.dao;

import coffee.recipes.entity.Ratios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatiosDao extends JpaRepository<Ratios, Long> {
}