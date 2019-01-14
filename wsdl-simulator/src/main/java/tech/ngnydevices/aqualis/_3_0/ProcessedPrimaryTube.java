
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
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Status" type="{http://www.ngnydevices.tech/aqualis/3-0}ProcessedPrimaryTubeStatus"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Location"/&gt;
 *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}VisualAnalysis" minOccurs="0"/&gt;
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "id",
    "status",
    "location",
    "visualAnalysis",
    "comment"
})
@XmlRootElement(name = "ProcessedPrimaryTube")
public class ProcessedPrimaryTube {

    @XmlElement(name = "Id", required = true)
    protected String id;
    @XmlElement(name = "Status", required = true)
    @XmlSchemaType(name = "string")
    protected ProcessedPrimaryTubeStatus status;
    @XmlElement(name = "Location", required = true)
    protected Location location;
    @XmlElement(name = "VisualAnalysis")
    protected VisualAnalysis visualAnalysis;
    @XmlElement(name = "Comment")
    protected String comment;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link ProcessedPrimaryTubeStatus }
     *     
     */
    public ProcessedPrimaryTubeStatus getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessedPrimaryTubeStatus }
     *     
     */
    public void setStatus(ProcessedPrimaryTubeStatus value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad location.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Define el valor de la propiedad location.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Obtiene el valor de la propiedad visualAnalysis.
     * 
     * @return
     *     possible object is
     *     {@link VisualAnalysis }
     *     
     */
    public VisualAnalysis getVisualAnalysis() {
        return visualAnalysis;
    }

    /**
     * Define el valor de la propiedad visualAnalysis.
     * 
     * @param value
     *     allowed object is
     *     {@link VisualAnalysis }
     *     
     */
    public void setVisualAnalysis(VisualAnalysis value) {
        this.visualAnalysis = value;
    }

    /**
     * Obtiene el valor de la propiedad comment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Define el valor de la propiedad comment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

}
