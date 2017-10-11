package com.raj.ctrl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raj.db.VratService;
import com.raj.model.VratBase;

@RestController
public class VratController {

	private static Logger log = Logger.getLogger(VratController.class);
	
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
		return dbServ.getLimitedVrats(page, 1L);
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
	
	@RequestMapping("/vratCountByFilter/{catId}/{elemPage}")
    public @ResponseBody int getVratCount(@PathVariable("catId") Long catId, @PathVariable("elemPage") Integer elemPage) {
    	int vrats = dbServ.getVratCountByCategory(catId);
    	int count = 0;
    	if(StringUtils.isEmpty(elemPage)) {
    		elemPage = elemPerPage;
    	}
    	if(vrats%elemPage == 0) {
    		count = vrats/elemPage;
    	} else {
    		count = (vrats/elemPage) + 1;
    	}
    	
    	return count;
    }
	
	@RequestMapping("/vrats/{pageId}/{catId}/{elemPage}")
	public @ResponseBody List<VratBase> getVratPerPage(@PathVariable("pageId") int pageId, @PathVariable("catId") long catId, @PathVariable("elemPage") Integer elemPage) {
		log.info("Getting List of vrat using "+elemPage+" elem per page and "+catId+" catId");
		if(StringUtils.isEmpty(elemPage)) {
    		elemPage = elemPerPage;
    	}
		Pageable page = new PageRequest(pageId, elemPage);
		List<VratBase> vrats = dbServ.getLimitedVrats(page, catId);
		return vrats;
	}
	
	@RequestMapping("/vrat/{id}")
	public @ResponseBody VratBase getVratById(@PathVariable("id") Long id) {
		return dbServ.getVratById(id);
	}
}
