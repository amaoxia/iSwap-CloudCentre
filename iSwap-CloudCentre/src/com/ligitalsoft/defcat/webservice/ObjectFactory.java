
package com.ligitalsoft.defcat.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ligitalsoft.defcat.webservice package. 
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

    private final static QName _GetResByidResponse_QNAME = new QName("http://webservice.defcat.ligitalsoft.com/", "getResByidResponse");
    private final static QName _FindCatalogTreeById_QNAME = new QName("http://webservice.defcat.ligitalsoft.com/", "findCatalogTreeById");
    private final static QName _ChangeItemTranResponse_QNAME = new QName("http://webservice.defcat.ligitalsoft.com/", "changeItemTranResponse");
    private final static QName _FindCatalogTreeByIdResponse_QNAME = new QName("http://webservice.defcat.ligitalsoft.com/", "findCatalogTreeByIdResponse");
    private final static QName _TransmissionResponse_QNAME = new QName("http://webservice.defcat.ligitalsoft.com/", "transmissionResponse");
    private final static QName _FindCatalogs_QNAME = new QName("http://webservice.defcat.ligitalsoft.com/", "findCatalogs");
    private final static QName _GetResByid_QNAME = new QName("http://webservice.defcat.ligitalsoft.com/", "getResByid");
    private final static QName _FindCatalogsResponse_QNAME = new QName("http://webservice.defcat.ligitalsoft.com/", "findCatalogsResponse");
    private final static QName _ChangeItemTran_QNAME = new QName("http://webservice.defcat.ligitalsoft.com/", "changeItemTran");
    private final static QName _Transmission_QNAME = new QName("http://webservice.defcat.ligitalsoft.com/", "transmission");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ligitalsoft.defcat.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindCatalogs }
     * 
     */
    public FindCatalogs createFindCatalogs() {
        return new FindCatalogs();
    }

    /**
     * Create an instance of {@link GetResByid }
     * 
     */
    public GetResByid createGetResByid() {
        return new GetResByid();
    }

    /**
     * Create an instance of {@link FindCatalogsResponse }
     * 
     */
    public FindCatalogsResponse createFindCatalogsResponse() {
        return new FindCatalogsResponse();
    }

    /**
     * Create an instance of {@link ChangeItemTran }
     * 
     */
    public ChangeItemTran createChangeItemTran() {
        return new ChangeItemTran();
    }

    /**
     * Create an instance of {@link FindCatalogTreeById }
     * 
     */
    public FindCatalogTreeById createFindCatalogTreeById() {
        return new FindCatalogTreeById();
    }

    /**
     * Create an instance of {@link FindCatalogTreeByIdResponse }
     * 
     */
    public FindCatalogTreeByIdResponse createFindCatalogTreeByIdResponse() {
        return new FindCatalogTreeByIdResponse();
    }

    /**
     * Create an instance of {@link TransmissionResponse }
     * 
     */
    public TransmissionResponse createTransmissionResponse() {
        return new TransmissionResponse();
    }

    /**
     * Create an instance of {@link GetResByidResponse }
     * 
     */
    public GetResByidResponse createGetResByidResponse() {
        return new GetResByidResponse();
    }

    /**
     * Create an instance of {@link Transmission }
     * 
     */
    public Transmission createTransmission() {
        return new Transmission();
    }

    /**
     * Create an instance of {@link ChangeItemTranResponse }
     * 
     */
    public ChangeItemTranResponse createChangeItemTranResponse() {
        return new ChangeItemTranResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResByidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.defcat.ligitalsoft.com/", name = "getResByidResponse")
    public JAXBElement<GetResByidResponse> createGetResByidResponse(GetResByidResponse value) {
        return new JAXBElement<GetResByidResponse>(_GetResByidResponse_QNAME, GetResByidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCatalogTreeById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.defcat.ligitalsoft.com/", name = "findCatalogTreeById")
    public JAXBElement<FindCatalogTreeById> createFindCatalogTreeById(FindCatalogTreeById value) {
        return new JAXBElement<FindCatalogTreeById>(_FindCatalogTreeById_QNAME, FindCatalogTreeById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeItemTranResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.defcat.ligitalsoft.com/", name = "changeItemTranResponse")
    public JAXBElement<ChangeItemTranResponse> createChangeItemTranResponse(ChangeItemTranResponse value) {
        return new JAXBElement<ChangeItemTranResponse>(_ChangeItemTranResponse_QNAME, ChangeItemTranResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCatalogTreeByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.defcat.ligitalsoft.com/", name = "findCatalogTreeByIdResponse")
    public JAXBElement<FindCatalogTreeByIdResponse> createFindCatalogTreeByIdResponse(FindCatalogTreeByIdResponse value) {
        return new JAXBElement<FindCatalogTreeByIdResponse>(_FindCatalogTreeByIdResponse_QNAME, FindCatalogTreeByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransmissionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.defcat.ligitalsoft.com/", name = "transmissionResponse")
    public JAXBElement<TransmissionResponse> createTransmissionResponse(TransmissionResponse value) {
        return new JAXBElement<TransmissionResponse>(_TransmissionResponse_QNAME, TransmissionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCatalogs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.defcat.ligitalsoft.com/", name = "findCatalogs")
    public JAXBElement<FindCatalogs> createFindCatalogs(FindCatalogs value) {
        return new JAXBElement<FindCatalogs>(_FindCatalogs_QNAME, FindCatalogs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResByid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.defcat.ligitalsoft.com/", name = "getResByid")
    public JAXBElement<GetResByid> createGetResByid(GetResByid value) {
        return new JAXBElement<GetResByid>(_GetResByid_QNAME, GetResByid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCatalogsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.defcat.ligitalsoft.com/", name = "findCatalogsResponse")
    public JAXBElement<FindCatalogsResponse> createFindCatalogsResponse(FindCatalogsResponse value) {
        return new JAXBElement<FindCatalogsResponse>(_FindCatalogsResponse_QNAME, FindCatalogsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeItemTran }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.defcat.ligitalsoft.com/", name = "changeItemTran")
    public JAXBElement<ChangeItemTran> createChangeItemTran(ChangeItemTran value) {
        return new JAXBElement<ChangeItemTran>(_ChangeItemTran_QNAME, ChangeItemTran.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transmission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.defcat.ligitalsoft.com/", name = "transmission")
    public JAXBElement<Transmission> createTransmission(Transmission value) {
        return new JAXBElement<Transmission>(_Transmission_QNAME, Transmission.class, null, value);
    }

}
