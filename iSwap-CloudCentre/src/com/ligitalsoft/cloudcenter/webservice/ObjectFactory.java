
package com.ligitalsoft.cloudcenter.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ligitalsoft.cloudcenter.webservice package. 
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

    private final static QName _Undeploy_QNAME = new QName("http://webservice.cloudcenter.ligitalsoft.com/", "undeploy");
    private final static QName _UndeployResponse_QNAME = new QName("http://webservice.cloudcenter.ligitalsoft.com/", "undeployResponse");
    private final static QName _Deploy_QNAME = new QName("http://webservice.cloudcenter.ligitalsoft.com/", "deploy");
    private final static QName _DeployResponse_QNAME = new QName("http://webservice.cloudcenter.ligitalsoft.com/", "deployResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ligitalsoft.cloudcenter.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UndeployResponse }
     * 
     */
    public UndeployResponse createUndeployResponse() {
        return new UndeployResponse();
    }

    /**
     * Create an instance of {@link Undeploy }
     * 
     */
    public Undeploy createUndeploy() {
        return new Undeploy();
    }

    /**
     * Create an instance of {@link DeployResponse }
     * 
     */
    public DeployResponse createDeployResponse() {
        return new DeployResponse();
    }

    /**
     * Create an instance of {@link Deploy }
     * 
     */
    public Deploy createDeploy() {
        return new Deploy();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Undeploy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.cloudcenter.ligitalsoft.com/", name = "undeploy")
    public JAXBElement<Undeploy> createUndeploy(Undeploy value) {
        return new JAXBElement<Undeploy>(_Undeploy_QNAME, Undeploy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UndeployResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.cloudcenter.ligitalsoft.com/", name = "undeployResponse")
    public JAXBElement<UndeployResponse> createUndeployResponse(UndeployResponse value) {
        return new JAXBElement<UndeployResponse>(_UndeployResponse_QNAME, UndeployResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Deploy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.cloudcenter.ligitalsoft.com/", name = "deploy")
    public JAXBElement<Deploy> createDeploy(Deploy value) {
        return new JAXBElement<Deploy>(_Deploy_QNAME, Deploy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeployResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.cloudcenter.ligitalsoft.com/", name = "deployResponse")
    public JAXBElement<DeployResponse> createDeployResponse(DeployResponse value) {
        return new JAXBElement<DeployResponse>(_DeployResponse_QNAME, DeployResponse.class, null, value);
    }

}
