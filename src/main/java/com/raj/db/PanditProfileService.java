package com.raj.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raj.model.PanditProfile;
import com.raj.model.repo.PanditProfileRepo;

@Service
public class PanditProfileService {

	@Autowired
	private PanditProfileRepo profileRepo;
	
	public @ResponseBody PanditProfile getAllPanditProfiles() {
		// This returns a JSON or XML with the users
		profileRepo.findAll();
		return profileRepo.findById(1L);
	}
}
