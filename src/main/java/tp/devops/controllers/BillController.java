package tp.devops.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tp.devops.dto.CreateBillDto;
import tp.devops.entities.Bill;
import tp.devops.entities.Customer;
import tp.devops.services.BillService;
import tp.devops.services.CustomerService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/api/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(final BillService billService) {
        this.billService=billService;
    }

    @PostMapping("/create")
    public Bill createBill (
            @RequestBody Bill createBillDto
    ) throws IOException {
        return this.billService.createBill(createBillDto);
    }

    @GetMapping("/all")
    public List<Bill> getAllCustomers () {
        return this.billService.getAllCustomers();
    }

}
