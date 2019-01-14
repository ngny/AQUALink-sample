
package tech.ngnydevices.aqualis._3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ExtractionCenter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PatientService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Section" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "extractionCenter",
    "patientService",
    "section"
})
@XmlRootElement(name = "Service")
public class Service {

    @XmlElement(name = "ExtractionCenter")
    protected String extractionCenter;
    @XmlElement(name = "PatientService")
    protected String patientService;
    @XmlElement(name = "Section")
    protected String section;

    /**
     * Obtiene el valor de la propiedad extractionCenter.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtractionCenter() {
        return extractionCenter;
    }

    /**
     * Define el valor de la propiedad extractionCenter.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtractionCenter(String value) {
        this.extractionCenter = value;
    }

    /**
     * Obtiene el valor de la propiedad patientService.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientService() {
        return patientService;
    }

    /**
     * Define el valor de la propiedad patientService.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientService(String value) {
        this.patientService = value;
    }

    /**
     * Obtiene el valor de la propiedad section.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSection() {
        return section;
    }

    /**
     * Define el valor de la propiedad section.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSection(String value) {
        this.section = value;
    }

}
