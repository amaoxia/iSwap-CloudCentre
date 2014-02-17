package com.ligitalsoft.iswapqa.plugin;

import java.io.File;

import com.common.jaxbutil.ObjectBindXmlImpl;

public class TestMain {

    public static void main(String[] agr) {
        File file = new File("F:/pluginConfig.xml");
        try {
            ObjectBindXmlImpl objXml = new ObjectBindXmlImpl();
            objXml.xmlToObj(file,"com.ligitalsoft.iswapqa.plugin.parse");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
