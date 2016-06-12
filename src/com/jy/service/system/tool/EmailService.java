package com.jy.service.system.tool;

import com.jy.entity.system.tool.Email;
import com.jy.service.base.BaseService;

public interface EmailService extends BaseService<Email>{
	
	/**发送邮件（简单版）
     * @param o
     * @return
     */
	public boolean sentEmailSimple(Email o);

}
