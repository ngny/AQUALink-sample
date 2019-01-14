
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
 *         &lt;element name="Width" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Height" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="VolumeEstimation" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="CapType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="HValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="LValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PictureUrl" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "width",
    "height",
    "volumeEstimation",
    "capType",
    "hValue",
    "iValue",
    "lValue",
    "pictureUrl"
})
@XmlRootElement(name = "VisualAnalysis")
public class VisualAnalysis {

    @XmlElement(name = "Width")
    protected double width;
    @XmlElement(name = "Height")
    protected double height;
    @XmlElement(name = "VolumeEstimation")
    protected double volumeEstimation;
    @XmlElement(name = "CapType", required = true)
    protected String capType;
    @XmlElement(name = "HValue", required = true)
    protected String hValue;
    @XmlElement(name = "IValue", required = true)
    protected String iValue;
    @XmlElement(name = "LValue", required = true)
    protected String lValue;
    @XmlElement(name = "PictureUrl", required = true)
    protected String pictureUrl;

    /**
     * Obtiene el valor de la propiedad width.
     * 
     */
    public double getWidth() {
        return width;
    }

    /**
     * Define el valor de la propiedad width.
     * 
     */
    public void setWidth(double value) {
        this.width = value;
    }

    /**
     * Obtiene el valor de la propiedad height.
     * 
     */
    public double getHeight() {
        return height;
    }

    /**
     * Define el valor de la propiedad height.
     * 
     */
    public void setHeight(double value) {
        this.height = value;
    }

    /**
     * Obtiene el valor de la propiedad volumeEstimation.
     * 
     */
    public double getVolumeEstimation() {
        return volumeEstimation;
    }

    /**
     * Define el valor de la propiedad volumeEstimation.
     * 
     */
    public void setVolumeEstimation(double value) {
        this.volumeEstimation = value;
    }

    /**
     * Obtiene el valor de la propiedad capType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapType() {
        return capType;
    }

    /**
     * Define el valor de la propiedad capType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapType(String value) {
        this.capType = value;
    }

    /**
     * Obtiene el valor de la propiedad hValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHValue() {
        return hValue;
    }

    /**
     * Define el valor de la propiedad hValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHValue(String value) {
        this.hValue = value;
    }

    /**
     * Obtiene el valor de la propiedad iValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIValue() {
        return iValue;
    }

    /**
     * Define el valor de la propiedad iValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIValue(String value) {
        this.iValue = value;
    }

    /**
     * Obtiene el valor de la propiedad lValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLValue() {
        return lValue;
    }

    /**
     * Define el valor de la propiedad lValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLValue(String value) {
        this.lValue = value;
    }

    /**
     * Obtiene el valor de la propiedad pictureUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * Define el valor de la propiedad pictureUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPictureUrl(String value) {
        this.pictureUrl = value;
    }

}
