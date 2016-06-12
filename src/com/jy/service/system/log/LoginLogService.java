package com.jy.service.system.log;

import java.util.List;

import com.jy.common.mybatis.Page;
import com.jy.entity.system.log.LoginLog;

public interface LoginLogService {

	public Page<LoginLog> findByPage(LoginLog o,Page<LoginLog> page);	
	
	public void saveLoginLog(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
}
