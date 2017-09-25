package com.raj.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raj.model.PoojaBase;
import com.raj.model.repo.PoojaMasterRepo;

@Service
public class PoojaService {

	@Autowired
	private PoojaMasterRepo repo;
	
	public @ResponseBody List<PoojaBase> getAllPoojas() {
		Iterable<PoojaBase> poojaItr = repo.findAll();
		List<PoojaBase> poojaList = new ArrayList<>();
		poojaItr.spliterator().forEachRemaining(p -> poojaList.add(p));
		return poojaList;
	}

	public PoojaBase getPoojaById(Long id) {
		return repo.findOne(id);
	}

	public List<PoojaBase> getLimitedPoojas(Pageable page) {
		return repo.findAll(page);
	}
}
