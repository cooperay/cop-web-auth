package com.jy.controller.system.resources;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.common.utils.base.Const;
import com.jy.common.utils.tree.MenuTreeUtil;
import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.resources.Resources;
import com.jy.service.system.resources.ResourcesService;
/*
 * 菜单
 */
@Controller
@RequestMapping("/backstage/resources/")
public class ResourcesController extends BaseController<Resources>{
	
	@Autowired
	public ResourcesService service;
	/**
	 * 菜单首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/system/resources/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="listAllParentMenu", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listAllParentMenu(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/resources/index")){
			try {
				Resources o=new Resources();
				List<Resources> r=service.find(o);
				List<Resources> tree=MenuTreeUtil.buildTree(r);
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("obj",tree);		
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
	
	@RequestMapping(value="listResources", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listAllResources(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/resources/index")){
			try {
				Resources o=new Resources();
				List<ZNodes> r=service.listResources(o);
				p.put("obj",r);
				p.put("res",Const.SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
		
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Resources o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.SAVE_FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){	
			try {
				o.setId(get32UUID());
				o.setCreateTime(new Date());
				o.setResUrl(StringUtils.trim(o.getResUrl()));
				o.setParentId(StringUtils.isNotBlank(o.getParentId())?o.getParentId():"0");
				service.insert(o);
				p.put("res",Const.SUCCEED);
				p.put("resMsg",Const.SAVE_SUCCEED);
			} catch (Exception e) {
				logger.error(e.toString(),e);
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
				List<Resources> list=new ArrayList<Resources>();
				for(String s:chk){
					Resources sd=new Resources();
					sd.setId(s);
					list.add(sd);
				}
				int res=service.tranDeleteBatch(list);
				if(res==1){
					p.put("res",Const.SUCCEED);
					p.put("resMsg",Const.DEL_SUCCEED);
				}else{
					p.put("resMsg","请先删除子资源");
				}	
			} catch (Exception e) {
				logger.error(e.toString(),e);
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  find(Resources o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){	
			try {
				List<Resources> list=service.find(o);
				Resources resources=list.get(0);
				p.put("obj",resources);
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
	public Map<String, Object> update(Resources o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.UPDATE_FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){	
			try {
				if(!StringUtils.equals(o.getId(), o.getParentId())){
					o.setUpdateTime(new Date());
					o.setResUrl(StringUtils.trim(o.getResUrl()));		
					service.update(o);
					p.put("res",Const.SUCCEED);
					p.put("resMsg",Const.UPDATE_SUCCEED);				
				}else{
					p.put("resMsg","上级资源不能是本资源");
				}	
			} catch (Exception e) {
				logger.error(e.toString(),e);
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> del(Resources o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.DEL_FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){	
			try {
				int res=service.tranDelete(o);
				if(res==1){
					p.put("res",Const.SUCCEED);
					p.put("resMsg",Const.DEL_SUCCEED);
				}else{
					p.put("resMsg","请先删除子资源");
				}	
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
