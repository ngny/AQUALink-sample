
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
 *         &lt;element name="RackId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="HoleId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "rackId",
    "holeId"
})
@XmlRootElement(name = "Location")
public class Location {

    @XmlElement(name = "RackId", required = true)
    protected String rackId;
    @XmlElement(name = "HoleId", required = true)
    protected String holeId;

    /**
     * Obtiene el valor de la propiedad rackId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRackId() {
        return rackId;
    }

    /**
     * Define el valor de la propiedad rackId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRackId(String value) {
        this.rackId = value;
    }

    /**
     * Obtiene el valor de la propiedad holeId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoleId() {
        return holeId;
    }

    /**
     * Define el valor de la propiedad holeId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoleId(String value) {
        this.holeId = value;
    }

}
