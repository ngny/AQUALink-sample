

Get tests
=========

POST http://172.17.62.15:55555/get-techniques 


* Request
```
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
   <soap:Body xmlns:m="/get-techniques">
      <m:ClientId>aqua9100</m:ClientId>
      <m:PrimaryTube>
         <m:Id>010151138539</m:Id>
         <m:RackCode>#Entrada3</m:RackCode>
      </m:PrimaryTube>
   </soap:Body>
</soap:Envelope>
```

* Response

```
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
   <soap:Body xmlns:m="/get-techniques">
      <m:TechniquesResponse>
         <m:ClientId>15138769</m:ClientId>
         <m:PrimaryTube>
            <m:Id>010151138539</m:Id>
         </m:PrimaryTube>
         <m:TechniquesList>
            <m:TechniqueId>A14485</m:TechniqueId>
            <m:TechniqueId>Q46785</m:TechniqueId>
            <m:TechniqueId>W29085</m:TechniqueId>
            <m:TechniqueId>W29185</m:TechniqueId>
            <m:TechniqueId>W29285</m:TechniqueId>
            <m:TechniqueId>W29385</m:TechniqueId>
            <m:TechniqueId>W29485</m:TechniqueId>
            <m:TechniqueId>Q85185</m:TechniqueId>
            <m:TechniqueId>Q85285</m:TechniqueId>
            <m:TechniqueId>Q85385</m:TechniqueId>
            <m:TechniqueId>W21185</m:TechniqueId>
            <m:TechniqueId>W21285</m:TechniqueId>
            <m:TechniqueId>Q85585</m:TechniqueId>
            <m:TechniqueId>Q85685</m:TechniqueId>
            <m:TechniqueId>ENASCR</m:TechniqueId>
            <m:TechniqueId>A24685</m:TechniqueId>
            <m:TechniqueId>A24285</m:TechniqueId>
            <m:TechniqueId>A24485</m:TechniqueId>
            <m:TechniqueId>A24385</m:TechniqueId>
            <m:TechniqueId>A02385</m:TechniqueId>
            <m:TechniqueId>A02485</m:TechniqueId>
            <m:TechniqueId>A01685</m:TechniqueId>
         </m:TechniquesList>
      </m:TechniquesResponse>
   </soap:Body>
</soap:Envelope>
```






Send results
============

POST http://172.17.62.15:55555/send-results


* Request
```
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
   <soap:Body xmlns:m="/send-techniques">
      <m:ClientId>aqua9100</m:ClientId>
      <m:PrimaryTube>
         <m:Id>010140464039</m:Id>
         <m:Location>#Cadena1:E:1</m:Location>
      </m:PrimaryTube>
      <m:TechniquesList>
         <m:Technique>
            <m:Id>A10985</m:Id>
            <m:Result>OK</m:Result>
            <m:SecondaryTubeId>177140464039</m:SecondaryTubeId>
            <m:Location>#Immuno:E:1</m:Location>
         </m:Technique>
      </m:TechniquesList>
   </soap:Body>
</soap:Envelope>                                                                                                                                                                                                                                                                                                                                                                                                                                            | Sent      |
```

* Response
```
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
   <soap:Body xmlns:m="/result-ack">
      <m:ResultAckResponse>
         <m:Message>OK</m:Message>
         <m:ClientId>aqua9100</m:ClientId>
         <m:PrimaryTube>
            <m:Id>010140464039</m:Id>
         </m:PrimaryTube>
      </m:ResultAckResponse>
   </soap:Body>
</soap:Envelope>         
``
