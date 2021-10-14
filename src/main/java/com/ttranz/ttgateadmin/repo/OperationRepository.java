package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.dto.DtoChart;
import com.ttranz.ttgateadmin.models.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;



public interface OperationRepository extends CrudRepository<Operation, Long> {

    @Query(value = "SELECT * FROM operation WHERE op_mti = '0200' ORDER BY id DESC LIMIT 20", nativeQuery = true)
    List<Operation> selectLastN();

    @Query(value = "SELECT * FROM operation WHERE op_org_id = :org_id AND op_mti = '0200' ORDER BY id DESC LIMIT 20", nativeQuery = true)
    List<Operation> selectLastNOrgId(@Param("org_id") Long org_id);

    @Query(value = "SELECT * FROM operation WHERE op_org_group_id = :org_group_id AND op_mti = '0200' ORDER BY id DESC LIMIT 20", nativeQuery = true)
    List<Operation> selectLastNOrgGroupId(@Param("org_group_id") Long org_group_id);

    @Query(value = "SELECT * FROM operation WHERE op_org_group_id = :org_group_id AND op_date_time BETWEEN :date_start AND :date_end AND op_mti = '0200' ORDER BY id ASC", nativeQuery = true)
    List<Operation> selectForReportOrgGroupId(@Param("org_group_id") Long org_group_id, @Param("date_start") Timestamp date_start, @Param("date_end") Timestamp date_end);

    @Query(value = "SELECT * FROM operation WHERE op_org_id = :org_id AND op_date_time BETWEEN :date_start AND :date_end AND op_mti = '0200' ORDER BY id ASC", nativeQuery = true)
    List<Operation> selectForReportOrgId(@Param("org_id") Long org_id, @Param("date_start") Timestamp date_start, @Param("date_end") Timestamp date_end);

    @Query(value = "SELECT * FROM operation WHERE op_date_time BETWEEN :date_start AND :date_end AND op_mti = '0200' ORDER BY id ASC", nativeQuery = true)
    List<Operation> selectForReportAll(@Param("date_start") Timestamp date_start, @Param("date_end") Timestamp date_end);

    //@Query(value = "SELECT * FROM operation WHERE op_org_id = :org_id AND op_date_time BETWEEN :date_start AND :date_end AND op_mti = '0200' AND op_status = 1 ORDER BY id ASC", nativeQuery = true)



    @Query(value = "SELECT * FROM operation WHERE op_org_id = :org_id AND op_date_time BETWEEN :date_start AND :date_end AND op_mti = '0200' ORDER BY id DESC", nativeQuery = true)
    List<Operation> viewReportOrgIdTime(@Param("org_id") Long org_id, @Param("date_start") Timestamp date_start, @Param("date_end") Timestamp date_end);

    @Query(value = "SELECT * FROM operation WHERE op_org_id = :org_id AND op_date_time BETWEEN :date_start AND :date_end AND op_mti = '0200' ORDER BY id DESC", nativeQuery = true)
    List<Operation> viewReportOrgId(@Param("org_id") Long org_id, @Param("date_start") Timestamp date_start, @Param("date_end") Timestamp date_end);



    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoChart(" +
            "o.op_date_time," +
            "o.op_amount" +
            ") " +
            "FROM Operation o WHERE o.op_org_id = :org_id AND o.op_date_time BETWEEN :date_start AND :date_end AND o.op_mti = '0200' AND o.op_status = 1 ORDER BY o.id ASC")

    List<DtoChart> selectForChartOrg(@Param("org_id") Long org_id, @Param("date_start") Timestamp date_start, @Param("date_end") Timestamp date_end);

    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoChart(" +
            "o.op_date_time," +
            "o.op_amount" +
            ") " +
            "FROM Operation o WHERE o.op_org_group_id = :org_group_id AND o.op_date_time BETWEEN :date_start AND :date_end AND o.op_mti = '0200' AND o.op_status = 1 ORDER BY o.id ASC")

    List<DtoChart> selectForChartOrgGroup(@Param("org_group_id") Long org_group_id, @Param("date_start") Timestamp date_start, @Param("date_end") Timestamp date_end);

    //@Query(value = "SELECT * FROM operation WHERE op_date_time BETWEEN :date_start AND :date_end AND op_mti = '0200' AND op_status = 1 ORDER BY id ASC", nativeQuery = true)
    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoChart(" +
            "o.op_date_time," +
            "o.op_amount" +
            ") " +
            "FROM Operation o WHERE o.op_date_time BETWEEN :date_start AND :date_end AND o.op_mti = '0200' AND o.op_status = 1 ORDER BY o.id ASC")

    List<DtoChart> selectForChartAll(@Param("date_start") Timestamp date_start, @Param("date_end") Timestamp date_end);


    @Query(value = "SELECT COUNT(id) FROM operation WHERE op_date_time BETWEEN NOW()-interval'1 minute' AND NOW()", nativeQuery = true)
    List<String> getOperationsPerMinute();


}
