
package tech.ngnydevices.aqualis._3_0;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="Sample" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}BulkPrimaryTube"/&gt;
 *                   &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Tests"/&gt;
 *                   &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}CustomFields" minOccurs="0"/&gt;
 *                   &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Patient" minOccurs="0"/&gt;
 *                   &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}SampleSource" minOccurs="0"/&gt;
 *                   &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Order" minOccurs="0"/&gt;
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
    "sample"
})
@XmlRootElement(name = "BulkOrder")
public class BulkOrder {

    @XmlElement(name = "Sample", required = true)
    protected List<BulkOrder.Sample> sample;

    /**
     * Gets the value of the sample property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sample property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSample().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BulkOrder.Sample }
     * 
     * 
     */
    public List<BulkOrder.Sample> getSample() {
        if (sample == null) {
            sample = new ArrayList<BulkOrder.Sample>();
        }
        return this.sample;
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
     *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}BulkPrimaryTube"/&gt;
     *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Tests"/&gt;
     *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}CustomFields" minOccurs="0"/&gt;
     *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Patient" minOccurs="0"/&gt;
     *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}SampleSource" minOccurs="0"/&gt;
     *         &lt;element ref="{http://www.ngnydevices.tech/aqualis/3-0}Order" minOccurs="0"/&gt;
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
        "bulkPrimaryTube",
        "tests",
        "customFields",
        "patient",
        "sampleSource",
        "order"
    })
    public static class Sample {

        @XmlElement(name = "BulkPrimaryTube", required = true)
        protected BulkPrimaryTube bulkPrimaryTube;
        @XmlElement(name = "Tests", required = true)
        protected Tests tests;
        @XmlElement(name = "CustomFields")
        protected CustomFields customFields;
        @XmlElement(name = "Patient")
        protected Patient patient;
        @XmlElement(name = "SampleSource")
        protected SampleSource sampleSource;
        @XmlElement(name = "Order")
        protected Order order;

        /**
         * Obtiene el valor de la propiedad bulkPrimaryTube.
         * 
         * @return
         *     possible object is
         *     {@link BulkPrimaryTube }
         *     
         */
        public BulkPrimaryTube getBulkPrimaryTube() {
            return bulkPrimaryTube;
        }

        /**
         * Define el valor de la propiedad bulkPrimaryTube.
         * 
         * @param value
         *     allowed object is
         *     {@link BulkPrimaryTube }
         *     
         */
        public void setBulkPrimaryTube(BulkPrimaryTube value) {
            this.bulkPrimaryTube = value;
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

    }

}
