package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.dto.DtoOrg;
import com.ttranz.ttgateadmin.dto.DtoOrgsAutocomplete;
import com.ttranz.ttgateadmin.models.Org;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrgRepository extends CrudRepository<Org, Long> {

    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoOrg(" +
            "o.id," +
            "g.org_group_name," +
            "o.org_group_id," +
            "o.org_name," +
            "o.org_owner" +
            ") " +
            "FROM Org o LEFT JOIN OrgGroup g ON(g.id = o.org_group_id)")
    Page<DtoOrg> selectIntoDtoOrg(Pageable pageable);


//    @Query(value = "SELECT * FROM org ORDER BY id DESC LIMIT 8", nativeQuery = true)
//    List<Org> selectLastN();

    @Query(value = "SELECT CASE WHEN (COUNT(org_name) > 0)  THEN true ELSE false END FROM org WHERE org_name = :name", nativeQuery = true)
    boolean selectOrgNameCheck(@Param("name") String name);

//    @Query (value = "SELECT * FROM org WHERE UPPER(org_name) LIKE UPPER(concat('%', :orgName,'%'))", nativeQuery = true)
//    List<Org> searchForOrg(@Param("orgName") String orgName);

    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoOrg(" +
            "o.id," +
            "g.org_group_name," +
            "o.org_group_id," +
            "o.org_name," +
            "o.org_owner" +
            ") " +
            "FROM Org o LEFT JOIN OrgGroup g ON(g.id = o.org_group_id) " +
            "WHERE UPPER(o.org_name) LIKE UPPER(concat('%', :org_name,'%')) ORDER BY o.org_name")
    Page<DtoOrg> searchForOrg(Pageable pageable, @Param("org_name") String org_name);




    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoOrgsAutocomplete(" +
            "o.id," +
            "o.org_name" +
            ") " +
            "FROM Org o " +
            "WHERE UPPER(o.org_name) LIKE UPPER(concat('%', :org_name,'%'))")
    List<DtoOrgsAutocomplete> searchForOrgAutocomplete(@Param("org_name") String org_name);

    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoOrgsAutocomplete(" +
            "o.id," +
            "o.org_name" +
            ") " +
            "FROM Org o " +
            "WHERE o.org_group_id = :org_group_id AND UPPER(o.org_name) LIKE UPPER(concat('%', :org_name,'%'))")
    List<DtoOrgsAutocomplete> searchForOrgAutocompleteOrgGroupId(@Param("org_name") String org_name, @Param("org_group_id") Long org_group_id);


    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoOrg(" +
            "o.id," +
            "g.org_group_name," +
            "o.org_group_id," +
            "o.org_name," +
            "o.org_owner" +
            ") " +
            "FROM Org o LEFT JOIN OrgGroup g ON(g.id = o.org_group_id) " +
            "WHERE o.id = :id")
    List<DtoOrg> searchOrgForEdit(@Param("id") Long id);


    @Query(value = "SELECT COUNT(id) FROM org WHERE org_group_id = :id", nativeQuery = true)
    int countOrgsThatHaveGroups(@Param("id") Long id);

    @Query(value = "SELECT * FROM org WHERE org_group_id = :org_group_id ORDER BY org_name", nativeQuery = true)
    List<Org> selectOrgsByOrgGroupId(@Param("org_group_id") Long org_group_id);


    Org findOrgById(Long id);

}

