/*
 * @(#)DetptResXml.java
 *
 * Copyright (C) 2005, zgcworld All right reserved.
 * see the site: http://www.zgcworld.com
 */

package com.ligitalsoft.sysmanager.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.xml.Dom4jXmlHelper;

/**
 * @author zhangx
 * @since 2011-1-20 下午03:54:28
 * @name com.ligitalsoft.sysmanager.util.DetptResXml.java
 * @version 1.0
 */

public class ParseXML {

    //private static ParseXML parseXML;

    private ParseXML() {

    }
    private static class ParseXMLHolder{
    	/**
    	 * 静态初始化器，由JVM保证线程安全
    	 * @author shawl
    	 */
    	private static ParseXML instance = new ParseXML();
    }
    private Logger logger = LoggerFactory.getLogger(getClass());
    private InputStream inputStream;

    public static ParseXML getParseXMLInstrance() {
        /*if (parseXML == null) {
            parseXML = new ParseXML();
        }
        return parseXML;*/

    	return ParseXMLHolder.instance;
    }
    /**
     * 得到document对象
     * @param path
     * @return
     * @author zhangx
     * @2011-2-24 下午06:20:23
     */
    public InputStream getDocumentObj(String path) {
        inputStream = getClass().getClassLoader().getResourceAsStream(path);
        return inputStream;
    }
    /**
     * 关闭流
     * @param inputStream
     * @author zhangx
     * @2011-2-24 下午06:36:31
     */
    public void closeIo(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("关闭流异常!", e);
            }
        }
    }

    /**
     * 得到资源前端码 通过xml文件
     * @return
     * @author zhangx
     * @2011-1-20 上午10:42:57
     */
    public String getRescoureCode() {
        getDocumentObj("config/system/resid.xml");
        Document document = Dom4jXmlHelper.getDocument(inputStream);
        Element root = document.getRootElement();
        Element rescouceCode = root.element("resourceCodeName");
        String resourceCodeName = rescouceCode.getTextTrim();
        closeIo(inputStream);
        return resourceCodeName;
    }
    @SuppressWarnings("unchecked")
    public Map<String, String> getDictTableName() {
        getDocumentObj("config/system/dict.xml");
        Document document = Dom4jXmlHelper.getDocument(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Element root = document.getRootElement();
        List<Element> list = root.elements("TableName");
        for (Element element : list) {
            String eName = element.getText().trim();
            map.put(eName, element.attributeValue("name"));
        }
        closeIo(inputStream);
        return map;
    }
    public static void main(String[] args) {
        Map<String, String> map = ParseXML.getParseXMLInstrance().getDictTableName();
        for (String string : map.keySet()) {
            System.out.println(string);
        }
    }
}
