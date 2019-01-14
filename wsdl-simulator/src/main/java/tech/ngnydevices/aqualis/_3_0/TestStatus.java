
package tech.ngnydevices.aqualis._3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TestStatus.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="TestStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Pending"/&gt;
 *     &lt;enumeration value="Done"/&gt;
 *     &lt;enumeration value="Validated"/&gt;
 *     &lt;enumeration value="Cancel"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TestStatus")
@XmlEnum
public enum TestStatus {

    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Done")
    DONE("Done"),
    @XmlEnumValue("Validated")
    VALIDATED("Validated"),
    @XmlEnumValue("Cancel")
    CANCEL("Cancel");
    private final String value;

    TestStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TestStatus fromValue(String v) {
        for (TestStatus c: TestStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
