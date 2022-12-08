package tp.devops;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
// import org.springframework.test.context.ContextConfiguration;

import tp.devops.entities.Product;
import tp.devops.repositories.ProductRepository;

import java.util.List;



@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    // JUnit test for saveEmployee
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveTest(){
        Product product = new Product();
        productRepository.save(product);
        Assertions.assertThat(product.getProductId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getProductTest(){

        Product product = productRepository.findById(1L).get();

        Assertions.assertThat(product.getProductId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfProductsTest(){

        List<Product> products = productRepository.findAll();

        Assertions.assertThat(products.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateProductTest(){

        Product product = productRepository.findById(1L).get();

        product.setAvailability(true);

        Product employeeUpdated =  productRepository.save(product);

        Assertions.assertThat(employeeUpdated.getAvailability()).isEqualTo(true);

    }
}
