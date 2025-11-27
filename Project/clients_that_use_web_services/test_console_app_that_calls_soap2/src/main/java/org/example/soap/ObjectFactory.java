
package org.example.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.soap package. 
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

    private final static QName _GetBusPass_QNAME = new QName("http://soap.squash.hccis.ca/", "getBusPass");
    private final static QName _GetBusPasses_QNAME = new QName("http://soap.squash.hccis.ca/", "getBusPasses");
    private final static QName _GetBusPassResponse_QNAME = new QName("http://soap.squash.hccis.ca/", "getBusPassResponse");
    private final static QName _GetCount_QNAME = new QName("http://soap.squash.hccis.ca/", "getCount");
    private final static QName _GetCountResponse_QNAME = new QName("http://soap.squash.hccis.ca/", "getCountResponse");
    private final static QName _GetBusPassesResponse_QNAME = new QName("http://soap.squash.hccis.ca/", "getBusPassesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCountResponse }
     * 
     */
    public GetCountResponse createGetCountResponse() {
        return new GetCountResponse();
    }

    /**
     * Create an instance of {@link GetBusPassesResponse }
     * 
     */
    public GetBusPassesResponse createGetBusPassesResponse() {
        return new GetBusPassesResponse();
    }

    /**
     * Create an instance of {@link GetBusPass }
     * 
     */
    public GetBusPass createGetBusPass() {
        return new GetBusPass();
    }

    /**
     * Create an instance of {@link GetCount }
     * 
     */
    public GetCount createGetCount() {
        return new GetCount();
    }

    /**
     * Create an instance of {@link GetBusPassResponse }
     * 
     */
    public GetBusPassResponse createGetBusPassResponse() {
        return new GetBusPassResponse();
    }

    /**
     * Create an instance of {@link GetBusPasses }
     * 
     */
    public GetBusPasses createGetBusPasses() {
        return new GetBusPasses();
    }

    /**
     * Create an instance of {@link BusPass }
     * 
     */
    public BusPass createBusPass() {
        return new BusPass();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBusPass }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.squash.hccis.ca/", name = "getBusPass")
    public JAXBElement<GetBusPass> createGetBusPass(GetBusPass value) {
        return new JAXBElement<GetBusPass>(_GetBusPass_QNAME, GetBusPass.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBusPasses }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.squash.hccis.ca/", name = "getBusPasses")
    public JAXBElement<GetBusPasses> createGetBusPasses(GetBusPasses value) {
        return new JAXBElement<GetBusPasses>(_GetBusPasses_QNAME, GetBusPasses.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBusPassResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.squash.hccis.ca/", name = "getBusPassResponse")
    public JAXBElement<GetBusPassResponse> createGetBusPassResponse(GetBusPassResponse value) {
        return new JAXBElement<GetBusPassResponse>(_GetBusPassResponse_QNAME, GetBusPassResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.squash.hccis.ca/", name = "getCount")
    public JAXBElement<GetCount> createGetCount(GetCount value) {
        return new JAXBElement<GetCount>(_GetCount_QNAME, GetCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.squash.hccis.ca/", name = "getCountResponse")
    public JAXBElement<GetCountResponse> createGetCountResponse(GetCountResponse value) {
        return new JAXBElement<GetCountResponse>(_GetCountResponse_QNAME, GetCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBusPassesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.squash.hccis.ca/", name = "getBusPassesResponse")
    public JAXBElement<GetBusPassesResponse> createGetBusPassesResponse(GetBusPassesResponse value) {
        return new JAXBElement<GetBusPassesResponse>(_GetBusPassesResponse_QNAME, GetBusPassesResponse.class, null, value);
    }

}
