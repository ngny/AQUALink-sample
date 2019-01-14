
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
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Tube"/&gt;
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
    "tube"
})
@XmlRootElement(name = "ConveyorInitialization")
public class ConveyorInitialization {

    @XmlElement(name = "ClientId", required = true)
    protected String clientId;
    @XmlElement(name = "Tube", required = true)
    protected Tube tube;

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
     * Obtiene el valor de la propiedad tube.
     * 
     * @return
     *     possible object is
     *     {@link Tube }
     *     
     */
    public Tube getTube() {
        return tube;
    }

    /**
     * Define el valor de la propiedad tube.
     * 
     * @param value
     *     allowed object is
     *     {@link Tube }
     *     
     */
    public void setTube(Tube value) {
        this.tube = value;
    }

}
