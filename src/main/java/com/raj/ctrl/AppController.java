package com.raj.ctrl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raj.db.PanditProfileService;
import com.raj.db.VratService;
import com.raj.model.VratBase;
import com.raj.model.VratContent;

@Controller
public class AppController {

	@Autowired
	private PanditProfileService dbServ;
	
	@Autowired
	private VratService vServ;
	
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
    	
    	System.out.println(dbServ.getAllPanditProfiles());
        model.put("name", dbServ.getAllPanditProfiles().getfName());
        return "greeting";
    }
    
    @RequestMapping("/errorpage")
    public String getErrorPage() {
    	return "error";
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/datamgr/vratMaster")
    public @ResponseBody VratBase createVratMaster(HttpServletRequest request) {
    	VratBase base = new VratBase();
    	base.setName(request.getParameter("vrat_name"));
    	base.setShortDesc(request.getParameter("short_desc"));
    	base.setIcon(request.getParameter("icon_loc"));
    	base.setImage(request.getParameter("img_loc"));
    	
    	
    	return vServ.saveMasterData(base);
    	
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/datamgr/vratContent")
    public @ResponseBody VratContent createVratContent(HttpServletRequest request) {
    	VratContent base = new VratContent();
    	if(!StringUtils.isNullOrEmpty(request.getParameter("vrat_master_id"))) {
    		base.setVratId(Long.parseLong(request.getParameter("vrat_master_id")));
    	if(!StringUtils.isNullOrEmpty(request.getParameter("lang")))
    		base.setLang(request.getParameter("lang"));
    	if(!StringUtils.isNullOrEmpty(request.getParameter("process")))
    		base.setProcess(request.getParameter("process"));
    	if(!StringUtils.isNullOrEmpty(request.getParameter("item")))
    		base.setItem(request.getParameter("item"));
    	if(!StringUtils.isNullOrEmpty(request.getParameter("content")))
    		base.setContent(request.getParameter("content"));
    	if(!StringUtils.isNullOrEmpty(request.getParameter("context")))
    		base.setContext(request.getParameter("context"));
    	} else {
    		return null;
    	}
    	
    	
    	return vServ.saveContentData(base);
    	
    }
    
}