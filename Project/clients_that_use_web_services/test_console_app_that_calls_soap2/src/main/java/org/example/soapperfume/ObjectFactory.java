
package org.example.soapperfume;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.soapsquash package. 
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

    private final static QName _GetAssessmentsResponse_QNAME = new QName("http://soap.squash.hccis.ca/", "getAssessmentsResponse");
    private final static QName _GetAssessments_QNAME = new QName("http://soap.squash.hccis.ca/", "getAssessments");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.soapsquash
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAssessments }
     * 
     */
    public GetAssessments createGetAssessments() {
        return new GetAssessments();
    }

    /**
     * Create an instance of {@link GetAssessmentsResponse }
     * 
     */
    public GetAssessmentsResponse createGetAssessmentsResponse() {
        return new GetAssessmentsResponse();
    }

    /**
     * Create an instance of {@link SkillsAssessmentSquashTechnical }
     * 
     */
    public SkillsAssessmentSquashTechnical createSkillsAssessmentSquashTechnical() {
        return new SkillsAssessmentSquashTechnical();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssessmentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.squash.hccis.ca/", name = "getAssessmentsResponse")
    public JAXBElement<GetAssessmentsResponse> createGetAssessmentsResponse(GetAssessmentsResponse value) {
        return new JAXBElement<GetAssessmentsResponse>(_GetAssessmentsResponse_QNAME, GetAssessmentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssessments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.squash.hccis.ca/", name = "getAssessments")
    public JAXBElement<GetAssessments> createGetAssessments(GetAssessments value) {
        return new JAXBElement<GetAssessments>(_GetAssessments_QNAME, GetAssessments.class, null, value);
    }

}
