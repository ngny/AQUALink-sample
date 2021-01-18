AQUALIS 3.0 - Examples




Initialization
==============
Request
```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <ConveyorInitialization xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <ClientId>eOMtT</ClientId>
         <Tube>
            <Id>hyhVN</Id>
            <Location>
               <RackId>LWUZN</RackId>
               <HoleId>RcBaQ</HoleId>
            </Location>
         </Tube>
      </ConveyorInitialization>
   </S:Body>
</S:Envelope>
```

Response
```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <ConveyorInitializationResponse xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <Result>Success</Result>
      </ConveyorInitializationResponse>
   </S:Body>
</S:Envelope>
```

Get Tests
=========

Request
```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <GetTests xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <ClientId>dUsFw</ClientId>
         <PrimaryTube>
            <Id>dkelQ</Id>
            <Location>
               <RackId>bxeTe</RackId>
               <HoleId>QOvaS</HoleId>
            </Location>
         </PrimaryTube>
      </GetTests>
   </S:Body>
</S:Envelope>		
```

Response
```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <GetTestsResponse xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <Result>InternalError</Result>
         <PrimaryTube>
            <Id>cfqIO</Id>
            <Location>
               <RackId>OmaaJ</RackId>
               <HoleId>xkyvR</HoleId>
            </Location>
         </PrimaryTube>
         <Order>
            <Status>nLRYt</Status>
            <Priority>GKbgi</Priority>
            <Id>cZaHC</Id>
         </Order>
         <SampleSource>
            <ExtractionCenter>BRQDS</ExtractionCenter>
            <Service>xVLhp</Service>
            <Section>fQGTM</Section>
         </SampleSource>
         <Patient>
            <Id>DYpsB</Id>
            <FamilyName>ZxvfB</FamilyName>
            <FirstName>oeygj</FirstName>
            <MiddleName>bUMaA</MiddleName>
            <Sex>IKKIk</Sex>
            <Physician>knjWE</Physician>
            <Type>XJUfP</Type>
            <BirthDate>xxQHe</BirthDate>
         </Patient>
         <CustomFields>
            <CF1>WKEJd</CF1>
            <CF2>pHYZG</CF2>
            <CF3>htgdn</CF3>
            <CF4>tugzv</CF4>
            <CF5>vKAXL</CF5>
            <CF6>hMLlN</CF6>
            <CF7>gNfZB</CF7>
            <CF8>dyFGR</CF8>
            <CF9>ajVfJ</CF9>
            <CF10>NonEn</CF10>
         </CustomFields>
         <Tests>
            <Test>
               <Id>A25485</Id>
               <Status>Validated</Status>
               <SecondaryTubeId>UfzQh</SecondaryTubeId>
            </Test>
            <Test>
               <Id>dgLLf</Id>
               <Status>Pending</Status>
               <SecondaryTubeId>DTDGs</SecondaryTubeId>
            </Test>
         </Tests>
      </GetTestsResponse>
   </S:Body>
</S:Envelope>
```

Send Results
============

