package com.raj.model.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.raj.model.PoojaBase;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface PoojaMasterRepo extends CrudRepository<PoojaBase, Long> {

	public List<PoojaBase> findAll(Pageable pageable);
}
