//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.04.26 at 04:29:34 PM CST 
//


package com.ligitalsoft.defcat.webservice.dto;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ligitalsoft.defcat.webservice.dto package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ligitalsoft.defcat.webservice.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RsInfo }
     * 
     */
    public RsInfo createRsInfo() {
        return new RsInfo();
    }

    /**
     * Create an instance of {@link KeyWord }
     * 
     */
    public KeyWord createKeyWord() {
        return new KeyWord();
    }

    /**
     * Create an instance of {@link RsList }
     * 
     */
    public RsList createRsList() {
        return new RsList();
    }

    /**
     * Create an instance of {@link CatalogDto }
     * 
     */
    public CatalogDto createCatalogDto() {
        return new CatalogDto();
    }

}
