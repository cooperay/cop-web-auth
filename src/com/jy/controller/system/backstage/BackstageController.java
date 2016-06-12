package com.jy.controller.system.backstage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.common.utils.base.Const;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.common.utils.tree.MenuTreeUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.account.Account;
import com.jy.entity.system.resources.Resources;
import com.jy.service.system.resources.ResourcesService;

@Controller
@RequestMapping("/backstage/")
public class BackstageController extends BaseController<Object>{
	
	@Autowired
	public ResourcesService menuService;
	/**
	 * 访问系统首页
	 */
	@RequestMapping("index")
	public String index(Model model) throws UnsupportedEncodingException{	
		//shiro获取用户信息
		Account currentAccount=AccountShiroUtil.getCurrentUser();
		model.addAttribute("currentAccount", currentAccount);		
		return "/system/index";
	}
	
	@RequestMapping(value="menu/getMenu", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMenu(String type) throws UnsupportedEncodingException{	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res",Const.FAIL);
		List<Resources> menus =new ArrayList<Resources>();
		Object menu_o=null;
		try {
			//shiro获取用户信息
			Subject currentUser=SecurityUtils.getSubject();
			//shiro管理的session
			Session session=currentUser.getSession();
			//获得用户
			Account acount=(Account) session.getAttribute(Const.SESSION_USER);
			//获得用户Id
			String userId=acount.getAccountId();
			if(!"refash".equals(type)){	
				menu_o=session.getAttribute(Const.SESSION_MENULIST);
			}
			if(menu_o!=null){
				menus = (List<Resources>)menu_o;
			}else{	
				menus=menuService.findMenuTree(userId);
				session.setAttribute(Const.SESSION_MENULIST, menus);
			}	
			if(menus!=null){
				//将对象处理成树
				String html=MenuTreeUtil.buildTreeHtml(menus);			
				map.put("html", html);
				map.put("res",Const.SUCCEED);
			}
		} catch (InvalidSessionException e) {
			logger.error(e.toString(),e);
		}			
		return map;
	}
	
	@RequestMapping("adv")
	public String advUI(Model model) throws UnsupportedEncodingException{	
		return "/system/adv/adv";
	}
	
	@RequestMapping("404")
	public String errorlistUI(Model model) throws UnsupportedEncodingException{	
		return "/system/error/404";
	}
}
