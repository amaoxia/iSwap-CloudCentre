package com.ligitalsoft.model.cloudnode;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.ligitalsoft.model.appitemmgr.AppMsg;
import com.ligitalsoft.model.cloudcenter.CloudNodeInfo;
import com.ligitalsoft.model.system.SysDept;

/**
 * 
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-6-9 下午06:53:26
 *@Team 研发中心
 */
@Entity
@Table(name = "CLOUDNODE_MAPPING")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MappingFile  extends LongIdObject{

	private static final long serialVersionUID = 1L;
	/**
	 * mapping名称
	 */
	private String mapName;
	/**
	 * mapping代码
	 */
	private String mapCode;
	/**
	 * 数据类型
	 */
	private String dataType;
	/**
	 * mpping模式　　0:发送  1:接收
	 */
	private String mappingType;
	/**
	 * 状态 		0:未部署  1:已部署
	 */
	private String status;
	/**
	 * 部门
	 */
	private SysDept dept;
	/**
	 * 应用
	 */
	private AppMsg appMsg;
	/**
	 * 云端节点
	 */
	private CloudNodeInfo cloudeNode;
	/**
	 * mapper内容
	 */
	private String contents;
	
	private List<SourceFile>  srcFlieList = new ArrayList<SourceFile>();
	
	private List<TargetFile>  tarFlieList = new ArrayList<TargetFile>();
	
	private  String  notes;
	
	

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMapCode() {
		return mapCode;
	}

	public void setMapCode(String mapCode) {
		this.mapCode = mapCode;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID")
	public SysDept getDept() {
		return dept;
	}

	public void setDept(SysDept dept) {
		this.dept = dept;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_ID")
	public AppMsg getAppMsg() {
		return appMsg;
	}

	public void setAppMsg(AppMsg appMsg) {
		this.appMsg = appMsg;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "NODE_ID")
	public CloudNodeInfo getCloudeNode() {
		return cloudeNode;
	}

	public void setCloudeNode(CloudNodeInfo cloudeNode) {
		this.cloudeNode = cloudeNode;
	}
	
	@OneToMany(mappedBy="mapping",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<SourceFile> getSrcFlieList() {
		return srcFlieList;
	}

	public void setSrcFlieList(List<SourceFile> srcFlieList) {
		this.srcFlieList = srcFlieList;
	}
	
	@OneToMany(mappedBy="mapping",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<TargetFile> getTarFlieList() {
		return tarFlieList;
	}

	public void setTarFlieList(List<TargetFile> tarFlieList) {
		this.tarFlieList = tarFlieList;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Column(columnDefinition="text")
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
