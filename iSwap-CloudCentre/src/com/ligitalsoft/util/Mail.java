package com.ligitalsoft.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Mail {

    private String hostName;
    private String to;
    private String from;
    private String username;
    private String password;
    private String subject;
    private String msg;


    public String getFrom() {
        return from;
    }


    public void setFrom(String from) {
        this.from = from;
    }


    public String getHostName() {
        return hostName;
    }


    public void setHostName(String hostName) {
        this.hostName = hostName;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getTo() {
        return to;
    }


    public void setTo(String to) {
        this.to = to;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void send() throws Exception {
        SimpleEmail email = new SimpleEmail();
        email.setHostName(this.getHostName());
        // 设置收件人邮箱
        try {
            email.addTo(this.getTo());
            email.setFrom(this.getFrom());

            // 如果要求身份验证，设置用户名、密码，分别为发件人在邮件服务器上注册的用户名和密码

            email.setAuthentication(this.getUsername(), this.getPassword());

            // 设置邮件的主题

            this.setSubject(this.getSubject());

            // 邮件正文消息

            email.setMsg(this.getMsg());

            email.send();

           

        } catch (EmailException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        // 发件人邮箱

    }

}
