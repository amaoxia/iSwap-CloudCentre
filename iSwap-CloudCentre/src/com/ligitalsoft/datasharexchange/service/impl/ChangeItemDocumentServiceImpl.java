/*
 * @(#)ChangeItemDocumentServiceImpl.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.datasharexchange.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.nio.channels.FileChannel;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.config.ConfigAccess;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.exception.ServiceException;
import com.common.framework.services.impl.BaseSericesImpl;
import com.ligitalsoft.datasharexchange.dao.ChangeItemDao;
import com.ligitalsoft.datasharexchange.dao.ChangeItemDocumentDao;
import com.ligitalsoft.datasharexchange.service.IChangeItemDocumentService;
import com.ligitalsoft.model.changemanage.ChangeItem;
import com.ligitalsoft.model.changemanage.ChangeItemDocument;
import com.ligitalsoft.sysmanager.util.FileUtil;

/**
 * 交换指标文档_文档管理 实现类
 * @author zhangx
 * @since Jun 27, 2011 2:52:27 PM
 * @name com.ligitalsoft.datasharexchange.service.impl.ChangeItemDocumentServiceImpl.java
 * @version 1.0
 */
@Service("changeItemDocumentService")
public class ChangeItemDocumentServiceImpl extends BaseSericesImpl<ChangeItemDocument> implements
                IChangeItemDocumentService {

    private ChangeItemDocumentDao changeItemDocumentDao;
    private ChangeItemDao changeItemDao;

    @Override
    public List<ChangeItemDocument> findListByDeptId(Long deptId) {
        String[] name = { "e.changeItem.sysDept.id" };
        String[] value = { deptId.toString() };
        return changeItemDocumentDao.findListByProperty(name, value, -1, -1);
    }

    @Override
    public void deleteAllByIds(Serializable[] ids) throws ServiceException {
        if (ids != null) {
            for (Serializable id : ids) {
                ChangeItemDocument document = findById(id);
                FileUtil.deleOnefile(document.getUploadPath());// 删除文件
                changeItemDocumentDao.remove(document);
                changeItemDocumentDao.getSession().flush();// 及时执行语句
            }
        }
    }

    @Override
    public void updateStatus(Serializable[] ids, String type) throws ServiceException {
        if (ids != null) {
            for (Serializable id : ids) {
                ChangeItem item = changeItemDao.findById(id);
                if(item != null ){
                    List<ChangeItemDocument> docs=changeItemDocumentDao.findByItemId(item.getId(), "1");
                    if (docs!=null&&docs.size() != 0) {
                        for (ChangeItemDocument document : docs) {
                            if (document != null && !document.getShareState().equals("1")) {
                                String shareDocument = ConfigAccess.init().findProp("shareDocument");// 共享目录
                                String publishDocument = ConfigAccess.init().findProp("publishDocument");// 发布目录
                                File file = new File(shareDocument + "\\" + document.getDocumentName());// 源文件
                                File newFile = new File(publishDocument + "/" + document.getDocumentName());// 新文件
                                try {
                                    FileChannel srcFile = new FileInputStream(file).getChannel();// 原文件
                                    FileChannel nowFile = new FileOutputStream(newFile).getChannel();
                                    nowFile.transferFrom(srcFile, 0, file.length());// 移动
                                    nowFile.close();
                                    srcFile.close();
                                    file.delete();//删除原文件
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                changeItemDao.update(item);
                                document.setShareState("1");// 已经发布
                                changeItemDocumentDao.update(document);
                                changeItemDocumentDao.getSession().flush();// 及时执行语句
                            }
                        }
                    }
                    item.setShareState("1");//发布
                    changeItemDao.update(item);
                    changeItemDao.getSession().flush();// 及时执行语句
                }
            }
        }
    }
    @Override
    public EntityHibernateDao<ChangeItemDocument> getEntityDao() {
        return changeItemDocumentDao;
    }

    @Autowired
    public void setChangeItemDocumentDao(ChangeItemDocumentDao changeItemDocumentDao) {
        this.changeItemDocumentDao = changeItemDocumentDao;
    }
    @Autowired
    public void setChangeItemDao(ChangeItemDao changeItemDao) {
        this.changeItemDao = changeItemDao;
    }
}
