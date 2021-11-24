require 'rubygems'
require 'sinatra'
require 'nokogiri'
require 'json'

use Rack::Logger

helpers do
  def logger
    request.logger
  end
end

set :bind, '0.0.0.0'

set :port, 55555




post '/aqualis/TestPort' do
  content_type 'text/xml'

 puts JSON.pretty_generate(request.env)

	puts headers.inspect
	puts "BODY"
	puts request.body.read.to_s 

'''<?xml version="1.0" encoding="UTF-8"?>
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
            <CF1>WRONGTRANS</CF1>
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
            </Test>
            <Test>
               <Id>T1</Id>
               <Status>Pending</Status>
               <SecondaryDestination>CHANGE_DESTINATION</SecondaryDestination>
            </Test>
         </Tests>
      </GetTestsResponse>
   </S:Body>
</S:Envelope>
'''
end


post '/aqualis/ResultPort' do
  content_type 'text/xml'
'''
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
   <soap:Body xmlns:m="/result-ack">
      <m:ResultAckResponse>
         <m:Message>OK</m:Message>
         <m:ClientId>aqua9100</m:ClientId>
         <m:PrimaryTube>
            <m:Id>018131048137</m:Id>
         </m:PrimaryTube>
      </m:ResultAckResponse>
   </soap:Body>
</soap:Envelope>
'''
end


