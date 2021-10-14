package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {


    User findUserByUserName(String userName);




    @Query(value = "SELECT * FROM users ORDER BY id DESC LIMIT 6", nativeQuery = true)
    List<User> selectLastN();

    @Query(value = "SELECT CASE WHEN (COUNT(user_name) > 0)  THEN true ELSE false END FROM users WHERE user_name = :userName", nativeQuery = true)
    boolean selectUserNameCheck(@Param("userName") String userName);

    @Query (value = "SELECT * FROM users WHERE UPPER(user_name) LIKE UPPER(concat('%', :userName,'%')) ORDER BY user_name", nativeQuery = true)
    List<User> searchForUser(@Param("userName") String userName);

    @Query(value = "SELECT org_group_name FROM org_group WHERE id = :orgOrgGroupId", nativeQuery = true)
    String searchOrgGroupName(@Param("orgOrgGroupId") Long orgGroupId);

    @Query(value = "SELECT org_name FROM org WHERE id = :orgId", nativeQuery = true)
    String searchOrgName(@Param("orgId") Long orgId);

    User findUserById(Long id);







}
