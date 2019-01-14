
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
 *         &lt;element name="ClientId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}ProcessedPrimaryTube"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}TestResults"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}GeneratedSecondaryTubes" minOccurs="0"/&gt;
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
    "clientId",
    "processedPrimaryTube",
    "testResults",
    "generatedSecondaryTubes"
})
@XmlRootElement(name = "SendResults")
public class SendResults {

    @XmlElement(name = "ClientId", required = true)
    protected String clientId;
    @XmlElement(name = "ProcessedPrimaryTube", required = true)
    protected ProcessedPrimaryTube processedPrimaryTube;
    @XmlElement(name = "TestResults", required = true)
    protected TestResults testResults;
    @XmlElement(name = "GeneratedSecondaryTubes")
    protected GeneratedSecondaryTubes generatedSecondaryTubes;

    /**
     * Obtiene el valor de la propiedad clientId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Define el valor de la propiedad clientId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientId(String value) {
        this.clientId = value;
    }

    /**
     * Obtiene el valor de la propiedad processedPrimaryTube.
     * 
     * @return
     *     possible object is
     *     {@link ProcessedPrimaryTube }
     *     
     */
    public ProcessedPrimaryTube getProcessedPrimaryTube() {
        return processedPrimaryTube;
    }

    /**
     * Define el valor de la propiedad processedPrimaryTube.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessedPrimaryTube }
     *     
     */
    public void setProcessedPrimaryTube(ProcessedPrimaryTube value) {
        this.processedPrimaryTube = value;
    }

    /**
     * Obtiene el valor de la propiedad testResults.
     * 
     * @return
     *     possible object is
     *     {@link TestResults }
     *     
     */
    public TestResults getTestResults() {
        return testResults;
    }

    /**
     * Define el valor de la propiedad testResults.
     * 
     * @param value
     *     allowed object is
     *     {@link TestResults }
     *     
     */
    public void setTestResults(TestResults value) {
        this.testResults = value;
    }

    /**
     * Obtiene el valor de la propiedad generatedSecondaryTubes.
     * 
     * @return
     *     possible object is
     *     {@link GeneratedSecondaryTubes }
     *     
     */
    public GeneratedSecondaryTubes getGeneratedSecondaryTubes() {
        return generatedSecondaryTubes;
    }

    /**
     * Define el valor de la propiedad generatedSecondaryTubes.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneratedSecondaryTubes }
     *     
     */
    public void setGeneratedSecondaryTubes(GeneratedSecondaryTubes value) {
        this.generatedSecondaryTubes = value;
    }

}
