/*
 * Copyright (C) 2010, CHINA zgcworld CO.,LTD. All right reserved.
 * see the site: http://www.zgcworld.com/
 */
package com.ligitalsoft.defcat.webservice.dto;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

/**
 * @author lifh
 * @mail wslfh2005@163.com
 * @since Apr 20, 2011 11:26:54 AM
 * @name com.ligitalsoft.defcat.webservice.dto.DataConverter.java
 * @version 1.0
 */

public class DataConverter {

    /**
     * LOG used to output the Log info..
     */
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
                    .getLog(DataConverter.class);
    private static final String XSD_FILENAME = "catalog.xsd";
    private static final String CONTEXT_PATH = "com.ligitalsoft.defcat.webservice.dto";

    /**
     * 数据编码,将对象转换为通用的XML，用于在WebService传输时直接传输XML数据。
     * @param datas
     *            实际的数据对象
     * @return 转换后的XML数据
     * @author lifh
     */
    public static String datas2Xml(CatalogDto catalogDto) {
        Writer writer = new StringWriter();
        String str = "";
        try {
            JAXBContext context = JAXBContext.newInstance(CONTEXT_PATH);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // m.setProperty("jaxb.encoding", "gbk");
            m.marshal(catalogDto, writer);
            str = writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            LOG.error("解析错误~");
        }
        return str;
    }

    /**
     * 数据解码,将XML数据转换为对象的形式，方便进行数据的调用访问。
     * @param xml
     *            输入的XML数据
     * @return 数据对象
     * @author lifh
     */
    public static CatalogDto xml2Datas(String xml) {
        Unmarshaller u = validate();
        CatalogDto datas = null;
        try {
            // unmarshal a po instance document into a tree of Java content
            // objects composed of classes from the com.common.config.user
            datas = (CatalogDto) u.unmarshal(new StringReader(xml));
        } catch (JAXBException je) {
            je.printStackTrace();
            throw new RuntimeException("解码出错！！" + je);
        }
        return datas;

    }

    /**
     * 对xml文件解组时的验证
     * @return boolean
     */
    private static Unmarshaller validate() {
        // create a JAXBContext capable of handling classes generated into
        // the com.common.config.user
        JAXBContext jc;
        Unmarshaller u = null;
        try {
            jc = JAXBContext.newInstance(CONTEXT_PATH);

            u = jc.createUnmarshaller();
//XMLConstants.W3C_XML_SCHEMA_NS_URI
            SchemaFactory sf = SchemaFactory.newInstance(null);
            try {
                Schema schema = sf.newSchema(DataConverter.class.getResource(XSD_FILENAME));
                u.setSchema(schema);
                u.setEventHandler(new ValidationEventHandler() {

                    public boolean handleEvent(ValidationEvent ve) {
                        if (ve.getSeverity() == ValidationEvent.WARNING || ve.getSeverity() != ValidationEvent.WARNING) {
                            ValidationEventLocator vel = ve.getLocator();
                            LOG.info("Line:Col[" + vel.getLineNumber() + ":" + vel.getColumnNumber() + "]:"
                                            + ve.getMessage());
                            return false;
                        }
                        return true;
                    }
                });
            } catch (org.xml.sax.SAXException se) {
                se.printStackTrace();
                throw new RuntimeException("Unable to validate due to following error." + se.getMessage());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to validate due to following error." + e.getMessage());
        }
        return u;
    }
    /**
     * 将时间类型转换为XML表示的时间类型
     * @param date
     *            时间
     * @return XML表示的时间类型
     * @author lifh
     */
    public static XMLGregorianCalendar getDate(Date date) {
        if (null == date) {
            return null;
        }
        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            throw new Error(e);
        }
    }

    /**
     * 转换为Date
     * @param cal
     * @return
     * @author lifh
     */
    public static Date convertToDate(XMLGregorianCalendar cal) {
        if(null == cal){
            return null;
        }
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }
}
