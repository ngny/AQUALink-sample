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
               <Id>OinZj</Id>
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
         <ClientId>QOvaS</ClientId>
         <ProcessedPrimaryTube>
            <Id>cfqIO</Id>
            <Status>Failure</Status>
            <Location>
               <RackId>OmaaJ</RackId>
               <HoleId>xkyvR</HoleId>
            </Location>
            <VisualAnalysis>
               <Width>0.7231742029971469</Width>
               <Height>0.9908988967772393</Height>
               <VolumeEstimation>0.25329310557439133</VolumeEstimation>
               <CapType>nLRYt</CapType>
               <HValue>GKbgi</HValue>
               <IValue>cZaHC</IValue>
               <LValue>BRQDS</LValue>
               <PictureUrl>xVLhp</PictureUrl>
            </VisualAnalysis>
            <Comment>fQGTM</Comment>
         </ProcessedPrimaryTube>
         <TestResults>
            <Test>
               <Id>DYpsB</Id>
               <Status>Failure</Status>
            </Test>
            <Test>
               <Id>ZxvfB</Id>
               <Status>Success</Status>
            </Test>
         </TestResults>
         <GeneratedSecondaryTubes>
            <SecondaryTube>
               <Id>oeygj</Id>
               <Location />
               <Comment>bUMaA</Comment>
               <VolumeMl>0.6088003703785169</VolumeMl>
               <Status>TubeCheckFailure</Status>
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
               <Id>rXPZk</Id>
            </BulkPrimaryTube>
            <Tests>
               <Test />
            </Tests>
            <CustomFields>
               <CF1>hnfLT</CF1>
               <CF2>BSXsP</CF2>
               <CF3>BOxmQ</CF3>
               <CF4>InyIv</CF4>
               <CF5>pRgmg</CF5>
               <CF6>QsYEK</CF6>
               <CF7>kAAAr</CF7>
               <CF8>yjCRh</CF8>
               <CF9>LTuhn</CF9>
               <CF10>TodUe</CF10>
            </CustomFields>
            <Patient>
               <Id>wZQqa</Id>
               <FamilyName>ZErUa</FamilyName>
               <FirstName>ofGvt</FirstName>
               <MiddleName>hLoyP</MiddleName>
               <Sex>LDADY</Sex>
               <Physician>zxWoa</Physician>
               <Type>MAzEE</Type>
               <BirthDate>plqjJ</BirthDate>
            </Patient>
            <SampleSource>
               <ExtractionCenter>jNBgp</ExtractionCenter>
               <Service>Tmxxp</Service>
               <Section>IoQMO</Section>
            </SampleSource>
            <Order>
               <Status>DRhfG</Status>
               <Priority>EfXIo</Priority>
               <Id>TtOmc</Id>
            </Order>
         </Sample>
         <Sample>
            <BulkPrimaryTube>
               <Id>BeivN</Id>
            </BulkPrimaryTube>
            <Tests>
               <Test />
            </Tests>
            <CustomFields>
               <CF1>UYvPB</CF1>
               <CF2>zMiJF</CF2>
               <CF3>ouxIL</CF3>
               <CF4>NvFth</CF4>
               <CF5>RIkEg</CF5>
               <CF6>EYzhl</CF6>
               <CF7>STPkc</CF7>
               <CF8>SUyTS</CF8>
               <CF9>bBkUO</CF9>
               <CF10>Vdwpr</CF10>
            </CustomFields>
            <Patient>
               <Id>LNbgN</Id>
               <FamilyName>vZUMp</FamilyName>
               <FirstName>qAXLJ</FirstName>
               <MiddleName>bjETi</MiddleName>
               <Sex>QaUNW</Sex>
               <Physician>ftniO</Physician>
               <Type>Aotnz</Type>
               <BirthDate>WvkQt</BirthDate>
            </Patient>
            <SampleSource>
               <ExtractionCenter>WruzG</ExtractionCenter>
               <Service>VyIdg</Service>
               <Section>FUYNd</Section>
            </SampleSource>
            <Order>
               <Status>kFYkk</Status>
               <Priority>jNIeE</Priority>
               <Id>Nlreq</Id>
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


