package com.ligitalsoft.model.serverinput;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;
import com.ligitalsoft.model.system.SysDept;

/**
 * ws服务接入
 * @Company 中海纪元
 * @author hudaowan
 * @version iSwap V6.0 数据交换平台
 * @date 2011-6-12 下午08:03:31
 * @Team 研发中心
 */
@Entity
@Table(name = "SERVINPUT_WSSERVERINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WebServerInfo extends LongIdObject
{

    private static final long serialVersionUID = 1L;
    /**
     * ws服务名称
     */
    private String            wsName;
    /**
     * 服务地址
     */
    private String            ipAddress;

    private String            userName;

    private String            passWord;
    
    /**
	 * 状态
	 */
	private String status;

    private SysDept           sysDept;

    private String            notes;

    /**
     * 创建时间
     */
    private Date              dateCreate       = new Date();

    public String getWsName()
    {
        return wsName;
    }

    public void setWsName(String wsName)
    {
        this.wsName = wsName;
    }

    
    public String getIpAddress() {
        return ipAddress;
    }

    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")
    public SysDept getSysDept()
    {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept)
    {
        this.sysDept = sysDept;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public Date getDateCreate()
    {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
