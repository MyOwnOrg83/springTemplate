package com.raj.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raj.db.VratService;
import com.raj.model.VratBase;

@RestController
public class VratController {

	@Autowired
	private VratService dbServ;
	
	@Value("${app.page.noofelem}")
	private int elemPerPage;
	
	@Value("${app.page.main.limit}")
	private int mainPageLimit;
	
	@Value("${app.page.main.limit.max}")
	private int mainPageMaxLimit;
	
	@RequestMapping("/vrats")
    public @ResponseBody List<VratBase> getVratList() {
		return dbServ.getAllVrats();
    }
	
	@RequestMapping("/mainvrats/{limit}")
    public @ResponseBody List<VratBase> getMainVratListLimit(@PathVariable("limit") int limit) {
		if(limit < 1) {
			limit = mainPageLimit;
		} else if(limit > mainPageMaxLimit) {
			limit = mainPageMaxLimit;
		}
		
		Pageable page = new PageRequest(0, limit);
		return dbServ.getLimitedVrats(page);
    }
	
	@RequestMapping("/vratCount")
    public @ResponseBody int getVratCount() {
    	List<VratBase> vrats = dbServ.getAllVrats();
    	int count = 0;
    	if(vrats.size()%elemPerPage == 0) {
    		count = vrats.size()/elemPerPage;
    	} else {
    		count = (vrats.size()/elemPerPage) + 1;
    	}
    	
    	return count;
    }
	
	@RequestMapping("/vrats/{pageId}")
	public @ResponseBody List<VratBase> getVratPerPage(@PathVariable("pageId") int pageId) {
		Pageable page = new PageRequest(pageId, elemPerPage);
		List<VratBase> vrats = dbServ.getLimitedVrats(page);
		return vrats;
	}
	
	@RequestMapping("/vrat/{id}")
	public @ResponseBody VratBase getVratById(@PathVariable("id") Long id) {
		return dbServ.getVratById(id);
	}
}
