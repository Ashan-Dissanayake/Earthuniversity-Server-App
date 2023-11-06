package lk.earth.earthuniversity.dao;


import lk.earth.earthuniversity.entity.Employee;
import lk.earth.earthuniversity.entity.Invoice;
import lk.earth.earthuniversity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceDao extends JpaRepository<Invoice,Integer> {

    @Query("SELECT i FROM Invoice i WHERE i.name = :name")
    Invoice findInvoiceByCode(String name);


}

