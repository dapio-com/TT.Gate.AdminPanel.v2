package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query(value = "SELECT * FROM operation ORDER BY id DESC LIMIT 15", nativeQuery = true)
    List<Operation> selectLastN();

    @Query(value = "SELECT * FROM operation WHERE op_date_time BETWEEN :date_from AND :date_to ORDER BY id DESC", nativeQuery = true)
    List<Operation> selectForReport(@Param("date_from") Timestamp date_from, @Param("date_to") Timestamp date_to);



    @Query(value = "SELECT COUNT(id) FROM operation WHERE op_date_time between NOW()-interval'1 minute' AND NOW()", nativeQuery = true)
    List<String> getOperationsPerMinute();


}
