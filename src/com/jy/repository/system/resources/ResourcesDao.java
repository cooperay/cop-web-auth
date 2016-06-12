package com.jy.repository.system.resources;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.entity.system.resources.Resources;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
/**
 * 资源数据层
 */
@JYBatis
public interface ResourcesDao extends BaseDao<Resources>{
	 /**
     * 菜单树
     * @param menuId
     * @param userId
     * @return
     */
	public List<Resources> findMenuTree(@Param("userId")String userId);
	 /**
     * 按钮列
     * @param menuId
     * @param userId
     * @return
     */
	public List<Resources> findBtn(@Param("type")String type,@Param("menuId")String menuId,@Param("userId")String userId);
    /**
     * 资源列表只含菜单不含按钮
     * @param r
     * @return
     */
	public List<ZNodes> listResources(Resources r);
	 /**
     * 权限列表包含按钮
     * @param 角色Id
     * @return
     */
	public List<ZNodes> listAuthorized(@Param("roleId")String roleId);
	
	 /**
     * 根据资源Id删除所有权限关系
     * @param roleId 资源Id
     * @return
     */
	public void delRoleAuthByResId(@Param("resourcesId")String resourcesId);
	 /**
     * 根据组织Id删除所有权限关系
     * @param roleId 资源Id
     * @return
     */
	public void delOrgAuthByResId(@Param("resourcesId")String resourcesId);
	 /**
     * 根据资源Id删除所有角色权限关系(批量)
     * @param os 资源Id集合
     * @return
     */
	public void delBatchRoleAuthByResId(List<Resources> os);
	 /**
     * 根据资源Id删除所有组织权限关系(批量)
     * @param os 资源Id集合
     * @return
     */
	public void delBatchOrgAuthByResId(List<Resources> os);
	 /**
     * 用户权限资源列表
     * @param userId
     * @param type
     * @return
     */
	public List<Resources> resAuthorized(@Param("userId")String userId,@Param("type")String type);
	 /**
     * 获取子资源数量
     * @param parentId
     * @return
     */
	public int childCount(@Param("parentId")String parentId);
	 /**
     * 获取子资源数量(批量)
     * @param os
     * @return
     */
	public int childBatchCount(List<Resources> os);
}
