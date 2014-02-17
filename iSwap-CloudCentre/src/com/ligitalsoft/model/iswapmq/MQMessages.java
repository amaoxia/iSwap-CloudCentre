/**
 * 
 */
package com.ligitalsoft.model.iswapmq;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * @author Tony
 */
@Entity
@Table(name = "ISWAPMQ_MSGS")
@Cache(usage = CacheConcurrencyStrategy.NONE)
public class MQMessages extends LongIdObject
{

    private static final long serialVersionUID = -3394366450698196823L;

    private String            container;

    private String            msgidProd;

    private int               msgidSeq;

    private int               expiration;

    private byte[]            msg;

    private int               priority;

    private String            msgBody;

    private String            createTime;

    private int               bytes;

    @Transient
    public int getBytes()
    {
        return bytes;
    }

    public void setBytes(int bytes)
    {
        this.bytes = bytes;
    }

    @Transient
    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    @Transient
    public String getMsgBody()
    {
        return msgBody;
    }

    
    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getContainer()
    {
        return container;
    }

    public void setContainer(String container)
    {
        this.container = container;
    }

    public String getMsgidProd()
    {
        return msgidProd;
    }

    public void setMsgidProd(String msgidProd)
    {
        this.msgidProd = msgidProd;
    }

    public int getMsgidSeq()
    {
        return msgidSeq;
    }

    public void setMsgidSeq(int msgidSeq)
    {
        this.msgidSeq = msgidSeq;
    }

    public int getExpiration()
    {
        return expiration;
    }

    public void setExpiration(int expiration)
    {
        this.expiration = expiration;
    }

    public byte[] getMsg()
    {
        return msg;
    }

    public void setMsg(byte[] msg)
    {
        this.msg = msg;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    
    
}
