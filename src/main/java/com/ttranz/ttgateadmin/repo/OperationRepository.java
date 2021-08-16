package com.ttranz.ttgateadmin.repo;

import com.ttranz.ttgateadmin.models.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<Operation, Long> {

    @Query(value = "SELECT * FROM operation ORDER BY id DESC LIMIT 15", nativeQuery = true)
    Iterable<Operation> selectLastN();


}
