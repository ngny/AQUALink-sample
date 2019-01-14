
package tech.ngnydevices.aqualis._3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para GetTestsResponseResult.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="GetTestsResponseResult"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Success"/&gt;
 *     &lt;enumeration value="PrimaryTubeNotFound"/&gt;
 *     &lt;enumeration value="InternalError"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "GetTestsResponseResult")
@XmlEnum
public enum GetTestsResponseResult {

    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("PrimaryTubeNotFound")
    PRIMARY_TUBE_NOT_FOUND("PrimaryTubeNotFound"),
    @XmlEnumValue("InternalError")
    INTERNAL_ERROR("InternalError");
    private final String value;

    GetTestsResponseResult(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GetTestsResponseResult fromValue(String v) {
        for (GetTestsResponseResult c: GetTestsResponseResult.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
