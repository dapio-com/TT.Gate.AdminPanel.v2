package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.models.Counter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CounterRepository extends CrudRepository<Counter, Long> {


//    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoCounters(" +
//            "c.total_operations," +
//            "c.total_orgs," +
//            "c.total_terminals" +
//            ") " +
//            "FROM Counters c")
//    List<DtoCounters> selectCounters();

    @Query(value = "SELECT id, total_operations, total_orgs, total_terminals FROM counter", nativeQuery = true)
    List<Counter> selectCounters();

    @Query(value = "UPDATE counter SET total_orgs = total_orgs + 1 RETURNING total_orgs", nativeQuery = true)
    Long counterOrgPlus();

    @Query(value = "UPDATE counter SET total_orgs = total_orgs - 1 RETURNING total_orgs", nativeQuery = true)
    Long counterOrgMinus();

    @Query(value = "UPDATE counter SET total_terminals = total_terminals + 1 RETURNING total_terminals", nativeQuery = true)
    Long counterTerminalPlus();

    @Query(value = "UPDATE counter SET total_terminals = total_terminals - 1 RETURNING total_terminals", nativeQuery = true)
    Long counterTerminalMinus();


}
