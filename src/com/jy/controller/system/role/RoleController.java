package com.jy.controller.system.role;

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

import com.jy.common.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.common.utils.webpage.PageData;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.role.Org;
import com.jy.entity.system.role.Role;
import com.jy.service.system.role.OrgService;
import com.jy.service.system.role.RoleService;
/*
 * 角色管理
 */
@Controller
@RequestMapping("/backstage/role/")
public class RoleController extends BaseController<Role>{
	
	@Autowired
	public OrgService orgService;
	
	@Autowired
	public RoleService roleService;
	
	
	/**
	 * 角色首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException{	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));		
			return "/system/role/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findByPage(Page<Role> page,Role o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/role/index")){
			try {
				Page<Role> roles=roleService.findByPage(o, page);
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("obj",roles);		
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
	public Map<String, Object> add(Role o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){		
			try {
				Org org=new Org();
				if(StringUtils.isNotBlank(o.getOrgId())){
					org.setId(o.getOrgId());
					List<Org> orgs=orgService.find(org);
					if(orgs.size()>0){
						Org pOrg=orgs.get(0);
						String pId=pOrg.getpId();
						if(StringUtils.isNotBlank(pId)&&!StringUtils.equals(pId,"0")){
							o.setId(get32UUID());
							o.setCreateTime(new Date());
							roleService.insert(o);
							p.put("res",Const.SUCCEED);
							p.put("resMsg",Const.SAVE_SUCCEED);
						}else{
							p.put("resMsg","请在二级机构组织新增角色");
						}
					}
				}						
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
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delBatch(String chks){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res", 0);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){		
			try {
				String[] chk =chks.split(",");
				List<Role> list=new ArrayList<Role>();
				for(String s:chk){
					Role sd=new Role();
					sd.setId(s);
					list.add(sd);
				}
				roleService.deleteBatch(list);
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
	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  find(Role o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){		
			try {
				List<Role> list=roleService.find(o);
				Role role=list.get(0);
				p.put("obj",role);
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
	public Map<String, Object> update(Role o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){	
			try {
				o.setUpdateTime(new Date());
				roleService.update(o);
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
	public Map<String, Object> del(Role o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){	
			try {
				roleService.delete(o);
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
	
	@RequestMapping(value="listAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listAuthorized(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){		
			try {
				PageData pd = this.getPageData();
				String roleId=pd.getString("id");
				List<ZNodes> r=roleService.listAuthorized(roleId);
				p.put("obj",r);
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
	
	@RequestMapping(value="saveAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveAuthorized(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON,"/backstage/role/listAuthorized")){
			try {
				PageData pd = this.getPageData();
				String roleId=pd.getString("id");
				String aus=pd.getString("aus");	
				roleService.saveAuthorized(roleId,aus);
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
	
	@RequestMapping(value="getOrgTree", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOrgTree(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/role/index")){	
			try {
				List<ZNodes> r=orgService.getOrgTree();
				p.put("obj",r);
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
	
	@RequestMapping(value="getPreOrgTree", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPreOrgTree(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/role/index")){	
			try {
				List<ZNodes> r=orgService.getPreOrgTree();
				p.put("obj",r);
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
	
	@RequestMapping(value="addOrg", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrg(Org o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/role/index")){	
			try {
				o.setId(get32UUID());
				o.setCreateTime(new Date());
				orgService.insert(o);
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
	
	
	@RequestMapping(value="updateOrg", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateOrg(Org o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/role/index")){	
			try {
				o.setUpdateTime(new Date());
				orgService.update(o);
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
	
	@RequestMapping(value="findOrg", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findOrg(Org o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/role/index")){	
			try {
				List<Org> list=orgService.find(o);
				Org org=list.get(0);
				p.put("obj",org);
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
	
	@RequestMapping(value="delOrg", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delOrg(Org o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/role/index")){	
			try {
				int res=orgService.delOrg(o);
				if(res==1){
					p.put("res",Const.SUCCEED);
					p.put("resMsg",Const.DEL_SUCCEED);
				}else{
					p.put("resMsg","请先删除全部的子组织和子角色");
				}				
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
	
	
	@RequestMapping(value="orglistAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> orglistAuthorized(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/role/index")){	
			try {
				PageData pd = this.getPageData();
				String roleId=pd.getString("id");
				List<ZNodes> r=orgService.listAuthorized(roleId);
				p.put("obj",r);
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
	
	@RequestMapping(value="saveOrgAuthorized", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveOrgAuthorized(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);	
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/role/index")){	
			try {
				PageData pd = this.getPageData();
				String orgId=pd.getString("id");
				String aus=pd.getString("aus");	
				orgService.saveAuthorized(orgId,aus);
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
}
