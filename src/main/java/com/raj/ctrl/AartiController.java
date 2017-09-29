package com.raj.ctrl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raj.db.AartiService;
import com.raj.model.AartiBase;

@RestController
public class AartiController {

	private static Logger log = Logger.getLogger(AartiController.class);
	@Autowired
	private AartiService dbServ;
	
	@Value("${app.page.noofelem}")
	private int elemPerPage;
	
	@Value("${app.page.main.limit}")
	private int mainPageLimit;
	
	@Value("${app.page.main.limit.max}")
	private int mainPageMaxLimit;
	
	@RequestMapping("/aartis")
    public @ResponseBody List<AartiBase> getPoojaList() {
		return dbServ.getAllAartis();
    }
	
	@RequestMapping("/mainaartis/{limit}")
    public @ResponseBody List<AartiBase> getMainAartiListLimit(@PathVariable("limit") int limit) {
		if(limit < 1) {
			limit = mainPageLimit;
		} else if(limit > mainPageMaxLimit) {
			limit = mainPageMaxLimit;
		}
		
		Pageable page = new PageRequest(0, limit);
		return dbServ.getLimitedAartis(page,1L);
    }
	
	@RequestMapping("/aartiCount")
    public @ResponseBody int getAartiCount() {
    	List<AartiBase> aartis = dbServ.getAllAartis();
    	int count = 0;
    	if(aartis.size()%elemPerPage == 0) {
    		count = aartis.size()/elemPerPage;
    	} else {
    		count = (aartis.size()/elemPerPage) + 1;
    	}
    	
    	return count;
    }
	
	@RequestMapping("/aartiCountByCat/{catId}")
    public @ResponseBody int getAartiCount(@PathVariable("catId") Long catId) {
    	int aartis = dbServ.getAartiCountByCategory(catId);
    	int count = 0;
    	if(aartis%elemPerPage == 0) {
    		count = aartis/elemPerPage;
    	} else {
    		count = (aartis/elemPerPage) + 1;
    	}
    	
    	return count;
    }
	
	@RequestMapping("/aartis/{pageId}/{catId}")
	public @ResponseBody List<AartiBase> getAartiPerPage(@PathVariable("pageId") int pageId, @PathVariable("catId") long catId) {
		log.info("Getting List of aarti using "+elemPerPage+" elem per page and "+catId+" catId");
		Pageable page = new PageRequest(pageId, elemPerPage);
		List<AartiBase> aartis = dbServ.getLimitedAartis(page, catId);
		return aartis;
	}
	
	@RequestMapping("/aarti/{id}")
	public @ResponseBody AartiBase getAartiById(@PathVariable("id") Long id) {
		return dbServ.getAartiById(id);
	}
}
