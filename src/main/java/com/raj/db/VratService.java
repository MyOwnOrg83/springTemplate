package com.raj.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raj.model.VratBase;
import com.raj.model.VratContent;
import com.raj.model.repo.VratContentRepo;
import com.raj.model.repo.VratMasterRepo;

@Service
public class VratService {
	
	@Autowired
	private VratMasterRepo repo;

	@Autowired
	private VratContentRepo cRepo;
	
	public @ResponseBody List<VratBase> getAllVrats() {
		// This returns a JSON or XML with the users
		Iterable<VratBase> aartiItr = repo.findAll();
		List<VratBase> aartiList = new ArrayList<>();
		aartiItr.spliterator().forEachRemaining(p -> aartiList.add(p));
		return aartiList;
	}

	public VratBase getVratById(Long id) {		
		return repo.findOne(id);
	}
	
	public List<VratBase> getLimitedVrats(Pageable page, Long catId) {
		return repo.findAll(page, catId);
	}
	
	public @ResponseBody int getVratCountByCategory(Long catId) {
		if(catId != null && catId > 0) {
			return repo.getCountByCategory(catId);
		}
		return getAllVrats().size();
	}
	
	public VratBase saveMasterData(VratBase baseData) {
		return repo.save(baseData);
	}
	
	public VratContent saveContentData(VratContent baseData) {
		return cRepo.save(baseData);
	}
}
