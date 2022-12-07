package tp.devops.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.devops.entities.Bill;

public interface BillRepository extends JpaRepository<Bill,Long>{
    
}
