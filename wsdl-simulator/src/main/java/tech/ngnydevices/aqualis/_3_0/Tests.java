
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
 *         &lt;element name="Test" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Status" type="{http://www.ngnydevices.tech/aqualis/3-0}TestStatus"/&gt;
 *                   &lt;element name="SecondaryTubeId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "test"
})
@XmlRootElement(name = "Tests")
public class Tests {

    @XmlElement(name = "Test", required = true)
    protected List<Tests.Test> test;

    /**
     * Gets the value of the test property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the test property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tests.Test }
     * 
     * 
     */
    public List<Tests.Test> getTest() {
        if (test == null) {
            test = new ArrayList<Tests.Test>();
        }
        return this.test;
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
     *         &lt;element name="Status" type="{http://www.ngnydevices.tech/aqualis/3-0}TestStatus"/&gt;
     *         &lt;element name="SecondaryTubeId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "secondaryTubeId"
    })
    public static class Test {

        @XmlElement(name = "Id", required = true)
        protected String id;
        @XmlElement(name = "Status", required = true)
        @XmlSchemaType(name = "string")
        protected TestStatus status;
        @XmlElement(name = "SecondaryTubeId", required = true)
        protected String secondaryTubeId;

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
         *     {@link TestStatus }
         *     
         */
        public TestStatus getStatus() {
            return status;
        }

        /**
         * Define el valor de la propiedad status.
         * 
         * @param value
         *     allowed object is
         *     {@link TestStatus }
         *     
         */
        public void setStatus(TestStatus value) {
            this.status = value;
        }

        /**
         * Obtiene el valor de la propiedad secondaryTubeId.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSecondaryTubeId() {
            return secondaryTubeId;
        }

        /**
         * Define el valor de la propiedad secondaryTubeId.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSecondaryTubeId(String value) {
            this.secondaryTubeId = value;
        }

    }

}
