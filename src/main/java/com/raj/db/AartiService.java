package com.raj.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raj.model.AartiBase;
import com.raj.model.repo.AartiMasterRepo;

@Service
public class AartiService {
	
	@Autowired
	private AartiMasterRepo repo;
	
	
	
	public @ResponseBody List<AartiBase> getAllAartis() {
		// This returns a JSON or XML with the users
		Iterable<AartiBase> aartiItr = repo.findAll();
		List<AartiBase> aartiList = new ArrayList<>();
		aartiItr.spliterator().forEachRemaining(p -> aartiList.add(p));
		return aartiList;
	}

	public AartiBase getAartiById(Long id) {		
		return repo.findOne(id);
	}
	
	public List<AartiBase> getLimitedAartis(Pageable page, Long catId) {
		return repo.findAll(page,catId);
	}
	
	public @ResponseBody int getAartiCountByCategory(Long catId) {
		if(catId != null && catId > 0) {
			return repo.getCountByCategory(catId);
		}
		return getAllAartis().size();
	}
}
