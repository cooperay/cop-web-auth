package com.jy.service.system.resources;

import java.util.List;

import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.entity.system.resources.Resources;
import com.jy.service.base.BaseService;

public interface ResourcesService extends BaseService<Resources>{
	 /**
     * 菜单树
     * @param menuId
     * @param userId
     * @return
     */
	public List<Resources> findMenuTree(String userId);
	 /**
     * 按钮列
     * @param type
     * @param menuId
     * @param userId
     * @return
     */
	public List<Resources> findBtn(String type,String menuId,String userId);
    /**
     * 资源列表只含菜单不含按钮
     * @param r
     * @return
     */
	public List<ZNodes> listResources(Resources r);
	 /**
     * 用户权限资源列表
     * @param userId
     * @param type
     * @return
     */
	public List<Resources> resAuthorized(String userId,String type);
	 /**
     * 事务删除资源
     * @param o
     * @return
     */
	public int tranDelete(Resources o);
	 /**
     * 事务删除资源（批量）
     * @param os
     * @return
     */
	public int tranDeleteBatch(List<Resources> os);
}
