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

import com.raj.db.ChalisaService;
import com.raj.model.ChalisaBase;

@RestController
public class ChalisaController {
	private static Logger log = Logger.getLogger(ChalisaController.class);
	@Autowired
	private ChalisaService dbServ;
	
	@Value("${app.page.noofelem}")
	private int elemPerPage;
	
	@Value("${app.page.main.limit}")
	private int mainPageLimit;
	
	@Value("${app.page.main.limit.max}")
	private int mainPageMaxLimit;
	
	@RequestMapping("/chalisas")
    public @ResponseBody List<ChalisaBase> getPoojaList() {
		return dbServ.getAllChalisas();
    }
	
	@RequestMapping("/mainchalisas/{limit}")
    public @ResponseBody List<ChalisaBase> getMainChalisaListLimit(@PathVariable("limit") int limit) {
		if(limit < 1) {
			limit = mainPageLimit;
		} else if(limit > mainPageMaxLimit) {
			limit = mainPageMaxLimit;
		}
		
		Pageable page = new PageRequest(0, limit);
		return dbServ.getLimitedChalisas(page);
    }
	
	@RequestMapping("/chalisaCount")
    public @ResponseBody int getChalisaCount() {
    	List<ChalisaBase> aartis = dbServ.getAllChalisas();
    	int count = 0;
    	if(aartis.size()%elemPerPage == 0) {
    		count = aartis.size()/elemPerPage;
    	} else {
    		count = (aartis.size()/elemPerPage) + 1;
    	}
    	
    	return count;
    }
	
	@RequestMapping("/chalisas/{pageId}")
	public @ResponseBody List<ChalisaBase> getChalisaPerPage(@PathVariable("pageId") int pageId) {
		Pageable page = new PageRequest(pageId, elemPerPage);
		List<ChalisaBase> aartis = dbServ.getLimitedChalisas(page);
		return aartis;
	}
	
	@RequestMapping("/chalisa/{id}")
	public @ResponseBody ChalisaBase getChalisaById(@PathVariable("id") Long id) {
		return dbServ.getChalisaById(id);
	}
}
