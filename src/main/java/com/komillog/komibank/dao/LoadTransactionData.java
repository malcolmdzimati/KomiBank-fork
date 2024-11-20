package com.komillog.komibank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.komillog.komibank.model.Customer;
import com.komillog.komibank.model.Operation;


public interface LoadTransactionData extends JpaRepository<Operation, Long>, JpaSpecificationExecutor<Operation> {

	@Query("select * Top 1000 from Operation op where op.account.code=:accountCode order by op.date desc")
	List<Operation> getTransactionHistory(@Param("accountCode") Long accountCode);
	
}
