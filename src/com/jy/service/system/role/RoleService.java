package com.jy.service.system.role;

import java.util.List;

import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.entity.system.role.Role;
import com.jy.service.base.BaseService;

public interface RoleService extends BaseService<Role>{

	 /**
     * 权限列表包含按钮
     * @param 角色Id
     * @return
     */
	public List<ZNodes> listAuthorized(String roleId);
	 /**
     * 根据角色Id保存权限列表
     * @param roleId 角色Id
     * @param auss 权限数组
     * @return
     */
	public void saveAuthorized(String roleId,String auss);
}
