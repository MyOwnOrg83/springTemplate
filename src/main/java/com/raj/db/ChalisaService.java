package com.raj.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raj.model.ChalisaBase;
import com.raj.model.repo.ChalisaMasterRepo;

@Service
public class ChalisaService {
	@Autowired
	private ChalisaMasterRepo repo;
	
	public @ResponseBody List<ChalisaBase> getAllChalisas() {
		// This returns a JSON or XML with the users
		Iterable<ChalisaBase> chalisaItr = repo.findAll();
		List<ChalisaBase> chalisaList = new ArrayList<>();
		chalisaItr.spliterator().forEachRemaining(p -> chalisaList.add(p));
		return chalisaList;
	}

	public ChalisaBase getChalisaById(Long id) {		
		return repo.findOne(id);
	}
	
	public List<ChalisaBase> getLimitedChalisas(Pageable page) {
		return repo.findAll(page);
	}
}