Request
```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <SendResults xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <ClientId>pDbQv</ClientId>
         <ProcessedPrimaryTube>
            <Id>BQYux</Id>
            <Status>Failure</Status>
            <Location>
               <RackId>iXXVy</RackId>
               <HoleId>tGCxz</HoleId>
            </Location>
            <VisualAnalysis>
               <Width>0.7231742029971469</Width>
               <Height>0.9908988967772393</Height>
               <VolumeEstimation>0.25329310557439133</VolumeEstimation>
               <CapType>Vllpg</CapType>
               <HValue>TJKhR</HValue>
               <IValue>Qqqsz</IValue>
               <LValue>YLYdv</LValue>
               <PictureUrl>DhtAs</PictureUrl>
            </VisualAnalysis>
            <Comment>LghPX</Comment>
         </ProcessedPrimaryTube>
         <TestResults>
            <Test>
               <Id>Agtbp</Id>
               <Status>Failure</Status>
            </Test>
         </TestResults>
         <GeneratedSecondaryTubes>
            <SecondaryTube>
               <Id>rXPZk</Id>
               <Location>
                  <RackId>hnfLT</RackId>
                  <HoleId>BSXsP</HoleId>
               </Location>
               <Comment>BOxmQ</Comment>
               <VolumeMl>0.6088003703785169</VolumeMl>
               <Status>TubeCheckFailure</Status>
            </SecondaryTube>
            <SecondaryTube>
               <Id>InyIv</Id>
               <Location1>
                  <RackId>pRgmg</RackId>
                  <HoleId>QsYEK</HoleId>
               </Location1>
               <Comment>kAAAr</Comment>
               <VolumeMl>0.8058695140834087</VolumeMl>
               <Status>DiameterTooHighForOutputHole</Status>
               <RelatedTests><Test><Id>I1250</Id></Test></RelatedTests>
            </SecondaryTube>
         </GeneratedSecondaryTubes>
      </SendResults>
   </S:Body>
</S:Envelope>
```

Response
```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <SendResultsResponse xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <Result>InternalError</Result>
      </SendResultsResponse>
   </S:Body>
</S:Envelope>
```

Bulk-Test-Upload
================

Request
```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <BulkOrder xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <Sample>
            <BulkPrimaryTube>
               <Id>yjCRh</Id>
            </BulkPrimaryTube>
            <Tests>
               <Test>
                  <Id>LTuhn</Id>
                  <Status>Cancel</Status>
                  <SecondaryTubeId>TodUe</SecondaryTubeId>
               </Test>
            </Tests>
            <CustomFields>
               <CF1>wZQqa</CF1>
               <CF2>ZErUa</CF2>
               <CF3>ofGvt</CF3>
               <CF4>hLoyP</CF4>
               <CF5>LDADY</CF5>
               <CF6>zxWoa</CF6>
               <CF7>MAzEE</CF7>
               <CF8>plqjJ</CF8>
               <CF9>jNBgp</CF9>
               <CF10>Tmxxp</CF10>
            </CustomFields>
            <Patient>
               <Id>IoQMO</Id>
               <FamilyName>DRhfG</FamilyName>
               <FirstName>EfXIo</FirstName>
               <MiddleName>TtOmc</MiddleName>
               <Sex>BeivN</Sex>
               <Physician>UYvPB</Physician>
               <Type>zMiJF</Type>
               <BirthDate>ouxIL</BirthDate>
            </Patient>
            <SampleSource>
               <ExtractionCenter>NvFth</ExtractionCenter>
               <Service>RIkEg</Service>
               <Section>EYzhl</Section>
            </SampleSource>
            <Order>
               <Status>STPkc</Status>
               <Priority>SUyTS</Priority>
               <Id>bBkUO</Id>
            </Order>
         </Sample>
      </BulkOrder>
   </S:Body>
</S:Envelope>
```

Response
```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <BulkOrderResponse xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <Result>FLCMF</Result>
      </BulkOrderResponse>
   </S:Body>
</S:Envelope>
```

Code to test
============



* Get tests

```
curl -vv  -H "Accept: text/xml, multipart/related" -H "Content-Type: text/xml; charset=utf-8" -H "SOAPAction: http://www.ngnydevices.tech/GetTests" -H "User-Agent: JAX-WS RI 2.2.9-b130926.1035 svn-revision#5f6196f2b90e9460065a4c2f4e30e065b245e51e" -H "Connection: keep-alive"  -d '<?xml version="1.0" ?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"><SOAP-ENV:Header></SOAP-ENV:Header><S:Body><GetTests xmlns="http://www.ngnydevices.tech/aqualis/3-0"><ClientId>aqua7109</ClientId><PrimaryTube><Id>123</Id><Location><RackId></RackId><HoleId></HoleId></Location></PrimaryTube></GetTests></S:Body></S:Envelope>' http://localhost:55555/aqualis/TestPort
```


