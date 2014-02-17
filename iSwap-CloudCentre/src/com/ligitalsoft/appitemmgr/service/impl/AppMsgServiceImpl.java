/*
 * @(#)AppMsgServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.appitemmgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.appitemmgr.dao.AppMsgDao;
import com.ligitalsoft.appitemmgr.service.AppMsgService;
import com.ligitalsoft.cloudcenter.action.Constant;
import com.ligitalsoft.util.Node;
import com.ligitalsoft.model.appitemmgr.AppMsg;

/**
 * @author lifh
 * @mail wslfh2005@163.com
 * @since 2011-6-15 上午10:31:25
 * @name com.ligitalsoft.cloudcenter.service.impl.AppMsgServiceImpl.java
 * @version 1.0
 */
@Service("appMsgService")
public class AppMsgServiceImpl extends BaseSericesImpl<AppMsg> implements AppMsgService {

    private AppMsgDao appMsgDao;

    @SuppressWarnings("unchecked")
    public List<AppMsg> findAllByProperty() {
        String hql = "from AppMsg where status =" + Constant.STATUS_OPEN;
        return appMsgDao.findListByHql(hql);
    }

    @Override
    public JSONArray getTree(String url) {
        List<AppMsg> apps = appMsgDao.findAll(-1, -1);
        List<Node> nodes = new ArrayList<Node>();
        // 创建根节点
        Node root = new Node();
        root.setId(-1L);
        root.setName("应用");
        root.setPid(0L);
        // 转换成treeNode
        for (AppMsg appMsg : apps) {
            Node ele = new Node();
            ele.setId(appMsg.getId());
            ele.setEname(appMsg.getAppCode());
            ele.setName(appMsg.getAppName());
            ele.setPid(-1L);
            ele.setTarget("right_content");//  ../../exchange/share/share!listApp.action
            ele.setUrl(url+"?appId=" + appMsg.getId());
            nodes.add(ele);
        }
        nodes.add(root);
        // 过滤
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {

            @Override
            public boolean apply(Object source, String name, Object value) {
                if (name.equals("name") || name.equals("id") || name.equals("pid") || name.equals("ename")
                                || name.equals("target") || name.equals("url")) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        return JSONArray.fromObject(nodes, jsonConfig);
    }

    @Override
    public EntityHibernateDao<AppMsg> getEntityDao() {
        return appMsgDao;
    }

    @Autowired
    public void setAppMsgDao(AppMsgDao appMsgDao) {
        this.appMsgDao = appMsgDao;
    }

}
