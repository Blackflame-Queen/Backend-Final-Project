package coffee.recipes.dao;

import coffee.recipes.entity.SpecialEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialEquipmentDao extends JpaRepository<SpecialEquipment, Long> {
    SpecialEquipment findBySpecialEquipment(String specialEquipment);
}