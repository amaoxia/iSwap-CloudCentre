package org.jbpm.pvm.internal.cfg;

import org.jbpm.pvm.internal.util.XmlUtil;
import org.jbpm.pvm.internal.wire.WireDefinition;
import org.jbpm.pvm.internal.wire.xml.WireParser;
import org.jbpm.pvm.internal.xml.Parse;
import org.jbpm.pvm.internal.xml.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * parses the <code>jbpm-configuration</code>, which is assumed the document element
 * and can contain the {@link EnvironmentFactoryXmlParser process-engine}
 * context and the {@link EnvironmentXmlParser environment} context.
 *@Company 中海纪元
 *@author   hudaowan
 *@version   iSwap V6.0 数据交换平台  
 *@date   2011-8-13 下午12:04:33
 *@Team 研发中心
 */
public class ConfigurationParser extends Parser {

  private static final long serialVersionUID = 1L;
  
  // private static Log log = Log.getLog(JbpmConfigurationParser.class.getName());

  Parser processEngineContextParser = new WireParser();
  Parser transactionContextParser = new WireParser();

  protected static ConfigurationParser INSTANCE = new ConfigurationParser();
  
  public static ConfigurationParser getInstance() {
    return INSTANCE;
  }

  public Object parseDocument(Document document, Parse parse) {
    // if the default environment factory was already set in the parse
    ConfigurationImpl configuration = parse.contextStackFind(ConfigurationImpl.class);
    
    Element documentElement = document.getDocumentElement();
    if (documentElement != null) {
      // this code will be called for the original jbpm.cfg.xml document as 
      // well as for the imported documents.  only one of those can specify
      // a spring-cfg.  for sure no 2 config files can specify different jndi-names
      String spring = XmlUtil.attribute(documentElement, "spring");
      if ("enabled".equals(spring)) {
        configuration.springEnabled();
      }
  
      // this code will be called for the original jbpm.cfg.xml document as 
      // well as for the imported documents.  only one of those can specify
      // a jndi-name.  for sure no 2 config files can specify different jndi-names
      String jndiName = XmlUtil.attribute(documentElement, "jndi-name");
      if (jndiName!=null) {
        if ( (configuration.getJndiName()!=null)
             && (!jndiName.equals(configuration.getJndiName()))
           ) {
          parse.addProblem("duplicate jndi name specification: "+jndiName+" != "+configuration.getJndiName());
        } else {
          configuration.jndi(jndiName);
        }
      }
  
      for (Element importElement : XmlUtil.elements(documentElement, "import")) {
        if (importElement.hasAttribute("resource")) {
          String resource = importElement.getAttribute("resource");
          Parse importParse = createParse()
            .setResource(resource)
            .contextStackPush(configuration)
            .propagateContexMap(parse)
            .execute();
          
          parse.addProblems(importParse.getProblems());
        }
      }
  
      Element processEngineElement = XmlUtil.element(documentElement, "process-engine-context");
      if (processEngineElement != null) {
        WireDefinition processEngineContextDefinition = configuration.getProcessEngineWireContext().getWireDefinition();
        parse.contextStackPush(processEngineContextDefinition);
        try {
          processEngineContextParser.parseDocumentElement(processEngineElement, parse);
        } finally {
          parse.contextStackPop();
        }
      }
  
      Element txCtxElement = XmlUtil.element(documentElement, "transaction-context");
      if (txCtxElement != null) {
        WireDefinition transactionContextDefinition = configuration.getTransactionWireDefinition();
        parse.contextStackPush(transactionContextDefinition);
        try {
          transactionContextParser.parseDocumentElement(txCtxElement, parse);
        } finally {
          parse.contextStackPop();
        }
      }
    }

    parse.setDocumentObject(configuration);
    return configuration;
  }

  public Parser getProcessEngineContextParser() {
    return processEngineContextParser;
  }
  public void setProcessEngineContextParser(Parser applicationWireXmlParser) {
    this.processEngineContextParser = applicationWireXmlParser;
  }
  public Parser getTransactionContextParser() {
    return transactionContextParser;
  }
  public void setTransactionContextParser(Parser blockWireXmlParser) {
    this.transactionContextParser = blockWireXmlParser;
  }
}
