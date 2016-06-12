package com.jy.repository.system.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.entity.system.resources.RoleResources;
import com.jy.entity.system.role.Role;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
@JYBatis
public interface RoleDao extends BaseDao<Role>{
	/**
     * 根据Id获得角色
     * @param 角色Id
     * @return
     */
	public Role getRole(@Param("id")String id);
	 /**
     * 权限列表包含按钮
     * @param 角色Id
     * @return
     */
	public List<ZNodes> listAuthorized(@Param("roleId")String roleId);	
	 /**
     * 根据角色Id删除所有权限关系
     * @param roleId 角色Id
     * @return
     */
	public void delAuthorizedByRoleId(@Param("roleId")String roleId);
	 /**
     * 根据角色Id删除所有权限关系(批量)
     * @param os 角色Id集合
     * @return
     */
	public void deleteBatchAuthorizedByRoleId(List<Role> os);
	/**
     * 通过角色资源对象列表建立权限关系(批量插入)
     * @param  list 角色资源对象列表
     * @return
     */
	public void insertAuthorizedByRoleId(List<RoleResources> list);


}
