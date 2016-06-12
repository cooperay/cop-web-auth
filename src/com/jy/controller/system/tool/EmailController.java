package com.jy.controller.system.tool;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jy.common.mybatis.Page;
import com.jy.common.utils.base.Const;
import com.jy.common.utils.email.MailConfig;
import com.jy.common.utils.email.MailUtil;
import com.jy.controller.base.BaseController;
import com.jy.entity.system.tool.Email;
import com.jy.service.system.tool.EmailService;

@Controller
@RequestMapping("/backstage/tool/email/")
public class EmailController extends BaseController<Email>{

	@Autowired
	private EmailService service;
	
	@RequestMapping("index")	
	public String index(Model model) throws UnsupportedEncodingException{
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU)){
			return "/system/tool/email/list";
		}
		return Const.NO_AUTHORIZED_URL;
	}
	
	
	@RequestMapping(value="findByPage", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findByPage(Page<Email> page,Email o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/tool/email/index")){
			try {
				Page<Email> accounts=service.findByPage(o, page);
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

	@RequestMapping(value="sendMail", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendMail(Email o){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg","发送失败");
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/tool/email/index")){
			try {
				o.setId(get32UUID());
				boolean res=service.sentEmailSimple(o);
				if(res){
					p.put("res",Const.SUCCEED);
					p.put("resMsg","发送成功");
				}
			} catch (Exception e) {
				logger.error(e.toString(),e);
				p.put("resMsg","发送失败");
			}
		}else{
			p.put("res",Const.NO_AUTHORIZED);
			p.put("resMsg",Const.NO_AUTHORIZED_MSG);
		}	
		return p;
	}
			
	@RequestMapping(value="getConfig", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getConfig(){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("res",Const.FAIL);
		p.put("resMsg",Const.DATA_FAIL);
		if(doSecurityIntercept(Const.RESOURCES_TYPE_MENU,"/backstage/tool/email/index")){
			try {
				MailConfig mc=MailUtil.setConfig(Const.EMAIL_CONFIG);
				p.put("obj",mc);		
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
}
