package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.models.Error;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ErrorRepository extends CrudRepository<Error, Long> {


    @Query(value = "SELECT * FROM error ORDER BY id DESC LIMIT 20", nativeQuery = true)
    List<Error> selectLastN();

}
