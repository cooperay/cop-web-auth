package com.jy.controller.system.account;

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
import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.account.Account;
import com.jy.service.system.account.AccountService;



@Controller
@RequestMapping("/backstage/account/")
public class AccountController extends BaseController<Account>{

	@Autowired
	private AccountService service;

	@RequestMapping("index")	
	public String index(Model model) throws UnsupportedEncodingException{
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			model.addAttribute("permitBtn", getPermitBtn(Const.RESOURCES_TYPE_FUNCTION));	
			return "/system/account/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	@RequestMapping(value="roleTree", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> roleTree(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/account/index")){
			try {
				List<ZNodes> list=service.getRoles();
				p.put("res",Const.SUCCEED);
				p.put("obj", list);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				p.put("resMsg", Const.DATA_FAIL);
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
		
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findByPage(Page<Account> page,Account o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/account/index")){
			try {
				Page<Account> accounts=service.findByPage(o, page);
				p.put("permitBtn",getPermitBtn(Const.RESOURCES_TYPE_BUTTON));
				p.put("obj",accounts);		
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
	public Map<String, Object> add(Account o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.SAVE_FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){			
			try {
				o.setAccountId(get32UUID());	
				int res=service.insertAccount(o);
				if(res==1){
					p.put("res",Const.SUCCEED);
					p.put("resMsg",Const.SAVE_SUCCEED);
				}else{
					p.put("res", 2);
					p.put("resMsg","登录名已存在");
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
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delBatch(String chks){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.DEL_FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_FUNCTION)){		
			try {
				String[] chk =chks.split(",");
				List<Account> list=new ArrayList<Account>();
				for(String s:chk){
					Account sd=new Account();
					sd.setAccountId(s);
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
	
	@RequestMapping(value="find", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  find(Account o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){	
			try {
				List<Account> list=service.find(o);
				Account acount =list.get(0);
				p.put("obj",acount);
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
	public Map<String, Object> update(Account o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.UPDATE_FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
			try {
				o.setUpdateTime(new Date());
				service.update(o);
				p.put("res",Const.SUCCEED);
				p.put("resMsg",Const.UPDATE_SUCCEED);
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
	public Map<String, Object> del(Account o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.DEL_FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
			try {
				service.delete(o);
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
	
	@RequestMapping(value="resetPwd", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resetPwd(Account o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.UPDATE_FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_BUTTON)){
			try {
				o.setUpdateTime(new Date());
				o.setPassword(getPageData().getString("pwd"));
				int res=service.sysResetPwd(o);
				if(res==1) p.put("resMsg",Const.SAVE_SUCCEED);
				p.put("res",res);	
			} catch (Exception e) {
				logger.error(e.toString(),e);
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
	
	@RequestMapping(value="setSetting", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setSetting(String skin){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.UPDATE_FAIL);
		try {
			service.setSetting(skin);
			p.put("res",Const.SUCCEED);
			p.put("resMsg",Const.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return p;
	}
	
	@RequestMapping(value="getPerData", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPerData(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		try {
			Account account=service.getPerData();
			p.put("res",Const.SUCCEED);
			p.put("obj", account);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			p.put("resMsg",Const.DATA_FAIL);
		}
		return p;
	}
	
	@RequestMapping(value="setHeadpic", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setHeadpic(Account o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.UPDATE_FAIL);
		try {
			service.setHeadpic(o);
			p.put("res",Const.SUCCEED);
			p.put("resMsg",Const.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return p;
	}
	
	@RequestMapping(value="setPerData", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setPerData(Account o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.UPDATE_FAIL);
		try {
			service.setPerData(o);
			p.put("res",Const.SUCCEED);
			p.put("resMsg",Const.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return p;
	}
	
	@RequestMapping(value="preResetPWD", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resetPWD(String opwd,String npwd,String qpwd){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.UPDATE_FAIL);
		try {
			int res=service.preResetPwd(opwd,npwd,qpwd);
			if     (res==1)p.put("resMsg",Const.UPDATE_SUCCEED);
			else if(res==2)p.put("resMsg","密码不正确");			
			else if(res==3)p.put("resMsg","两次密码不一致");			
			p.put("res",res);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return p;
	}
	
}
