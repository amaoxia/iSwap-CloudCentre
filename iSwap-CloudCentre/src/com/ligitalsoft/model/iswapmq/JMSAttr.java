package com.ligitalsoft.model.iswapmq;

public class JMSAttr implements java.io.Serializable
{

    private static final long serialVersionUID = 7716910560711088240L;

    private String            url;                                    // 连接地址

    private String            userName;

    private String            passWord;

    private String            squeName;                               // 发送消息的队列

    private String            rqueName;                               // 接受消息的队列

    public JMSAttr()
    {

    }
    public JMSAttr(String url, String userName, String passWord)
    {
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

    public String getRqueName()
    {
        return rqueName;
    }

    public void setRqueName(String rqueName)
    {
        this.rqueName = rqueName;
    }

    public String getSqueName()
    {
        return squeName;
    }

    public void setSqueName(String squeName)
    {
        this.squeName = squeName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

}
