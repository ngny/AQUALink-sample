
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
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}PrimaryTube"/&gt;
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
    "primaryTube"
})
@XmlRootElement(name = "GetTests")
public class GetTests {

    @XmlElement(name = "ClientId", required = true)
    protected String clientId;
    @XmlElement(name = "PrimaryTube", required = true)
    protected PrimaryTube primaryTube;

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

}
