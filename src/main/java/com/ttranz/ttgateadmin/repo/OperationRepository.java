package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.dto.DtoChart;
import com.ttranz.ttgateadmin.dto.DtoTerminal;
import com.ttranz.ttgateadmin.models.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;



public interface OperationRepository extends CrudRepository<Operation, Long> {

    @Query(value = "SELECT * FROM operation WHERE op_mti = '0200' ORDER BY id DESC LIMIT 15", nativeQuery = true)
    List<Operation> selectLastN();

    @Query(value = "SELECT * FROM operation WHERE op_org_id = :org_id AND op_mti = '0200' ORDER BY id DESC LIMIT 15", nativeQuery = true)
    List<Operation> selectLastNOrgId(@Param("org_id") Long org_id);

    @Query(value = "SELECT * FROM operation WHERE op_org_id = :org_id AND op_date_time BETWEEN :date_start AND :date_end AND op_mti = '0200' ORDER BY id DESC", nativeQuery = true)
    List<Operation> selectForReport(@Param("org_id") Long org_id, @Param("date_start") Timestamp date_start, @Param("date_end") Timestamp date_end);


    @Query(value = "SELECT * FROM operation WHERE op_org_id = :org_id AND op_date_time BETWEEN :date_start AND :date_end AND op_mti = '0200' AND op_status = 1 ORDER BY id DESC", nativeQuery = true)
    List<Operation> selectForChart(@Param("org_id") Long org_id, @Param("date_start") Timestamp date_start, @Param("date_end") Timestamp date_end);


    @Query(value = "SELECT COUNT(id) FROM operation WHERE op_date_time between NOW()-interval'1 minute' AND NOW()", nativeQuery = true)
    List<String> getOperationsPerMinute();


}
