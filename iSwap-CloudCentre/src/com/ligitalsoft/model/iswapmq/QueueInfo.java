/**  
 * @公司名称：北京光码软件有限公司
 * @项目名称：iSwapV6.0云计算数据交换平台
 * @文件名称：QueueInfo.java
 * @子模块名：iSwapMQ
 * @模块名称：
 * @功能作用：(用一句话描述该文件做什么)
 * @文件作者： Tony Wong
 * @创建时间：2011-5-12 上午11:21:51
 * @版本编号：v1.0  
 * @最后修改：(修改人) 2011-5-12 上午11:21:51
 */
package com.ligitalsoft.model.iswapmq;

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
 * @ClassName: QueueInfo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Tony Wong
 * @date 2011-5-12 上午11:21:51
 */
@Entity
@Table(name = "ISWAPMQ_QUEUEINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QueueInfo extends LongIdObject {

    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 6373944430210473144L;

    /**
     * @Fields QueueName : 队列名称
     */
    private String            queueName;

    /**
     * @Fields QueueType : 队列类型 1 Queue ： 0 Topic
     */
    private String            queueType;

    /**
     * @Fields QueueDepth : 队列深度
     */
    private String            queueDepth;

    /**
     * @Fields QueueState : 队列状态 1 激活 ：0 睡眠
     */
    private String            queueState;

    /**
     * @Fields QueueSize : 队列大小
     */
    private String            queueSize;

    /**
     * 部门
     */
    private SysDept           sysDept;

    /**
     * @Fields serType : 服务类型 1 发送 ：0 接收
     */
    private String            serType;

    private Date              createTime       = new Date();

    private String            description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public Date getCreateTime() {
        return createTime;
    }

    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getQueueDepth() {
        return queueDepth;
    }

    public void setQueueDepth(String queueDepth) {
        this.queueDepth = queueDepth;
    }

    public String getQueueState() {
        return queueState;
    }

    public void setQueueState(String queueState) {
        this.queueState = queueState;
    }

    public String getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(String queueSize) {
        this.queueSize = queueSize;
    }

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")
    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public String getSerType() {
        return serType;
    }

    public void setSerType(String serType) {
        this.serType = serType;
    }

}
