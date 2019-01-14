
package tech.ngnydevices.aqualis._3_0;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="SecondaryTube" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Location"/&gt;
 *                   &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="VolumeMl" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                   &lt;element name="Status" type="{http://www.ngnydevices.tech/aqualis/3-0}SecondaryTubeStatus"/&gt;
 *                   &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}RelatedTests" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "secondaryTube"
})
@XmlRootElement(name = "GeneratedSecondaryTubes")
public class GeneratedSecondaryTubes {

    @XmlElement(name = "SecondaryTube", required = true)
    protected List<GeneratedSecondaryTubes.SecondaryTube> secondaryTube;

    /**
     * Gets the value of the secondaryTube property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the secondaryTube property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecondaryTube().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GeneratedSecondaryTubes.SecondaryTube }
     * 
     * 
     */
    public List<GeneratedSecondaryTubes.SecondaryTube> getSecondaryTube() {
        if (secondaryTube == null) {
            secondaryTube = new ArrayList<GeneratedSecondaryTubes.SecondaryTube>();
        }
        return this.secondaryTube;
    }


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
     *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Location"/&gt;
     *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="VolumeMl" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *         &lt;element name="Status" type="{http://www.ngnydevices.tech/aqualis/3-0}SecondaryTubeStatus"/&gt;
     *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}RelatedTests" minOccurs="0"/&gt;
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
        "location",
        "comment",
        "volumeMl",
        "status",
        "relatedTests"
    })
    public static class SecondaryTube {

        @XmlElement(name = "Id", required = true)
        protected String id;
        @XmlElement(name = "Location", required = true)
        protected Location location;
        @XmlElement(name = "Comment", required = true)
        protected String comment;
        @XmlElement(name = "VolumeMl")
        protected double volumeMl;
        @XmlElement(name = "Status", required = true)
        @XmlSchemaType(name = "string")
        protected SecondaryTubeStatus status;
        @XmlElement(name = "RelatedTests")
        protected RelatedTests relatedTests;

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

        /**
         * Obtiene el valor de la propiedad volumeMl.
         * 
         */
        public double getVolumeMl() {
            return volumeMl;
        }

        /**
         * Define el valor de la propiedad volumeMl.
         * 
         */
        public void setVolumeMl(double value) {
            this.volumeMl = value;
        }

        /**
         * Obtiene el valor de la propiedad status.
         * 
         * @return
         *     possible object is
         *     {@link SecondaryTubeStatus }
         *     
         */
        public SecondaryTubeStatus getStatus() {
            return status;
        }

        /**
         * Define el valor de la propiedad status.
         * 
         * @param value
         *     allowed object is
         *     {@link SecondaryTubeStatus }
         *     
         */
        public void setStatus(SecondaryTubeStatus value) {
            this.status = value;
        }

        /**
         * Obtiene el valor de la propiedad relatedTests.
         * 
         * @return
         *     possible object is
         *     {@link RelatedTests }
         *     
         */
        public RelatedTests getRelatedTests() {
            return relatedTests;
        }

        /**
         * Define el valor de la propiedad relatedTests.
         * 
         * @param value
         *     allowed object is
         *     {@link RelatedTests }
         *     
         */
        public void setRelatedTests(RelatedTests value) {
            this.relatedTests = value;
        }

    }

}
