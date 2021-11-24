require 'rubygems'
require 'sinatra'
require 'nokogiri'
require 'json'

set :bind, '0.0.0.0'

set :port, 55555




post '/get-techniques' do
'''
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
   <soap:Body xmlns:m="/get-techniques">
      <m:TechniquesResponse>
         <m:ClientId>14997254</m:ClientId>
         <m:PrimaryTube>
            <m:Id>018131048137</m:Id>
         </m:PrimaryTube>
         <m:TechniquesList>
            <m:TechniqueId>Z02572</m:TechniqueId>
         </m:TechniquesList>
      </m:TechniquesResponse>
   </soap:Body>
</soap:Envelope>
'''
end


post '/send-results' do
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


