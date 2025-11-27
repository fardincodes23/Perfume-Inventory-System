
package org.example.soapperfume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for skillsAssessmentSquashTechnical complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="skillsAssessmentSquashTechnical">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="assessmentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assessorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="athleteName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="backhandDrives" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="backhandVolleyMax" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="backhandVolleySum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="createdDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="forehandDrives" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="forehandVolleyMax" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="forehandVolleySum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="technicalScore" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "skillsAssessmentSquashTechnical", propOrder = {
    "assessmentDate",
    "assessorName",
    "athleteName",
    "backhandDrives",
    "backhandVolleyMax",
    "backhandVolleySum",
    "createdDateTime",
    "forehandDrives",
    "forehandVolleyMax",
    "forehandVolleySum",
    "id",
    "technicalScore"
})
public class SkillsAssessmentSquashTechnical {

    protected String assessmentDate;
    protected String assessorName;
    protected String athleteName;
    protected Integer backhandDrives;
    protected Integer backhandVolleyMax;
    protected Integer backhandVolleySum;
    protected String createdDateTime;
    protected Integer forehandDrives;
    protected Integer forehandVolleyMax;
    protected Integer forehandVolleySum;
    protected Integer id;
    protected Integer technicalScore;

    /**
     * Gets the value of the assessmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessmentDate() {
        return assessmentDate;
    }

    /**
     * Sets the value of the assessmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentDate(String value) {
        this.assessmentDate = value;
    }

    /**
     * Gets the value of the assessorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessorName() {
        return assessorName;
    }

    /**
     * Sets the value of the assessorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessorName(String value) {
        this.assessorName = value;
    }

    /**
     * Gets the value of the athleteName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAthleteName() {
        return athleteName;
    }

    /**
     * Sets the value of the athleteName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAthleteName(String value) {
        this.athleteName = value;
    }

    /**
     * Gets the value of the backhandDrives property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBackhandDrives() {
        return backhandDrives;
    }

    /**
     * Sets the value of the backhandDrives property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBackhandDrives(Integer value) {
        this.backhandDrives = value;
    }

    /**
     * Gets the value of the backhandVolleyMax property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBackhandVolleyMax() {
        return backhandVolleyMax;
    }

    /**
     * Sets the value of the backhandVolleyMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBackhandVolleyMax(Integer value) {
        this.backhandVolleyMax = value;
    }

    /**
     * Gets the value of the backhandVolleySum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBackhandVolleySum() {
        return backhandVolleySum;
    }

    /**
     * Sets the value of the backhandVolleySum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBackhandVolleySum(Integer value) {
        this.backhandVolleySum = value;
    }

    /**
     * Gets the value of the createdDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets the value of the createdDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedDateTime(String value) {
        this.createdDateTime = value;
    }

    /**
     * Gets the value of the forehandDrives property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getForehandDrives() {
        return forehandDrives;
    }

    /**
     * Sets the value of the forehandDrives property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setForehandDrives(Integer value) {
        this.forehandDrives = value;
    }

    /**
     * Gets the value of the forehandVolleyMax property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getForehandVolleyMax() {
        return forehandVolleyMax;
    }

    /**
     * Sets the value of the forehandVolleyMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setForehandVolleyMax(Integer value) {
        this.forehandVolleyMax = value;
    }

    /**
     * Gets the value of the forehandVolleySum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getForehandVolleySum() {
        return forehandVolleySum;
    }

    /**
     * Sets the value of the forehandVolleySum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setForehandVolleySum(Integer value) {
        this.forehandVolleySum = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the technicalScore property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTechnicalScore() {
        return technicalScore;
    }

    /**
     * Sets the value of the technicalScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTechnicalScore(Integer value) {
        this.technicalScore = value;
    }


    @Override
    public String toString() {
        return "SkillsAssessmentSquashTechnical\n" +
                "    assessmentDate     = '" + assessmentDate + "',\n" +
                "    createdDateTime    = '" + createdDateTime + "',\n" +
                "    athleteName        = '" + athleteName + "',\n" +
                "    assessorName       = '" + assessorName + "',\n" +
                "    forehandDrives     = " + forehandDrives + ",\n" +
                "    backhandDrives     = " + backhandDrives + ",\n" +
                "    forehandVolleyMax  = " + forehandVolleyMax + ",\n" +
                "    forehandVolleySum  = " + forehandVolleySum + ",\n" +
                "    backhandVolleyMax  = " + backhandVolleyMax + ",\n" +
                "    backhandVolleySum  = " + backhandVolleySum + ",\n" +
                "    technicalScore     = " + technicalScore + "\n";
    }


}
