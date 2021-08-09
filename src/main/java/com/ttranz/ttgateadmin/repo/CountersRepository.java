package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.dto.DtoCounters;
import com.ttranz.ttgateadmin.dto.DtoOrgsAutocomplete;
import com.ttranz.ttgateadmin.dto.DtoTerminals;
import com.ttranz.ttgateadmin.models.Counters;
import com.ttranz.ttgateadmin.models.Orgs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountersRepository extends CrudRepository<Counters, Long> {


//    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoCounters(" +
//            "c.total_operations," +
//            "c.total_orgs," +
//            "c.total_terminals" +
//            ") " +
//            "FROM Counters c")
//    List<DtoCounters> selectCounters();

    @Query(value = "SELECT id, total_operations, total_orgs, total_terminals FROM counters", nativeQuery = true)
    List<Counters> selectCounters();

}
