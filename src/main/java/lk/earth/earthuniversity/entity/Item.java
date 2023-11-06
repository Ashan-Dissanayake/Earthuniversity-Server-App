package lk.earth.earthuniversity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "saleprice")
    private BigDecimal saleprice;
    @Basic
    @Column(name = "purchaseprice")
    private BigDecimal purchaseprice;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    @JsonIgnore
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

    public BigDecimal getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    public BigDecimal getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(BigDecimal purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (saleprice != null ? !saleprice.equals(item.saleprice) : item.saleprice != null) return false;
        if (purchaseprice != null ? !purchaseprice.equals(item.purchaseprice) : item.purchaseprice != null)
            return false;
        if (quantity != null ? !quantity.equals(item.quantity) : item.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (saleprice != null ? saleprice.hashCode() : 0);
        result = 31 * result + (purchaseprice != null ? purchaseprice.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    public Collection<Invoiceitem> getInvoiceitem() {
        return invoiceitem;
    }

    public void setInvoiceitem(Collection<Invoiceitem> invoiceitem) {
        this.invoiceitem = invoiceitem;
    }
}
