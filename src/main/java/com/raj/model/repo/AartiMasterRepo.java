package com.raj.model.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.raj.model.AartiBase;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface AartiMasterRepo extends CrudRepository<AartiBase, Long> {
	@Query("select a from AartiBase a inner join AartiCategory c on a.id=c.aartiId and c.catId = :catId")
	public List<AartiBase> findAll(Pageable pageable,@Param("catId")Long catId);
	
	@Query("select count(a) from AartiBase a inner join AartiCategory c on a.id=c.aartiId and c.catId = :catId")
	public int getCountByCategory(@Param("catId")Long catId);
}