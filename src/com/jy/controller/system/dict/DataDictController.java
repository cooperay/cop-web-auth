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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.common.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.dict.DataDict;
import com.jy.service.system.dict.DataDictService;
/*
 * 数据字典
 */
@Controller
@RequestMapping("/backstage/dataDict/")
public class DataDictController extends BaseController<DataDict>{
	
	@Autowired
	public DataDictService service;

	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));
			return "/system/dict/data/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findByPage(Page<DataDict> page,DataDict o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/dataDict/index")){
			try {
				Page<DataDict> result=service.findByPage(o, page);
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
	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  find(DataDict o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){	
			try {
				List<DataDict> list= service.find(o);
				DataDict obj=list.get(0);
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
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(@RequestBody DataDict o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){					
			try {
				o.setId(get32UUID());
				int res=service.insertDataDict(o);
				if(res==1)     p.put("resMsg",Const.SAVE_SUCCEED);
				else if(res==0)p.put("resMsg","关键字已存在");
				p.put("res",res);			
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
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(@RequestBody DataDict o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){		
			try {
				o.setUpdateTime(new Date());
				int res=service.updateDataDict(o);
				if(res==1)p.put("resMsg",Const.UPDATE_SUCCEED);
				p.put("res",res);
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
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delBatch(String chks){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){	
			try {
				String[] chk =chks.split(",");
				List<DataDict> list=new ArrayList<DataDict>();
				for(String s:chk){
					DataDict sd=new DataDict();
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
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> del(DataDict o){
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
	
	@RequestMapping(value="getDictSelect", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDictSelect(String ids,String keys) throws UnsupportedEncodingException{	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res",Const.FAIL);
		try {
			Map<String,DataDict> obj=service.findDatas(ids,keys);
			map.put("obj", obj);
			map.put("res",Const.SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return map;
	}
	
	
}
