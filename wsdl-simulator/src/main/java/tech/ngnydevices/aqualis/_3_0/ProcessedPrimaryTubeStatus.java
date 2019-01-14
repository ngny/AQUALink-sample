
package tech.ngnydevices.aqualis._3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ProcessedPrimaryTubeStatus.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="ProcessedPrimaryTubeStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Success"/&gt;
 *     &lt;enumeration value="Failure"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ProcessedPrimaryTubeStatus")
@XmlEnum
public enum ProcessedPrimaryTubeStatus {

    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("Failure")
    FAILURE("Failure");
    private final String value;

    ProcessedPrimaryTubeStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProcessedPrimaryTubeStatus fromValue(String v) {
        for (ProcessedPrimaryTubeStatus c: ProcessedPrimaryTubeStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
