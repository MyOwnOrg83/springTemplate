package com.raj.model.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.raj.model.VratBase;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface VratMasterRepo extends CrudRepository<VratBase, Long> {
	@Query("select a from VratBase a inner join VratCategory c on a.id=c.vratId and c.catId = :catId")
	public List<VratBase> findAll(Pageable pageable, @Param("catId")Long catId);
	
	@Query("select count(a) from VratBase a inner join VratCategory c on a.id=c.vratId and c.catId = :catId")
	public int getCountByCategory(@Param("catId")Long catId);
}