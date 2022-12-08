package tp.devops.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.devops.entities.Bill;
import tp.devops.repositories.BillRepository;

import java.io.IOException;
import java.util.List;
@Service
public class BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillService(final BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill createBill(Bill bill) throws IOException {
        try{
            return this.billRepository.save(bill);
        }catch (Exception e){
            throw new IOException("Cannot create this customer");
        }
    }

    public List<Bill> getAllCustomers() {
        return this.billRepository.findAll();
    }

}
