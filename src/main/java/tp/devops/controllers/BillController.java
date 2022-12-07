package tp.devops.controllers;

import org.springframework.web.bind.annotation.*;
import tp.devops.entities.Bill;
import tp.devops.services.BillService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bills")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService){this.billService = billService;}

    @PostMapping("/create")
    public Bill createVendor(@RequestBody Bill createBillDto) throws IOException {
        return this.billService.createBill(createBillDto);
    }

    @GetMapping("/all")
    public List<Bill> getAllbills() {return  this.billService.getAllBills();}
}
