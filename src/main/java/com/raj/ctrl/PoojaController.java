package com.raj.ctrl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raj.db.PoojaService;
import com.raj.model.PoojaBase;

@Controller
public class PoojaController {	
	
	private static Logger log = Logger.getLogger(PoojaController.class);
	
	@Autowired
    private PoojaService dbServ;

	@Value("${app.page.noofelem}")
	private int elemPerPage;
	
	@Value("${app.page.main.limit}")
	private int mainPageLimit;
	
	@Value("${app.page.main.limit.max}")
	private int mainPageMaxLimit;
	
	@RequestMapping("/poojas")
    public @ResponseBody List<PoojaBase> getPoojaList() {
    	return dbServ.getAllPoojas();
    }
	
	@RequestMapping("/mainpoojas/{limit}")
    public @ResponseBody List<PoojaBase> getMainPoojaListLimit(@PathVariable("limit") int limit) {
		if(limit < 1) {
			limit = mainPageLimit;
		} else if(limit > mainPageMaxLimit) {
			limit = mainPageMaxLimit;
		}
		
		Pageable page = new PageRequest(0, limit);
		return dbServ.getLimitedPoojas(page, 1L);
    }
	
	@RequestMapping("/poojaCount")
    public @ResponseBody int getPoojaCount() {
    	List<PoojaBase> poojas = dbServ.getAllPoojas();
    	int count = 0;
    	if(poojas.size()%elemPerPage == 0) {
    		count = poojas.size()/elemPerPage;
    	} else {
    		count = (poojas.size()/elemPerPage) + 1;
    	}
    	
    	return count;
    }
	
	@RequestMapping("/poojaCountByFilter/{catId}/{elemPage}")
    public @ResponseBody int getPoojaCount(@PathVariable("catId") Long catId, @PathVariable("elemPage") Integer elemPage) {
    	int poojas = dbServ.getPoojaCountByCategory(catId);
    	int count = 0;
    	if(StringUtils.isEmpty(elemPage)) {
    		elemPage = elemPerPage;
    	}
    	if(poojas%elemPage == 0) {
    		count = poojas/elemPage;
    	} else {
    		count = (poojas/elemPage) + 1;
    	}
    	
    	return count;
    }
	
	@RequestMapping("/poojas/{pageId}/{catId}/{elemPage}")
	public @ResponseBody List<PoojaBase> getPoojaPerPage(@PathVariable("pageId") int pageId, @PathVariable("catId") long catId, @PathVariable("elemPage") Integer elemPage) {
		log.info("Getting List of aarti using "+elemPage+" elem per page and "+catId+" catId");
		if(StringUtils.isEmpty(elemPage)) {
    		elemPage = elemPerPage;
    	}
		Pageable page = new PageRequest(pageId, elemPage);
		List<PoojaBase> poojas = dbServ.getLimitedPoojas(page, catId);
		return poojas;
	}
	
	@RequestMapping("/pooja/{id}")
	public @ResponseBody PoojaBase getPoojaById(@PathVariable("id") Long id, Map<String, Object> model) {
		model.put("data", dbServ.getPoojaById(id));
		return dbServ.getPoojaById(id);
	}
}
