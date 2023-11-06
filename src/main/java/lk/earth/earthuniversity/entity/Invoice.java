package lk.earth.earthuniversity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
public class Invoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Pattern(regexp = "I[0-9]{3}", message = "Invalid Code")
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "time")
    private Time time;
    @Basic
    @Column(name = "grandtotal")
    private BigDecimal grandtotal;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "invoicestatus_id", referencedColumnName = "id", nullable = false)
    private Invoicestatus invoicestatus;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Invoiceitem> invoiceitem;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public BigDecimal getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(BigDecimal grandtotal) {
        this.grandtotal = grandtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (id != null ? !id.equals(invoice.id) : invoice.id != null) return false;
        if (name != null ? !name.equals(invoice.name) : invoice.name != null) return false;
        if (date != null ? !date.equals(invoice.date) : invoice.date != null) return false;
        if (time != null ? !time.equals(invoice.time) : invoice.time != null) return false;
        if (grandtotal != null ? !grandtotal.equals(invoice.grandtotal) : invoice.grandtotal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (grandtotal != null ? grandtotal.hashCode() : 0);
        return result;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoicestatus getInvoicestatus() {
        return invoicestatus;
    }

    public void setInvoicestatus(Invoicestatus invoicestatus) {
        this.invoicestatus = invoicestatus;
    }

    public Collection<Invoiceitem> getInvoiceitem() {
        return invoiceitem;
    }

    public void setInvoiceitem(Collection<Invoiceitem> invoiceitem) {
        this.invoiceitem = invoiceitem;
    }
}
