package com.komillog.komibank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.komillog.komibank.model.Operation;


public interface OperationDao extends JpaRepository<Operation, Long>{

	@Query("select op from Operation op where op.account.code=:x order by op.date desc")
	Page<Operation> getAccountOperations(@Param("x") Long accountCode, Pageable pageable);
	
}
