
package com.ligitalsoft.defcat.webservice;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * This class was generated by Apache CXF 2.2.10
 * Tue Apr 19 15:31:14 CST 2011
 * Generated source version: 2.2.10
 * 
 */

public final class CatalogWebServicePortClient {

    private static final QName SERVICE_NAME = new QName("http://webservice.defcat.ligitalsoft.com/", "CatalogWebServiceImplService");

    public CatalogWebServicePortClient() {
    }

    public static CatalogWebService getCatalogWebService(String url){
        URL u = null;
        CatalogWebService port;
        try {
            u = new URL(url);
            CatalogWebServiceImplService ss = new CatalogWebServiceImplService(u, SERVICE_NAME);
            port = ss.getCatalogWebServiceImplPort();  
        } catch (Exception e) {
            throw new RuntimeException("不能得到service，请检查地址！");
        }

        return port;
    }
    
    public static void main(String args[]) throws Exception {
        URL wsdlURL = CatalogWebServiceImplService.WSDL_LOCATION;
        if (args.length > 0) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        CatalogWebServiceImplService ss = new CatalogWebServiceImplService(wsdlURL, SERVICE_NAME);
        CatalogWebService port = ss.getCatalogWebServiceImplPort();  
        
        {
        System.out.println("Invoking findCatalogs...");
        java.lang.String _findCatalogs__return = port.findCatalogs();
        System.out.println("findCatalogs.result=" + _findCatalogs__return);


        }
        {
        System.out.println("Invoking getResByid...");
        java.lang.Long _getResByid_arg0 = 1071l;
        java.lang.String _getResByid__return = port.findCatalogTreeById(_getResByid_arg0);
        System.out.println("getResByid.result=" + _getResByid__return);


        }

        System.exit(0);
    }

}
