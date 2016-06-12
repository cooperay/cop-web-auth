package com.jy.controller.system.log;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.common.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.log.LoginLog;
import com.jy.service.system.log.LoginLogService;
/*
 * 登录日志
 */
@Controller
@RequestMapping("/backstage/loginLog/")
public class LoginLogController extends BaseController<LoginLog>{
	
	@Autowired
	public LoginLogService service;
	/**
	 * 登录日志首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){	
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));	
			return "/system/log/loginLog/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  findByPage(Page<LoginLog> page,LoginLog o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/loginLog/index")){
			try {
				Page<LoginLog> result=service.findByPage(o,page);
				p.put("obj",result);		
				p.put("res",Const.SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				p.put("resMsg",Const.DATA_FAIL);
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delBatch(String chks){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.DEL_FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){		
			try {
				String[] chk =chks.split(",");
				List<LoginLog> list=new ArrayList<LoginLog>();
				for(String s:chk){
					LoginLog sd=new LoginLog();
					sd.setId(s);
					list.add(sd);
				}
				service.deleteBatch(list);
				p.put("res",Const.SUCCEED);
				p.put("resMsg",Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
}
