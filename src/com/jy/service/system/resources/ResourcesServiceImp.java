package com.jy.service.system.resources;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.entity.system.resources.Resources;
import com.jy.repository.system.resources.ResourcesDao;
import com.jy.service.base.BaseServiceImp;


@Service("ResourcesService")
public class ResourcesServiceImp extends BaseServiceImp<Resources> implements ResourcesService {


	@Override
	public List<Resources> findMenuTree(String userId) {			
		return ((ResourcesDao) baseDao).findMenuTree(userId);
	}
	@Override
	public List<Resources> findBtn(String type,String menuId,String userId) {			
		return ((ResourcesDao) baseDao).findBtn(type,menuId,userId);
	}
	@Override
	public List<ZNodes> listResources(Resources r) {
		return ((ResourcesDao) baseDao).listResources(r);
	}
	
	@Override
	@Transactional
	public int tranDelete(Resources o) {
		int res=0;
		ResourcesDao dao=(ResourcesDao) baseDao;
		String resId=o.getId();
		int childCount=dao.childCount(resId);
		if(childCount==0){
			dao.delete(o);
			dao.delOrgAuthByResId(resId);//删除组织权限
			dao.delRoleAuthByResId(resId);//删除角色权限
			res=1;
		}
		return res;
	}
	
	@Override
	@Transactional
	public int tranDeleteBatch(List<Resources> os) {
		int res=0;
		ResourcesDao dao=(ResourcesDao) baseDao;
		int childCount=dao.childBatchCount(os);
		if(childCount==0){
			dao.deleteBatch(os);
			dao.delBatchOrgAuthByResId(os);//删除组织权限
			dao.delBatchRoleAuthByResId(os);//删除角色权限
			res=1;
		}
		return res;
	}
	
	@Override
	public List<Resources> resAuthorized(String userId, String type) {
		return ((ResourcesDao) baseDao).resAuthorized(userId, type);
	}
	
	
}
