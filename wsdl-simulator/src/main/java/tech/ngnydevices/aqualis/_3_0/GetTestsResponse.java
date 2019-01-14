
package tech.ngnydevices.aqualis._3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="Result" type="{http://www.ngnydevices.tech/aqualis/3-0}GetTestsResponseResult"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}PrimaryTube"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Order" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}SampleSource" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Patient" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}CustomFields" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Tests"/&gt;
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
    "result",
    "primaryTube",
    "order",
    "sampleSource",
    "patient",
    "customFields",
    "tests"
})
@XmlRootElement(name = "GetTestsResponse")
public class GetTestsResponse {

    @XmlElement(name = "Result", required = true)
    @XmlSchemaType(name = "string")
    protected GetTestsResponseResult result;
    @XmlElement(name = "PrimaryTube", required = true)
    protected PrimaryTube primaryTube;
    @XmlElement(name = "Order")
    protected Order order;
    @XmlElement(name = "SampleSource")
    protected SampleSource sampleSource;
    @XmlElement(name = "Patient")
    protected Patient patient;
    @XmlElement(name = "CustomFields")
    protected CustomFields customFields;
    @XmlElement(name = "Tests", required = true)
    protected Tests tests;

    /**
     * Obtiene el valor de la propiedad result.
     * 
     * @return
     *     possible object is
     *     {@link GetTestsResponseResult }
     *     
     */
    public GetTestsResponseResult getResult() {
        return result;
    }

    /**
     * Define el valor de la propiedad result.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTestsResponseResult }
     *     
     */
    public void setResult(GetTestsResponseResult value) {
        this.result = value;
    }

    /**
     * Obtiene el valor de la propiedad primaryTube.
     * 
     * @return
     *     possible object is
     *     {@link PrimaryTube }
     *     
     */
    public PrimaryTube getPrimaryTube() {
        return primaryTube;
    }

    /**
     * Define el valor de la propiedad primaryTube.
     * 
     * @param value
     *     allowed object is
     *     {@link PrimaryTube }
     *     
     */
    public void setPrimaryTube(PrimaryTube value) {
        this.primaryTube = value;
    }

    /**
     * Obtiene el valor de la propiedad order.
     * 
     * @return
     *     possible object is
     *     {@link Order }
     *     
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Define el valor de la propiedad order.
     * 
     * @param value
     *     allowed object is
     *     {@link Order }
     *     
     */
    public void setOrder(Order value) {
        this.order = value;
    }

    /**
     * Obtiene el valor de la propiedad sampleSource.
     * 
     * @return
     *     possible object is
     *     {@link SampleSource }
     *     
     */
    public SampleSource getSampleSource() {
        return sampleSource;
    }

    /**
     * Define el valor de la propiedad sampleSource.
     * 
     * @param value
     *     allowed object is
     *     {@link SampleSource }
     *     
     */
    public void setSampleSource(SampleSource value) {
        this.sampleSource = value;
    }

    /**
     * Obtiene el valor de la propiedad patient.
     * 
     * @return
     *     possible object is
     *     {@link Patient }
     *     
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Define el valor de la propiedad patient.
     * 
     * @param value
     *     allowed object is
     *     {@link Patient }
     *     
     */
    public void setPatient(Patient value) {
        this.patient = value;
    }

    /**
     * Obtiene el valor de la propiedad customFields.
     * 
     * @return
     *     possible object is
     *     {@link CustomFields }
     *     
     */
    public CustomFields getCustomFields() {
        return customFields;
    }

    /**
     * Define el valor de la propiedad customFields.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomFields }
     *     
     */
    public void setCustomFields(CustomFields value) {
        this.customFields = value;
    }

    /**
     * Obtiene el valor de la propiedad tests.
     * 
     * @return
     *     possible object is
     *     {@link Tests }
     *     
     */
    public Tests getTests() {
        return tests;
    }

    /**
     * Define el valor de la propiedad tests.
     * 
     * @param value
     *     allowed object is
     *     {@link Tests }
     *     
     */
    public void setTests(Tests value) {
        this.tests = value;
    }

}
