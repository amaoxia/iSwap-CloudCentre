package com.ligitalsoft.model.cloudcenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * 云端节点信息管理
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-6-8 下午05:23:11
 * @Team 研发中心
 */
@Entity
@Table(name = "CLOUDCENTER_NODE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CloudNodeInfo extends LongIdObject {

    private static final long serialVersionUID = 1L;
    /**
     * 节点名称
     */
    private String nodesName;
    /**
     * 地址
     */
    private String address;
    /**
     * 端口
     */
    private String port="5678";
    /**
     * 部署状态 0--表示未部署 1--表示已部署
     */
    private int status;
    /**
     * 运行状态 0--运行正常 1--连接错误
     */
    private String runStatus;

    private Date creatDate;
    
    private String code;//云端节点编码
    /**
     * 应用和云端节点关系
     */
    private List<AppCloudNode> appCloudNode = new ArrayList<AppCloudNode>();

    private String appMsgNames = "";
    private String appMsgIds = "";
    /**
     * 云端节点和部门的关系
     */
    private List<CouldNodeDept> nodeDept = new ArrayList<CouldNodeDept>();

    private String sysDeptNames = "";
    private String sysDeptIds = "";

    /**
     * 描述
     */
    private String remark;

    

    public String getNodesName() {
		return nodesName;
	}

	public void setNodesName(String nodesName) {
		this.nodesName = nodesName;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    @OneToMany(mappedBy = "cloudNodeInfo", cascade = CascadeType.ALL)
    public List<AppCloudNode> getAppCloudNode() {
        return appCloudNode;
    }

    public void setAppCloudNode(List<AppCloudNode> appCloudNode) {
        this.appCloudNode = appCloudNode;
    }

    @OneToMany(mappedBy = "couldNode", cascade = CascadeType.ALL)
    public List<CouldNodeDept> getNodeDept() {
        return nodeDept;
    }

    public void setNodeDept(List<CouldNodeDept> nodeDept) {
        this.nodeDept = nodeDept;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Transient
    public String getAppMsgNames() {
        return appMsgNames;
    }

    public void setAppMsgNames(String appMsgNames) {
        this.appMsgNames = appMsgNames;
    }
    @Transient
    public String getAppMsgIds() {
        return appMsgIds;
    }

    public void setAppMsgIds(String appMsgIds) {
        this.appMsgIds = appMsgIds;
    }
    @Transient
    public String getSysDeptNames() {
        return sysDeptNames;
    }

    public void setSysDeptNames(String sysDeptNames) {
        this.sysDeptNames = sysDeptNames;
    }
    @Transient
    public String getSysDeptIds() {
        return sysDeptIds;
    }

    public void setSysDeptIds(String sysDeptIds) {
        this.sysDeptIds = sysDeptIds;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    

}
