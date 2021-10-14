package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.dto.DtoOrgGroup;
import com.ttranz.ttgateadmin.models.OrgGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrgGroupRepository extends CrudRepository<OrgGroup, Long> {


    @Query(value = "SELECT * FROM org_group ORDER BY id DESC LIMIT 9", nativeQuery = true)
    List<OrgGroup> selectLastN();

    @Query(value = "SELECT CASE WHEN (COUNT(org_group_name) > 0)  THEN true ELSE false END FROM org_group WHERE org_group_name = :name", nativeQuery = true)
    boolean selectOrgGroupNameCheck(@Param("name") String name);

    @Query (value = "SELECT * FROM org_group WHERE UPPER(org_group_name) LIKE UPPER(concat('%', :orgGroupName,'%'))", nativeQuery = true)
    List<OrgGroup> searchForOrgGroup(@Param("orgGroupName") String orgGroupName);

    @Query("SELECT new com.ttranz.ttgateadmin.dto.DtoOrgGroup(" +
            "g.id," +
            "g.org_group_name," +
            "g.org_group_description" +
            ") " +
            "FROM OrgGroup g " +
            "WHERE UPPER(g.org_group_name) LIKE UPPER(concat('%', :orgGroupName,'%'))")
    List<DtoOrgGroup> searchForOrgGroupAutocomplete(@Param("orgGroupName") String orgGroupName);

    OrgGroup findOrgGroupById(Long id);

}
