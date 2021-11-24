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




post '/aqualis/HomingPort' do
  content_type 'text/xml'

'''<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <ConveyorInitializationResponse xmlns="http://www.ngnydevices.tech/aqualis/3-0">
         <Result>Success</Result>
      </ConveyorInitializationResponse>
   </S:Body>
</S:Envelope>
'''

'''<?xml version="1.0"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/">
  <SOAP-ENV:Body xmlns:NS1="http://www.ngnydevices.tech/aqualis/3-0" SOAP-ENV:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
    <NS1:ConveyorInitializationResponse>
      <Result xsi:type="xsd:string">Success</Result>
    </NS1:ConveyorInitializationResponse>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>'''


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


