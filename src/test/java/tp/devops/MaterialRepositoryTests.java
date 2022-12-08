package tp.devops;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import tp.devops.entities.Material;
import tp.devops.repositories.MaterialRepository;
import java.util.List;



@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaterialRepositoryTests {

    @Autowired
    private MaterialRepository materialRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveTest(){
        Material material = new Material();
        materialRepository.save(material);
        Assertions.assertThat(material.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getMaterialTest(){

        Material material = materialRepository.findById(1L).get();

        Assertions.assertThat(material.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfMaterialsTest(){

        List<Material> materials = materialRepository.findAll();

        Assertions.assertThat(materials.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateMaterialTest(){

        Material material = materialRepository.findById(1L).get();

        material.setStock(400);

        Material materialUpdated =  materialRepository.save(material);

        Assertions.assertThat(materialUpdated.getStock()).isEqualTo(400);

    }
}
