package com.jy.service.system.role;

import java.util.List;

import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.entity.system.role.Org;
import com.jy.service.base.BaseService;

public interface OrgService extends BaseService<Org>{

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
	public List<ZNodes> listAuthorized(String orgId);
	 /**
     * 根据角色Id保存权限列表
     * @param orgId 组织Id
     * @param auss 权限数组
     * @return
     */
	public void saveAuthorized(String orgId,String auss);
	/**
	 * 删除机构
	 * @return
	 */
	public int delOrg(Org o);
}
