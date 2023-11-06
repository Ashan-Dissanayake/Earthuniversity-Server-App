package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

}

