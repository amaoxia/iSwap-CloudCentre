package com.ligitalsoft.ajax.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.common.framework.dao.SortPara;
import com.common.framework.dao.hibernate.EntityHibernateDao;
import com.common.framework.web.pager.PageBean;
import com.ligitalsoft.model.system.SysDept;

/**
 * Ajax DAO层
 * 
 * @author zhangx
 * @since May 16, 2011 11:01:19 AM
 * @name com.ligitalsoft.ajax.dao.AjaxDao.java
 * @version 1.0
 */
@Repository
public class AjaxDao extends EntityHibernateDao {

}