* Send results

```
curl -vvv http://10.1.125.136:4500/aqualis/ResultPort -H "Accept: text/xml, multipart/related" -H "Content-Type: text/xml; charset=utf-8" -H "SOAPAction: http://www.ngnydevices.tech/SendResults" -H "User-Agent: JAX-WS RI 2.2.9-b130926.1035 svn-revision#5f6196f2b90e9460065a4c2f4e30e065b245e51e" -H "Connection: keep-alive" -d '<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" ><S:Body ><SendResults xmlns="http://www.ngnydevices.tech/aqualis/3-0" ><ClientId>pDbQv</ClientId ><ProcessedPrimaryTube ><Id>BQYux</Id ><Status>Failure</Status ><Location ><RackId>iXXVy</RackId ><HoleId>tGCxz</HoleId ></Location ><VisualAnalysis ><Width>0.7231742029971469</Width ><Height>0.9908988967772393</Height ><VolumeEstimation>0.25329310557439133</VolumeEstimation ><CapType>Vllpg</CapType ><HValue>TJKhR</HValue ><IValue>Qqqsz</IValue ><LValue>YLYdv</LValue ><PictureUrl>DhtAs</PictureUrl ></VisualAnalysis ><Comment>LghPX</Comment ></ProcessedPrimaryTube ><TestResults ><Test ><Id>Agtbp</Id ><Status>Failure</Status ></Test ></TestResults ><GeneratedSecondaryTubes ><SecondaryTube ><Id>rXPZk</Id ><Location ><RackId>hnfLT</RackId ><HoleId>BSXsP</HoleId ></Location ><Comment>BOxmQ</Comment ><VolumeMl>0.6088003703785169</VolumeMl ><Status>TubeCheckFailure</Status ></SecondaryTube ><SecondaryTube ><Id>InyIv</Id ><Location1 ><RackId>pRgmg</RackId ><HoleId>QsYEK</HoleId ></Location1 ><Comment>kAAAr</Comment ><VolumeMl>0.8058695140834087</VolumeMl ><Status>DiameterTooHighForOutputHole</Status ></SecondaryTube ></GeneratedSecondaryTubes ></SendResults ></S:Body></S:Envelope>'
```

* Search pool request (get the primary tube sample ids for pool tube with sample id 10001) 
```
curl -vv  
-H "Accept: text/xml, multipart/related" 
-H "Content-Type: text/xml; charset=utf-8" 
-H "SOAPAction: http://www.ngnydevices.tech/SearchPoolTubes" 
-d 
'<?xml version="1.0" ?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
<SOAP-ENV:Header></SOAP-ENV:Header>
<S:Body>
 <PoolSearchRequest xmlns="http://www.ngnydevices.tech/aqualis/3-0">
   <PoolTube>10001</PoolTube>
</PoolSearchRequest>
</S:Body>
</S:Envelope>' 
http://Aqualink_IP:4567/aqualis/PoolSearch
```

* Search pool response
```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <PoolSearchResponse xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <Tube><PrimaryTube>0981238123112</PrimaryTube><PoolTube>10001</PoolTube></Tube>
         <Tube><PrimaryTube>0981231223114</PrimaryTube><PoolTube>10001</PoolTube></Tube>
         <Tube><PrimaryTube>0981238323111</PrimaryTube><PoolTube>10001</PoolTube></Tube>
      </PoolSearchResponse>
   </S:Body>
</S:Envelope>
```








Response in the case on nothing to do to a primary tube
=======================================================

```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <GetTestsResponse xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <Result>Success</Result>
         <PrimaryTube>
            <Id>PRIMARY_TUBE_CODE</Id>
         </PrimaryTube>
         <Tests>
         </Tests>
      </GetTestsResponse>
   </S:Body>
</S:Envelope>
```
