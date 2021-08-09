package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.dto.DtoOrgsAutocomplete;
import com.ttranz.ttgateadmin.models.Org;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrgRepository extends CrudRepository<Org, Long> {

    @Query(value = "SELECT * FROM org ORDER BY id DESC LIMIT 8", nativeQuery = true)
    Iterable<Org> selectLastN();

    @Query(value = "SELECT CASE WHEN (COUNT(org_name) > 0)  THEN true ELSE false END FROM org WHERE org_name = :name", nativeQuery = true)
    boolean selectOrgNameCheck(@Param("name") String name);

    @Query (value = "SELECT * FROM org WHERE UPPER(org_name) LIKE UPPER(concat('%', :orgName,'%'))", nativeQuery = true)
    Iterable<Org> searchForOrg(@Param("orgName") String orgName);

    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoOrgsAutocomplete(" +
            "o.id," +
            "o.org_name" +
            ") " +
            "FROM Org o " +
            "WHERE UPPER(o.org_name) LIKE UPPER(concat('%', :org_name,'%'))")
    List<DtoOrgsAutocomplete> searchForOrgAutocomplete(@Param("org_name") String org_name);

}

