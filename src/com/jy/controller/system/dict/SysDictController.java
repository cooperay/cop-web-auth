package com.jy.controller.system.dict;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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
import com.jy.entity.system.dict.SysDict;
import com.jy.service.system.dict.SysDictService;
/*
 * 系统字典
 */
@Controller
@RequestMapping("/backstage/sysDict/")
public class SysDictController extends BaseController<SysDict>{
	
	@Autowired
	public SysDictService service;
	/**
	 * 系统字典首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/system/dict/sys/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  findByPage(Page<SysDict> page,SysDict o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/sysDict/index")){
			try {
				Page<SysDict> result=service.findByPage(o,page);
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
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
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  add(SysDict o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){		
			try {
				o.setId(get32UUID());
				o.setCreateTime(new Date());	
				service.insert(o);
				p.put("res",Const.SUCCEED);
				p.put("resMsg",Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				p.put("resMsg",Const.SAVE_FAIL);
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  find(SysDict o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){		
			try {
				List<SysDict> list=service.find(o);
				SysDict obj=list.get(0);
				p.put("obj",obj);
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
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(SysDict o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){		
			try {
				o.setUpdateTime(new Date());
				service.update(o);
				p.put("res",Const.SUCCEED);
				p.put("resMsg",Const.UPDATE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				p.put("resMsg",Const.UPDATE_FAIL);
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> del(SysDict o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){	
			try {
				service.delete(o);
				p.put("res",Const.SUCCEED);
				p.put("resMsg",Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				p.put("resMsg",Const.DEL_FAIL);
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
		if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){	
			try {
				String[] chk =chks.split(",");
				List<SysDict> list=new ArrayList<SysDict>();
				for(String s:chk){
					SysDict sd=new SysDict();
					sd.setId(s);
					list.add(sd);
				}
				service.deleteBatch(list);
				p.put("res",Const.SUCCEED);
				p.put("resMsg",Const.DEL_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				p.put("resMsg",Const.DEL_FAIL);
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
}
