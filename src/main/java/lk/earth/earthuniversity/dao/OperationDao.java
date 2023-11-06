package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationDao extends JpaRepository<Operation,Integer> {
}
