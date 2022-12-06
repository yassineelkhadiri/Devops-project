package tp.devops.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bill_id;

    private Long customer_id;

    private Long tax;

    private Date date;

    public Date getDate() {
        return date;
    }

    public Long getBill_id() {
        return bill_id;
    }

    public Long getTax() {
        return tax;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public void setBill_id(Long bill_id) {
        this.bill_id = bill_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }
}
