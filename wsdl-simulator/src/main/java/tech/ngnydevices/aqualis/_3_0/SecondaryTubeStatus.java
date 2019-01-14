
package tech.ngnydevices.aqualis._3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SecondaryTubeStatus.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="SecondaryTubeStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Success"/&gt;
 *     &lt;enumeration value="AspirationError"/&gt;
 *     &lt;enumeration value="TubeGenerationError"/&gt;
 *     &lt;enumeration value="FamilyCancelError"/&gt;
 *     &lt;enumeration value="LisError"/&gt;
 *     &lt;enumeration value="DispenseError"/&gt;
 *     &lt;enumeration value="PipettorStateError"/&gt;
 *     &lt;enumeration value="NoResult"/&gt;
 *     &lt;enumeration value="AliquotePending"/&gt;
 *     &lt;enumeration value="DiameterTooHighForOutputHole"/&gt;
 *     &lt;enumeration value="TubeCheckFailure"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SecondaryTubeStatus")
@XmlEnum
public enum SecondaryTubeStatus {

    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("AspirationError")
    ASPIRATION_ERROR("AspirationError"),
    @XmlEnumValue("TubeGenerationError")
    TUBE_GENERATION_ERROR("TubeGenerationError"),
    @XmlEnumValue("FamilyCancelError")
    FAMILY_CANCEL_ERROR("FamilyCancelError"),
    @XmlEnumValue("LisError")
    LIS_ERROR("LisError"),
    @XmlEnumValue("DispenseError")
    DISPENSE_ERROR("DispenseError"),
    @XmlEnumValue("PipettorStateError")
    PIPETTOR_STATE_ERROR("PipettorStateError"),
    @XmlEnumValue("NoResult")
    NO_RESULT("NoResult"),
    @XmlEnumValue("AliquotePending")
    ALIQUOTE_PENDING("AliquotePending"),
    @XmlEnumValue("DiameterTooHighForOutputHole")
    DIAMETER_TOO_HIGH_FOR_OUTPUT_HOLE("DiameterTooHighForOutputHole"),
    @XmlEnumValue("TubeCheckFailure")
    TUBE_CHECK_FAILURE("TubeCheckFailure");
    private final String value;

    SecondaryTubeStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SecondaryTubeStatus fromValue(String v) {
        for (SecondaryTubeStatus c: SecondaryTubeStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
