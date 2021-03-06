package tech.ngnydevices.aqualis._3_0;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.4
 * 2018-07-17T00:07:21.470+02:00
 * Generated source version: 3.2.4
 *
 */
@WebService(targetNamespace = "http://www.ngnydevices.tech/aqualis/3-0", name = "ResultServicePortType")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ResultServicePortType {

    @WebMethod(operationName = "SendResults", action = "http://www.ngnydevices.tech/SendResults")
    @WebResult(name = "SendResultsResponse", targetNamespace = "http://www.ngnydevices.tech/aqualis/3-0", partName = "body")
    public SendResultsResponse sendResults(
        @WebParam(partName = "body", name = "SendResults", targetNamespace = "http://www.ngnydevices.tech/aqualis/3-0")
        SendResults body
    );
}
