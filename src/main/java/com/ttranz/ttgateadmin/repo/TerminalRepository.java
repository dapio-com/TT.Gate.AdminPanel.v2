package com.ttranz.ttgateadmin.repo;


import com.ttranz.ttgateadmin.dto.DtoTerminal;
import com.ttranz.ttgateadmin.models.Terminal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TerminalRepository extends CrudRepository<Terminal, Long> {

//
//    SELECT terminal.terminal_id, org.org_name, terminal.terminal_org_id as org_id, terminal.terminal_tid as tid, terminals.terminal_tsp, terminal.terminal_status " +
//            "FROM terminal, org WHERE terminal.terminal_tid = tid AND org.org_id = org_id", tid)
//
    //@Query(value = "SELECT * FROM terminal ORDER BY id DESC LIMIT 8", nativeQuery = true)
    @Query(value = "SELECT " +
            "terminal.id, " +
            "org.org_name, " +
            "terminal.terminal_org_id, " +
            "terminal.terminal_tid, " +
            "terminal.terminal_tsp, " +
            "terminal.terminal_status " +
            "FROM " +
            "terminal, org " +
            "WHERE org.id = terminal.terminal_org_id ORDER BY terminal.id DESC LIMIT 8", nativeQuery = true)
    Iterable<Terminal> selectLastN();

    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoTerminal(" +
            "t.id," +
            "o.org_name," +
            "t.terminal_org_id," +
            "t.terminal_tid," +
            "t.terminal_tsp," +
            "t.terminal_status" +
            ") " +
            "FROM Terminal t LEFT JOIN Org o ON(o.id = t.terminal_org_id)")
    Page<DtoTerminal> selectIntoDtoTerminal(Pageable pageable);






    @Query(value = "SELECT CASE WHEN (COUNT(terminal_tid) > 0)  THEN true ELSE false END FROM terminal WHERE terminal_tid = :tid", nativeQuery = true)
    boolean selectTerminalTIDCheck(@Param("tid") String tid);

//    @Query (value = "SELECT * FROM org WHERE UPPER(terminal_tid) LIKE UPPER(concat('%', :tid,'%'))", nativeQuery = true)
//    Iterable<Terminal> searchForTerminal(@Param("tid") String tid);

    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoTerminal(" +
            "t.id," +
            "o.org_name," +
            "t.terminal_org_id," +
            "t.terminal_tid," +
            "t.terminal_tsp," +
            "t.terminal_status" +
            ") " +
            "FROM Terminal t LEFT JOIN Org o ON(o.id = t.terminal_org_id) " +
            "WHERE UPPER(t.terminal_tid) LIKE UPPER(concat('%', :tid,'%'))")
    Page<DtoTerminal> searchForTerminal(Pageable pageable, @Param("tid") String tid);
}
