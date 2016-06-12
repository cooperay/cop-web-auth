package com.jy.repository.system.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.entity.system.resources.OrgResources;
import com.jy.entity.system.resources.RoleResources;
import com.jy.entity.system.role.Org;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
@JYBatis
public interface OrgDao extends BaseDao<Org>{
	/**
     * 通过ID获取机构
     * @return
     */
	public Org getOrg(@Param("id")String id);
	/**
     * 通过父ID子机构
     * @return
     */
	public List<Org> findAndChild(@Param("id")String id);
	/**
     * 获得新的不是权限的Id
     * @return
     */
	public List<String> getNotAuthoByOrg(@Param("orgId")String orgId,@Param("resIds")List<String> resIds);
	/**
     * 获取机构树
     * @return
     */
	public List<ZNodes> getOrgTree();
	/**
     * 获取上级机构树
     * @return
     */
	public List<ZNodes> getPreOrgTree();
	/**
     * 权限列表包含按钮
     * @param orgId 组织Id
     * @return
     */
	public List<ZNodes> listAuthorized(@Param("orgId")String orgId);	
	/**
     * 权限列表包含按钮（带门限）
     * @param pId   父Id
     * @param orgId 组织Id
     * @return
     */
	public List<ZNodes> listAuthorizedByTh(@Param("pId")String pId,@Param("orgId")String orgId);
	 /**
     * 根据组织Id删除所有权限关系
     * @param orgId 角色Id
     * @return
     */
	public void delAuthorizedByOrgId(@Param("orgId")String orgId);
	 /**
     * 根据组织Id删除所有权限关系(批量)
     * @param os 组织Id集合
     * @return
     */
	public void deleteBatchAuthorizedByOrgId(List<Org> os);
	/**
     * 通过组织资源对象列表建立权限关系(批量插入)
     * @param  list 角色资源对象列表
     * @return
     */
	public void insertAuthorizedByOrgId(List<OrgResources> list);
	 /**
     * 根据组织删除权限
     * @param os 组织id和资源id集合
     * @return
     */
	public void delBatchAuthByOrg(List<OrgResources> or);
	 /**
     * 根据角色删除权限
     * @param os 角色id和资源id集合
     * @return
     */
	public void delBatchAuthByRole(List<RoleResources> rr);
	
}
