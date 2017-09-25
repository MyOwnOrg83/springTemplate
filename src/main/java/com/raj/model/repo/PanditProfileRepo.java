package com.raj.model.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.raj.model.PanditProfile;


@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface PanditProfileRepo extends CrudRepository<PanditProfile, Long>{

	@Query("Select p from PanditProfile p where p.id=:ppId")
	PanditProfile findById(@Param("ppId") Long id);
}
