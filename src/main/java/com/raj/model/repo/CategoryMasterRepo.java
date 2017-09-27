package com.raj.model.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.raj.model.CategoryBase;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface CategoryMasterRepo extends CrudRepository<CategoryBase, Long> {

}
