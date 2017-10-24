package com.raj.model.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.raj.model.ChalisaBase;

public interface ChalisaMasterRepo extends CrudRepository<ChalisaBase, Long> {
	public List<ChalisaBase> findAll(Pageable pageable);
}
