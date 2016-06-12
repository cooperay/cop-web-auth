package com.jy.service.system.role;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jy.common.utils.tree.entity.ZNodes;
import com.jy.entity.system.resources.OrgResources;
import com.jy.entity.system.resources.RoleResources;
import com.jy.entity.system.role.Org;
import com.jy.entity.system.role.Role;
import com.jy.repository.system.role.OrgDao;
import com.jy.service.base.BaseServiceImp;

@Service("OrgService")
public class OrgServiceImp extends BaseServiceImp<Org> implements OrgService{

	@Override
	@Transactional
	public int delOrg(Org o) {
		int res=0;
		//进行事务删除，删除组织还删除组织资源关系表
		OrgDao dao=(OrgDao)baseDao;
		String oId=o.getId();
		List<String> orgIds=new ArrayList<String>();
		List<String> roleIds=new ArrayList<String>();
		List<Org> findOrgChilds=dao.findAndChild(oId);
		dealOrg(findOrgChilds,orgIds,roleIds);
		if(orgIds.size()==0&&roleIds.size()==0){//这样做是怕误操作
			dao.delete(o);
			dao.delAuthorizedByOrgId(oId);
			res=1;
		}else{
			res=2;//旗下还有组织和角色
		}
		return res;
	}

	@Override
	public List<ZNodes> getOrgTree() {
		return ((OrgDao)baseDao).getOrgTree();
	}

	@Override
	public List<ZNodes> getPreOrgTree() {
		return ((OrgDao)baseDao).getPreOrgTree();
	}

	@Override
	public List<ZNodes> listAuthorized(String orgId) {
		OrgDao rd=(OrgDao)baseDao;
		List<ZNodes> list=new ArrayList<ZNodes>();
		Org dataOrg=rd.getOrg(orgId);
		String pId=dataOrg.getpId();
		if(StringUtils.equals("0",pId)){
			//第一机构可获取所有权限
			list=rd.listAuthorized(orgId);
		}else{
			//其他机构要通过上级机构门限获取权限
			list=rd.listAuthorizedByTh(pId,orgId);
		}	
		return list;
	}

	@Override
	@Transactional
	public void saveAuthorized(String orgId, String aus) {
		List<String> authIds=new ArrayList<String>();
		List<OrgResources> orgAuth=new ArrayList<OrgResources>();
		String[] auss=aus.split(",");
		for(String s:auss){
			if(StringUtils.isNotBlank(s)){
				orgAuth.add(new OrgResources(orgId,s));
				authIds.add(s);
			}
		}
		OrgDao dao=(OrgDao)baseDao;	
		//处理旗下机构和其子机构
		List<Org> findOrgChilds=dao.findAndChild(orgId);
		List<String> orgIds=new ArrayList<String>();
		List<String> roleIds=new ArrayList<String>();
		List<OrgResources> ors=new ArrayList<OrgResources>();
		List<RoleResources> rrs=new ArrayList<RoleResources>();
		dealOrg(findOrgChilds,orgIds,roleIds);
		if(orgAuth.size()>0){
			List<String> notAuths=dao.getNotAuthoByOrg(orgId,authIds);
			if(notAuths.size()>0){		
				for(String nAuth:notAuths){
					for(String oId:orgIds){
						ors.add(new OrgResources(oId,nAuth));
					}
					for(String rId:roleIds){
						rrs.add(new RoleResources(rId,nAuth));
					}
				}
				//批量删除机构权限
				if(ors.size()>0)dao.delBatchAuthByOrg(ors);
				//批量删除角色权限
				if(rrs.size()>0)dao.delBatchAuthByRole(rrs);
			}
		}	
		//处理机构，先根据机构Id删除机构权限再重新插入机构权限，优化速度
		dao.delAuthorizedByOrgId(orgId);
		if(orgAuth.size()>0)dao.insertAuthorizedByOrgId(orgAuth);	
	}
	
	private void dealOrg(List<Org> orgChilds,List<String> orgIds,List<String> roleIds){
		//递归处理机构和角色
		for(Org orgChild:orgChilds){
			List<Org> orgs=orgChild.getOrgs();
			List<Role> roles=orgChild.getRoles();
			for(Org o:orgs){
				orgIds.add(o.getId());		
			}
			for(Role r:roles){
				roleIds.add(r.getId());
			}
			//递归
			dealOrg(orgs,orgIds,roleIds);
		}
	}
}
