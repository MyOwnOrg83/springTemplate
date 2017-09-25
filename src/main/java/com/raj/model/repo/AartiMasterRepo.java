package com.raj.model.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.raj.model.AartiBase;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface AartiMasterRepo extends CrudRepository<AartiBase, Long> {
	public List<AartiBase> findAll(Pageable pageable);
}