package com.ligitalsoft.help.ws;

import java.util.List;

import com.common.config.ConfigAccess;
import com.ligitalsoft.fusionids.model.ArrayOfIdsAppAuthRelation;
import com.ligitalsoft.fusionids.model.ArrayOfIdsDeptUserRelation;
import com.ligitalsoft.fusionids.model.IdsAppAuthRelation;
import com.ligitalsoft.fusionids.model.IdsDeptUserRelation;
import com.ligitalsoft.fusionids.model.IdsUsers;
import com.ligitalsoft.fusionids.webservice.client.IFusionIDSWebServiceClient;
import com.ligitalsoft.fusionids.webservice.client.IFusionIDSWebServicePortType;
/**
 * 单点集成WS
 * @author bilee
 *
 */
public class RemoteServiceHelper {
	//static final String userName = ConfigAccess.init().findProp("webService_name");
	//static final String password = ConfigAccess.init().findProp("webService_passwprd");
	static final String url =  ConfigAccess.init().findProp("webService_url"); 
	/***
	 * 获取远程服务
	 * @return
	 */
	public static IFusionIDSWebServicePortType getIDSWebService(){
		/*String userName = "fusionids";
		String password = "fusionids";
		String url =  "http://localhost:5690/fusionids/service/IFusionIDSWebService";*/
		
		IFusionIDSWebServiceClient client = new IFusionIDSWebServiceClient();
		IFusionIDSWebServicePortType  service = client.getIFusionIDSWebServiceHttpPort(url,"fusionids","fusionids");
		return service;
	}
	
	/***
	 * 获取远程用户的部门编码
	 * @param user
	 * @return
	 */
	public static String[] getRemoteDeptCode(IdsUsers user){
		ArrayOfIdsDeptUserRelation arrayOfIdsDeptUserRelation = user.getIdsDeptUserRelations().getValue();		
		List<IdsDeptUserRelation> idsDeptUserRelationList = arrayOfIdsDeptUserRelation.getIdsDeptUserRelation();
		
		String[] deptCode = new String[idsDeptUserRelationList.size()]; 	//部门编码
		
		int index = 0;
		for(IdsDeptUserRelation idsDeptUserRelation : idsDeptUserRelationList){
			deptCode[index] = idsDeptUserRelation.getIdsDept().getValue().getDeptName().getValue();
			index ++;
		}
		return deptCode;
		
	}
	
	/***
	 * 获取远程用户的角色编码
	 * @param user
	 * @return
	 */
	public static String getRemoteRoleCode(IdsUsers user){
		ArrayOfIdsAppAuthRelation arrayOfIdsAppAuthRelation = user.getIdsAppAuthRelations().getValue();
		List<IdsAppAuthRelation> idsAppAuthList = arrayOfIdsAppAuthRelation.getIdsAppAuthRelation();
		
		String roleCode = "01";	//角色编码:部门业务人员
		
		for(IdsAppAuthRelation idsAppAuth : idsAppAuthList){
			String appCode = idsAppAuth.getIdsApp().getValue().getAppCode().getValue();
			
			if(appCode.equals(ConfigAccess.init().findProp("SYS_CODE"))){
				roleCode = idsAppAuth.getIdsAppRole().getValue().getRoleCode().getValue();
				break;
			}
		}
		return roleCode;
	}
}